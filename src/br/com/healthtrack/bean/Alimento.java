package br.com.healthtrack.bean;

import java.io.Serializable;

/**
 * A classe Alimento representa os alimentos do mundo real que s�o ingeridos.
 * Al�m disso, a classe oferece atributos que representa  os valores nutricionais do alimento
 * e m�todos no qual � poss�vel obter e alterar cada valor nutricional.
 * @author Luan Ribeiro
 * @version 1.0
 */
public class Alimento implements Serializable {
	
	private int codigo;
	private String nome;
	private GrupoAlimento grupoAlimento;
	private Medida medida;
	private double valorMedida;
	private double kcal;
	private double carboidrato;
	private double gorduraTotal;
	private double proteina;
	private double gorduraSaturada;
	private double gorduraTrans;
	private double colesterol;
	private double sodio;
	private double potassio;
	private double fibraDietetica;
	private double acucares;
	private double vitaminaA;
	private double vitaminaC;
	private double calcio;
	private double ferro;
	
	/**
	 * Construtor no qual � definido nome do alimento, 
	 * medida do alimento(x�cara, colher, kg, unidade), 
	 * valor da medida(1 x�cara, 2 colheres, 20 kg, 3 unidades) e total de kcal, levando em
	 * considera��o a medida e valor dela.
	 * @param nome Nome do Alimento
	 * @param medida Medida do Alimento(x�cara, colher, kg, unidade)
	 * @param valorMedida Valor da Medida(1 x�cara, 2 colheres, 20 kg, 3 unidades)
	 * @param totalKcal Total de Kcal
	 */
	public Alimento(int codigo, String nome, Medida medida, double valorMedida, double totalKcal,
					GrupoAlimento grupoAlimento) {
		setCodigo(codigo);
		setNome(nome);
		setMedida(medida);
		setValorMedida(valorMedida);
		setKcal(totalKcal);
		setGrupoAlimento(grupoAlimento);
	}
	
	
	public Alimento(int codigo) {
		super();
		this.codigo = codigo;
	}

	/**
	 * Construtor com bloco de instru��es vazio e sem par�metros
	 */
	public Alimento() {
		
	}
	
	/**
	 * Obt�m o c�digo do alimento
	 * @return Um int que representa o c�digo do alimento
	 */
	public int getCodigo() {
		return codigo;
	}
	
	/**
	 * Define o c�digo do alimento
	 * @param codigo C�digo do Alimento
	 */
	private void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	
	/**
	 * Obt�m o nome do alimento
	 * @return Uma string que representa o nome do alimento
	 */
	public String getNome() {
		return nome;
	}
	
	/**
	 * Define o nome do alimento
	 * @param nome Nome do alimento
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	/**
	 * Obt�m o grupo do alimento
	 * @return Uma string que representa o grupo do alimento
	 */
	public GrupoAlimento getGrupoAlimento() {
		return grupoAlimento;
	}
	
	/**
	 * Define o grupo do alimento
	 * @param grupoAlimento Grupo do Alimento
	 */
	public void setGrupoAlimento(GrupoAlimento grupoAlimento) {
		this.grupoAlimento = grupoAlimento;
	}
	
	/**
	 * Obt�m a medida do alimento
	 * @return Uma string que representa a medida do alimento(x�cara, sopa, colher, unidade, kg etc...)
	 */
	public Medida getMedida() {
		return medida;
	}
	
	/**
	 * Define a medida do alimento
	 * @param medida Medida do Alimento(x�cara, sopa, colher, unidade, kg etc...)
	 */
	public void setMedida(Medida medida) {
		this.medida = medida;
	}
	
	/**
	 * Obt�m o total de kcal do alimento
	 * @return O total de kcal levando em considera��o a medida e valor dela.
	 */
	public double getKcal() {
		return kcal;
	}
	
	/**
	 * Define o total de kcal do alimento. Deve se levado em considera��o a medida e valor dela.
	 * @param kcal Total de Kcal
	 */
	public void setKcal(double kcal) {
		this.kcal = kcal;
	}
	
	/**
	 * Obt�m o total de carboidrato do alimento
	 * @return O total carboidrato
	 */
	public double getCarboidrato() {
		return carboidrato;
	}
	
	/**
	 * Define o total de carboidrato do alimento
	 * @param carboidrato Total de Carboidrato
	 */
	public void setCarboidrato(double carboidrato) {
		this.carboidrato = carboidrato;
	}

	/**
	 * Obt�m o valor da medida
	 * @return O valor da medida(1 x�cara, 2 colheres, 20 kg, 3 unidades)
	 */
	public double getValorMedida() {
		return valorMedida;
	}
	
	/**
	 * Obt�m a gordura total do alimento
	 * @return A gordura total
	 */
	public double getGorduraTotal() {
		return gorduraTotal;
	}
	
	/**
	 * Obt�m o total de prote�na do alimento
	 * @return O total de prote�na
	 */
	public double getProteina() {
		return proteina;
	}
	
	/**
	 * Obt�m o total de gordura saturada do alimento
	 * @return O total de gordura saturada
	 */
	public double getGorduraSaturada() {
		return gorduraSaturada;
	}
	
	/**
	 * Obt�m o total de gordura trans do alimento
	 * @return O total de gordura trans
	 */
	public double getGorduraTrans() {
		return gorduraTrans;
	}
	
	/**
	 * Obt�m o total de colesterol do alimento
	 * @return O total de colesterol
	 */
	public double getColesterol() {
		return colesterol;
	}
	
	/**
	 * Obt�m o total de s�dio do alimento
	 * @return O total de s�dio
	 */
	public double getSodio() {
		return sodio;
	}
	
	/**
	 * Obt�m o total de pot�ssio do alimento
	 * @return O total de pot�ssio
	 */
	public double getPotassio() {
		return potassio;
	}
	
	/**
	 * Obt�m o total de fibra diet�tica do alimento
	 * @return O total de fibra diet�tica
	 */
	public double getFibraDietetica() {
		return fibraDietetica;
	}
	
	/**
	 * Obt�m o total de a�ucares do alimento
	 * @return O total de a�ucares
	 */
	public double getAcucares() {
		return acucares;
	}
	
	/**
	 * Obt�m o total de vitamina a do alimento
	 * @return O total de vitamina a
	 */
	public double getVitaminaA() {
		return vitaminaA;
	}
	
	/**
	 * Obt�m o total de vitamina c do alimento
	 * @return O total de vitamina c
	 */
	public double getVitaminaC() {
		return vitaminaC;
	}
	
	/**
	 * Obt�m o total de c�lcio do alimento
	 * @return O total de c�lcio
	 */
	public double getCalcio() {
		return calcio;
	}
	
	/**
	 * Obt�m o total de ferro do alimento
	 * @return O total de ferro
	 */
	public double getFerro() {
		return ferro;
	}
	
	/**
	 * Define o valor da medida do alimento
	 * @param valorMedida Valor da Medida(1 x�cara, 2 colheres, 20 kg, 3 unidades)
	 */
	public void setValorMedida(double valorMedida) {
		this.valorMedida = valorMedida;
	}
	
	/**
	 * Define a gordura total do alimento
	 * @param gorduraTotal Quantidade de Gordura Total
	 */
	public void setGorduraTotal(double gorduraTotal) {
		this.gorduraTotal = gorduraTotal;
	}

	/**
	 * Define o total de prote�na do alimento
	 * @param proteina Quantidade de Prote�na
	 */
	public void setProteina(double proteina) {
		this.proteina = proteina;
	}
	
	/**
	 * Define o total de gordura saturada do alimento
	 * @param gorduraSaturada Quantidade de Gordura Saturada
	 */
	public void setGorduraSaturada(double gorduraSaturada) {
		this.gorduraSaturada = gorduraSaturada;
	}
	
	/**
	 * Define o total de gordura trans do alimento
	 * @param gorduraTrans Quantidade de Gordura Trans
	 */
	public void setGorduraTrans(double gorduraTrans) {
		this.gorduraTrans = gorduraTrans;
	}
	
	/**
	 * Define o total de colesterol do alimento
	 * @param colesterol Quantidade de Colesterol
	 */
	public void setColesterol(double colesterol) {
		this.colesterol = colesterol;
	}
	
	/**
	 * Define o total de s�dio do alimento
	 * @param sodio Quantidade de S�dio
	 */
	public void setSodio(double sodio) {
		this.sodio = sodio;
	}
	
	/**
	 * Define o total de pot�ssio do alimento
	 * @param potassio Quantidade de Pot�ssio
	 */
	public void setPotassio(double potassio) {
		this.potassio = potassio;
	}
	
	/**
	 * Define o total de fibra diet�tica do alimento
	 * @param fibraDietetica Quantidade de Fibra Diet�tica
	 */
	public void setFibraDietetica(double fibraDietetica) {
		this.fibraDietetica = fibraDietetica;
	}
	
	/**
	 * Define o total de a�ucares do alimento
	 * @param acucares Quantidade de A�ucares
	 */
	public void setAcucares(double acucares) {
		this.acucares = acucares;
	}
	
	/**
	 * Define o total de vitamina a do alimento
	 * @param vitaminaA Quantidade de Vitamina A
	 */
	public void setVitaminaA(double vitaminaA) {
		this.vitaminaA = vitaminaA;
	}
	
	/**
	 * Define o total de vitamina c do alimento
	 * @param vitaminaC Quantidade de Vitamina C
	 */
	public void setVitaminaC(double vitaminaC) {
		this.vitaminaC = vitaminaC;
	}
	
	/**
	 * Define o total de c�lcio do alimento
	 * @param calcio Quantidade de C�lcio
	 */
	public void setCalcio(double calcio) {
		this.calcio = calcio;
	}
	
	/**
	 * Define o total de ferro do alimento
	 * @param ferro Quantidade de Ferro
	 */
	public void setFerro(double ferro) {
		this.ferro = ferro;
	}
	
	/**
	 * String formatada com todos os dados do Alimento
	 * @return Retorna uma representa��o em string do objeto
	 */
	@Override
	public String toString() {
		return String.format("----------------------\n"
				+ "Alimento: %s\n"
				+ "C�digo: %d\n"
				+ "Grupo do Alimento: %s\n"
				+ "Medida: %.1f %s\n"
				+ "Kcal: %.1f\n"
				+ "Carboidrato: %.1f\n"
				+ "Gordura Total: %.1f\n"
				+ "Prote�na: %.1f\n"
				+ "Gordura Saturada: %.1f\n"
				+ "Gordura Trans:  %.1f\n"
				+ "Colesterol: %.1f\n"
				+ "S�dio: %.1f\n"
				+ "Pot�ssio: %.1f\n"
				+ "Fibra Diet�tica: %.1f\n"
				+ "A�ucares: %.1f\n"
				+ "Vitamina A: %.1f\n"
				+ "Vitamina C: %.1f\n"
				+ "C�lcio: %.1f\n"
				+ "Ferro: %.1f\n"
				+ "----------------------", getNome(), getCodigo(), getGrupoAlimento(), 
				getValorMedida(), getMedida(), getKcal(), getCarboidrato(), getGorduraTotal(), 
				getProteina(), getGorduraSaturada(), getGorduraTrans(), getColesterol(),
				getSodio(), getPotassio(), getFibraDietetica(), getAcucares(),
				getVitaminaA(), getVitaminaC(), getCalcio(), getFerro());
	}
}
