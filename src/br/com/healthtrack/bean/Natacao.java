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
		/*if (getEstilo() == "Nado Peito") {
			setKcalPerdida(kcalPerdidaMinuto(10, getUsuario().getPeso().getPeso()));
		}else if(getEstilo() == "Nado Costas") {
			setKcalPerdida(kcalPerdidaMinuto(7, getUsuario().getPeso().getPeso()));
		}else if(getEstilo() == "Borboleta") {
			setKcalPerdida(kcalPerdidaMinuto(11, getUsuario().getPeso().getPeso()));
		}else if(getEstilo() == "Voltas") {
			if(getRitmo() == "Leve" || getRitmo() == "Moderado") {
				setKcalPerdida(kcalPerdidaMinuto(7, getUsuario().getPeso().getPeso()));
			}else {
				setKcalPerdida(kcalPerdidaMinuto(10, getUsuario().getPeso().getPeso()));
			}
		}*/
	}
	
	private double kcalPerdidaMinuto(double mets, double peso) {
		/*
		 * Fonte
		 * http://www.cdof.com.br/MET_compendium.pdf
         * 
		 * � utilizado a seguinte f�rmula
		 * (MET * 3,5) * Peso.Usuario * 5 (5 Kcal para cada litro de oxig�nio consumido)
		 * Obtenho assim o gasto cal�rico por minuto do usu�rio. Depois multiplico pelo
		 * tempo em minutos que durou a atividade f�sica, obtendo assim o total de kcal
		 * perdida.
		 * 
		 */
		
		// Divido por 1000 para obter em litros, em detrimento de ml
		return (mets * 3.5) * peso / 1000 * 5;
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
