package j2andrewnauzetbeltranperez;

public class Tablero {

    private final int largo;
    private final int ancho;
    private Casilla[][] casillas;
    private int casillasParaGanar;
    private final int minas;
    private boolean primerMovimiento;

    public Tablero(int largo, int ancho, int minas) {

        this.largo = largo;
        this.ancho = ancho;
        this.primerMovimiento = true;
        this.minas = minas;
        this.casillasParaGanar = largo * ancho - minas;
        casillas = new Casilla[largo][ancho];
        rellenarTablero();
    }
    
    public Casilla getCasilla(int largo, int ancho) {
        return casillas[largo][ancho];
    }

    public int getLargo() {
        return largo;
    }

    public int getAncho() {
        return ancho;
    }

    public int getCasillasParaGanar() {
        return casillasParaGanar;
    }
    
    public int getMinas() {
        return minas;
    }
    
    public int getNumeroBanderas(){
        int cantidad = 0;
        for (int i = 0; i < largo; i++) {
            for (int j = 0; j < ancho; j++) {
                if (casillas[i][j].getBandera()) {
                    cantidad++;
                }
            }
        }
        return cantidad;
    }

    public void ColocarMinas(int minas) {
        for (int i = 0; i < minas; i++) {

            int pos1 = (int) (Math.random() * largo);
            int pos2 = (int) (Math.random() * ancho);
            if (!casillas[pos1][pos2].getMina() && !casillas[pos1][pos2].getProtegida()) {
                casillas[pos1][pos2].setMina();
            } else {
                i--;
            }
            establecerCantidadMinasCercanas();
        }
    }
    
    private void protegerCasillasPrimerMovimiento(int pos1, int pos2){
        for (int pos1Comparar = pos1 - 1, i = 0; i < 3; pos1Comparar++, i++) {
            for (int pos2Comparar = pos2 - 1, j = 0; j < 3; pos2Comparar++, j++) {
                if (pos1Comparar > -1 && pos2Comparar > -1
                        && pos1Comparar < largo && pos2Comparar < ancho) {
                    casillas[pos1Comparar][pos2Comparar].setProtegida();
                }
            }
        }
    }
    

    private void establecerCantidadMinasCercanas() {
        for (int i = 0; i < largo; i++) {
            for (int j = 0; j < ancho; j++) {
                casillas[i][j].setMinasCercanas(contarMinasAlrededorCasilla(i, j));
            }
        }
    }

    private void rellenarTablero() {
        for (int i = 0; i < largo; i++) {
            for (int j = 0; j < ancho; j++) {
                casillas[i][j] = new Casilla();
            }
        }
    }

    
    public void contarMinasCasillas() {
        for (int i = 0; i < largo; i++) {
            for (int j = 0; j < ancho; j++) {
                contarMinasAlrededorCasilla(i, j);
            }
        }
    }

    private int contarMinasAlrededorCasilla(int pos1, int pos2) {

        int contador = 0;

        for (int pos1Comparar = pos1 - 1, i = 0; i < 3; pos1Comparar++, i++) {
            for (int pos2Comparar = pos2 - 1, j = 0; j < 3; pos2Comparar++, j++) {
                if (!(i == 1 && j == 1)) {
                    if (pos1Comparar > -1 && pos2Comparar > -1
                            && pos1Comparar < largo && pos2Comparar < ancho) {
                        if (casillas[pos1Comparar][pos2Comparar].getMina()) {
                            contador++;
                        }
                    }
                }
            }
        }
        return contador;
    }
    
    public void setBandera(int pos1, int pos2){
        casillas[pos1][pos2].setBandera();
    }

    public void comprobarCasillasCercanas(int pos1, int pos2){
        for (int pos1Comparar = pos1 - 1, i = 0; i < 3; pos1Comparar++, i++) {
            for (int pos2Comparar = pos2 - 1, j = 0; j < 3; pos2Comparar++, j++) {
                if (!(i == 1 && j == 1)) {
                    if (pos1Comparar > -1 && pos2Comparar > -1
                            && pos1Comparar < largo && pos2Comparar < ancho) {
                        if(!casillas[pos1Comparar][pos2Comparar].getComprobada()){
                            comprobarCasillas(pos1Comparar, pos2Comparar);
                        }
                    }
                }
            }
        }
    }
    
    private void primerMovimiento(int pos1, int pos2){
        protegerCasillasPrimerMovimiento(pos1, pos2);
            ColocarMinas(minas);
            contarMinasCasillas();
            primerMovimiento = false;
    }
    
    public void comprobarCasillas(int pos1, int pos2) {
        if (primerMovimiento){
            primerMovimiento(pos1, pos2);
        }
        casillas[pos1][pos2].setComprobada();
        casillasParaGanar--;
        if (casillas[pos1][pos2].getMinasCercanas() == 0){
            comprobarCasillasCercanas(pos1, pos2);
        }
    }
    
    public boolean comprobarMina(int pos1, int pos2) {
        return casillas[pos1][pos2].getMina();
    }
}
