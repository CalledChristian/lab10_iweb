package com.example.lab10_iweb.Servlets;

import com.example.lab10_iweb.Beans.*;
import com.example.lab10_iweb.Daos.DaoCredentials;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "ServletLogin", urlPatterns = {"","/ServletLogin"})
public class ServletLogin extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action") == null ?
                "login" : request.getParameter("action");
        HttpSession session = request.getSession();
        RequestDispatcher rd;

        switch (action){
            case "login":
                Credentials credentials = (Credentials) session.getAttribute("usuarioLogueado");

                if(credentials != null && credentials.getNumeroDocumento()!= null){
                    response.sendRedirect(request.getContextPath());
                }else{
                    rd = request.getRequestDispatcher("index.jsp");
                    rd.forward(request, response);
                }
                break;

            case "logout":
                session.invalidate();
                response.sendRedirect(request.getContextPath());
                break;
        }
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DaoCredentials credentialsDao = new DaoCredentials();

        String username = request.getParameter("numDocumento");
        String password = request.getParameter("contrasena");

        Credentials credentials = credentialsDao.validarUsuarioPassword(username, password);
        HttpSession session = request.getSession();

        if(credentials != null){
            session.setAttribute("usuarioLogueado", credentials);
            if (credentials.getTipoUsuario() ==1) {
                response.sendRedirect(request.getContextPath() + "/ServletAdministrador");
            }
            else{
                response.sendRedirect(request.getContextPath()+"/ServletCliente");
            }
        }else{
            response.sendRedirect(request.getContextPath() + "/ServletLogin?error");
        }

    }
}