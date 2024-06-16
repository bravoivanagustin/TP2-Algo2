package aed;

import java.util.ArrayList;

public class Trie<T> {

    private class NodoTrie<R> {
        
        private ArrayList<NodoTrie<R>> hijos;
        private R fin;

        public NodoTrie() {
            this.hijos = new ArrayList<NodoTrie<R>>();
            this.fin = null;
        }
    }

    private NodoTrie<T> raiz;

    public Trie(){
        raiz = new NodoTrie<>();
    }

    public void insertar(String palabra, T fin){
        NodoTrie<T> actual = this.raiz;
        for (int i = 0; i < palabra.length(); i++) {
            char c = palabra.charAt(i);
            if (actual.hijos.get(c - 'a') == null) {
                NodoTrie<T> nuevo = new NodoTrie<>();
                actual.hijos.set(c - 'a', nuevo);
            }
            actual = actual.hijos.get(c - 'a');
        }
        actual.fin = fin;
    }

    public boolean buscar(String palabra) { 
        NodoTrie<T> nodo = obtener(palabra);
        return nodo != null && nodo.fin != null;
    }

    public boolean esPrefijo(String palabra) {
        NodoTrie<T> nodo = obtener(palabra);
        return nodo != null;
    }

    private NodoTrie<T> obtener(String palabra) {
        NodoTrie<T> actual = this.raiz;
        for (int i = 0; i < palabra.length(); i++) {
            char c = palabra.charAt(i);
            if (actual.hijos.get(c - 'a') == null) {return null;}
            actual = actual.hijos.get(c - 'a');
        }
        return actual;
    }
}