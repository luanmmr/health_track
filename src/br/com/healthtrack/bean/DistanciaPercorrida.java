package br.com.healthtrack.bean;

/**
 * Essa interface possibilita que atividades insiram a dist�ncia percorrida. � inserido dois
 * m�todos abstract setDistancia e getDistancia.
 * @author Luan Ribeiro
 * @version 1.0
 */
public interface DistanciaPercorrida {
	
	/**
	 * Define a dist�ncia percorrida
	 * @param distancia Dist�ncia Percorrida
	 */
	void setDistancia(double distancia);
	
	/**
	 * Obt�m a dist�ncia percorrida
	 * @return A dist�ncia percorrida
	 */
	double getDistancia();
}	
