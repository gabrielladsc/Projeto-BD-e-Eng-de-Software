/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Model.Brinquedo;
import Model.MySqlConnector;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author gabi0
 */
public class BrinquedoDao extends Dao {
    
    //Construtor que inicializa a conexão com o banco de dados
    public BrinquedoDao() {
        this.connection = MySqlConnector.getConnection();
    }

    //Através de uma instância do brinqudo cria um novo brinquedo no banco
    public boolean cadastrarBrinquedo(Brinquedo brinquedo) {
        try {
            //Insere um novo brinquedo no banco de dados através da Query SQL
            //Caso insira com sucesso, retorna true, senão, false
            this.statement = this.connection.createStatement();
            String query = "INSERT INTO Brinquedo (codigo, nome, "
                    + "quantidade) " 
                    + "VALUES ('"+ brinquedo.getCodigo() + "','" + brinquedo.getNome() + "','" + brinquedo.getQuantidade() + "');";
            this.statement.executeUpdate(query);
            return true;
        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
            return false;
        }
    }

    //Atualiza brinquedo. Caso atualize com sucesso,
    //retorna true, senão, false
    public boolean editarBrinquedo(Brinquedo brinquedo) {
        try {
            this.statement = this.connection.createStatement();
            String queryAtualizar = "UPDATE brinquedo SET "
                    + "codigo = " + '"' + brinquedo.getCodigo() + '"'
                    + ", nome = " + '"' + brinquedo.getNome() + '"'
                    + ", quantidade = " + '"' + brinquedo.getQuantidade()+ '"' 
                    + "WHERE codigo = "  + '"' + brinquedo.getCodigo() + '"';
            
            this.statement.executeUpdate(queryAtualizar);
            return true;
        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
            return false;
        }
    }

    //Deleta o brinquedo. Caso delete com sucesso retorna true, senão, false
    public boolean excluirBrinquedo(Brinquedo brinquedo) {
        try {
            this.statement = this.connection.createStatement();
            String queryDeletar = "DELETE FROM brinquedo WHERE codigo = " + '"' + brinquedo.getCodigo() + '"';
            this.statement.executeUpdate(queryDeletar);
            return true;
        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
            return false;
        }
    }
    
    public List<Brinquedo> read(){
        List <Brinquedo> brinquedos = new ArrayList<>();
        
        try {
            this.statement = this.connection.createStatement();
            ResultSet rs = null;
            String query = "SELECT * FROM Brinquedo";
            rs = this.statement.executeQuery(query);
            while (rs.next()) {
                Brinquedo brinq = new Brinquedo();
                brinq.setCodigo(rs.getString("codigo"));
                brinq.setNome(rs.getString("nome"));
                brinq.setQuantidade(rs.getString("quantidade"));
                brinquedos.add(brinq);
            }
        } catch (SQLException ex) {
            Logger.getLogger(BrinquedoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return brinquedos;
    }
}