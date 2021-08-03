package com.yufei.util;

import com.yufei.model.BottomPanel;
import com.yufei.model.CenterPanel;

import javax.swing.*;

public class PopoUtil {

    public static void gameOver() {
        CenterPanel centerPanel = CenterPanel.getInstance();
        BottomPanelUtil.cancelTimer();
        JOptionPane.showMessageDialog(centerPanel, "Game Over!");
        CenterPanelUtil.generateAgain(centerPanel.getEmptyPercent(), centerPanel.getPanelMatrix());
        BottomPanelUtil.setTimer(BottomPanel.getInstance());
    }

    public static void gameWin() {
        CenterPanel centerPanel = CenterPanel.getInstance();
        BottomPanelUtil.cancelTimer();
        JOptionPane.showMessageDialog(centerPanel, "Yes! You Win!!");
        CenterPanelUtil.generateAgain(centerPanel.getEmptyPercent(), centerPanel.getPanelMatrix());
        BottomPanelUtil.setTimer(BottomPanel.getInstance());
    }

}
