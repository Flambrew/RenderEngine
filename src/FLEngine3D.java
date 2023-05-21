package src;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JFrame;

import src.data.Vector3;
import src.rendering.Camera;
import src.rendering.Scene;

public abstract class FLEngine3D extends Canvas implements Runnable {
    // initialize engine thread and window
    private Thread renderThread;
    private JFrame window;

    // initialize simulation and window dimensions, scene and camera
    private Dimension windowSize;
    private Scene currentScene;
    private Camera camera;

    // initialize frame time variables
    private int frameRate;
    private long startTime, previousTime, deltaTime, frame;

    public FLEngine3D(final Dimension windowSize, final int framerate) {
        this.frameRate = framerate;
        this.windowSize = windowSize;
        this.camera = new Camera(windowSize, new Vector3(1, 1, 2), new Vector3());

        window = new JFrame();
        window.getContentPane().add(this);
        window.setLocationRelativeTo(null);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(windowSize.width, windowSize.height);
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

            repaint();
            frameUpdate();
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        currentScene.paint(g);
    }

    public double deltaTime() {
        return deltaTime / 1000.;
    }

    public long startTime() {
        return startTime;
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

    public abstract void frameUpdate();
}