import java.util.*;

public class Arbol {
    private Nodo raiz;
    private ArrayList<Nodo> nodos;
    private int numNodos;

    public Arbol() {
        raiz = null;
        nodos = new ArrayList<>();
        numNodos = 0;
    }

    public void anadirNodo(Nodo nodo, Nodo padre, String posicion) {
        if (padre == null) {
            if (raiz == null) {
                raiz = nodo;
            } else {
                throw new IllegalArgumentException("La raíz ya existe");
            }
        } else {
            switch (posicion) {
                case "Izquierda":
                    if (padre.izquierda == null) {
                        padre.izquierda = nodo;
                    } else {
                        throw new IllegalArgumentException("Hoja Izquierda ya existe");
                    }
                    break;
                case "Centro":
                    if (padre.centro == null) {
                        padre.centro = nodo;
                    } else {
                        throw new IllegalArgumentException("Hoja Central ya existe");
                    }
                    break;
                case "Derecha":
                    if (padre.derecha == null) {
                        padre.derecha = nodo;
                    } else {
                        throw new IllegalArgumentException("Hoja Derecha ya existe");
                    }
                    break;
                default:
                    throw new IllegalArgumentException("Posición inválida");
            }
        }
        nodos.add(nodo);
    }

    public ArrayList<Nodo> getNodos() {
        return nodos;
    }

    public Nodo getRaiz() {
        return raiz;
    }

    public String getEtiquetaNodoSiguiente() {
        return String.valueOf((char) ('A' + numNodos++));
    }

    public String bfs() {
        if (raiz == null) return "";

        StringBuilder resultado = new StringBuilder();
        Queue<Nodo> queue = new LinkedList<>();
        queue.add(raiz);

        while (!queue.isEmpty()) {
            Nodo nodo = queue.poll();
            resultado.append(nodo.etiqueta).append(" ");
            if (nodo.izquierda != null) queue.add(nodo.izquierda);
            if (nodo.centro != null) queue.add(nodo.centro);
            if (nodo.derecha != null) queue.add(nodo.derecha);
        }

        return resultado.toString().trim();
    }

    public String dfs() {
        if (raiz == null) return "";

        StringBuilder resultado = new StringBuilder();
        Stack<Nodo> stack = new Stack<>();
        stack.push(raiz);

        while (!stack.isEmpty()) {
            Nodo nodo = stack.pop();
            resultado.append(nodo.etiqueta).append(" ");
            if (nodo.derecha != null) stack.push(nodo.derecha);
            if (nodo.centro != null) stack.push(nodo.centro);
            if (nodo.izquierda != null) stack.push(nodo.izquierda);
        }

        return resultado.toString().trim();
    }

    public Object[][] getMatrizAdyacencia() {
        int tam = nodos.size();
        Object[][] matriz = new Object[tam][tam];
        Map<String, Integer> etiquetaAIndice = new HashMap<>();

        for (int i = 0; i < tam; i++) {
            etiquetaAIndice.put(nodos.get(i).etiqueta, i);
            for (int j = 0; j < tam; j++) {
                matriz[i][j] = 0;
            }
        }

        for (Nodo nodo : nodos) {
            int desdeIndice = etiquetaAIndice.get(nodo.etiqueta);
            if (nodo.izquierda != null) {
                int hastaIndice = etiquetaAIndice.get(nodo.izquierda.etiqueta);
                matriz[desdeIndice][hastaIndice] = 1;
            }
            if (nodo.centro != null) {
                int hastaIndice = etiquetaAIndice.get(nodo.centro.etiqueta);
                matriz[desdeIndice][hastaIndice] = 1;
            }
            if (nodo.derecha != null) {
                int hastaIndice = etiquetaAIndice.get(nodo.derecha.etiqueta);
                matriz[desdeIndice][hastaIndice] = 1;
            }
        }

        return matriz;
    }
}
