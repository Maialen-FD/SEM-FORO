/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package HILOS;

import GUI.PanelCarretera;
import GUI.PanelSemaforo;
import javax.swing.*;
import java.util.List;

public class HiloAuto implements Runnable {

    private JLabel auto;
    private PanelCarretera panelCarretera;
    private List<PanelSemaforo> semaforos;
    private int direccion;  // 0: izquierda a derecha, 1: derecha a izquierda, 2: arriba a abajo, 3: abajo a arriba
    private static final int VELOCIDAD_NORMAL = 5;
    private static final int VELOCIDAD_LENTA = 2;

    public HiloAuto(JLabel auto, PanelCarretera panelCarretera, List<PanelSemaforo> semaforos, int direccion) {
        this.auto = auto;
        this.panelCarretera = panelCarretera;
        this.semaforos = semaforos;
        this.direccion = direccion;
    }

    @Override
    public void run() {
        while (true) {
            try {
                int velocidad = VELOCIDAD_NORMAL;
                boolean puedeAvanzar = false;
                for (PanelSemaforo semaforo : semaforos) {
                    String estado = semaforo.getHiloSemaforo().getEstado();
                    if (estado.equals("verde")) {
                        puedeAvanzar = true;
                        velocidad = VELOCIDAD_NORMAL;
                    } else if (estado.equals("amarillo")) {
                        velocidad = VELOCIDAD_LENTA;
                    }
                }

                if (puedeAvanzar || velocidad == VELOCIDAD_LENTA) {
                    if (direccion == 0) auto.setLocation(auto.getX() + velocidad, auto.getY());
                    else if (direccion == 1) auto.setLocation(auto.getX() - velocidad, auto.getY());
                    else if (direccion == 2) auto.setLocation(auto.getX(), auto.getY() + velocidad);
                    else auto.setLocation(auto.getX(), auto.getY() - velocidad);
                    Thread.sleep(50);
                } else {
                    Thread.sleep(100);
                }

                if (auto.getX() > panelCarretera.getWidth() || auto.getX() < 0
                        || auto.getY() > panelCarretera.getHeight() || auto.getY() < 0) {
                    panelCarretera.remove(auto);
                    break;
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
