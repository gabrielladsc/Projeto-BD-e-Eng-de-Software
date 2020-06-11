/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;


import Model.MySqlConnector;
import java.sql.SQLException;

/**
 *
 * @author gabi0
 */
public class ProduzDao extends Dao {
    
    //Construtor que inicializa a conexão com o banco de dados
    public ProduzDao() {
        this.connection = MySqlConnector.getConnection();
    }

    //Através do id do funcionario escolhido e do id do brinquedo selecionado, cria
    //uma nova relação entre eles no banco
    public boolean adicionar(String brinquedoCodigo, String funcionarioNome) {
        try {
            this.statement = this.connection.createStatement();
            String query = "INSERT INTO Produz (funcionarioNome, brinquedoCodigo) "
                    + "VALUES ('"
                    + funcionarioNome + "','" + brinquedoCodigo + "');";
            this.statement.executeUpdate(query);
            return true;
        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
            return false;
        }
    }

    //Remove a relação entre um funcionario e um brinquedo. 
    //Contudo, ambos continuam existindo separadamente
    public boolean remover(String funcionarioNome, String brinquedoCodigo) {
        try {
            this.statement = this.connection.createStatement();
            String query = "DELETE FROM Produz WHERE funcionarioNome = " + funcionarioNome + '"' + " and brinquedoCodigo = " + brinquedoCodigo + '"';
            this.statement.executeUpdate(query);
            return true;
        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
            return false;
        }
    }
}
