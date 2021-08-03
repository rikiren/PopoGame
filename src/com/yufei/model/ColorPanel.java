package com.yufei.model;

import javax.swing.*;
import java.awt.*;

public class ColorPanel extends JPanel {

    private final int row;
    private final int col;

    public ColorPanel(int row, int col) {
        this.row = row;
        this.col = col;
    }

    private Color color = Color.WHITE;

    public void setColor(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(color);
        g.fillRect(1, 1, this.getWidth() - 2, this.getHeight() - 2);
    }

}
