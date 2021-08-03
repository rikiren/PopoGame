package com.yufei.util;

import com.yufei.PopoGui;
import com.yufei.listener.ColorPanelListener;
import com.yufei.model.CenterPanel;
import com.yufei.model.ColorPanel;
import com.yufei.model.Direction;

import java.awt.*;
import java.util.*;
import java.util.List;

import static com.yufei.PopoGui.COLORS;
import static com.yufei.model.Direction.*;
import static com.yufei.model.Direction.RIGHT;

public class CenterPanelUtil {

    public static void createColorPanels(CenterPanel panel, int rows, int cols, double emptyPercent) {
        panel.setLayout(new GridLayout(rows, cols));
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                ColorPanel colorPanel = new ColorPanel(i, j);
                Color color = getRandomColor(emptyPercent, COLORS);
                colorPanel.setColor(color);
                colorPanel.addMouseListener(ColorPanelListener.getInstance());
                panel.add(colorPanel);
                panel.getPanelMatrix()[i][j] = colorPanel;
            }
        }
    }

    public static Color getRandomColor(double emptyPercent, Color[] colors) {
        Random rand = new Random();
        if (rand.nextDouble() < emptyPercent) {
            return Color.WHITE;
        }
        return colors[rand.nextInt(colors.length)];
    }

    public static void generateAgain(double emptyPercent, ColorPanel[][] panelMatrix) {
        for (ColorPanel[] colorPanels : panelMatrix) {
            for (ColorPanel panel : colorPanels) {
                Color color = getRandomColor(emptyPercent, COLORS);
                panel.setColor(color);
                panel.repaint();
            }
        }
    }

    public static List<ColorPanel> getFourDirectionPanels(int row, int col, ColorPanel[][] panelMatrix) {
        List<ColorPanel> panelList = new ArrayList<>();
        panelList.add(checkOneDirection(row, col, panelMatrix, UP));
        panelList.add(checkOneDirection(row, col, panelMatrix, DOWN));
        panelList.add(checkOneDirection(row, col, panelMatrix, LEFT));
        panelList.add(checkOneDirection(row, col, panelMatrix, RIGHT));
        return panelList;
    }

    public static boolean checkAndUpdatePanel(int row, int col, ColorPanel[][] panelMatrix) {
        if (Color.WHITE.equals(panelMatrix[row][col].getColor())) {
            List<ColorPanel> panelList = getFourDirectionPanels(row, col, panelMatrix);
            if (!updatePanels(panelList)) {
                penalty(panelMatrix);
                return checkLose(panelMatrix, 3);
            }
        }
        return true;
    }

    public static void penalty(ColorPanel[][] panelMatrix) {
        Random random = new Random();
        int count = 0;
        while(count < 3) {
            int row = random.nextInt(panelMatrix.length);
            int col = random.nextInt(panelMatrix[0].length);
            ColorPanel panel = panelMatrix[row][col];
            if (panel.getColor().equals(Color.WHITE)) {
                panel.setColor(PopoGui.COLORS[random.nextInt(PopoGui.COLORS.length)]);
                panel.repaint();
                count++;
            }
        }
    }

    public static boolean checkLose(ColorPanel[][] panelMatrix, int penaltyNumber) {
        int count = 0;
        for (ColorPanel[] panels : panelMatrix) {
            for (ColorPanel panel : panels) {
                if (panel.getColor().equals(Color.WHITE)) {
                    count++;
                }
            }
        }
        return count >= penaltyNumber;
    }

    public static boolean checkWin(ColorPanel[][] panelMatrix) {
        for (int i = 0; i < panelMatrix.length; i++) {
            for (int j = 0; j < panelMatrix[0].length; j++) {
                if (Color.WHITE.equals(panelMatrix[i][j].getColor())) {
                    List<ColorPanel> panelList = getFourDirectionPanels(i, j, panelMatrix);
                    if (checkPanels(panelList)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public static boolean checkPanels(List<ColorPanel> panelList) {
        Set<Color> colors = new HashSet<>();
        for (ColorPanel panel : panelList) {
            if (panel != null) {
                Color color = panel.getColor();
                if (colors.contains(color)) {
                    return true;
                }
                colors.add(color);
            }
        }
        return false;
    }

    public static boolean updatePanels(List<ColorPanel> panelList) {
        Map<Color, ColorPanel> colorMap = new HashMap<>();
        boolean isCorrect = false;
        for (ColorPanel panel : panelList) {
            if (panel != null) {
                Color color = panel.getColor();
                if (colorMap.containsKey(color)) {
                    ColorPanel lastPanel = colorMap.get(color);
                    lastPanel.setColor(Color.WHITE);
                    panel.setColor(Color.WHITE);
                    lastPanel.repaint();
                    panel.repaint();
                    isCorrect = true;
                } else {
                    colorMap.put(color, panel);
                }
            }
        }
        return isCorrect;
    }

    // Return the first panel with non-white color in one direction
    public static ColorPanel checkOneDirection(int row, int col, ColorPanel[][] panelMatrix, Direction direction) {
        if (row < 0 || row >= panelMatrix.length || col < 0 || col >= panelMatrix[0].length) {
            return null;
        }
        Color thisColor = panelMatrix[row][col].getColor();
        if (Color.WHITE.equals(thisColor)) {
            ColorPanel panel;
            switch (direction) {
                case UP:
                    panel = checkOneDirection(row - 1, col, panelMatrix, UP);
                    break;
                case DOWN:
                    panel = checkOneDirection(row + 1, col, panelMatrix, DOWN);
                    break;
                case LEFT:
                    panel = checkOneDirection(row, col - 1, panelMatrix, LEFT);
                    break;
                case RIGHT:
                    panel = checkOneDirection(row, col + 1, panelMatrix, RIGHT);
                    break;
                default:
                    panel = null;
            }
            return panel;
        } else {
            return panelMatrix[row][col];
        }
    }

}
