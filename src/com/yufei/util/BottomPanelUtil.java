package com.yufei.util;

import com.yufei.model.BottomPanel;

import javax.swing.*;
import java.text.SimpleDateFormat;
import java.util.Timer;
import java.util.TimerTask;

public class BottomPanelUtil {

    private final static SimpleDateFormat df = new SimpleDateFormat("mm:ss");

    private static Timer timer;
    private static long startTime;

    public static void setTimer(BottomPanel bottomPanel) {
        long duration = bottomPanel.getDuration();
        startTime = System.currentTimeMillis();
        if (timer == null) {
            timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    long clockTime = System.currentTimeMillis() - startTime;
                    if (clockTime >= duration) {
                        clockTime = duration;
                        PopoUtil.gameOver();
                    }
                    JLabel label = bottomPanel.getTimeLabel();
                    label.setText(df.format(duration - (clockTime)));
                }
            }, 0, 100);
        }
    }

    public static void cancelTimer() {
        if (timer != null) {
            timer.cancel();
        }
        timer = null;
    }

}
