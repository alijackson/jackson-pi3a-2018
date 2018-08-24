/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.tads.pi.five.agenda;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author alijackson.msilva
 */
public class Agenda {
    
    private Connection con() 
            throws ClassNotFoundException, SQLException{
        // 
        Class.forName("com.mysql.jdbc.Driver");
        
        // Abrir conexão
        Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/agendabd" ,
                "root" , "");
        
        return conn;
    }
    
    public void listar() 
            throws SQLException{
        
        String querySql = "SELECT id, nome, dtnascimento FROM pessoa";
        try(    Connection conn = con();
                PreparedStatement pst = conn.prepareStatement(querySql);
                ResultSet rs = pst.executeQuery();)
        {
            while(rs.next()){
                System.out.println("==== Aluno\nID: " +rs.getLong("id"));
                System.out.println("Nome: " +rs.getString("nome"));
                System.out.println("Data de nascimento: "+rs.getDate("dtnascimento")+"\n");
                
            }            
            } catch (ClassNotFoundException ex) {
            Logger.getLogger(Agenda.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static void main (String [] args){
        try {
            Agenda ag = new Agenda();
            ag.listar();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
