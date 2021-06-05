package j2andrewnauzetbeltranperez;

import java.util.Scanner;

public class EntradaSalidaConsola {

    static Scanner sc = new Scanner(System.in);

    public static int pedirNumeroTeclado() {
        int numero = sc.nextInt();
        sc.nextLine();
        return numero;
    }

    public static void textoElegirDificultad() {
        System.out.println(" 1- Fácil"
                + "\n 2- Normal"
                + "\n 3- Dificil"
                + "\n 4- Personalizado"
                + "\n 5- Como jugar");
    }

    public static void textoComoJugar() {
        System.out.println("Dificultades: "
                + "\n  Fácil: cuadrícula de 8 x 10 con 10 minas."
                + "\n  Normal: cuadrícula de 14 x 18 con 40 minas."
                + "\n  Dificil: cuadrícula de 20 x 24 con 99 minas."
                + "\n"
                + "\nControles:"
                + "\n  Comprobar casilla: introduce 2 coordenadas con valor"
                + "\n    positivo separadas por espacio."
                + "\n      Ejemplo: \"2 4\""
                + "\n  Colocar Bandera: para marcar o desmarcar una casilla"
                + "\n    donde creas que se hubica una mina introduce 2 "
                + "\n    coordenadas e introducir la primera en valor negativo."
                + "\n      Ejemplo: \"-2 4\""
                + "\n"
                + "\n Pulsa ENTER para continuar");
    }

    public static void pausaConsola() {
        String pausa = sc.nextLine();
    }
    
    public static Tablero crearTablero() {
        System.out.println("Seleccionar dificultad:");
        
        int alto = 0, ancho = 0, minas = 0;
        boolean dificultadSeleccionadaCorrectamente = false;
        while (!dificultadSeleccionadaCorrectamente) {
            EntradaSalidaConsola.textoElegirDificultad();
            switch (EntradaSalidaConsola.pedirNumeroTeclado()) {
                case 1:
                    alto = 8;
                    ancho = 10;
                    minas = 10;
                    dificultadSeleccionadaCorrectamente = true;
                    break;
                case 2:
                    alto = 14;
                    ancho = 18;
                    minas = 40;
                    dificultadSeleccionadaCorrectamente = true;
                    break;
                case 3:
                    alto = 20;
                    ancho = 24;
                    minas = 99;
                    dificultadSeleccionadaCorrectamente = true;
                    break;
                case 4:
                    System.out.println("Introduce tamaño y cantidad de minas separado por espacio");
                    alto = sc.nextInt();
                    ancho = sc.nextInt();
                    minas = sc.nextInt();
                    dificultadSeleccionadaCorrectamente = true;
                    break;
                case 5:
                    EntradaSalidaConsola.textoComoJugar();
                    EntradaSalidaConsola.pausaConsola();
                    break;
                default:
                    System.out.println("Introduce una opción válida");
            }
        }
        
        Tablero tablero = new Tablero(alto, ancho, minas);
        return tablero;
    }

    public static void imprimirMinasRestantes(int minasRestantes) {
        if (minasRestantes > 9) {
            System.out.print("M" + minasRestantes);
        } else {
            System.out.print("M" + minasRestantes + " ");
        }
    }

    public static void imprimirNumerosHorizontal(int ancho) {
        System.out.print(" ");
        for (int h = 1; h < ancho + 1; h++) {
            if (h < 10) {
                System.out.print(h + "  ");
            } else {
                System.out.print(h + " ");
            }
            if (h == ancho) {
                System.out.println();
            }
        }
    }

    public static void imprimirNumerosVertical(int i) {
        if (i < 10) {
            System.out.print(" " + i + "| ");
        } else {
            System.out.print(i + "| ");
        }
    }
    
    public static void imprimirCasillaNoComprobada() {
        System.out.print("o  ");
    }
    
    public static void imprimirCasillasComprobadas(Casilla casilla) {
        if (casilla.getMinasCercanas() == 0) {
            System.out.print("-  ");
        } else {
            System.out.print(casilla.getMinasCercanas() + "  ");
        }
    }
}
