package src;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JFrame;

import src.rendering.Scene;

public abstract class FLEngine extends Canvas implements Runnable {

    private Thread renderThread;
    private JFrame window;
    private Graphics g;

    private Dimension windowSize;
    public Scene currentScene;

    private int frameRate;
    private long startTime, previousTime, deltaTime, frame;

    public FLEngine(final Dimension windowSize, final int framerate) {
        this.frameRate = framerate;
        this.windowSize = windowSize;
        startTime = System.currentTimeMillis();
        previousTime = System.currentTimeMillis();

        setBackground(Color.BLACK);

        window = new JFrame();
        window.getContentPane().add(this);
        window.setLocationRelativeTo(null);
        window.setSize(windowSize.width, windowSize.height);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);

        start();
        
        renderThread = new Thread(this);
        renderThread.start();
    }

    @Override
    public void run() {
        while (true) {
            do {
                deltaTime = System.currentTimeMillis() - previousTime;
            } while ((System.currentTimeMillis() - startTime) * frameRate / 1000 < frame);
            previousTime = System.currentTimeMillis();
            frame++;

            update();
            repaint();
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        this.g = g;

        currentScene.paint(g);
        System.out.println("paint");
        g.drawRect(100, 100, 100, 100);
    }

    public double deltaTime() {
        return deltaTime;
    }

    public Dimension windowSize() {
        return windowSize;
    }

    public void setScene(Scene scene) {
        this.currentScene = scene;
    }

    public abstract void start();

    public abstract void update();
}