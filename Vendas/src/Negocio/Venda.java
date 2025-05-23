/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Negocio;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author aluno
 */
public class Venda {
   private int numero;
   private Pessoa cliente;
   private Pessoa funcionario;
   private String data;
   private boolean pago;
   private final List<ItemV>item;

  public Venda() {
      item = new ArrayList<>();
  }
  
  public int getNumero() {
        return numero;
  }

   public void setNumero(int numero) {
        this.numero = numero;
   }

    public Pessoa getCliente() {
        return cliente;
    }

    public void setCliente(Pessoa cliente) {
        this.cliente = cliente;
    }

    public Pessoa getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Pessoa funcionario) {
        this.funcionario = funcionario;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public boolean isPago() {
        return pago;
    }

    public void setPago(boolean pago) {
        this.pago = pago;
    }

    public ItemV getItem(int posi) {
        return item.get(posi);
    }

    public void setItem(ItemV item) {
        this.item.add(item);
    }
   
    public int getTamanho() {
        return item.size();
    }
    
    public float total() {
        float tot = 0;
        
       for (ItemV it : item) {
           tot += it.valorItem();
       }        
        return tot;
    }
     
}
