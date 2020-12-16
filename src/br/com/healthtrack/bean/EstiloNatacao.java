package br.com.healthtrack.bean;

import java.io.Serializable;

public class EstiloNatacao implements Serializable {
	
	private int codigo;
	private String nomeEstilo;
	
	
	public EstiloNatacao(int codigo, String nomeEstilo) {
		super();
		this.codigo = codigo;
		this.nomeEstilo = nomeEstilo;
	}
	
	public EstiloNatacao(int codigo) {
		super();
		this.codigo = codigo;
	}

	public int getCodigo() {
		return codigo;
	}
	
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	
	public String getNomeEstilo() {
		return nomeEstilo;
	}
	
	public void setNomeEstilo(String nomeEstilo) {
		this.nomeEstilo = nomeEstilo;
	}
	
	

}
