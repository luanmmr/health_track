package br.com.healthtrack.bean;

import java.util.Calendar;

/**
 * A classe Natacao representa um tipo de atividade mais espec�fica do que a sua superclasse 
 * Atividade. Ela implementa a interface DistanciaPercorrida para que consiga definir a 
 * dist�ncia percorrida com a corrida.
 * @author Luan Ribeiro
 * @version 1.0
 */
public class Natacao extends Atividade {
	
	private EstiloNatacao estilo;
	
	/**
	 * Construtor no qual � definido o c�digo da atividade, a data de in�cio e fim da 
	 * atividade, usu�rio da atividade e a dist�ncia percorrida. Posteriormente � calculado 
	 * a quantidade de kcal que foi perdida.
	 * @param c�digo da atividade
	 * @param dataInicio Data/Hora do In�cio da atividade
	 * @param dataFim Data/Hora do Fim da atividade
	 * @param usr Usu�rio
	 * @param estilo Estilo da Nata��o
	 * @param ritmo Ritmo da Atividade(Leve, Moderado, Intenso)
	 */
	public Natacao(int codigo, Calendar dataInicio, Calendar dataFim, Usuario usr, 
				   EstiloNatacao estilo, RitmoAtividade ritmo) {
		super(codigo, dataInicio, dataFim, usr, ritmo);
		setEstilo(estilo);
		calcularKcalPerdida();
	}
	
	/**
	 * Construtor com bloco de instru��es vazio e sem par�metros
	 */
	public Natacao() {	
	}
	
	/**
	 * Obt�m o estilo da nata��o
	 * @return O estilo da nata��o
	 */
	public EstiloNatacao getEstilo() {
		return estilo;
	}
	
	/**
	 * Define o estilo da nata��o
	 * @param estilo Estilo
	 */
	public void setEstilo(EstiloNatacao estilo) {
		this.estilo = estilo;
	}
	
	/**
	 * Calcula a quantidade de kcal perdida se baseando no tempo, equivalente 
	 * metab�lico(MET) da atividade e peso do usu�rio
	 */
	@Override
	public void calcularKcalPerdida() {	
		if (getEstilo().getCodigo() == 1) {
			setKcalPerdida(kcalPerdida(11, getUsuario().getPeso()));
			
		}else if(getEstilo().getCodigo() == 2) {
			setKcalPerdida(kcalPerdida(10, getUsuario().getPeso()));
			
		}else if(getEstilo().getCodigo() == 3) {
			setKcalPerdida(kcalPerdida(7, getUsuario().getPeso()));
		}
	}
	
	/**
	 * String formatada com todos os dados da Nata��o
	 * @return Retorna uma representa��o em string do objeto
	 */
	@Override
	public String toString() {
		return String.format("%s\"estilo\": \"%s\" \n\n}", super.toString(), getEstilo());
	}

}
