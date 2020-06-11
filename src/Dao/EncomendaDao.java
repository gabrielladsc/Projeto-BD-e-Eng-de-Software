/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Model.Brinquedo;
import Model.Encomenda;
import Model.MySqlConnector;
import java.sql.Connection;
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
public class EncomendaDao extends Dao {

    //Construtor que inicializa a conexão com o banco de dados
    public EncomendaDao() {
        this.connection = MySqlConnector.getConnection();
    }

    //Através de uma instância da encomenda cria uma nova encomenda no banco
    public boolean cadastrarEncomenda(Encomenda encomenda) {
        try {
            //Insere uma nova encomenda no banco de dados através da Query SQL
            //Caso insira com sucesso, retorna true, senão, false
            this.statement = this.connection.createStatement();
            String query = "INSERT INTO encomenda (codigo, descricao, nome, "
                    + "quantidade, funcionarioNome) "
                    + "VALUES ('"
                    + encomenda.getCodigo() + "','" + encomenda.getDescricao() 
                    + "','" + encomenda.getNome() + "','" + encomenda.getQuantidade() 
                    + "','" + encomenda.getfuncionarioNome() + "');";
            this.statement.executeUpdate(query);
            return true;
        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
            return false;
        }
    }

    //Atualiza encomenda. Caso atualize com sucesso,
    //retorna true, senão, false
    public boolean editarEncomenda(Encomenda encomenda) {
        try {
            this.statement = this.connection.createStatement();
            String queryAtualizar = "UPDATE encomenda SET "
                    + "codigo = " + '"' + encomenda.getCodigo() + '"'
                    + ", nome = " + '"' + encomenda.getNome() + '"'
                    + ", descricao = " + '"' + encomenda.getDescricao() + '"' 
                    + ", quantidade = " + '"' + encomenda.getQuantidade() + '"'
                    + "WHERE codigo = " + '"' + encomenda.getCodigo() + '"';
            
            this.statement.executeUpdate(queryAtualizar);
            return true;
        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
            return false;
        }
    }

    //Deleta a encomenda. Caso delete com sucesso retorna true, senão, false
    public boolean excluirEncomenda(Encomenda encomenda) {
        try {
            this.statement = this.connection.createStatement();
            String queryDeletar = "DELETE FROM Encomenda WHERE codigo = " + '"' + encomenda.getCodigo() + '"';
            this.statement.executeUpdate(queryDeletar);
            return true;
        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
            return false;
        }
    }
    
    public List<Encomenda> read(){
        List <Encomenda> encomendas = new ArrayList<>();
        
        try {
            this.statement = this.connection.createStatement();
            ResultSet rs = null;
            String query = "SELECT * FROM Encomenda";
            rs = this.statement.executeQuery(query);
            while (rs.next()) {
                Encomenda enc = new Encomenda();
                enc.setCodigo(rs.getString("codigo"));
                enc.setNome(rs.getString("nome"));
                enc.setDescricao(rs.getString("descricao"));
                enc.setQuantidade(rs.getString("quantidade"));
                encomendas.add(enc);
            }
        } catch (SQLException ex) {
            Logger.getLogger(EncomendaDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return encomendas;
    }
}
