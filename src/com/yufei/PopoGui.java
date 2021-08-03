package com.yufei;

import com.yufei.model.CenterPanel;
import com.yufei.model.BottomPanel;

import javax.swing.*;
import java.awt.*;

public class PopoGui {

    private final JFrame frame = createJFrame("POPO POPO", 500, 700);
    private final JMenuBar menuBar = createMenuBar();
    private final JPanel bottomPanel = createBottomPanel();
    private final CenterPanel centerPanel = createCenterPanel();

    public final static Color[] COLORS = new Color[] {
//            new Color(255, 175, 175),
//            new Color(235, 155, 155),
//            new Color(205, 125, 125),
//            new Color(185, 105, 105),
//            new Color(165, 85, 85)
            Color.PINK,
            Color.BLUE,
            Color.GREEN,
            Color.CYAN,
            Color.YELLOW
    };

    public final static double EMPTY_PERCENT = 0.85;

    public void createGui() {
        //Adding Components to the frame.
        frame.getContentPane().add(BorderLayout.SOUTH, bottomPanel);
        frame.getContentPane().add(BorderLayout.NORTH, menuBar);
        frame.getContentPane().add(BorderLayout.CENTER, centerPanel);
        frame.setVisible(true);
    }

    //Creating the Frame
    public JFrame createJFrame(final String title, final int width, final int height) {
        JFrame frame = new JFrame(title);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(width, height);
        return frame;
    }

    //Creating the MenuBar and adding components
    public JMenuBar createMenuBar() {
        JMenuBar mb = new JMenuBar();
        JMenu m1 = new JMenu("Level");
        JMenu m2 = new JMenu("Help");
        mb.add(m1);
        mb.add(m2);
        JMenuItem m11 = new JMenuItem("Easy");
        JMenuItem m12 = new JMenuItem("Medium");
        JMenuItem m13 = new JMenuItem("Hard");
        m1.add(m11);
        m1.add(m12);
        m1.add(m13);
        return mb;
    }

    //Creating the panel at bottom and adding components
    public JPanel createBottomPanel() {
//        JPanel panel = new JPanel(); // the panel is not visible in output
//        BottomPanel bottomPanel = new BottomPanel();
//        JButton restart = new JButton("Restart");
//
//        restart.addActionListener(e -> {
//            Timer timer = bottomPanel.getTimer();
//            bottomPanel.setStartTime(-1);
//            timer.start();
////            centerPanel.refreshAll();
//            frame.repaint();
//        });
//
//        panel.add(bottomPanel); // Components Added using Flow Layout
//        panel.add(restart);
        return BottomPanel.getInstance();
    }

    //Creating the panel at center and adding components
    public CenterPanel createCenterPanel() {
        final int rows = 11;
        final int cols = 8;
        CenterPanel.initCenterPanel(rows, cols, EMPTY_PERCENT);
        return CenterPanel.getInstance();
    }

}
