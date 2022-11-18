package com.example.lab10_iweb.Servlets;

import com.example.lab10_iweb.Beans.Credentials;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "ServletAdministrador", value = "/ServletAdministrador")
public class ServletAdministrador extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action") == null ? "formCreateCredentials" : request.getParameter("action");

        RequestDispatcher view;

        switch (action) {
            case "formCreateCredentials":

                view = request.getRequestDispatcher("InicioAdmin.jsp");
                view.forward(request, response);

        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*switch (action){
            case "mostrarInicio":
                //request.setAttribute("lista", daoClientes.listarClientes());

                view = request.getRequestDispatcher("InicioCliente.jsp");
                view.forward(request, response);
            case "crear":
                requestDispatcher = request.getRequestDispatcher("clientes/formCrear.jsp");
                requestDispatcher.forward(request,response);
        }
    }*/
    }
}
