package aed;

public class SistemaSIU {

    /*InvRep de SistemaSIU:

        1) Cada nodo del trie alumnos tiene que tener valor >= 0.
        2) El valor del ultimo nodo de un estudiante es igual a la cantidad de veces que ese estudiante aparece en los Trie<Materia> del Trie carreras.
    */

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
                    this.carreras.obtener(infoMaterias[j].getParesCarreraMateria()[n].getCarrera()).valor().insertar(infoMaterias[j].getParesCarreraMateria()[n].getNombreMateria(), materia);
                }
            }
        }
    }

    public void inscribir(String estudiante, String carrera, String materia){
        this.carreras.obtener(carrera).valor().obtener(materia).valor().lista_alumnos().agregar_alumno(estudiante);
        this.alumnos.obtener(estudiante).valor ++;
    }

    public int inscriptos(String materia, String carrera){
        return this.carreras.obtener(carrera).valor().obtener(materia).valor().lista_alumnos().longitud();
    }

    public void agregarDocente(CargoDocente cargo, String carrera, String materia){
        this.carreras.obtener(carrera).valor().obtener(materia).valor().agregar_docente(cargo);
    }

    public int[] plantelDocente(String materia, String carrera){
        return this.carreras.obtener(carrera).valor().obtener(materia).valor().plantel_docente();	    
    }

    public boolean excedeCupo(String materia, String carrera){
        return this.carreras.obtener(carrera).valor().obtener(materia).valor().excede_cupo();	    
    }

    public String[] carreras(){
        return this.carreras.inOrder();	    
    }

    public String[] materias(String carrera){
        return this.carreras.obtener(carrera).valor().inOrder();	    
    }

    public int materiasInscriptas(String estudiante){
        return this.alumnos.obtener(estudiante).valor();	    
    }

    public void cerrarMateria(String materia, String carrera){

        Materia materia_eliminar = this.carreras.obtener(carrera).valor().obtener(materia).valor();
        for (int i = 0; i < materia_eliminar.lista_alumnos().longitud(); i ++) {
            this.alumnos.obtener(materia_eliminar.lista_alumnos().libretas().get(i)).valor --;
        }

        
    }
}
