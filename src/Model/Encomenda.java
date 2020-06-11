/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author gabi0
 */
public class Encomenda {
    //identificação da encomenda no banco de dados
    private String codigo;
    
    private String nome;
    private String descricao;
    private String quantidade;
    
    private String funcionarioNome;
    
    public Encomenda (String codigo, String nome, String descricao, String quantidade){
        this.codigo = codigo;
        this.descricao = descricao;
        this.nome = nome;
        this.quantidade = quantidade;
    }
    
    public Encomenda(){
        
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(String quantidade) {
        this.quantidade = quantidade;
    }

    public String getfuncionarioNome() {
        return funcionarioNome;
    }

    public void setFuncionarioNome(String funcionarioNome) {
        this.funcionarioNome = funcionarioNome;
    }
    
    @Override
    public String toString() {
        return getCodigo(); //To change body of generated methods, choose Tools | Templates.
    }
}
