/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

import Negocio.Lote;
import Negocio.Produto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author usuario
 */
public class DaoLote implements DaoBasico {
   
  public DaoLote() {
    String inst = "CREATE TABLE IF NOT EXISTS Lote"
                          + " (NumLote INT NOT NULL"
                          + ", Codigo INT NOT NULL"
                          + ", DataVal VARCHAR(10)"
                          + ", PRIMARY KEY (NumLote)"
                          + ", KEY Codigo (Codigo)" 
                          + ", CONSTRAINT CodigoV FOREIGN KEY (Codigo) REFERENCES Produto (Codigo)"
                          + ") ENGINE=InnoDB DEFAULT CHARSET=latin1;";
    try {
        Connection con = DaoConexaoAB.getInstancia().getCon();
        try (PreparedStatement pS = con.prepareStatement(inst)) {
                pS.execute();               
        }
        DaoConexaoAB.getInstancia().setCon(con);
    } 
    catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
    } 
  }
    
  @Override
  public boolean incluir (Object o){
       boolean result = true;
       Lote l = (Lote) o;
       String inst = "Insert into Lote (NumLote, Codigo, DataVal) ";
             inst +="values (?, ?, ?)";
       try {
           Connection con = DaoConexaoAB.getInstancia().getCon();
           try (PreparedStatement pS = con.prepareStatement(inst)) {
                pS.setInt(1, l.getNumLote());
                pS.setInt(2, l.getProduto().getCodigo());
                pS.setString(3, l.getDataVal());
                pS.execute();
           }
           DaoConexaoAB.getInstancia().setCon(con);
       }
       catch (SQLException e){
             result = false;
             throw new RuntimeException(e.getMessage());
       }
       return (result);       
  }
  
  @Override
  public boolean alterar (Object o) {
       boolean result = true;
       Lote l = (Lote) o;
       String inst = "Update Lote set Codigo = ?, DataVal =?";
             inst += " where NumLote = ?";   
       try{
          Connection con = DaoConexaoAB.getInstancia().getCon();
          try (PreparedStatement pS = con.prepareStatement(inst)){
                pS.setInt(1, l.getProduto().getCodigo());
                pS.setString(2, l.getDataVal());
                pS.setInt(3, l.getNumLote());
                pS.execute();  
          }
          DaoConexaoAB.getInstancia().setCon(con);
        }
        catch (SQLException e){
             result = false;
             throw new RuntimeException(e.getMessage());
        }
        return (result);       
    } 
    
    @Override
    public boolean excluir(Object o) {
        boolean result = true;
        Lote l = (Lote) o;
        String inst = "Delete from Lote where NumLote = ?"; 
        try {
            Connection con = DaoConexaoAB.getInstancia().getCon();
            try (PreparedStatement pS = con.prepareStatement(inst)) {
                pS.setInt(1, l.getNumLote());
                pS.execute();           
            }
            DaoConexaoAB.getInstancia().setCon(con);
        } catch (SQLException e){
            result = false;
            throw new RuntimeException(e.getMessage());
        }       
        return (result);
    }
    
    @Override
    public Object busca(int numero, int nada) {
        String inst = "Select * from Lote where numLote = ?";
        Lote l = null;
        ResultSet rS;
        try{
             Connection con = DaoConexaoAB.getInstancia().getCon();
             try (PreparedStatement pS = con.prepareStatement(inst)) {
                   pS.setInt(1, numero);
                   rS = pS.executeQuery();
                   DaoConexaoAB.getInstancia().setCon(con);    
                   if (rS.next()) {
                      l = new Lote();
                      l.setNumLote(rS.getInt("NumLote"));
                      DaoProduto dP = new DaoProduto(); 
                      Produto p = (Produto) dP.busca(rS.getInt("Codigo"), 0);
                      l.setProduto(p);
                      l.setDataVal(rS.getString("DataVal"));
                   }
             }   
         }
         catch (SQLException e) {
                 throw new RuntimeException(e.getMessage());            
         }
         return(l);    
    }
    
    @Override
    public List<Object> carrega() {
        String inst = "Select * from Lote order by data desc";
        ResultSet rS;
        List <Object> lista = new ArrayList<>();
        Object o;
        
        try {
            try (PreparedStatement pS =
                        DaoConexaoAB.getInstancia().getCon().prepareStatement(inst)) {
                 rS = pS.executeQuery(inst);
                 DaoConexaoAB.getInstancia().setCon(DaoConexaoAB.getInstancia().getCon());
                 if (rS != null)
                    while (rS.next()){
                        o = busca(rS.getInt("NumLote"), 0);
                        lista.add(o);
                    }
                pS.close();
            }             
        } catch(SQLException e){
                throw new RuntimeException(e.getMessage());
        }
        return lista;    
    }  
}
