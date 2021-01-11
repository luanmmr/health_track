package br.com.healthtrack.bean;

import java.util.Calendar;

/**
 * A classe Corrida representa um tipo de atividade mais espec�fica do que a sua superclasse 
 * Atividade. Ela implementa a interface DistanciaPercorrida para que consiga definir a 
 * dist�ncia percorrida com a corrida.
 * @author Luan Ribeiro
 * @version 1.0
 */
public class Corrida extends Atividade implements DistanciaPercorrida {
	
	private double distanciaPercorrida;
	
	/**
	 * Construtor no qual � definido o c�digo da atividade, a data de in�cio e fim da 
	 * atividade, usu�rio da atividade e a dist�ncia percorrida. Posteriormente � calculado 
	 * a quantidade de kcal que foi perdida.
	 * @param c�digo da atividade
	 * @param dataInicio Data/Hora do In�cio da atividade
	 * @param dataFim Data/Hora do Fim da atividade
	 * @param usr Usu�rio
	 * @param distancia Dist�ncia Percorrida em KM
	 * @param ritmo Ritmo da Atividade(Leve, Moderado, Intenso)
	 */
	public Corrida(int codigo, Calendar dataInicio, Calendar dataFim, Usuario usr, 
				   double distancia, RitmoAtividade ritmo) {
		super(codigo, dataInicio, dataFim, usr, ritmo);
		setDistancia(distancia);
		calcularKcalPerdida();
	}
	
	public Corrida(int codigo, Calendar dataInicio, Calendar dataFim,
	 		 	   double distancia, RitmoAtividade ritmo) {
		super(codigo, dataInicio, dataFim, ritmo);
		setDistancia(distancia);
	}
	
	/**
	 * Construtor com bloco de instru��es vazio e sem par�metros
	 */
	public Corrida() {
		
	}
	
	/**
	 * Define a dist�ncia percorrida com a corrida
	 * @param distancia Dist�ncia Percorrida
	 */
	@Override
	public void setDistancia(double distancia) {
		this.distanciaPercorrida = distancia;
	}
	
	/**
	 * Obt�m a dist�ncia percorrida com a corrida
	 * @return A dist�ncia percorrida
	 */
	@Override
	public double getDistancia() {
		return distanciaPercorrida;
	}
	
	/**
	 * Calcula a quantidade de kcal perdida se baseando no tempo, equivalente 
	 * metab�lico(MET) da atividade e peso do usu�rio
	 */
	@Override
	public void calcularKcalPerdida() {	
	  /*
	  * Fonte
	  * http://www.cdof.com.br/MET_compendium.pdf
	  * 
	  * Corrida Leve
	  * At� 10,7 km/h = MET 11
      * Corrida Moderado
      * De 10,7 km/h at� 12,8 km/h = MET 13.5
      * Corrida Intenso
      * De 12,8 km/h at� 16,1 km/h = MET 16
      *
	  */
	  if (getRitmo().getCodigo() == 1) {
	    setKcalPerdida(kcalPerdida(11, getUsuario().getPeso()));
	    
	  }else if(getRitmo().getCodigo() == 2) {
		setKcalPerdida(kcalPerdida(13.5, getUsuario().getPeso()));
		
	  }else {
		setKcalPerdida(kcalPerdida(16, getUsuario().getPeso()));
		
	  }
	}
	
	
	/**
	 * String formatada com todos os dados da Corrida
	 * @return Retorna uma representa��o em string do objeto
	 */
	@Override
	public String toString() {
		return String.format("%s\"distanciaPercorrida\": %.1f \n\n}", super.toString(), distanciaPercorrida);
	}
}
