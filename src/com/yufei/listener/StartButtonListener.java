package com.yufei.listener;

import com.yufei.model.BottomPanel;
import com.yufei.model.CenterPanel;
import com.yufei.util.BottomPanelUtil;
import com.yufei.util.CenterPanelUtil;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;

public class StartButtonListener implements ActionListener {

    private static StartButtonListener instance;

    private StartButtonListener() {}

    public static StartButtonListener getInstance() {
        if (instance == null) {
            instance = new StartButtonListener();
        }
        return instance;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Reset Timer
        BottomPanel panel = BottomPanel.getInstance();
        BottomPanelUtil.setTimer(panel);
        // Reset Center Panel
        CenterPanel centerPanel = CenterPanel.getInstance();
        CenterPanelUtil.generateAgain(centerPanel.getEmptyPercent(), centerPanel.getPanelMatrix());
    }

}