package de.ibapp.ghostnet;

import java.io.*;

import de.ibapp.ghostnet.DAO.DAO_GohstNet;
import de.ibapp.ghostnet.moduls.GhostNetModul;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import de.ibapp.ghostnet.moduls.GhostNetModul;

@WebServlet(name = "Main", value = "/main")
public class Main extends HttpServlet {


    public Main(){}



    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        if (200 == response.getStatus()){
            GhostNetModul gnm = new GhostNetModul(request.getParameter("lat"), request.getParameter("lon"), request.getParameter("name"), request.getParameter(" nummer"),0);
            DAO_GohstNet daoGohstNet = new DAO_GohstNet(gnm);
        } else {
            response.sendError(404);
        }

    }


    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

    }












}