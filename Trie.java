package aed;

import java.util.ArrayList;

public class Trie<T> { 

    public class NodoTrie<R> {

        public ArrayList<NodoTrie<R>> hijos;
        public int cant_hijos;
        public int cant_palabras;
        public R valor;

        public NodoTrie() { 
            this.hijos = new ArrayList<NodoTrie<R>>();
            for (int i = 0; i < 256; i ++) {this.hijos.add(null);}
            this.cant_hijos = 0;
            this.cant_palabras = 0;
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

    public void insertar(String palabra, T valor){ // O(|palabra|)
        NodoTrie<T> actual = this.raiz;
        actual.cant_palabras ++;

        for (int i = 0; i < palabra.length(); i++) {
            char c = palabra.charAt(i);

            if (actual.hijos.get(c) == null) {
                NodoTrie<T> nuevo = new NodoTrie<>();
                actual.hijos.set(c, nuevo);
                actual.cant_hijos ++;
            }

            actual = actual.hijos.get(c);
            actual.cant_palabras ++;
        }
        actual.valor = valor;
    }

    public boolean buscar(String palabra) { // O(|palabra|)
        NodoTrie<T> nodo = obtener(palabra);
        return nodo != null && nodo.valor != null;
    }

    public boolean esPrefijo(String palabra) { // O(|palabra|)
        NodoTrie<T> nodo = obtener(palabra);
        return nodo != null;
    }

    public NodoTrie<T> obtener(String palabra) { // O(|palabra|)
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

    public String[] inOrderNodo(NodoTrie<T> nodo, String s, ArrayList<String> res) { // Complejidad de cant de pasos para llegar a caso base
        
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

    public void borrar(String palabra) { // O(|palabra|)
        NodoTrie<T> actual = this.raiz;
        int i = 0;
        while (i < palabra.length() && actual.hijos.get(palabra.charAt(i)).cant_palabras > 1) {
            actual.cant_palabras --;
            actual = actual.hijos.get(palabra.charAt(i));
            i ++;
        }
        if (i == palabra.length()) {actual.valor = null; actual.cant_palabras --;}
        else {actual.hijos.set(palabra.charAt(i), null); actual.cant_hijos --;}
    }
}