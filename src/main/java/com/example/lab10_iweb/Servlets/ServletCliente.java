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
            action = (action == null) ? "listar" : action;
            RequestDispatcher requestDispatcher;
            DaoCliente daoClientes = new DaoCliente();
            ArrayList<Cliente> list = daoClientes.listarClientes();

            switch (action) {
                case "misDatos":
                    String idUsuario = credentialsLogueado.getNumeroDocumento();
                    request.setAttribute("misdatos", daoClientes.buscarPorId(idUsuario));
                    requestDispatcher = request.getRequestDispatcher("clientes/lista.jsp");
                    requestDispatcher.forward(request, response);
                case "crear":
                    requestDispatcher = request.getRequestDispatcher("clientes/formCrear.jsp");
                    requestDispatcher.forward(request, response);

                case "listarContratos":
                    ArrayList<Contrato> listacontratos = daoClientes.obtenerlistaContratos(credentialsLogueado.getNumeroDocumento());
                    request.setAttribute("lista", listacontratos);
                    requestDispatcher = request.getRequestDispatcher("clientes/ContratosCliente.jsp");
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