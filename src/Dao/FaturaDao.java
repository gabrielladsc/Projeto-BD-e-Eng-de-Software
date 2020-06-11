/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Model.Encomenda;
import Model.Fatura;
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
public class FaturaDao extends Dao {
    //Construtor que inicializa a conexão com o banco de dados
    public FaturaDao() {
        this.connection = MySqlConnector.getConnection();
    }

    //Através de uma instância da fatura cria uma nova fatura no banco
    public boolean cadastrarFatura(Fatura fatura) {
        try {
            //Insere uma nova fatura no banco de dados através da Query SQL
            //Caso insira com sucesso, retorna true, senão, false
            this.statement = this.connection.createStatement();
            String query = "INSERT INTO Fatura (codigo, nome, valor, "
                    + "descricao) "
                    + "VALUES ('"
                    + fatura.getCodigo() + "','" + fatura.getNome() + "','" 
                    + fatura.getValor()+ "','" + fatura.getDescricao() + "');";
            this.statement.executeUpdate(query);
            return true;
        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
            return false;
        }
    }

    //Atualiza fatura. Caso atualize com sucesso,
    //retorna true, senão, false
    public boolean editarFatura(Fatura fatura) {
        try {
            System.out.println(fatura.getNome());
            this.statement = this.connection.createStatement();
            String queryAtualizar = "UPDATE Fatura SET "
                    + "codigo = " + '"' + fatura.getCodigo() + '"'
                    + ", nome = " + '"' + fatura.getNome() + '"'
                    + ", valor = " + '"' + fatura.getValor() + '"'
                    + ", descricao = "+ '"' + fatura.getDescricao() + '"'
                    + "WHERE codigo = "  + '"' + fatura.getCodigo() + '"';

            this.statement.executeUpdate(queryAtualizar);
            return true;
        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
            return false;
        }
    }

    //Deleta a fatura. Caso delete com sucesso retorna true, senão, false
    public boolean excluirFatura(Fatura fatura) {
        try {
            this.statement = this.connection.createStatement();
            String queryDeletar = "DELETE FROM Fatura WHERE codigo = " + '"' + fatura.getCodigo() + '"';
            this.statement.executeUpdate(queryDeletar);
            return true;
        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
            return false;
        }
    }
    
    public List<Fatura> read(){
        List <Fatura> faturas = new ArrayList<>();
        
        try {
            this.statement = this.connection.createStatement();
            ResultSet rs = null;
            String query = "SELECT * FROM Fatura";
            rs = this.statement.executeQuery(query);
            while (rs.next()) {
                Fatura fat = new Fatura();
                fat.setCodigo(rs.getString("codigo"));
                fat.setNome(rs.getString("nome"));
                fat.setValor(rs.getString("valor"));
                fat.setDescricao(rs.getString("descricao"));
                faturas.add(fat);
            }
        } catch (SQLException ex) {
            Logger.getLogger(FaturaDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return faturas;
    }
}
