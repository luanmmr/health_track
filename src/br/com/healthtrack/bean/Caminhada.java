package br.com.healthtrack.bean;

import java.util.Calendar;

/**
 * A classe Caminhada representa um tipo de atividade mais espec�fica do que a sua superclasse 
 * Atividade. Ela implementa a interface DistanciaPercorrida para que consiga definir a 
 * dist�ncia percorrida na caminhada.
 * @author Luan Ribeiro
 * @version 1.0
 */
public class Caminhada extends Atividade implements DistanciaPercorrida {
	
	private double distanciaPercorrida;
	
	/**
	 * Construtor no qual � definido o c�digo da atividade, a data de in�cio e fim da 
	 * atividade, usu�rio da atividade e a dist�ncia percorrida. Posteriormente � calculado 
	 * a quantidade de kcal que foi perdida.
	 * @param c�digo da atividade
	 * @param dataInicio Data/Hora do In�cio da atividade
	 * @param dataFim Data/Hora do Fim da atividade
	 * @param usr Usu�rio
	 * @param distancia Dist�ncia Percorrida
	 * @param ritmo Ritmo da Atividade(Leve, Moderado, Intenso)
	 */
	public Caminhada(int codigo, Calendar dataInicio, Calendar dataFim, Usuario usr, 
					 double distancia, RitmoAtividade ritmo) {
		super(codigo, dataInicio, dataFim, usr, ritmo);
		setDistancia(distancia);
		calcularKcalPerdida();
	}
	
	public Caminhada(int codigo, Calendar dataInicio, Calendar dataFim,
			 		 double distancia, RitmoAtividade ritmo) {
		super(codigo, dataInicio, dataFim, ritmo);
		setDistancia(distancia);
	}
	
	/**
	 * Construtor com bloco de instru��es vazio e sem par�metros
	 */
	public Caminhada() {
		
	}
	
	/**
	 * Define a dist�ncia percorrida com a caminhada
	 * @param distancia Dist�ncia Percorrida
	 */
	@Override
	public void setDistancia(double distancia) {
		this.distanciaPercorrida = distancia;
	}
	
	/**
	 * Obt�m a dist�ncia percorrida com a caminhada
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
	  * Caminhada Leve
	  * At� 4,8 km/h = MET 3.3
      * Caminhada Moderada
      * De 4,8 km/h at� 6,4 km/h = MET 5
      * Caminhada Intensa
      * De 6,4 km/h at� 8 km/h = MET 8
      * 
	  */
	  if (getRitmo().getCodigo() == 1) {
	    setKcalPerdida(kcalPerdida(3.3, getUsuario().getPeso()));
	    
	  }else if(getRitmo().getCodigo() == 2) {
		setKcalPerdida(kcalPerdida(5, getUsuario().getPeso()));
		
	  }else {
		setKcalPerdida(kcalPerdida(8, getUsuario().getPeso()));
		
	  }
	  
	}
	
	/**
	 * String formatada com todos os dados da Caminhada
	 * @return Retorna uma representa��o em string do objeto
	 */
	@Override
	public String toString() {
	  return String.format("%s\"distanciaPercorrida\": %.1f \n\n}", super.toString(), distanciaPercorrida);
	}
}
