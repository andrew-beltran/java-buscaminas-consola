package j2andrewnauzetbeltranperez;

public class Casilla {
    private boolean mina;
    private boolean comprobada;
    private boolean bandera;
    private boolean protegida;
    private int minasCercanas;
    
    public Casilla() {
        this.mina = false;
        this.comprobada = false;
        this.bandera = false;
        this.protegida = false;
    }
    
    public boolean getMina(){
        return this.mina;
    }
    
    public boolean getComprobada(){
        return comprobada;
    }
    
    public boolean getBandera(){
        return bandera;
    }
    
    public boolean getProtegida(){
        return protegida;
    }
    
    public int getMinasCercanas(){
        return minasCercanas;
    }
    
    public void setMina(){
        mina = true;
    }
    
    public void setComprobada(){
        comprobada = true;
        if (bandera) {
            bandera = false;
        }
    }
    
    public void setBandera(){
        bandera = !bandera;
    }
    
    public void setProtegida(){
        mina = false;
        protegida = true;
    }
    
    public void setMinasCercanas(int minasCercanas){
        this.minasCercanas = minasCercanas;
    }
    
}
