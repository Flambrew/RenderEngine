package src.rendering;

import java.awt.Color;
import java.awt.Graphics;

import src.data.Matrix4;
import src.data.Vector3;

public class Triangle {
	public Vector3 a, b, c;

	public Triangle(Vector3 a, Vector3 b, Vector3 c) {
		this.a = a;
		this.b = b;
		this.c = c;
	}

	public void paint(Graphics g, Camera camera, Matrix4 projectionMatrix) {
        Triangle projection = new Triangle(a.applyMatrix(projectionMatrix),
                b.applyMatrix(projectionMatrix),
                c.applyMatrix(projectionMatrix));

        //g.setColor(new Color(32 * (int) (Math.random() * 8),
                //32 * (int) (Math.random() * 8),
                //32 * (int) (Math.random() * 8)));
		g.setColor(Color.WHITE);

		int[] xCoordinates = new int[] {
				(int) ((projection.a.x + 1) * camera.windowSize().width / 2),
				(int) ((projection.b.x + 1) * camera.windowSize().width / 2),
				(int) ((projection.c.x + 1) * camera.windowSize().width / 2) };
		int[] yCoordinates = new int[] {
				(int) ((projection.a.y + 1) * camera.windowSize().height / 2),
				(int) ((projection.b.y + 1) * camera.windowSize().height / 2),
				(int) ((projection.c.y + 1) * camera.windowSize().height / 2) };

        boolean fill = false;
        if (fill)
            g.fillPolygon(xCoordinates, yCoordinates, 3);
        else
            g.drawPolygon(xCoordinates, yCoordinates, 3);
	}
}
