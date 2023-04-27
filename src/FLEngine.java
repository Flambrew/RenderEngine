package src;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JFrame;

import src.data.Vector3;
import src.rendering.Camera;
import src.rendering.Scene;

public abstract class FLEngine extends Canvas implements Runnable {

    private Thread renderThread;
    private JFrame window;

    private Dimension windowSize;
    private Scene currentScene;
    private Camera camera;

    private int frameRate;
    private long startTime, previousTime, deltaTime, frame;

    public FLEngine(final Dimension windowSize, final int framerate) {
        this.frameRate = framerate;
        this.windowSize = windowSize;
        this.camera = new Camera(windowSize, new Vector3(), new Vector3());

        window = new JFrame();
        window.getContentPane().add(this);
        window.setLocationRelativeTo(null);
        window.setSize(windowSize.width, windowSize.height);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);
        setBackground(Color.BLACK);

        startTime = System.currentTimeMillis();
        previousTime = System.currentTimeMillis();
    
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

            update();
            repaint();
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        currentScene.paint(g);
    }

    public double deltaTime() {
        return deltaTime;
    }

    public Dimension windowSize() {
        return windowSize;
    }

    public Scene scene() {
        return currentScene;
    }

    public void setScene(Scene scene) {
        this.currentScene = scene;
    }

    public Camera camera() {
        return camera;
    }

    public abstract void start();

    public abstract void update();
}