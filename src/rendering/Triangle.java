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

	public void paint(Graphics g, FLEngine3D engine , Matrix4 projectionMatrix) {
		Vector3 camPos = engine.camera().transform();

		Triangle translation = new Triangle(
			a.sum(-camPos.x, -camPos.y, -camPos.z),
			b.sum(-camPos.x, -camPos.y, -camPos.z),
			c.sum(-camPos.x, -camPos.y, -camPos.z)
		);

		Vector3 normal, line1, line2;
		line1 = new Vector3(translation.b.x - translation.a.x, translation.b.y - translation.a.y, translation.b.z - translation.a.z);
		line2 = new Vector3(translation.c.x - translation.a.x, translation.c.y - translation.a.y, translation.c.z - translation.a.z);
		normal = new Vector3(line1.y * line2.z - line1.z * line2.y, line1.z * line2.x - line1.x * line2.z, line1.x * line2.y - line1.y * line2.x);
		normal = normal.normalize();

		if (normal.x * (translation.a.x/* - camPos.x*/) + 
			normal.y * (translation.a.y/* - camPos.y*/) +
			normal.z * (translation.a.z/* - camPos.z*/) < 0) {

			Triangle projection = new Triangle(
					translation.a.applyMatrix(projectionMatrix),
					translation.b.applyMatrix(projectionMatrix),
					translation.c.applyMatrix(projectionMatrix));
					
			int[] xCoordinates = new int[] {
					(int) ((projection.a.x + 1) * engine.windowSize().width / 2),
					(int) ((projection.b.x + 1) * engine.windowSize().width / 2),
					(int) ((projection.c.x + 1) * engine.windowSize().width / 2) };
			int[] yCoordinates = new int[] {
					(int) ((projection.a.y + 1) * engine.windowSize().height / 2),
					(int) ((projection.b.y + 1) * engine.windowSize().height / 2),
					(int) ((projection.c.y + 1) * engine.windowSize().height / 2) };

			boolean fill = true;
			if (fill) {
				g.setColor(color);
				g.fillPolygon(xCoordinates, yCoordinates, 3);
			} else {	
			g.setColor(Color.WHITE);
				g.drawPolygon(xCoordinates, yCoordinates, 3);
			}
		}
	}
}
