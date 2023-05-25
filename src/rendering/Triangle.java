package src.rendering;

import java.awt.Color;
import java.awt.Graphics;

import src.data.Matrix4;
import src.data.Vector3;
import src.engine.FLEngine3D;

public class Triangle {
	public Vector3 a, b, c;
	private Color color;

	public Triangle(Vector3 a, Vector3 b, Vector3 c) {
		this.a = a;
		this.b = b;
		this.c = c;
		this.color = Color.WHITE;
	}

	public Vector3[] asArray() {
		return new Vector3[] { a, b, c };
	}

	public void paint(Graphics g, FLEngine3D engine, Matrix4 projectionMatrix) {
		Vector3 camPos = engine.camera().transform().clone().scale(-1);

		Triangle translation = new Triangle( //
				a.clone().sum(camPos), //
				b.clone().sum(camPos), //
				c.clone().sum(camPos) //
		);

		Vector3 negatedA = translation.a.clone().scale(-1);
		Vector3 line1 = translation.b.clone().sum(translation.a.clone().scale(-1));
		Vector3 line2 = translation.c.clone().sum(translation.a.clone().scale(-1));
		Vector3 normal = line1.crossProduct(line2).normalize();

		if (normal.dotProduct(translation.a) < 0) {
			Triangle projection = new Triangle( //
					translation.a.clone().transform(projectionMatrix), //
					translation.b.clone().transform(projectionMatrix), //
					translation.c.clone().transform(projectionMatrix) //
			);

			int[] xCoordinates = new int[] { //
					(int) ((projection.a.x() + 1) * engine.windowSize().width / 2), //
					(int) ((projection.b.x() + 1) * engine.windowSize().width / 2), //
					(int) ((projection.c.x() + 1) * engine.windowSize().width / 2) //
			};

			int[] yCoordinates = new int[] { //
					(int) ((projection.a.y() + 1) * engine.windowSize().height / 2), //
					(int) ((projection.b.y() + 1) * engine.windowSize().height / 2), //
					(int) ((projection.c.y() + 1) * engine.windowSize().height / 2) //
			};

			/*
			 * old shading code:
			 * 
			 * Vector3 color = new Vector3(this.color.getRed(),
			 * this.color.getGreen(), this.color.getBlue()); color =
			 * color.scale(engine.camera().lightsource().normalize().dotProduct(normal) /
			 * 2.22 + 0.54954); g.setColor(new Color((int)color.x, (int)color.y,
			 * (int)color.z));
			 */

			g.setColor(color);
			g.fillPolygon(xCoordinates, yCoordinates, 3);
			g.setColor(Color.BLACK);
			g.drawPolygon(xCoordinates, yCoordinates, 3);
		}
	}
}
