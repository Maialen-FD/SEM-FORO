/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import javax.swing.*;
import java.awt.*;

public class PanelCarretera extends JPanel {

    private int totalAutos;

    public PanelCarretera() {
        setBackground(Color.LIGHT_GRAY);
        setLayout(null);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Dibuja las dos calles horizontales
        g.setColor(Color.GRAY);
        g.fillRect(0, getHeight() / 4 - 20, getWidth(), 40);
        g.fillRect(0, (3 * getHeight()) / 4 - 20, getWidth(), 40);

        // Dibuja las dos calles verticales
        g.fillRect(getWidth() / 4 - 20, 0, 40, getHeight());
        g.fillRect((3 * getWidth()) / 4 - 20, 0, 40, getHeight());

        // Dibuja la intersección en el centro
        g.setColor(Color.YELLOW);
        g.fillRect(getWidth() / 2 - 10, getHeight() / 2 - 10, 20, 20);
    }

    public void setTotalAutos(int totalAutos) {
        this.totalAutos = totalAutos;
    }

    public int getTotalAutos() {
        return totalAutos;
    }
}
