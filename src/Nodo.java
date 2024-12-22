public class Nodo {
    public String etiqueta;
    public Nodo izquierda;
    public Nodo centro;
    public Nodo derecha;

    public Nodo(String etiqueta) {
        this.etiqueta = etiqueta;
        this.izquierda = null;
        this.centro = null;
        this.derecha = null;
    }
}
