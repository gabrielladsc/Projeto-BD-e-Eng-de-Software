/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Model.Encomenda;
import Model.Funcionario;
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
public class FuncionarioDao extends Dao {
    //Construtor que inicializa a conexão com o banco de dados
    public FuncionarioDao() {
        this.connection = MySqlConnector.getConnection();
    }

    //Através de uma instância do funcionario cria um novo funcionario no banco
    public boolean cadastrarFuncionario(Funcionario funcionario) {
        try {
            //Insere um novo funcionario no banco de dados através da Query SQL
            //Caso insira com sucesso, retorna true, senão, false
            this.statement = this.connection.createStatement();
            String query = "INSERT INTO Funcionario (nome, cpf, dataNascimento, telefone, email)"
                    + "VALUES ('"
                    + funcionario.getNome() + "','" + funcionario.getCpf() + "','"
                    + funcionario.getDataNascimento()
                    + "','" + funcionario.getTelefone() + "','" + funcionario.getEmail() + "');";
            this.statement.executeUpdate(query);
            return true;
        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
            return false;
        }
    }

    //Atualiza funcionario. Caso atualize com sucesso,
    //retorna true, senão, false
    public boolean editarFuncionario(Funcionario funcionario) {
        try {
            this.statement = this.connection.createStatement();
            String queryAtualizar = "UPDATE Funcionario SET "
                    + "nome = " + '"' +  funcionario.getNome() + '"' 
                    + ", cpf = " + '"' + funcionario.getCpf() + '"' 
                    + ", dataNascimento = " + '"' + funcionario.getDataNascimento() + '"'
                    + ", telefone = " + '"' + funcionario.getTelefone() + '"'
                    + ", email = " + '"' + funcionario.getEmail() + '"'
                    + " WHERE nome = " + '"' + funcionario.getNome() + '"';
            
            this.statement.executeUpdate(queryAtualizar);
          
            this.statement.executeUpdate(queryAtualizar);
            return true;
        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
            return false;
        }
    }

    //Deleta o funcionario. Caso delete com sucesso retorna true, senão, false
    public boolean excluirFuncionario(Funcionario funcionario) {
        try {
            this.statement = this.connection.createStatement();
            String queryDeletar = "DELETE FROM Funcionario WHERE nome = "+ '"' + funcionario.getNome() + '"';
            this.statement.executeUpdate(queryDeletar);
            return true;
        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
            return false;
        }
    }
    
    public List<Funcionario> read(){
        List <Funcionario> funcionarios = new ArrayList<>();
        
        try {
            this.statement = this.connection.createStatement();
            ResultSet rs = null;
            String query = "SELECT * FROM Funcionario";
            rs = this.statement.executeQuery(query);
            while (rs.next()) {
                Funcionario func = new Funcionario();
                func.setNome(rs.getString("nome"));
                func.setCpf(rs.getString("cpf"));
                func.setDataNascimento(rs.getString("dataNascimento"));
                func.setTelefone(rs.getString("telefone"));
                func.setEmail(rs.getString("email"));
                funcionarios.add(func);
            }
        } catch (SQLException ex) {
            Logger.getLogger(FuncionarioDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return funcionarios;
    }
}
