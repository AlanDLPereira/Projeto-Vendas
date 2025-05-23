/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Negocio;

/**
 *
 * @author aluno
 */
public class Lote {
  private int numLote;
  private Produto produto;
  private String DataVal;

  public int getNumLote() {
        return numLote;
  }

  public void setNumLote(int numLote) {
        this.numLote = numLote;
  }
  
  public Produto getProduto() {
       return produto;
  }

  public void setProduto(Produto produto) {
       this.produto = produto;
  }

  public String getDataVal() {
       return DataVal;
  }

  public void setDataVal(String DataVal) {
       this.DataVal = DataVal;
  }

 

}
