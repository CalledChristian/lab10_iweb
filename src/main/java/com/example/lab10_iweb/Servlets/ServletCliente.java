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

        HttpSession session = (HttpSession) request.getSession();
        Credentials credentialsLogueado = (Credentials) session.getAttribute("usuarioLogueado");
        if (credentialsLogueado == null) {
            response.sendRedirect(request.getContextPath());
        } else {

            String action = request.getParameter("action");
            action = (action == null) ? "mostrarInicio" : action;
            RequestDispatcher requestDispatcher;
            DaoCliente daoClientes = new DaoCliente();
            //ArrayList<Cliente> list = daoClientes.listarClientes();

            switch (action) {

                case "mostrarInicio":

                    requestDispatcher = request.getRequestDispatcher("InicioCliente.jsp");
                    requestDispatcher.forward(request, response);

                case "mostrarDatos":
                    String numDocumento = credentialsLogueado.getNumeroDocumento();
                    request.setAttribute("cliente", daoClientes.buscarCliente(numDocumento));
                    requestDispatcher = request.getRequestDispatcher("DatosCliente.jsp");
                    requestDispatcher.forward(request, response);

                case "mostrarContratos":
                    ArrayList<Contrato> listacontratos = daoClientes.obtenerlistaContratos(credentialsLogueado.getNumeroDocumento());
                    request.setAttribute("lista", listacontratos);
                    requestDispatcher = request.getRequestDispatcher("ContratosCliente.jsp");
                    requestDispatcher.forward(request, response);

                case "mostrarContratosPorEstado":
                    requestDispatcher = request.getRequestDispatcher("ContratosPorEstado.jsp");
                    requestDispatcher.forward(request, response);


                case "mostrarMaxExpectedLoss":
                    requestDispatcher = request.getRequestDispatcher("MaxExpectedLoss.jsp");
                    requestDispatcher.forward(request, response);
            }

        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        DaoCliente daoCliente = new DaoCliente();

        switch (action) {
            case "buscar":
                String searchText = request.getParameter("searchText");

                Cliente cliente_encontrado = daoCliente.buscarPorId(searchText);
                request.setAttribute("cliente", cliente_encontrado);
                request.setAttribute("searchText", searchText);

                RequestDispatcher requestDispatcher = request.getRequestDispatcher("clientes/lista.jsp");
                requestDispatcher.forward(request, response);
                break;


        }
    }
}