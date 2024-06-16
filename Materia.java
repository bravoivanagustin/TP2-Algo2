package aed;

public class Materia {
    
    private int alumnos;
    private int[] docentes;

    public Materia() {
        this.alumnos = 0;
        this.docentes = new int[] {0,0,0,0};
    }

    enum CargoDocente{
        AY2,
        AY1,
        JTP,
        PROF
    }

    private void agregar_docente(CargoDocente docente) {
        CargoDocente[] c = new CargoDocente[] {CargoDocente.PROF, CargoDocente.JTP, CargoDocente.AY1, CargoDocente.AY2};
        for (int i = 0; i < 4; i++) {
            if (c[i] == docente) {this.docentes[i] ++;}
        }
    }

    private void agregar_alumno() {
        this.alumnos ++;
    }

    private int cupo() {
        int[] c = new int[] {250, 100, 20, 30};
        int[] cd = new int[4];
        for (int i = 0; i < 4; i++) {
            cd[i] = this.docentes[i] * c[i];
        }
        int min = cd[0];
        for (int i = 0; i < 4; i++) {
            if (cd[i] < min) {min = cd[i];}
        }
        return min;
    }
}
