package com.example.lab10_iweb.Daos;
import com.example.lab10_iweb.Beans.*;
import java.sql.*;
import java.util.ArrayList;

public class DaoCliente extends DaoBase{
    public ArrayList<Cliente> listarClientes(){

        ArrayList<Cliente> listaClientes = new ArrayList<>();
        /*String sql = "SELECT concat(g4093_name,' ',coalesce(g4093_last_name,'')) as nombreCliente, g4093_age,g4093_type,g4093_documentType,g4093_nro_id,\n" +
                "\t   case when g4093_type = \"J\" then \"Juridica\"\n" +
                "\t\t\twhen g4093_type = \"N\" then \"Normal\"\n" +
                "            end as tipoCliente\n" +
                " FROM bi_corp_business.jm_client_bii;"

         */

        String sql = "select * from jm_client_bii ";
        try (Connection connection = this.getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)){

            while (rs.next()){
                Cliente cliente = new Cliente();
                cliente.setNumeroDocumento(rs.getString(1));
                cliente.setNombreCliente(rs.getString(2));
                cliente.setEdad(rs.getString(3));
                cliente.setTipoCliente(rs.getString(4));
                cliente.setTipoDocumento(rs.getString(5));
                listaClientes.add(cliente);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaClientes;
    }

    public Cliente buscarPorId(String numeroId) {

        Cliente cliente = new Cliente();

        String sql = "SELECT * from jm_client_bii where jm_client_bii.g4093_nro_id = ?";

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

    public Cliente buscarCliente(String numeroDocumento) {

        Cliente cliente = null;

        String sql = "select * from jm_client_bii where g4093_nro_id = ?";

        try(Connection connection = this.getConnection();
            PreparedStatement pstmt = connection.prepareStatement(sql)){
            pstmt.setString(1, numeroDocumento);
            try(ResultSet rs = pstmt.executeQuery();){
                if (rs.next()) {
                    cliente = new Cliente();
                    cliente.setNumeroDocumento(numeroDocumento);
                    cliente.setNombreCliente(rs.getString(2));
                    cliente.setEdad(rs.getString(3));
                    cliente.setTipoCliente(rs.getString(4));
                    cliente.setTipoDocumento(rs.getString(5));

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

    public void createCredentialCliente2(String num_doc, String password){


        String sql = "INSERT INTO credentials(nro_documento, password, hashedPassword, tipoUsuario) VALUES (?,?,sha2(?,256))";


        try (Connection connection = this.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {

            pstmt.setString(1, num_doc);
            pstmt.setString(2, password);
            pstmt.setString(2, password);
            pstmt.setString(2, "2");


            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public java.util.ArrayList<Contrato> obtenerlistaContratos(String idUsuario) {
        ArrayList<Contrato> listaContratos = new ArrayList<>();

        String sql = "select * from jm_cotr_bis where jm_cotr_bis.client_nro_id = ?";


        try (Connection connection = this.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql);) {

            pstmt.setString(1, idUsuario);

            try (ResultSet rs = pstmt.executeQuery();) {

                while (rs.next()) {
                    Contrato contrato = new Contrato();
                    contrato.setIdCliente(rs.getString(2));
                    contrato.setDivisa(rs.getString(3));
                    contrato.setNumeroDeContrato(rs.getString(1));
                    contrato.setEstado(rs.getInt(4));
                    contrato.setMesesEnEseEstado(rs.getInt(5));
                    listaContratos.add(contrato);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return listaContratos;
    }

    public ArrayList<Credentials> listarCredentials(){

        ArrayList<Credentials> listaCredentials = new ArrayList<>();
        /*String sql = "SELECT concat(g4093_name,' ',coalesce(g4093_last_name,'')) as nombreCliente, g4093_age,g4093_type,g4093_documentType,g4093_nro_id,\n" +
                "\t   case when g4093_type = \"J\" then \"Juridica\"\n" +
                "\t\t\twhen g4093_type = \"N\" then \"Normal\"\n" +
                "            end as tipoCliente\n" +
                " FROM bi_corp_business.jm_client_bii;"

         */

        String sql = "select nro_documento, tipoUsuario from credentials ";
        try (Connection connection = this.getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)){

            while (rs.next()){
                Credentials credentials = new Credentials();
                credentials.setNumeroDocumento(rs.getString(1));
                credentials.setTipoUsuario(rs.getInt(2));
                listaCredentials.add(credentials);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaCredentials;
    }
}