import javax.swing.*;
import java.awt.*;

public class ArbolGrafico extends JPanel {
    private Arbol arbol;

    public ArbolGrafico(Arbol arbol) {
        this.arbol = arbol;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        int width = getWidth();
        int height = getHeight();

        int x = width / 2;
        int y = 50;
        int deltaX = width / 4;
        int deltaY = 50;

        dibujarNodo(g2d, arbol.getRaiz(), x, y, deltaX, deltaY);
    }

    private void dibujarNodo(Graphics2D g2d, Nodo nodo, int x, int y, int deltaX, int deltaY) {
        if (nodo != null) {
            g2d.setColor(Color.BLUE);
            g2d.fillOval(x - 15, y - 15, 30, 30);
            g2d.setColor(Color.WHITE);
            g2d.drawString(nodo.etiqueta, x - 10, y + 5);

            if (nodo.izquierda != null) {
                g2d.setColor(Color.BLACK);
                g2d.drawLine(x, y, x - deltaX, y + deltaY);
                dibujarNodo(g2d, nodo.izquierda, x - deltaX, y + deltaY, deltaX / 2, deltaY);
            }

            if (nodo.centro != null) {
                g2d.setColor(Color.BLACK);
                g2d.drawLine(x, y, x, y + deltaY);
                dibujarNodo(g2d, nodo.centro, x, y + deltaY, deltaX / 2, deltaY);
            }

            if (nodo.derecha != null) {
                g2d.setColor(Color.BLACK);
                g2d.drawLine(x, y, x + deltaX, y + deltaY);
                dibujarNodo(g2d, nodo.derecha, x + deltaX, y + deltaY, deltaX / 2, deltaY);
            }
        }
    }
}
