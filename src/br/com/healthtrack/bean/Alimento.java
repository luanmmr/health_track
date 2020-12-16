package br.com.healthtrack.bean;

import java.io.Serializable;

/**
 * A classe Alimento representa os alimentos do mundo real que são ingeridos.
 * Além disso, a classe oferece atributos que representa  os valores nutricionais do alimento
 * e métodos no qual é possível obter e alterar cada valor nutricional.
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
	 * Construtor no qual é definido nome do alimento, 
	 * medida do alimento(xícara, colher, kg, unidade), 
	 * valor da medida(1 xícara, 2 colheres, 20 kg, 3 unidades) e total de kcal, levando em
	 * consideração a medida e valor dela.
	 * @param nome Nome do Alimento
	 * @param medida Medida do Alimento(xícara, colher, kg, unidade)
	 * @param valorMedida Valor da Medida(1 xícara, 2 colheres, 20 kg, 3 unidades)
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
	 * Construtor com bloco de instruções vazio e sem parâmetros
	 */
	public Alimento() {
		
	}
	
	/**
	 * Obtém o código do alimento
	 * @return Um int que representa o código do alimento
	 */
	public int getCodigo() {
		return codigo;
	}
	
	/**
	 * Define o código do alimento
	 * @param codigo Código do Alimento
	 */
	private void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	
	/**
	 * Obtém o nome do alimento
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
	 * Obtém o grupo do alimento
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
	 * Obtém a medida do alimento
	 * @return Uma string que representa a medida do alimento(xícara, sopa, colher, unidade, kg etc...)
	 */
	public Medida getMedida() {
		return medida;
	}
	
	/**
	 * Define a medida do alimento
	 * @param medida Medida do Alimento(xícara, sopa, colher, unidade, kg etc...)
	 */
	public void setMedida(Medida medida) {
		this.medida = medida;
	}
	
	/**
	 * Obtém o total de kcal do alimento
	 * @return O total de kcal levando em consideração a medida e valor dela.
	 */
	public double getKcal() {
		return kcal;
	}
	
	/**
	 * Define o total de kcal do alimento. Deve se levado em consideração a medida e valor dela.
	 * @param kcal Total de Kcal
	 */
	public void setKcal(double kcal) {
		this.kcal = kcal;
	}
	
	/**
	 * Obtém o total de carboidrato do alimento
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
	 * Obtém o valor da medida
	 * @return O valor da medida(1 xícara, 2 colheres, 20 kg, 3 unidades)
	 */
	public double getValorMedida() {
		return valorMedida;
	}
	
	/**
	 * Obtém a gordura total do alimento
	 * @return A gordura total
	 */
	public double getGorduraTotal() {
		return gorduraTotal;
	}
	
	/**
	 * Obtém o total de proteína do alimento
	 * @return O total de proteína
	 */
	public double getProteina() {
		return proteina;
	}
	
	/**
	 * Obtém o total de gordura saturada do alimento
	 * @return O total de gordura saturada
	 */
	public double getGorduraSaturada() {
		return gorduraSaturada;
	}
	
	/**
	 * Obtém o total de gordura trans do alimento
	 * @return O total de gordura trans
	 */
	public double getGorduraTrans() {
		return gorduraTrans;
	}
	
	/**
	 * Obtém o total de colesterol do alimento
	 * @return O total de colesterol
	 */
	public double getColesterol() {
		return colesterol;
	}
	
	/**
	 * Obtém o total de sódio do alimento
	 * @return O total de sódio
	 */
	public double getSodio() {
		return sodio;
	}
	
	/**
	 * Obtém o total de potássio do alimento
	 * @return O total de potássio
	 */
	public double getPotassio() {
		return potassio;
	}
	
	/**
	 * Obtém o total de fibra dietética do alimento
	 * @return O total de fibra dietética
	 */
	public double getFibraDietetica() {
		return fibraDietetica;
	}
	
	/**
	 * Obtém o total de açucares do alimento
	 * @return O total de açucares
	 */
	public double getAcucares() {
		return acucares;
	}
	
	/**
	 * Obtém o total de vitamina a do alimento
	 * @return O total de vitamina a
	 */
	public double getVitaminaA() {
		return vitaminaA;
	}
	
	/**
	 * Obtém o total de vitamina c do alimento
	 * @return O total de vitamina c
	 */
	public double getVitaminaC() {
		return vitaminaC;
	}
	
	/**
	 * Obtém o total de cálcio do alimento
	 * @return O total de cálcio
	 */
	public double getCalcio() {
		return calcio;
	}
	
	/**
	 * Obtém o total de ferro do alimento
	 * @return O total de ferro
	 */
	public double getFerro() {
		return ferro;
	}
	
	/**
	 * Define o valor da medida do alimento
	 * @param valorMedida Valor da Medida(1 xícara, 2 colheres, 20 kg, 3 unidades)
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
	 * Define o total de proteína do alimento
	 * @param proteina Quantidade de Proteína
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
	 * Define o total de sódio do alimento
	 * @param sodio Quantidade de Sódio
	 */
	public void setSodio(double sodio) {
		this.sodio = sodio;
	}
	
	/**
	 * Define o total de potássio do alimento
	 * @param potassio Quantidade de Potássio
	 */
	public void setPotassio(double potassio) {
		this.potassio = potassio;
	}
	
	/**
	 * Define o total de fibra dietética do alimento
	 * @param fibraDietetica Quantidade de Fibra Dietética
	 */
	public void setFibraDietetica(double fibraDietetica) {
		this.fibraDietetica = fibraDietetica;
	}
	
	/**
	 * Define o total de açucares do alimento
	 * @param acucares Quantidade de Açucares
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
	 * Define o total de cálcio do alimento
	 * @param calcio Quantidade de Cálcio
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
	 * @return Retorna uma representação em string do objeto
	 */
	@Override
	public String toString() {
		return String.format("----------------------\n"
				+ "Alimento: %s\n"
				+ "Código: %d\n"
				+ "Grupo do Alimento: %s\n"
				+ "Medida: %.1f %s\n"
				+ "Kcal: %.1f\n"
				+ "Carboidrato: %.1f\n"
				+ "Gordura Total: %.1f\n"
				+ "Proteína: %.1f\n"
				+ "Gordura Saturada: %.1f\n"
				+ "Gordura Trans:  %.1f\n"
				+ "Colesterol: %.1f\n"
				+ "Sódio: %.1f\n"
				+ "Potássio: %.1f\n"
				+ "Fibra Dietética: %.1f\n"
				+ "Açucares: %.1f\n"
				+ "Vitamina A: %.1f\n"
				+ "Vitamina C: %.1f\n"
				+ "Cálcio: %.1f\n"
				+ "Ferro: %.1f\n"
				+ "----------------------", getNome(), getCodigo(), getGrupoAlimento(), 
				getValorMedida(), getMedida(), getKcal(), getCarboidrato(), getGorduraTotal(), 
				getProteina(), getGorduraSaturada(), getGorduraTrans(), getColesterol(),
				getSodio(), getPotassio(), getFibraDietetica(), getAcucares(),
				getVitaminaA(), getVitaminaC(), getCalcio(), getFerro());
	}
}
