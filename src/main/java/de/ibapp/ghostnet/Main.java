package de.ibapp.ghostnet;

import de.ibapp.ghostnet.DAO.DAO_GohstNet;
import de.ibapp.ghostnet.bean.GhostNetBean;
import de.ibapp.ghostnet.moduls.GhostNetModul;
import de.ibapp.ghostnet.moduls.Person;
import de.ibapp.ghostnet.validation.Validation;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;


@WebServlet(name = "Main", value = "/main")
public class Main extends HttpServlet {
    private ArrayList<GhostNetModul> gnmList;

    GhostNetModul gnm;

    public Main() {
    }

    @Override
    public void init() throws ServletException {
        super.init();
    }

    public boolean hasLicenese(HttpServletRequest req) {
        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("license".equals(cookie.getName())) {
                    DAO_GohstNet dao = new DAO_GohstNet();
                    return dao.checkLicense(cookie.getValue());
                }
            }
        }
        return false;
    }


    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        DAO_GohstNet daoGohstNet = new DAO_GohstNet();
        ArrayList<String> latList = new ArrayList<String>();
        ArrayList<String> lonList = new ArrayList<String>();
        ArrayList<GhostNetBean> gnbList = new ArrayList<>();
        for (GhostNetModul gnm : daoGohstNet.selectDB()) {
            GhostNetBean gnb = new GhostNetBean();
            gnb.setKoordinaten(gnm.getLat(), gnm.getLon());
            gnb.setSize(gnm.getSize());
            gnb.setEditor(gnm.getEditor());
            gnb.setReporter(gnm.getReporter());
            gnb.setStatus(gnm.getStatus());
            gnb.setId(gnm.getId());
            gnbList.add(gnb);
            if (gnm.getStatus() != 2) {
                latList.add(gnm.getLat());
                lonList.add(gnm.getLon());
            }
        }
        Person licenseUser = new Person();
        licenseUser.setLicensed(hasLicenese(request));
        request.setAttribute("latList", latList.toArray());
        request.setAttribute("lonList", lonList.toArray());
        request.setAttribute("gnbs", gnbList);
        request.setAttribute("hasLicense", licenseUser.islicensed());
        request.getRequestDispatcher("index.jsp").forward(request, response);

    }


    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        DAO_GohstNet daoGohstNet = new DAO_GohstNet();
        Validation validator = new Validation();
        if (200 == response.getStatus()) {
            if (validator.isValidName(request.getParameter("name")) && (validator.isValidPhoneNumber(request.getParameter("nummer")) || request.getParameter("nummer").equals("")) && validator.isvalidateNumber(request.getParameter("size"))) {
                Person reporter = new Person(request.getParameter("name"), request.getParameter("nummer"));
                this.gnm = new GhostNetModul(request.getParameter("lat"), request.getParameter("lon"),
                        Integer.parseInt(request.getParameter("size")), "", reporter.toString(), 0);
                daoGohstNet = new DAO_GohstNet(gnm);
            } else {
                request.setAttribute("Fail", "onload='alert('sie haben einen ungueltigen wert eingeben');'");
            }
        } else {
            response.sendError(404);
        }
        doGet(request, response);
    }
}