package aed;

import java.util.ArrayList;

public class Alumnos { //InvRep (informal): longitud = |libretas|. 

    /*InvRep de Alumnos:

        1) longitud = |libretas|

    */
    
    public ArrayList<String> libretas;
    public int longitud;

    public Alumnos() {
        this.libretas = new ArrayList<>();
        this.longitud = 0;
    }

    public int longitud() {
        return this.longitud;
    }

    public ArrayList<String> libretas() {
        return this.libretas;
    }

    public void agregar_alumno(String libreta) {
        this.libretas.add(libreta);
        this.longitud ++;
    }
}
