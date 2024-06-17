package aed;

public class SistemaSIU {

    private Trie<Integer> alumnos;
    private Trie<Trie<Materia>> carreras;

    enum CargoDocente{
        AY2,
        AY1,
        JTP,
        PROF
    }

    public SistemaSIU(InfoMateria[] infoMaterias, String[] libretasUniversitarias){

        this.alumnos = new Trie<>();
        this.carreras = new Trie<>();

        for (int i = 0; i < libretasUniversitarias.length; i ++) {
            this.alumnos.insertar(libretasUniversitarias[i], 0);
        }

        for (int j = 0; j < infoMaterias.length; j ++) {

            Materia materia = new Materia();

            for (int n = 0; n < infoMaterias[j].getParesCarreraMateria().length; n ++) {

                if (!this.carreras.buscar(infoMaterias[j].getParesCarreraMateria()[n].getCarrera())) {
                    Trie<Materia> materias = new Trie<>();
                    materias.insertar(infoMaterias[j].getParesCarreraMateria()[n].getNombreMateria(), materia);
                    this.carreras.insertar(infoMaterias[j].getParesCarreraMateria()[n].getCarrera(), materias);
                }

                else {
                    this.carreras.obtener(infoMaterias[j].getParesCarreraMateria()[n].getCarrera()).valor.insertar(infoMaterias[j].getParesCarreraMateria()[n].getNombreMateria(), materia);
                }
            }
        }
    }

    public void inscribir(String estudiante, String carrera, String materia){
        this.carreras.obtener(carrera).valor.obtener(materia).valor.alumnos ++;
        this.alumnos.obtener(estudiante).valor ++;
    }

    public int inscriptos(String materia, String carrera){
        return this.carreras.obtener(carrera).valor.obtener(materia).valor.alumnos;	    
    }

    public void agregarDocente(CargoDocente cargo, String carrera, String materia){
        this.carreras.obtener(carrera).valor.obtener(materia).valor.agregar_docente(cargo);
    }

    public int[] plantelDocente(String materia, String carrera){
        return this.carreras.obtener(carrera).valor.obtener(materia).valor.docentes;	    
    }

    public boolean excedeCupo(String materia, String carrera){
        return this.carreras.obtener(carrera).valor.obtener(materia).valor.excede_cupo();	    
    }

    public String[] carreras(){
        return this.carreras.inOrder();	    
    }

    public String[] materias(String carrera){
        return this.carreras.obtener(carrera).valor.inOrder();	    
    }

    public int materiasInscriptas(String estudiante){
        return this.alumnos.obtener(estudiante).valor;	    
    }

    public void cerrarMateria(String materia, String carrera){
        throw new UnsupportedOperationException("Método no implementado aún");	    
    }
}
