package br.com.healthtrack.bean;

/**
 * Essa interface possibilita que atividades insiram a distância percorrida. É inserido dois
 * métodos abstract setDistancia e getDistancia.
 * @author Luan Ribeiro
 * @version 1.0
 */
public interface DistanciaPercorrida {
	
	/**
	 * Define a distância percorrida
	 * @param distancia Distância Percorrida
	 */
	void setDistancia(double distancia);
	
	/**
	 * Obtém a distância percorrida
	 * @return A distância percorrida
	 */
	double getDistancia();
}	
