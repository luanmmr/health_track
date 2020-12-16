package br.com.healthtrack.bean;

import java.io.Serializable;

public class PeriodoRefeicao implements Serializable {
	
	private int codigo;
	private String nomePeriodo;
	
	
	public PeriodoRefeicao(int codigo, String nomePeriodo) {
		super();
		this.codigo = codigo;
		this.nomePeriodo = nomePeriodo;
	}
	
	public PeriodoRefeicao(int codigo) {
		super();
		this.codigo = codigo;
	}

	public int getCodigo() {
		return codigo;
	}
	
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	
	public String getNomePeriodo() {
		return nomePeriodo;
	}
	
	public void setNomePeriodo(String nomePeriodo) {
		this.nomePeriodo = nomePeriodo;
	}
	
	

}
