package es.ies;

/**
 * Clase Baraja. Un objeto de esta clase representa una baraja en particular
 * 
 * @author Flor
 * @version 1.0
 */
public class Baraja {
	// ATRIBUTOS
	private Carta cartas[];
	private int posSiguienteCarta;

	// Cartas
	public static final int NUM_CARTAS = 40;

	// CONSTRUCTOR
	public Baraja() {
		this.cartas = new Carta[NUM_CARTAS];
		this.posSiguienteCarta = 0;
		crearBaraja(); // Creamos la baraja
		barajar(); // barajamos la baraja
	}

	// GETTERS Y SETTERS
	public Carta[] getCartas() {
		return cartas;
	}

	public void setCartas(Carta[] cartas) {
		this.cartas = cartas;
	}

	public int getPosSiguienteCarta() {
		return posSiguienteCarta;
	}

	public void setPosSiguienteCarta(int posSiguienteCarta) {
		this.posSiguienteCarta = posSiguienteCarta;
	}

	// METODOS
	/**
	 * Metodo crearBaraja. Crea la baraja ordenada
	 */
	private void crearBaraja() {
		// Atributos
		String[] palos = Carta.PALOS;

		// Recorremos los palos
		for (int i = 0; i < palos.length; i++) {

			// Recorremos los numeros
			for (int j = 0; j < Carta.LIMITE_CARTA_PALO; j++) {
				// Las posiciones del 8 y del 9 son el 7 y el 8 (emepzamos en 8)
				if (!(j == 7 || j == 8)) {
					if (j >= 9) {
						// Solo para la sota, caballo y rey
						cartas[((i * (Carta.LIMITE_CARTA_PALO - 2)) + (j - 2))] = new Carta(j + 1, palos[i]);
					} else {
						// Para los casos de 1 a 7
						cartas[((i * (Carta.LIMITE_CARTA_PALO - 2)) + j)] = new Carta(j + 1, palos[i]);
					}

				}
			}

		}

	}

	/**
	 * Metodo bajarar. Baraja todas las cartas
	 */
	public void barajar() {

		int posAleatoria = 0;
		Carta c;

		// Recorremos las cartas
		for (int i = 0; i < cartas.length; i++) {

			posAleatoria = generaNumAleatorio(0, NUM_CARTAS - 1);

			// intercambiamos
			c = cartas[i];
			cartas[i] = cartas[posAleatoria];
			cartas[posAleatoria] = c;

		}

		// La posición vuelve al inicio
		this.posSiguienteCarta = 0;

	}

	/**
	 * Metodo siguienteCarta. Devuelve la carta donde se encuentre
	 * "posSiguienteCarta"
	 * 
	 * @return carta del arreglo
	 */
	public Carta siguienteCarta() {

		Carta c = null;

		if (posSiguienteCarta == NUM_CARTAS) {
			System.out.println("Ya no hay mas cartas, barajea de nuevo");
		} else {
			c = cartas[posSiguienteCarta++];
		}

		return c;

	}

	/**
	 * Metodo darCartas. Controla si hay mas cartas de
	 * las que se piden
	 * 
	 * @param numCartas
	 * @return devuelve un numero de cartas
	 */
	public Carta[] darCartas(int numCartas) {

		if (numCartas > NUM_CARTAS) {
			System.out.println("No se puede dar mas cartas de las que hay");
		} else if (cartasDisponible() < numCartas) {
			System.out.println("No hay suficientes cartas que mostrar");
		} else {

			Carta[] cartasDar = new Carta[numCartas];

			// recorremos el array para rellenarlo
			for (int i = 0; i < cartasDar.length; i++) {
				cartasDar[i] = siguienteCarta(); // uso el metodo anterior
			}

			// Lo devolvemos
			return cartasDar;

		}

		// yn caso de error
		return null;

	}

	/**
	 * Metodo cartasDisponible. Indica el numero de cartas que hay disponibles
	 * 
	 * @return devuelve el numero de cartas disponibles en la baraja
	 */
	public int cartasDisponible() {
		return NUM_CARTAS - posSiguienteCarta;
	}

	/**
	 * Metodo generaNumAleatorio. 
	 * 
	 * @param minimo
	 * @param maximo
	 * @return devuelve un numero aleatorio entre dos pasados por parametro
	 */

	public static int generaNumAleatorio(int minimo, int maximo) {
		int num = (int) (Math.random() * (minimo - (maximo + 1)) + (maximo + 1));
		return num;
	}

}
