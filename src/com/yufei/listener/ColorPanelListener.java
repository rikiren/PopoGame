package com.yufei.listener;

import com.yufei.model.BottomPanel;
import com.yufei.model.CenterPanel;
import com.yufei.model.ColorPanel;
import com.yufei.util.BottomPanelUtil;
import com.yufei.util.CenterPanelUtil;
import com.yufei.util.PopoUtil;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


/** A singleton class Listener class. The instance is meant to be added to a ColorPanel
 *  Describe the reactions of mouse actions to each ColorPanel
 *  Author: Yufei Ren
 */
public class ColorPanelListener implements MouseListener {

    private static ColorPanelListener instance;

    private ColorPanelListener() {}

    public static ColorPanelListener getInstance() {
        if (instance == null) {
            instance = new ColorPanelListener();
        }
        return instance;
    }

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

    @Override
    public void mouseClicked(MouseEvent e) {
        ColorPanel colorPanel = (ColorPanel) e.getComponent();
        CenterPanel centerPanel = CenterPanel.getInstance();
        if (CenterPanelUtil.checkAndUpdatePanel(colorPanel.getRow(), colorPanel.getCol(), centerPanel.getPanelMatrix())) {
            colorPanel.repaint();
            if (CenterPanelUtil.checkWin(centerPanel.getPanelMatrix())) {
                PopoUtil.gameWin();
            }
        } else {
            PopoUtil.gameOver();
        }
    }

}
