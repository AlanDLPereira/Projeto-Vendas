/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

import Negocio.ItemC;
import Negocio.Produto;
import Negocio.Compra;
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

   
public class DaoItemC implements DaoBasico {
    
   public DaoItemC() {
    String inst = "CREATE TABLE IF NOT EXISTS ItemC"
                          + " (Numero INT NOT NULL"
                          + ", Item INT NOT NULL"
                          + ", Codigo INT NOT NULL"
                          + ", Quantidade INT NOT NULL"
                          + ", ValorItem INT NOT NULL"
                          + ", PRIMARY KEY (Numero, Item)"
                          + ", KEY Numero (Numero)"
                          + ", KEY Codigo (Codigo)" 
                          + ", CONSTRAINT NumeroIC FOREIGN KEY (Numero) REFERENCES Compra (Numero)"
                          + ", CONSTRAINT CodigoIC FOREIGN KEY (Codigo) REFERENCES Produto (Codigo)"
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
       ItemC i = (ItemC) o;
       String inst = "Insert into ItemC (Numero, Item, Codigo, ";
             inst +="Quantidade, ValorItem) values (?, ?, ?, ?, ?)";
       try {
           Connection con = DaoConexaoAB.getInstancia().getCon();
           try (PreparedStatement pS = con.prepareStatement(inst)) {
                pS.setInt(1, i.getCompra().getNumero());
                pS.setInt(2, i.getItem());
                pS.setInt(3, i.getProduto().getCodigo());
                pS.setInt(4, i.getQuantidade());
                pS.setFloat(5, i.getValorItem());
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
       ItemC i = (ItemC) o;
       String inst = "Update ItemC  set Codigo = ?, Quantidade =?, ValorItem = ?";
             inst += " where Numero = ? And Item = ?";   
       try{
          Connection con = DaoConexaoAB.getInstancia().getCon();
          try (PreparedStatement pS = con.prepareStatement(inst)){
               pS.setInt(1, i.getProduto().getCodigo());
               pS.setInt(2, i.getQuantidade());
               pS.setFloat(3, i.getValorItem());
               pS.setInt(4, i.getCompra().getNumero());
               pS.setInt(5, i.getItem());
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
        ItemC i = (ItemC) o;
        String inst = "Delete from Item where Numero = ? And Item = ?"; 
        try {
            Connection con = DaoConexaoAB.getInstancia().getCon();
            try (PreparedStatement pS = con.prepareStatement(inst)) {
                pS.setInt(1, i.getCompra().getNumero());
                pS.setInt(2, i.getItem());
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
    public Object busca(int numero, int item) {
        String inst = "Select * from Item where numero = ? And item = ?";
        ItemC i = null;
        ResultSet rS;
        try{
             Connection con = DaoConexaoAB.getInstancia().getCon();
             try (PreparedStatement pS = con.prepareStatement(inst)) {
                   pS.setInt(1, numero);
                   rS = pS.executeQuery();
                   DaoConexaoAB.getInstancia().setCon(con);    
                   if (rS.next()) {
                      i = new ItemC(); 
                      DaoCompra dV = new DaoCompra(); 
                      Compra c = (Compra) dV.busca(rS.getInt("Numero"), 0); 
                      i.setCompra(c);
                      i.setItem(rS.getInt("Item"));
                      DaoProduto dP = new DaoProduto(); 
                      Produto p = (Produto) dP.busca(rS.getInt("Codigo"), 0);
                      i.setProduto(p);
                      i.setQuantidade(rS.getInt("Quantidade"));
                      i.setValorItem(rS.getFloat("ValorItem"));
                   }
             }   
         }
         catch (SQLException e) {
                 throw new RuntimeException(e.getMessage());            
         }
         return(i);    
    }
    
    @Override
    public List<Object> carrega() {
        String inst = "Select * from Item";
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
                        o = busca(rS.getInt("Numero"), rS.getInt("Item"));
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