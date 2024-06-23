package aed;

import java.util.ArrayList;

public class Materia { 

    /*InvRep de Materia:

    1) Todos los elementos de docentes debe ser >= 0.
    2) |docentes| = 4.
    3) |padres| = |nombres_padres|
    4) alumnos != null
    
    */
    
    public ArrayList<Trie<Materia>> padres;
    public ArrayList<String> nombres_padres;
    public Alumnos alumnos;
    public int[] docentes;

    public Materia() {
        this.alumnos = new Alumnos();
        this.docentes = new int[] {0,0,0,0};
    }

    public void agregar_docente(aed.SistemaSIU.CargoDocente cargo) {
        aed.SistemaSIU.CargoDocente[] c = new aed.SistemaSIU.CargoDocente[] {aed.SistemaSIU.CargoDocente.PROF, aed.SistemaSIU.CargoDocente.JTP, aed.SistemaSIU.CargoDocente.AY1, aed.SistemaSIU.CargoDocente.AY2};
        for (int i = 0; i < 4; i++) {
            if (c[i] == cargo) {this.docentes[i] ++;}
        } 
    } // O(1)

    public void agregar_alumno(String libretaUniversitaria) {
        this.alumnos.agregar_alumno(libretaUniversitaria);
    } // O(1)

    public Alumnos lista_alumnos() {
        return this.alumnos;
    }

    public int[] plantel_docente() {
        return this.docentes;
    }

    public int[] cupo_por_docente() { 
        int[] c = new int[] {250, 100, 20, 30};
        int[] cd = new int[4];
        for (int i = 0; i < 4; i++) {
            cd[i] = this.docentes[i] * c[i];
        }
        return cd;
    } // O(1)

    public int cupo() {
        int[] cd = this.cupo_por_docente();
        int min = cd[0];
        for (int i = 0; i < 4; i++) {
            if (cd[i] < min) {min = cd[i];}
        }
        return min;
    } // O(1)

    public boolean excede_cupo() {
        int cupo = this.cupo();
        return this.alumnos.longitud() > cupo;
    } // O(1)
} 
