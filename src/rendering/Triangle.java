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
		this.color = Color.WHITE;
	}

	public void paint(Graphics g, FLEngine3D engine , Matrix4 projectionMatrix) {
		Vector3 camPos = engine.camera().transform();

		Triangle translation = new Triangle(
			a.sum(-camPos.x, -camPos.y, -camPos.z),
			b.sum(-camPos.x, -camPos.y, -camPos.z),
			c.sum(-camPos.x, -camPos.y, -camPos.z)
		);

		Vector3 line1 = new Vector3(translation.b.x - translation.a.x, translation.b.y - translation.a.y, translation.b.z - translation.a.z);
		Vector3 line2 = new Vector3(translation.c.x - translation.a.x, translation.c.y - translation.a.y, translation.c.z - translation.a.z);
		Vector3 normal = line1.crossProduct(line2).normalize();

		if (normal.dotProduct(translation.a) < 0) {
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

			Vector3 color = new Vector3(this.color.getRed(), this.color.getGreen(), this.color.getBlue());
			color = color.scale(engine.camera().lightsource().normalize().dotProduct(normal) / 3 + 0.5);
			
			g.setColor(new Color((int)color.x, (int)color.y, (int)color.z));
			g.fillPolygon(xCoordinates, yCoordinates, 3);
			g.setColor(Color.BLACK);
			g.drawPolygon(xCoordinates, yCoordinates, 3);
		}
	}
}
