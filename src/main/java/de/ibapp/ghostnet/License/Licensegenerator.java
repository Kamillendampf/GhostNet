package de.ibapp.ghostnet.License;

import de.ibapp.ghostnet.DAO.DAO_GohstNet;
import de.ibapp.ghostnet.validation.Validation;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.util.Base64;

@WebServlet(name = "lgen", value = "/lgen")
public class Licensegenerator extends HttpServlet {
    private final Validation validatior = new Validation();


    private PrintWriter out;
    private String name;
    private boolean vldName;
    private String nummer;
    private boolean vldNummer;



    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        this.out = resp.getWriter();


        this.out.println("<html>" +
                "<head> <title> Licensegenerator </title> </head." +
                "<body> <form action='lgen' method='post'>" +
                "Name: <input type='text' name='name'>" +
                "Telefonnummer: <input type='text' name='nummer'>" +
                "<button> License generieren</button>" +
                "</form> </body>" );
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.setContentType("text/html");
        this.out = res.getWriter();
        if(this.validatior.isValidName(req.getParameter("name"))){  name = req.getParameter("name"); vldName = true;} else { vldName = false; out.println("<script> alert('Der Benutzername entspricht keiner Validen eingabe') </script>");}
        if(this.validatior.isValidPhoneNumber(req.getParameter("nummer"))){ nummer = req.getParameter("nummer"); vldNummer = true;} else { vldNummer = false; out.println("<script> alert('Die Telefonnummer entspricht keiner Validen eingabe') </script>");}
        if (vldName && vldNummer) {
            try {
                MessageDigest digest = MessageDigest.getInstance("SHA-256");
                LocalDate dateToday = LocalDate.now();
                LocalDate expireDate = dateToday.plusYears(1);
                String input = name + nummer + dateToday.toString() + expireDate.toString();
                String base64Hash = Base64.getEncoder().encodeToString(digest.digest(input.getBytes(StandardCharsets.UTF_8)));
                Cookie licenseKeyCookie = new Cookie("license", base64Hash);
                licenseKeyCookie.setMaxAge(31622400);
                DAO_GohstNet dao = new DAO_GohstNet();
                dao.insertLicense(name, nummer, base64Hash);

                res.addCookie(licenseKeyCookie);
                res.setContentType("text/html");
                this.out = res.getWriter();
                this.out.println("Vielen Dank dass sie sich fuer eine Lizensierte Version entschieden haben. Ihr Key lautet: " + base64Hash);
            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException(e);
            }
        }else {
            doGet(req, res);
        }

    }




}
