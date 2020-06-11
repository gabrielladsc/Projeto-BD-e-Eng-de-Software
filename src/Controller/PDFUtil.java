/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.MySqlConnector;
import View.GerarRelatorio;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author gabi0
 */
public class PDFUtil {
    public void createPDF(String file_name, String item){
        //String file_name = "C:\\Users\\gabi0\\Desktop\\EasyToyV3\\Brinquedos.pdf";
        Document document = new Document();
        try {
            PdfWriter.getInstance(document, new FileOutputStream(file_name));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(GerarRelatorio.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DocumentException ex) {
            Logger.getLogger(GerarRelatorio.class.getName()).log(Level.SEVERE, null, ex);
        }
        document.open();
        
        MySqlConnector conex = new MySqlConnector();
        Connection connection = conex.getConnection();
        ResultSet rs = null;
        Statement statement = null;
        
        String query = "SELECT * FROM " + item;
        try {
            statement = connection.prepareStatement(query);
        } catch (SQLException ex) {
            Logger.getLogger(GerarRelatorio.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            rs = statement.executeQuery(query);
            if (item == "Briquedo") {
                createBrinquedo(rs, document);
            } else if (item == "Encomenda") {
                createEncomenda(rs, document);
            } else if ( item == "Fatura"){
                createFatura(rs, document);
            } else if ( item == "Funcionario" ){
                createFuncionario(rs, document);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(GerarRelatorio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void createBrinquedo(ResultSet rs, Document document){
        try {
            while (rs.next()) {
                Paragraph para = new Paragraph("Código: " + rs.getString("codigo") 
                + "\n" + " Nome: " + rs.getString("nome") 
                + "\n" + " Quantidade: " + rs.getString("quantidade"));
                document.add(para);
                document.add(new Paragraph(" "));
            }
        } catch (SQLException ex) {
            Logger.getLogger(GerarRelatorio.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DocumentException ex) {
            Logger.getLogger(GerarRelatorio.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        document.close();
    }
    
    public void createFatura(ResultSet rs, Document document){
        try {
            while (rs.next()) {
                Paragraph par = new Paragraph("Código: " + rs.getString("codigo") 
                + "\n" + " Nome: " + rs.getString("nome") 
                + "\n" + " Valor: " + rs.getString("valor")
                + "\n" + " Descrição: " + rs.getString("descricao"));
                document.add(par);
                document.add(new Paragraph(" "));
            }
        } catch (SQLException ex) {
            Logger.getLogger(GerarRelatorio.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DocumentException ex) {
            Logger.getLogger(GerarRelatorio.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        document.close();
    }
    
    public void createEncomenda(ResultSet rs, Document document){
        try {
            while (rs.next()) {
                Paragraph para = new Paragraph("Código: " + rs.getString("codigo") 
                + "\n" + " Descrição: " + rs.getString("descricao") 
                + "\n" + " Nome: " + rs.getString("nome")
                + "\n" + " Quantidade: " + rs.getString("quantidade")
                + "\n" + " Nome do funcionário responsável: " + rs.getString("funcionarioNome"));
                document.add(para);
                document.add(new Paragraph(" "));
            }
        } catch (SQLException ex) {
            Logger.getLogger(GerarRelatorio.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DocumentException ex) {
            Logger.getLogger(GerarRelatorio.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        document.close();
    }
    
    public void createFuncionario(ResultSet rs, Document document){
        try {
            while (rs.next()) {
                Paragraph para = new Paragraph("Nome: " + rs.getString("nome") 
                + "\n " + " CPF: " + rs.getString("cpf")
                + "\n" + " Data de Nascimento: " + rs.getString("dataNascimento")
                + "\n" + " Telefone: " + rs.getString("telefone")
                + "\n" + " Email: " + rs.getString("email"));
                document.add(para);
                document.add(new Paragraph(" "));
            }
        } catch (SQLException ex) {
            Logger.getLogger(GerarRelatorio.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DocumentException ex) {
            Logger.getLogger(GerarRelatorio.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        document.close();
    }
}
