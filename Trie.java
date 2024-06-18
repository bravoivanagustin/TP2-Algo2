package aed;

import java.util.ArrayList;

public class Trie<T> { //InvRep (informal): raiz es un NodoTrie<T> o el trie es vacío (o nunca puede ser vacio?). raiz.valor = null. (Después todo lo demás debería estar en el InvRep de la clase NodoTrie<T>)

    public class NodoTrie<R> { //InvRep (informal): Cada nodo debe o tener cant_hijos > 0 o ser significado. Si un nodo tiene un hijo en la pos 1, el valor de ese nodo hijo debe ser el caracter ASCII correspondiente a la pos 1. |hijos| = 255.  0 <= cant_hijos <= 256. 
                               //                   cant_hijos = posiciones no nulas del array hijos. Todos los elementos del array hijos deben ser o null o un NodoTrie<T>. "valor" puede ser o null o R.
        
        public ArrayList<NodoTrie<R>> hijos;
        public int cant_hijos;
        public R valor;

        public NodoTrie() {
            this.hijos = new ArrayList<NodoTrie<R>>();
            for (int i = 0; i < 256; i ++) {this.hijos.add(null);}
            this.cant_hijos = 0;
            this.valor = null;
        }

        public R valor() {
            return this.valor;
        }
    }

    public NodoTrie<T> raiz;

    public Trie(){
        raiz = new NodoTrie<>();
    }

    public void insertar(String palabra, T valor){
        NodoTrie<T> actual = this.raiz;

        for (int i = 0; i < palabra.length(); i++) {
            char c = palabra.charAt(i);

            if (actual.hijos.get(c) == null) {
                NodoTrie<T> nuevo = new NodoTrie<>();
                actual.hijos.set(c, nuevo);
                actual.cant_hijos ++;
            }
            actual = actual.hijos.get(c);
        }
        actual.valor = valor;
    }

    public boolean buscar(String palabra) { 
        NodoTrie<T> nodo = obtener(palabra);
        return nodo != null && nodo.valor != null;
    }

    public boolean esPrefijo(String palabra) {
        NodoTrie<T> nodo = obtener(palabra);
        return nodo != null;
    }

    public NodoTrie<T> obtener(String palabra) {
        NodoTrie<T> actual = this.raiz;
        for (int i = 0; i < palabra.length(); i++) {
            char c = palabra.charAt(i);
            if (actual.hijos.get(c) == null) {return null;}
            actual = actual.hijos.get(c);
        }
        return actual;
    }

    public String[] inOrder() {
        NodoTrie<T> actual = this.raiz;
        ArrayList<String> res = new ArrayList<>();
        return inOrderNodo(actual, "", res);
    }

    public String[] inOrderNodo(NodoTrie<T> nodo, String s, ArrayList<String> res) {
        
        if (nodo.valor != null) {res.add(s);}

        for (int i = 0; i < 256; i ++) {
            if (nodo.hijos.get(i) != null) {
                char c = (char) i;
                inOrderNodo(nodo.hijos.get(i), s+c, res);
            }
        }

        String[] res1 = new String[res.size()];
        res1 = res.toArray(res1);
        return res1;
    }

    public void borrar(String palabra) {
        //NodoTrie<T> actual = this.raiz;
    }
}
