package com.example.lab10_iweb.Servlets;

import com.example.lab10_iweb.Beans.*;
import com.example.lab10_iweb.Daos.DaoCliente;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "ServletCliente", value = "/ServletCliente")
public class ServletCliente extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("action");
        action = (action == null) ? "mostrarInicio" : action;
        RequestDispatcher view;
        DaoCliente daoCliente = new DaoCliente();
        //ArrayList<Cliente> list = daoCliente.listarClientes();

        switch (action){
            case "mostrarInicio":

                view = request.getRequestDispatcher("InicioCliente.jsp");
                view.forward(request, response);
            case "mostrarDatos":
                view = request.getRequestDispatcher("DatosCliente.jsp");
                view.forward(request,response);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        DaoCliente daoCliente = new DaoCliente();

        /*switch (action){
            case "buscar":
                String searchText = request.getParameter("searchText");

                ArrayList<Cliente> lista = daoCliente.buscarPorId(searchText);
                request.setAttribute("lista", lista);
                request.setAttribute("searchText", searchText);

                RequestDispatcher requestDispatcher = request.getRequestDispatcher("clientes/lista.jsp");
                requestDispatcher.forward(request, response);
                break;
        }*/
    }
}