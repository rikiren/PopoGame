package com.yufei.model;

import com.yufei.listener.StartButtonListener;
import com.yufei.util.BottomPanelUtil;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;

import static javax.management.timer.Timer.ONE_SECOND;

public class BottomPanel extends JPanel {

    private static BottomPanel instance;

    private final JLabel timeLabel = new JLabel();
    private final JButton button = new JButton("Restart");
    private long duration = 30 * ONE_SECOND;

    private BottomPanel() {
        this.add(timeLabel);
        this.add(button);
        BottomPanelUtil.setTimer(this);
        button.addActionListener(StartButtonListener.getInstance());
    }

    public static BottomPanel getInstance() {
        if (instance == null) {
            instance = new BottomPanel();
        }
        return instance;
    }

    public JLabel getTimeLabel() {
        return timeLabel;
    }

    public JButton getButton() {
        return button;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }
}
