/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

import Negocio.ItemC;
import Negocio.Pessoa;
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
public class DaoCompra implements DaoBasico{
   
   DaoBasico dIC;
   public DaoCompra() {
       String inst = "CREATE TABLE IF NOT EXISTS Compra"
                          + " (Numero INT NOT NULL"
                          + ", Inscricao INT NOT NULL"
                          + ", Data VARCHAR(10) NOT NULL"
                          + ", Pago TINYINT(1) NOT NULL"
                          + ", PRIMARY KEY (Numero)"
                          + ", KEY Inscricao (Inscricao)" 
                          + ", CONSTRAINT InscricaoC FOREIGN KEY (Inscricao) REFERENCES Pessoa (Identifica)"
                          + ") ENGINE=InnoDB DEFAULT CHARSET=latin1;";
          try {
            Connection con = DaoConexaoAB.getInstancia().getCon();
            try (PreparedStatement pS = con.prepareStatement(inst)) {
                pS.execute();
                dIC = new DaoItemC();              
            }
            DaoConexaoAB.getInstancia().setCon(con);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
    
    @Override
    public boolean incluir(Object o) {
        boolean result = true;
        Compra c = (Compra) o;
        String inst = "Insert into Compra";
               inst += " (Numero, Inscricao, Data, Pago)";
               inst += " values (?, ?, ?, ?)";
        try {
            Connection con = DaoConexaoAB.getInstancia().getCon();
            try (PreparedStatement pS = con.prepareStatement(inst)) {
                pS.setInt(1, c.getNumero());  
                pS.setInt(2, c.getFornecedor().getIdentifica());
                pS.setString(3, c.getData());
                pS.setBoolean(4, c.isPago());
                pS.execute();
               for (int ind = 0; ind < c.getTamanho(); ind++)
                   dIC.incluir(c.getItem(ind));               
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
    public boolean alterar(Object o) {
        boolean result = true;
        Compra c = (Compra) o;
        String inst = "Update Compra set Inscricao = ?, Data =?,";
               inst += " Pago = ? where Numero = ? ";   
         try{
            Connection con = DaoConexaoAB.getInstancia().getCon();
            try (PreparedStatement pS = con.prepareStatement(inst)){
                pS.setInt(1, c.getFornecedor().getIdentifica());
                pS.setString(2, c.getData());
                pS.setBoolean(3, c.isPago());
                pS.setInt(4, c.getNumero());
                pS.execute();
                for (int ind = 0; ind < c.getTamanho(); ind++)
                   dIC.alterar(c.getItem(ind));
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
        Compra c = (Compra) o;
        String inst = "Delete from Compra where Numero = ? "; 
        try {
            Connection con = DaoConexaoAB.getInstancia().getCon();
            try (PreparedStatement pS = con.prepareStatement(inst)) {
                   for (int ind = 0; ind < c.getTamanho(); ind++)
                       dIC.excluir(c.getItem(ind));    
                   pS.setInt(1, c.getNumero());
                   pS.execute();           
            }
            DaoConexaoAB.getInstancia().setCon(con);
        } 
        catch (SQLException e){
            result = false;
            throw new RuntimeException(e);
        }       
        return (result);
    }

    @Override
    public Object busca(int numero, int nada) {
        String inst = "Select * from Compra where numero = ?";
        Compra c = null;
        ResultSet rS;
           
        try {
            Connection con = DaoConexaoAB.getInstancia().getCon();
            try (PreparedStatement pS = con.prepareStatement(inst)) {
               pS.setInt(1, numero);
               rS = pS.executeQuery();
               DaoConexaoAB.getInstancia().setCon(con);    
               if (rS.next()) {
                  c = new Compra(); 
                  c.setNumero(rS.getInt("Numero"));
                  DaoPessoa dP = new DaoPessoa(); 
                  Pessoa p = (Pessoa) dP.busca(rS.getInt("Inscricao"), 0);
                  c.setFornecedor(p);
                  c.setData(rS.getString("Data"));
                  c.setPago(rS.getBoolean("Pago"));
                  List<Object> listaI;
                  listaI = dIC.carrega();
                  for (Object o : listaI) {
                      ItemC i  = (ItemC) o;    
                      c.setItem(i);
                  }
               }
            }
            DaoConexaoAB.getInstancia().setCon(con);
        }   
        catch (SQLException e) {
                 throw new RuntimeException(e.getMessage());            
        }
        return(c);    
    }

    @Override
    public List<Object> carrega() {
        String inst = "Select * from Compra order by data desc";
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
                        o = busca(rS.getInt("Numero"), 0);
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

