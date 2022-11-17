package com.example.lab10_iweb.Daos;
import com.example.lab10_iweb.Beans.*;
import java.sql.*;
import java.util.ArrayList;

public class DaoCliente extends DaoBase{
    public ArrayList<Cliente> listarClientes(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        String url = "jdbc:mysql://localhost:3306/hr";
        ArrayList<Cliente> listaClientes = new ArrayList<>();
        try {
            Connection connection = DriverManager.getConnection(url, "root", "123456");
            String sql = "SELECT concat(g4093_name,' ',coalesce(g4093_last_name,'')) as nombreCliente, g4093_age,g4093_type,g4093_documentType,g4093_nro_id,\n" +
                    "\t   case when g4093_type = \"J\" then \"Juridica\"\n" +
                    "\t\t\twhen g4093_type = \"N\" then \"Normal\"\n" +
                    "            end as tipoCliente\n" +
                    " FROM bi_corp_business.jm_client_bii;";
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()){
                Cliente cliente = new Cliente();
                cliente.setNombreCliente(rs.getString("nombreCliente"));
                cliente.setEdad(rs.getString("g4093_age"));
                cliente.setTipoCliente(rs.getString("tipoCliente"));
                cliente.setTipoDocumento(rs.getString("g4093_documentType"));
                cliente.setNumeroDocumento(rs.getString("g4093_nro_id"));
                listaClientes.add(cliente);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaClientes;
    }

    public Cliente buscarPorId(String numeroId) {

        Cliente cliente = new Cliente();

        String sql = "SELECT u.codigo, u.nombre, u.apellido, u.correo, u.DNI, u.validaUsuario, u.password, u.nickname, u.celular, r.idRoles, r.nombreRol, catpucp.idCategoriaPUCP, catpucp.nombreCategoria,\n" +
                "fp.idFotoPerfil, fp.nombreFoto, fp.fotoPerfil \n" +
                "FROM Usuarios u inner join Roles r on r.idRoles = u.idRoles left join CategoriaPUCP catpucp on catpucp.idCategoriaPUCP = u.idCategoriaPUCP \n" +
                "left join FotoPerfil fp on u.idFotoPerfil = fp.idFotoPerfil where u.codigo = ?";

        try(Connection connection = this.getConnection();
            PreparedStatement pstmt = connection.prepareStatement(sql)){
            pstmt.setString(1, numeroId);
            try(ResultSet rs = pstmt.executeQuery();){
                if (rs.next()) {
                    cliente.setNombreCliente(rs.getString(1));
                    cliente.setEdad(rs.getString(2));
                    cliente.setTipoCliente(rs.getString(3));
                    cliente.setTipoDocumento(rs.getString(4));
                    cliente.setNumeroDocumento(rs.getString(5));

                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cliente;
    }


    public void createCredentialCliente(Cliente cliente){


        String sql = "INSERT INTO jm_client_bii(g4093_name, g4093_age, g4093_type, g4093_documentType, g4093_nro_id) VALUES (?,?,?,?,sha2(?,256),?,?,?,?,?)";


        try (Connection connection = this.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {

            pstmt.setString(1, cliente.getNombreCliente());
            pstmt.setString(2, cliente.getEdad());
            pstmt.setString(3, cliente.getTipoCliente());
            pstmt.setString(4, cliente.getTipoDocumento());
            pstmt.setString(5, cliente.getNumeroDocumento());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
