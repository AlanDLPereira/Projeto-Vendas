/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Negocio;

/**
 *
 * @author usuario
 */
public class ItemC {
   private Compra compra;
   private int item;
   private Produto produto;
   private int quantidade;
   private float valorItem;

   public Compra getCompra() {
         return compra;
   }

   public void setCompra(Compra compra) {
        this.compra = compra;
   }

   public int getItem() {
        return item;
   }

   public void setItem(int item) {
       this.item = item;
   }

   public Produto getProduto() {
       return produto;
   }

   public void setProduto(Produto produto) {
       this.produto = produto;
   }

   public int getQuantidade() {
       return quantidade;
   }

   public void setQuantidade(int quantidade) {
       this.quantidade = quantidade;
   }

   public float getValorItem() {
       return valorItem;
   } 

   public void setValorItem(float valorItem) {
       this.valorItem = valorItem;
   }
     
}
