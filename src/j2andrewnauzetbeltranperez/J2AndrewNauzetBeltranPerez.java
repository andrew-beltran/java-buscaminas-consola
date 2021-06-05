package j2andrewnauzetbeltranperez;

import java.util.Scanner;

public class J2AndrewNauzetBeltranPerez {

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        Tablero tablero = EntradaSalidaConsola.crearTablero();
        boolean perdiste = false;
        while ( !perdiste && tablero.getCasillasParaGanar() != 0) {
            for (int i = 0; i < tablero.getLargo(); i++) {
                if (i == 0) {
                    EntradaSalidaConsola.imprimirMinasRestantes(tablero.getMinas() - tablero.getNumeroBanderas());
                    EntradaSalidaConsola.imprimirNumerosHorizontal(tablero.getAncho());
                }
                EntradaSalidaConsola.imprimirNumerosVertical(i + 1);
                for (int j = 0; j < tablero.getAncho(); j++) {
                    Casilla casilla = tablero.getCasilla(i, j);
                    if (!casilla.getComprobada()) {
                        if (casilla.getBandera()) {
                            System.out.print("B  ");
                        } else {
                            EntradaSalidaConsola.imprimirCasillaNoComprobada();
                        }
                    } else {
                        EntradaSalidaConsola.imprimirCasillasComprobadas(casilla);
                    }

                }
                System.out.println();
            }
            int pos1 = sc.nextInt();
            int pos2 = sc.nextInt();

            boolean colocandoBandera = pos1 < 0;
            
            if (colocandoBandera) {
                pos1 = pos1 * - 1 - 1;
            } else {
                pos1--;
            }
            pos2--;
            if (colocandoBandera) {
                if (!tablero.getCasilla(pos1, pos2).getComprobada()) {
                    tablero.setBandera(pos1, pos2);
                }
            } else if (!tablero.getCasilla(pos1, pos2).getBandera()){
                if (tablero.comprobarMina(pos1, pos2)) {
                    perdiste = true;
                }
                tablero.comprobarCasillas(pos1, pos2);
            }
        }

        for (int i = 0; i < tablero.getLargo(); i++) {
            if (i == 0) {
                System.out.print("   ");
                EntradaSalidaConsola.imprimirNumerosHorizontal(tablero.getAncho());
            }
            EntradaSalidaConsola.imprimirNumerosVertical(i + 1);
            for (int j = 0; j < tablero.getAncho(); j++) {
                Casilla casilla = tablero.getCasilla(i, j);
                if (casilla.getMina()) {
                    System.out.print("*  ");
                } else if (!casilla.getComprobada()) {
                    EntradaSalidaConsola.imprimirCasillaNoComprobada();
                } else {
                    EntradaSalidaConsola.imprimirCasillasComprobadas(casilla);
                }
            }
            System.out.println();
        }
        if (perdiste) {
            System.out.println("Perdiste");
        } else {
            System.out.println("Ganaste LUL ");
        }
    }
}
