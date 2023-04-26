package src;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JFrame;

public abstract class FLEngine extends Canvas implements Runnable {

    private Thread renderThread;
    private JFrame window;
    public Graphics g;

    // Timekeeping
    private int frameRate;
    private long startTime, previousTime, deltaTime, frame;

    public FLEngine(final Dimension windowSize, final int framerate) {
        this.frameRate = framerate;
        startTime = System.currentTimeMillis();
        previousTime = System.currentTimeMillis();

        setBackground(Color.BLACK);

        window = new JFrame();
        window.getContentPane().add(this);
        window.setLocationRelativeTo(null);
        window.setSize(windowSize.width, windowSize.height);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);
        
        renderThread = new Thread(this);
        renderThread.start();
        start();
    }

    @Override
    public void run() {
        while (true) {
            do {
                deltaTime = System.currentTimeMillis() - previousTime;
            } while ((System.currentTimeMillis() - startTime) * frameRate / 1000 < frame);
            previousTime = System.currentTimeMillis();
            frame++;

            update(g);
            repaint();
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        this.g = g;
    }

    public double deltaTime() {
        return deltaTime;
    }

    public abstract void start();

    public abstract void update(Graphics g);
}