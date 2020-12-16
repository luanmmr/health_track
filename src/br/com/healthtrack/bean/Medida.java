package br.com.healthtrack.bean;

import java.io.Serializable;


public class Medida implements Serializable {
	
	private int codigo;
	private String nomeAbreviado;
	private String nomeMedida;
	
		
	public Medida(int codigo, String nomeAbreviado, String nomeMedida) {
		super();
		this.codigo = codigo;
		this.nomeAbreviado = nomeAbreviado;
		this.nomeMedida = nomeMedida;
	}
	
	public Medida(int codigo) {
		super();
		this.codigo = codigo;
	}

	public int getCodigo() {
		return codigo;
	}
	
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	
	public String getNomeAbreviado() {
		return nomeAbreviado;
	}
	
	public void setNomeAbreviado(String nomeAbreviado) {
		this.nomeAbreviado = nomeAbreviado;
	}

	public String getNomeMedida() {
		return nomeMedida;
	}

	public void setNomeMedida(String nomeMedida) {
		this.nomeMedida = nomeMedida;
	}
		
}
