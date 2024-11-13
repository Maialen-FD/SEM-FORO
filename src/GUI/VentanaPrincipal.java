/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package GUI;

import HILOS.HiloAuto;
import HILOS.HiloSemaforo;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class VentanaPrincipal extends JFrame {

    private List<PanelSemaforo> semaforos;
    private PanelCarretera panelCarretera;
    private List<JLabel> listaAutos;
    private int cantidadAutos;
    private int autosEnMovimiento;

    public VentanaPrincipal(int cantidadAutos) {
        this.cantidadAutos = cantidadAutos;
        this.autosEnMovimiento = cantidadAutos;

        setTitle("Simulación de Intersección de Semáforos");
        setSize(800, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(null);

        panelCarretera = new PanelCarretera();
        panelCarretera.setBounds(0, 0, getWidth(), getHeight());
        add(panelCarretera);

        semaforos = new ArrayList<>();
        for (int i = 0; i < 2; i++) {  // Dos semáforos horizontales y dos verticales
            HiloSemaforo hiloSemaforo = new HiloSemaforo();
            PanelSemaforo panelSemaforo = new PanelSemaforo(hiloSemaforo);
            semaforos.add(panelSemaforo);
            new Thread(hiloSemaforo).start();
        }

        // Posicionamos los semáforos en cada intersección
        semaforos.get(0).setBounds(getWidth() - 70, getHeight() / 2 - 75, 50, 150); // Semáforo horizontal derecho
        semaforos.get(1).setBounds(getWidth() / 8 - 80, getHeight() - 790, 50, 150); // Semáforo vertical inferior
        add(semaforos.get(0));
        add(semaforos.get(1));

        // Inicializa y coloca autos en los carriles horizontales y verticales
        listaAutos = new ArrayList<>();
        for (int i = 0; i < cantidadAutos; i++) {
            JLabel auto = new JLabel(new ImageIcon("src/car.png"));
            auto.setSize(20, 10);

            if (i % 4 == 0) {
                auto.setLocation(50 + (i * 20), getHeight() / 4);
                new Thread(new HiloAuto(auto, panelCarretera, semaforos, 0)).start(); // Mover de izquierda a derecha
            } else if (i % 4 == 1) {
                auto.setLocation(50 + (i * 20), (3 * getHeight()) / 4);
                new Thread(new HiloAuto(auto, panelCarretera, semaforos, 1)).start(); // Mover de derecha a izquierda
            } else if (i % 4 == 2) {
                auto.setLocation(200 + getWidth() / 2, 40 + (i * 20));
                new Thread(new HiloAuto(auto, panelCarretera, semaforos, 2)).start(); // Mover de arriba a abajo
            } else {
                auto.setLocation(getWidth() / 4, getHeight() - 100 - (i * 20));
                new Thread(new HiloAuto(auto, panelCarretera, semaforos, 3)).start(); // Mover de abajo a arriba
            }

            listaAutos.add(auto);
            panelCarretera.add(auto);
        }

        // Revisar cada 100ms si todos los autos han pasado
        new Thread(() -> {
            while (autosEnMovimiento > 0) {
                int autosPasados = 0;
                for (JLabel auto : listaAutos) {
                    if (auto.getX() > panelCarretera.getWidth() || auto.getX() < 0
                            || auto.getY() > panelCarretera.getHeight() || auto.getY() < 0) {
                        autosPasados++;
                    }
                }

                if (autosPasados == cantidadAutos) {
                    System.exit(0);
                }

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
