/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Model.Brinquedo;
import Model.MySqlConnector;
import java.sql.SQLException;
import Model.Relatorio;

        
/**
 *
 * @author gabi0
 */
public class RelatorioDao extends Dao {
    
    //Construtor que inicializa a conexão com o banco de dados
    public RelatorioDao() {
        this.connection = MySqlConnector.getConnection();
    }

    //Através de uma instância do relatorio cria um novo relatorio no banco
    public boolean cadastrarRelatorio(Relatorio relatorio) {
        try {
            //Insere um novo relatorio no banco de dados através da Query SQL
            //Caso insira com sucesso, retorna true, senão, false
            this.statement = this.connection.createStatement();
            String query = "INSERT INTO Relatorio (id, opcao, "
                    + "funcionarioNome) " 
                    + "VALUES ('"+ relatorio.getId() + "','" + relatorio.getOpcao() + "','" + relatorio.getFuncionarioNome()+ "');";
            this.statement.executeUpdate(query);
            return true;
        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
            return false;
        }
    }

    //Atualiza relatorio. Caso atualize com sucesso,
    //retorna true, senão, false
    public boolean editarRelatorio(Relatorio relatorio) {
        try {
            this.statement = this.connection.createStatement();
            String queryAtualizar = "UPDATE Relatorio SET "
                    + "id = " + '"' + relatorio.getId() + '"' 
                    + ", opcao = " + '"' + relatorio.getOpcao()+ '"'
                    + "WEHERE id = " + '"' + relatorio.getId() + '"';

            this.statement.executeUpdate(queryAtualizar);
            return true;
        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
            return false;
        }
    }

    //Deleta o relatorio. Caso delete com sucesso retorna true, senão, false
    public boolean excluirRelatorio(Relatorio relatorio) {
        try {
            this.statement = this.connection.createStatement();
            String queryDeletar = "DELETE FROM Relatorio WHERE id = " + '"' + relatorio.getId() + '"';
            this.statement.executeUpdate(queryDeletar);
            return true;
        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
            return false;
        }
    }
}
