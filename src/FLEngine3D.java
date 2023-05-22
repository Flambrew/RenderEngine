package src;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

import src.data.Vector3;
import src.rendering.Camera;
import src.rendering.Scene;

public abstract class FLEngine3D extends JPanel {
    private JFrame window;
    private Dimension windowSize;
    private Scene currentScene;
    private Camera camera;
    private Timer frameTimer;

    private long startTime;
    private int framerate;

    public FLEngine3D(final Dimension windowSize, final int framerate) {
        this.windowSize = windowSize;
        this.framerate = framerate;
        this.camera = new Camera(new Vector3(0, 0, -3), new Vector3(), new Vector3());

        window = new JFrame();
        window.getContentPane().add(this);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(windowSize.width, windowSize.height);
        window.setLocationRelativeTo(null);
        window.setVisible(true);
        setBackground(Color.BLACK);

        start();

        startTime = System.currentTimeMillis();
        frameTimer = new Timer(1000 / framerate, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frameUpdate();
                repaint();
            }
        }); 
        frameTimer.start();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        currentScene.paint(g);
    }

    public double timeElapsed() {
        return (startTime - System.currentTimeMillis()) / 1000.;
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

    public int framerate() {
        return framerate;
    }

    public abstract void start();

    public abstract void frameUpdate();
}