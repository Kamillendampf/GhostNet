package de.ibapp.ghostnet.DAO;

import de.ibapp.ghostnet.moduls.GhostNetModul;
import de.ibapp.ghostnet.moduls.Person;

import java.sql.*;
import java.util.ArrayList;

public class DAO_GohstNet {
    private final String jdbcURL = "jdbc:postgresql://localhost:5432/ghost-net";
    private final String USER = "postgres";
    private final String PASSWORT = "admin";

    public DAO_GohstNet() {
    }

    public DAO_GohstNet(GhostNetModul gnm) {
        this.insertDB(gnm);
    }


    private String checkforDatabase() {
        return "CREATE TABLE IF NOT EXISTS nets(gn_id serial PRIMARY KEY, gn_lat  VARCHAR(250) not null, gn_lon  VARCHAR(250) not null, gn_size  INT not null, gn_editor VARCHAR(250) null, gn_reporter VARCHAR(250) not null, gn_status INT not null, unique(gn_id, gn_lat, gn_lon, gn_size, gn_reporter));";
    }


    public void insertDB(GhostNetModul gnm) {
        try {
            Connection connection = DriverManager.getConnection(jdbcURL, USER, PASSWORT);

            String sql = this.checkforDatabase() + "INSERT INTO nets (gn_lat, gn_lon, gn_size, gn_editor, gn_reporter, gn_status)" +
                    "VALUES ('" + gnm.getLat() +
                    "', '" + gnm.getLon() +
                    "', " + gnm.getSize() +
                    ", '" + gnm.getEditor() +
                    "', '" + gnm.getReporter() +
                    "', " + gnm.getStatus() + ");";
            PreparedStatement ps = connection.prepareStatement(sql);
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<GhostNetModul> selectDB() {
        ArrayList<GhostNetModul> gnmListNull = new ArrayList<>();
        ArrayList<GhostNetModul> gnmList = null;
        try {
            Connection connection = DriverManager.getConnection(jdbcURL, "postgres", "admin");
            gnmList = new ArrayList<>();
            String sql = "SELECT * FROM nets";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            for (int i = 0; resultSet.next(); i++) {
                int id = Integer.parseInt(resultSet.getString("gn_id"));
                String lat = resultSet.getString("gn_lat");
                String lon = resultSet.getString("gn_lon");
                int size = Integer.parseInt(resultSet.getString("gn_size"));
                String editor = resultSet.getString("gn_editor");
                String reporter = resultSet.getString("gn_reporter");
                int status = Integer.parseInt(resultSet.getString("gn_status"));
                gnmList.add(new GhostNetModul(id, lat, lon, size, editor, reporter, status));
            }

            connection.close();
            return gnmList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void updateDB(String editor, int id) {
        try {
            Connection connection = DriverManager.getConnection(jdbcURL, USER, PASSWORT);
            String sql = "UPDATE nets SET gn_editor ='" + editor + "' WHERE gn_id=" + id;
            PreparedStatement ps = connection.prepareStatement(sql);
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public void updateStatus(int status, int id) {
        try {
            Connection connection = DriverManager.getConnection(jdbcURL, USER, PASSWORT);
            String sql = "UPDATE nets SET gn_status ='" + status + "' WHERE gn_id=" + id;
            PreparedStatement ps = connection.prepareStatement(sql);
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }
    public void updateVerschollenDB(String reporter, int id) {
        try {
            Connection connection = DriverManager.getConnection(jdbcURL, USER, PASSWORT);
            String sql = "UPDATE nets SET gn_reporter ='"+reporter+"', gn_status=3 WHERE gn_id=" + id;
            PreparedStatement ps = connection.prepareStatement(sql);
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
        } catch (Exception e){
            throw new RuntimeException(e);
        }

    }

    private String checkforDatabaseForLicense() {
        return "CREATE TABLE IF NOT EXISTS licens(l_name VARCHAR(250) not null, l_number VARCHAR(250) not null, l_key VARCHAR(250) not null);";
    }

    public void insertLicense(String name, String number, String key){
        try {
            Connection connection = DriverManager.getConnection(jdbcURL, USER, PASSWORT);
            String sql = this.checkforDatabaseForLicense() + "INSERT INTO licens(l_name, l_number, l_key) " +
                    "VALUES('" + name + "'," +
                    "'" + number + "', " +
                    "'" + key + "')";
            PreparedStatement ps = connection.prepareStatement(sql);
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean checkLicense(String key){
        try {
            Connection connection = DriverManager.getConnection(jdbcURL, USER, PASSWORT);
            String sql = "SELECT EXISTS(SELECT * FROM licens WHERE l_key='" + key + "') AS return";
            PreparedStatement ps = connection.prepareStatement(sql);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                return resultSet.getBoolean("return");
            } else {
                return false;
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

    }

    public Person selectLicense(String key){
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(jdbcURL, USER, PASSWORT);
            String sql = "SELECT * FROM licens WHERE l_key='"+ key + "'";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()){
                return new Person(resultSet.getString("l_name"), resultSet.getString("l_number"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return new Person();
    }




}
