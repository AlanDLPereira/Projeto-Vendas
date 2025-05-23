/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Negocio;

/**
 *
 * @author 
 */
public class Produto {
   private int codigo;
   private String descricao;
   private String grupo;
   private float precoUnit;
   private boolean perecivel;

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
    public String getGrupo() {
		return grupo;
	}

	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}

	public float getPrecoUnit() {
        return precoUnit;
    }

    public void setPrecoUnit(float precoUnit) {
        this.precoUnit = precoUnit;
    }

    public boolean isPerecivel() {
        return perecivel;
    }

    public void setPerecivel(boolean perecivel) {
        this.perecivel = perecivel;
    }
  
  
}
