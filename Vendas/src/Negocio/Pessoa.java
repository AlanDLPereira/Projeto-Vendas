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
public abstract class Pessoa {
   private int identifica;
   private String nome;
   private String endereco;
   private char definicao;
   private String telefone1;
   private String telefone2;
   private String email;
  
   public int getIdentifica() {
       return identifica;
   }

   public void setIdentifica(int identifica) {
       this.identifica = identifica;
   }

   public String getNome() {
       return nome;
   }

   public void setNome(String nome) {
       this.nome = nome;
   }

   public String getEndereco() {
       return endereco;
   }

   public void setEndereco(String endereco) {
       this.endereco = endereco;
   }

   public char getDefinicao() {
       return definicao;
   }

   public void setDefinicao(char definicao) {
       this.definicao = definicao;
   }

    public String getTelefone1() {
        return telefone1;
    }

    public void setTelefone1(String telefone1) {
        this.telefone1 = telefone1;
    }

    public String getTelefone2() {
        return telefone2;
    }

    public void setTelefone2(String telefone2) {
        this.telefone2 = telefone2;
    }

   public String getEmail() {
       return email;
   }

   public void setEmail(String email) {
       this.email = email;
   }
 
   
}
