package aed;

public class Trie {

    private class NodoTrie {
        
        private char valor;
        private NodoTrie[] hijos;
        private boolean fin;

        public NodoTrie(char c) {
            this.valor = c;
            this.hijos = new NodoTrie[256];
            this.fin = false;
        }
    }

    private NodoTrie raiz;

    public Trie(){
        raiz = new NodoTrie('\0');
    }

    public void insertar(String palabra){
        NodoTrie actual = this.raiz;
        for (int i = 0; i < palabra.length(); i++) {
            char c = palabra.charAt(i);
            if (actual.hijos[c - 'a'] == null) {actual.hijos[c - 'a'] = new NodoTrie(c);}
            actual = actual.hijos[c - 'a'];
        }
        actual.fin = true;
    }

    public boolean buscar(String palabra) {
        NodoTrie nodo = obtener(palabra);
        return nodo != null && nodo.fin;
    }

    public boolean esPrefijo(String palabra) {
        NodoTrie nodo = obtener(palabra);
        return nodo != null;
    }

    private NodoTrie obtener(String palabra) {
        NodoTrie actual = this.raiz;
        for (int i = 0; i < palabra.length(); i++) {
            char c = palabra.charAt(i);
            if (actual.hijos[c - 'a'] == null) {return null;}
            actual = actual.hijos[c - 'a'];
        }
        return actual;
    }

}
