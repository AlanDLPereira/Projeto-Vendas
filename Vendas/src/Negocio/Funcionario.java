/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Negocio;

/**
 *
 * @author aluno
 */
public class Funcionario extends Pessoa{
  private String cartTrab;
  private String cpf;
  private float salario;

  public String getCartTrab() {
      return cartTrab;
  }

  public void setCartTrab(String cartTrab) {
      this.cartTrab = cartTrab;
  }

  public String getCpf() {
      return cpf;
  }

  public void setCpf(String cpf) {
      this.cpf = cpf;
  }

  

  

   

  public float getSalario() {
     return salario;
  }

  public void setSalario(float salario){
     this.salario = salario;
  }

}
