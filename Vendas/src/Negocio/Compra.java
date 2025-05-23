/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Negocio;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author usuario
 */
public class Compra {
    private int numero;
    private Pessoa fornecedor;
    private String data;
    private boolean pago;
    private final List<ItemC>item;

    public Compra() {
        item = new ArrayList<>();
    }
    
    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public Pessoa getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Pessoa fornecedor) {
        this.fornecedor = fornecedor;
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
   
    public ItemC getItem(int posi) {
        return item.get(posi);
    }

    public void setItem(ItemC item) {
        this.item.add(item);
    }
    
    public int getTamanho() {
       return item.size();
    }
     
    public float total() {
        float tot = 0;
        
       for (ItemC it : item) {
           tot += it.getQuantidade() * it.getValorItem();
       }        
        return tot;
    }
}
