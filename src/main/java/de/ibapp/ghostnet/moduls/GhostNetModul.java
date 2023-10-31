package de.ibapp.ghostnet.moduls;

public class GhostNetModul {

    public String name;
    public String lat;
    public String lon;
    public String number;
    public int status;

    public GhostNetModul(){}
    public GhostNetModul(String lat, String lon, String name, String nummer, int status){
        this.lat = lat;
        this.lon = lon;
        this.name = name;
        setNumber(number);
        setStatus(status);
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getNumber() {
        return number;
    }

    public int getStatus() {
        return status;
    }

    public String getName() {
        return name;
    }

    public String getLat() {
        return lat;
    }

    public String getLon(){ return  lon;}
}
