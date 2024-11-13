/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import HILOS.HiloSemaforo;
import javax.swing.*;
import java.awt.*;

public class PanelSemaforo extends JPanel {

    private HiloSemaforo hiloSemaforo;
    private JLabel luzRoja, luzAmarilla, luzVerde;

    public PanelSemaforo(HiloSemaforo hiloSemaforo) {
        this.hiloSemaforo = hiloSemaforo;
        setLayout(new GridLayout(3, 1));

        luzRoja = new JLabel(new ImageIcon("src/R.png"));
        luzAmarilla = new JLabel(new ImageIcon("src/A.png"));
        luzVerde = new JLabel(new ImageIcon("src/V.png"));

        add(luzRoja);
        add(luzAmarilla);
        add(luzVerde);

        actualizarSemaforo();
    }

    public void actualizarSemaforo() {
        new Thread(() -> {
            while (true) {
                switch (hiloSemaforo.getEstado()) {
                    case "rojo":
                        luzRoja.setVisible(true);
                        luzAmarilla.setVisible(false);
                        luzVerde.setVisible(false);
                        break;
                    case "amarillo":
                        luzRoja.setVisible(false);
                        luzAmarilla.setVisible(true);
                        luzVerde.setVisible(false);
                        break;
                    case "verde":
                        luzRoja.setVisible(false);
                        luzAmarilla.setVisible(false);
                        luzVerde.setVisible(true);
                        break;
                }
                repaint();
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public HiloSemaforo getHiloSemaforo() {
        return hiloSemaforo;
    }
}
