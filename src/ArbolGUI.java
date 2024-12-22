import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ArbolGUI {
    private JPanel pGeneral;
    private JTable tbMatrizAdyacencia;
    private JButton btnMatrizAdyacencia;
    private JTextField txtNodoPadre;
    private JComboBox<String> cbIzqCentroDer;
    private JButton btnAgregarNodo;
    private JButton btnDibujarArbol;
    private JButton btnRecorridoAnchura;
    private JButton btnRecorridoProfundidad;
    private JTextArea textArea;
    private JPanel panelArbol;
    // Variable de instancia para el gráfico del árbol
    private ArbolGrafico arbolGrafico;
    private Arbol arbol = new Arbol();
    private DefaultTableModel modeloTabla = new DefaultTableModel();

    public ArbolGUI() {
        // Configuración del ComboBox
        cbIzqCentroDer.setModel(new DefaultComboBoxModel<>(new String[]{"Izquierda", "Centro", "Derecha"}));

        // Configuración de la tabla de matriz de adyacencia
        tbMatrizAdyacencia.setModel(modeloTabla);

        // Crear e inicializar el árbol y su representación gráfica
        arbol = new Arbol();
        arbolGrafico = new ArbolGrafico(arbol);

        // Agregar el panel gráfico al contenedor
        panelArbol.setLayout(new BorderLayout());
        panelArbol.add(arbolGrafico, BorderLayout.CENTER);


        // Acción para agregar nodos
        btnAgregarNodo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String etiqueta = arbol.getEtiquetaNodoSiguiente();
                    Nodo nuevoNodo = new Nodo(etiqueta);
                    String etiquetaPadre = txtNodoPadre.getText().trim();
                    String posicion = (String) cbIzqCentroDer.getSelectedItem();

                    Nodo nodoPadre = null;
                    for (Nodo nodo : arbol.getNodos()) {
                        if (nodo.etiqueta.equals(etiquetaPadre)) {
                            nodoPadre = nodo;
                            break;
                        }
                    }

                    arbol.anadirNodo(nuevoNodo, nodoPadre, posicion);
                    textArea.append("Nodo " + etiqueta + " agregado a " + etiquetaPadre + " en posición " + posicion + "\n");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
                }
            }
        });

        // Acción para mostrar el recorrido en anchura
        btnRecorridoAnchura.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String resultado = arbol.bfs();
                textArea.append("Recorrido en Anchura (BFS): " + resultado + "\n");
            }
        });

        // Acción para mostrar el recorrido en profundidad
        btnRecorridoProfundidad.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String resultado = arbol.dfs();
                textArea.append("Recorrido en Profundidad (DFS): " + resultado + "\n");
            }
        });

        // Acción para dibujar el árbol
        btnDibujarArbol.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dibujarArbolEnPanel();
            }
        });

        // Acción para mostrar la matriz de adyacencia
        btnMatrizAdyacencia.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object[][] matriz = arbol.getMatrizAdyacencia();
                String[] columnas = new String[matriz.length];
                for (int i = 0; i < matriz.length; i++) {
                    columnas[i] = String.valueOf((char) ('A' + i));
                }
                modeloTabla.setDataVector(matriz, columnas);
            }
        });
    }
    private void imprimirArbol() {
        textArea.setText("");
        textArea.append("Nodos:\n");
        for (Nodo nodo : arbol.getNodos()) {
            textArea.append(nodo.etiqueta + "\n");
        }
    }

    private void dibujarArbolEnPanel() {
        Graphics g = panelArbol.getGraphics();
        if (g != null) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.clearRect(0, 0, panelArbol.getWidth(), panelArbol.getHeight());
            arbolGrafico.paintComponent(g);
        }
    }

    private void mostrarMatrizAdyacencia() {
        Object[][] matriz = arbol.getMatrizAdyacencia();
        String[] columnas = new String[matriz.length];
        for (int i = 0; i < matriz.length; i++) {
            columnas[i] = String.valueOf((char) ('A' + i));
        }

        modeloTabla.setDataVector(matriz, columnas);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("ArbolGUI");
        frame.setContentPane(new ArbolGUI().pGeneral);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
