package com.yufei.model;

import com.yufei.util.CenterPanelUtil;

import javax.swing.*;

public class CenterPanel extends JPanel {

    private static CenterPanel instance;

    private final int rows;
    private final int cols;
    private final double emptyPercent;
    private final ColorPanel[][] panelMatrix;

    private CenterPanel(int rows, int cols, double emptyPercent) {
        this.rows = rows;
        this.cols = cols;
        this.emptyPercent = emptyPercent;
        this.panelMatrix = new ColorPanel[rows][cols];
        CenterPanelUtil.createColorPanels(this, rows, cols, emptyPercent);
    }

    public static void initCenterPanel(int rows, int cols, double emptyPercent) {
        instance = new CenterPanel(rows, cols, emptyPercent);
    }

    public static CenterPanel getInstance() {
        if (instance == null) {
            throw new NullPointerException("Center Panel has not been initialized!");
        }
        return instance;
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }

    public double getEmptyPercent() {
        return emptyPercent;
    }

    public ColorPanel[][] getPanelMatrix() {
        return panelMatrix;
    }

}
