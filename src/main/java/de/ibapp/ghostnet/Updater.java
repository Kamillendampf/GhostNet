package de.ibapp.ghostnet;

import de.ibapp.ghostnet.DAO.DAO_GohstNet;
import de.ibapp.ghostnet.moduls.Person;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;


@WebServlet(name = "Update", value = "/update")
public class Updater extends HttpServlet {

    private String getLicenseKey(HttpServletRequest req) {
        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("license".equals(cookie.getName())) {
                    return cookie.getValue();
                }
            }
        }
        return "";
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(!(req.getParameter("id") ==null)) {
            DAO_GohstNet daoGohstNet = new DAO_GohstNet();
            Person p = new Person(req.getParameter("name"), req.getParameter("nummer"));
            daoGohstNet.updateVerschollenDB(p.toString(), Integer.parseInt(req.getParameter("id")));
            Main responseToClient = new Main();
            responseToClient.doGet(req, resp);
        }
        if (!(req.getParameter("lid") == null)) {
            DAO_GohstNet daoGohstNet = new DAO_GohstNet();

            if (req.getParameter("lid").equals("3")){
                Person licensPerson = daoGohstNet.selectLicense(this.getLicenseKey(req));
                daoGohstNet.updateDB(licensPerson.toString(), Integer.parseInt(req.getParameter("lid")));
            }
            daoGohstNet.updateStatus(Integer.parseInt(req.getParameter("status")), Integer.parseInt(req.getParameter("lid")));
            Main responseToClient = new Main();
            responseToClient.doGet(req, resp);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DAO_GohstNet daoGohstNet = new DAO_GohstNet();
        Person licensPerson = daoGohstNet.selectLicense(this.getLicenseKey(req));
        daoGohstNet.updateDB(licensPerson.toString(), Integer.parseInt(req.getParameter("id")));
        Main responseToClient = new Main();
        responseToClient.doGet(req, resp);
    }


}


