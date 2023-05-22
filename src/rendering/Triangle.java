package src.rendering;

import java.awt.Color;
import java.awt.Graphics;

import src.FLEngine3D;
import src.data.Matrix4;
import src.data.Vector3;

public class Triangle {
	public Vector3 a, b, c;
	private Color color;

	public Triangle(Vector3 a, Vector3 b, Vector3 c) {
		this.a = a;
		this.b = b;
		this.c = c;
		this.color = new Color((int) (Math.random() * 16) * 16, (int) (Math.random() * 16) * 16, (int) (Math.random() * 16) * 16);
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

		Triangle translation = new Triangle(
			a.applyMatrix(matRotZ).applyMatrix(matRotX).sum(0, 0, 3),
			b.applyMatrix(matRotZ).applyMatrix(matRotX).sum(0, 0, 3),
			c.applyMatrix(matRotZ).applyMatrix(matRotX).sum(0, 0, 3)
		);

		Vector3 normal, line1, line2;
		line1 = new Vector3(translation.b.x - translation.a.x, translation.b.y - translation.a.y, translation.b.z - translation.a.z);
		line2 = new Vector3(translation.c.x - translation.a.x, translation.c.y - translation.a.y, translation.c.z - translation.a.z);
		normal = new Vector3(line1.y * line2.z - line1.z * line2.y, line1.z * line2.x - line1.x * line2.z, line1.x * line2.y - line1.y * line2.x);
		normal = normal.normalize();

		if (normal.x * (translation.a.x - camera.transform().x) + 
			normal.y * (translation.a.y - camera.transform().y) +
			normal.z * (translation.a.z - camera.transform().z) < 0) {

			Triangle projection = new Triangle(
					translation.a.applyMatrix(projectionMatrix),
					translation.b.applyMatrix(projectionMatrix),
					translation.c.applyMatrix(projectionMatrix));

			g.setColor(Color.WHITE);
					
			int[] xCoordinates = new int[] {
					(int) ((projection.a.x + 1) * camera.windowSize().width / 2),
					(int) ((projection.b.x + 1) * camera.windowSize().width / 2),
					(int) ((projection.c.x + 1) * camera.windowSize().width / 2) };
			int[] yCoordinates = new int[] {
					(int) ((projection.a.y + 1) * camera.windowSize().height / 2),
					(int) ((projection.b.y + 1) * camera.windowSize().height / 2),
					(int) ((projection.c.y + 1) * camera.windowSize().height / 2) };

			boolean fill = true;
			if (fill) {
				g.setColor(color);
				g.fillPolygon(xCoordinates, yCoordinates, 3);
			} else {	
				g.drawPolygon(xCoordinates, yCoordinates, 3);
			}
		}
	}
}
