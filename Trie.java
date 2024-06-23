package aed;

import java.util.ArrayList;

public class Trie<T> { 

    /*InvRep de Trie:

        1) t.raiz.valor = null
        2) t.raiz != null o el trie es vacio
    */
    
    public class NodoTrie<R> {
    /*InvRep de NodoTrie:

    1) cant_hijos debe estar entre 0 y 256 y debe ser igual a la cantidad de elementos no nulos en n.hijos.
    2) cant_hijos = posiciones no nulas del array hijos.
    3) |n.hijos| = 256.
    4) Si un nodo tiene un hijo en la pos 1, el valor de ese nodo hijo debe ser el caracter ASCII correspondiente.
    5) valor es siempre null menos cuando ese nodo es el fin de una palabra.
    
    */
        
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
