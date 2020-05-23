package es.ies;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Clase Main CartaMasAltaApp. Representa el juego de la carta mas alta
 * 
 * @author Flor
 * @version 1.0
 *
 */
public class CartaMasAltaApp {

	public static void main(String[] args) {
		// Creamos la baraja
		Baraja b = new Baraja();

		// Variables utilizadas
		Carta carta[];
		int elegida;
		boolean repetir = false;
		int compara;
		boolean jugar;

		System.out.println("¡¡Bienvenido al juego de la Carta Más Alta!!");
		System.out.println("");

		// Bucle del juego, no se sale hasta que el jugador decida que no quiere jugar
		// mas
		do {

			// Barajamos
			System.out.println("Mezclando la baraja...");
			b.barajar();

			// Sacamos 2 cartas
			System.out.println("    _____      _____    ");
			System.out.println("   |     |    |     |    ");
			System.out.println("   |  1  |    |  2  |    ");
			System.out.println("   |     |    |     |    ");
			System.out.println("   |_____|    |_____|    ");
			System.out.println("");

			// Comprobamos que las cartas sean distintas, sino, volvemos a dar las cartas
			do {
				carta = b.darCartas(2);

				if (!carta[0].equals(carta[1])) {
					repetir = true;
				}

			} while (!repetir);

			// Pedimos el numero de carta
			elegida = pedirNumero();

			// Imprimimos las cartas por pantalla
			System.out.println("");
			System.out.println("Girando las cartas...");
			System.out.println("");

			for (int i = 0; i < carta.length; i++) {
				System.out.println("CARTA " + (i + 1) + " -> " + carta[i].toString());
			}

			System.out.println("");

			// LLamamos al método compareTo para comparar las cartas
			compara = carta[0].compareTo(carta[1]);

			// Llamamos al metodo ganador que imprime si has ganado, perdido o empatado
			ganador(compara, elegida);

			// LLamamos al metodo jugarAgain para saber si el jugador sigue jugando
			jugar = jugarAgain();

		} while (jugar);

	} // Fin del Main

	/**
	 * Metodo pedirNumero. Metodo para pedir el numero
	 * 
	 * @return devuelve un numero, el 1 o el 2, segun la eleccion del usuario
	 */
	public static int pedirNumero() {
		// Variables utilizadas
		int cartaElegida = 0;
		String cad;
		boolean correcto = false;

		// Para leer datos desde el teclado
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// Bucle para pedir la carta
		do {

			try {
				do {
					System.out.println("Elige una carta...(1/2)");
					cad = br.readLine().trim();

					try {
						cartaElegida = Integer.parseInt(cad);
						correcto = true;
					} catch (NumberFormatException nfe) {
						System.out.println("Introduce un número. \nError: " + nfe.getMessage());
						System.out.println("");
					}
				} while (!correcto);

				correcto = false;

				if (cartaElegida == 1 || cartaElegida == 2) {
					correcto = true;
				} else {
					System.out.println("Introduce el número adecuado (1/2)");
					System.out.println("");
				}

			} catch (IOException e) {
				System.out.println("Introduce el número correcto. \nError: " + e.getMessage());
				System.out.println("");
			}

		} while (!correcto); // Fin del dowhile

		return cartaElegida;

	} // Fin metodo pedirNumero

	/**
	 * Metodo ganador Muestra por pantalla un mensaje si has ganado, perdido o
	 * empatado
	 * 
	 * @param numero pasado
	 */
	public static void ganador(int numero, int elegida) {

		switch (numero) {
		case 1: // La segunda carta es mayor
			if (elegida == 1) {
				System.out.println("Lo sentimos, has perdido...");
				System.out.println("");

			} else {
				System.out.println("  _______________________  ");
				System.out.println(" |                       | ");
				System.out.println(" |  ¡¡¡ENHORABUENA!!!    | ");
				System.out.println(" |   ¡¡HAS  GANADO!!     | ");
				System.out.println(" |_______________________| ");
				System.out.println("");
			}

			break;

		case 0: // Las cartas son iguales
			System.out.println("Las cartas son iguales");
			System.out.println("¡Has empatado!");
			System.out.println("");

			break;

		case -1: // La primera carta es mayor
			if (elegida == 1) {
				System.out.println("  _______________________  ");
				System.out.println(" |                       | ");
				System.out.println(" |  ¡¡¡ENHORABUENA!!!    | ");
				System.out.println(" |   ¡¡HAS  GANADO!!     | ");
				System.out.println(" |_______________________| ");
				System.out.println("");

			} else {
				System.out.println("Lo sentimos, has perdido...");
				System.out.println("");
			}

			break;

		default:
			System.out.println("Valor inesperado: " + numero);
			System.out.println("");

		} // Fin del switch
	} // Fin del metodo ganador

	/**
	 * Metodo jugarAgain. Pregunta si queremos seguir jugando
	 * 
	 * @return true si queremos seguir, false si no queremos seguir jugando
	 */
	public static boolean jugarAgain() {
		String cad;
		boolean seguir = false, correcto = false;

		// Para leer datos desde el teclado
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		do {
			System.out.println("¿Quieres jugar otra vez? (S/N)...");

			try {
				cad = br.readLine().trim().toUpperCase();

				switch (cad) {
				case "S":
					System.out.println("");
					correcto = true;
					seguir = true;
					break;
					
				case "N":
					System.out.println("¡¡HASTA PRONTO!!");
					correcto = true;
					seguir = false;
					break;

				default:
					System.out.println("Introduce una respuesta válida...");
					
				}

			} catch (IOException e) {
				System.out.println("Error: " + e.getMessage());
			}

		} while (!correcto);

		return seguir;

	} // Fin metodo jugarAgain

}
