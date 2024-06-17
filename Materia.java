package aed;

public class Materia {
    
    public int alumnos;
    public int[] docentes;

    public Materia() {
        this.alumnos = 0;
        this.docentes = new int[] {0,0,0,0};
    }

    public void agregar_docente(aed.SistemaSIU.CargoDocente cargo) {
        aed.SistemaSIU.CargoDocente[] c = new aed.SistemaSIU.CargoDocente[] {aed.SistemaSIU.CargoDocente.PROF, aed.SistemaSIU.CargoDocente.JTP, aed.SistemaSIU.CargoDocente.AY1, aed.SistemaSIU.CargoDocente.AY2};
        for (int i = 0; i < 4; i++) {
            if (c[i] == cargo) {this.docentes[i] ++;}
        } 
    } // O(1)

    public void agregar_alumno() {
        this.alumnos ++;
    } // O(1)

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
        return this.alumnos > cupo;
    } // O(1)
} 
