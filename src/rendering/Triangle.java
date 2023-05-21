package src.rendering;

import java.awt.Color;
import java.awt.Graphics;

import src.FLEngine3D;
import src.data.Matrix4;
import src.data.Vector3;

public class Triangle {
	public Vector3 a, b, c;

	public Triangle(Vector3 a, Vector3 b, Vector3 c) {
		this.a = a;
		this.b = b;
		this.c = c;
	}

	public void paint(Graphics g,FLEngine3D engine , Matrix4 projectionMatrix) {

		Camera camera = engine.camera();

		Matrix4 matRotZ, matRotX;
		double fTheta = engine.timeElapsed(); 

		matRotZ = new Matrix4(
			new double[][] {
				new double[]{Math.cos(fTheta), Math.sin(fTheta), 0, 0},
				new double[]{-Math.sin(fTheta), Math.cos(fTheta), 0, 0},
				new double[]{0, 0, 1, 0},
				new double[]{0, 0, 0, 1}
			}
		);

		matRotX = new Matrix4(
			new double[][] {
				new double[]{1, 0, 0, 0},
				new double[]{0, Math.cos(fTheta * .5), Math.sin(fTheta * .5), 0},
				new double[]{0, -Math.sin(fTheta * .5), Math.cos(fTheta * .5), 0},
				new double[]{0, 0, 0, 1}
			}
		);

        Triangle projection = new Triangle(
				a.applyMatrix(matRotZ).applyMatrix(matRotX).sum(0, 0, 3).applyMatrix(projectionMatrix),
                b.applyMatrix(matRotZ).applyMatrix(matRotX).sum(0, 0, 3).applyMatrix(projectionMatrix),
                c.applyMatrix(matRotZ).applyMatrix(matRotX).sum(0, 0, 3).applyMatrix(projectionMatrix));

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
