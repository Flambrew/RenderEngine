package src.rendering;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Arrays;

import src.FLEngine3D;
import src.data.Matrix4;
import src.data.Vector3;

public class Mesh {
	private ArrayList<Triangle> triangles;

	public Mesh(Triangle[] triangles) {
		this.triangles = new ArrayList<Triangle>(Arrays.asList(triangles));
	}

	public Mesh(ArrayList<Triangle> triangles) {
		this.triangles = triangles;
	}

	public ArrayList<Triangle> triangles() {
		return triangles;
	}

	public void paint(Graphics g, FLEngine3D engine, Matrix4 projectionMatrix) {
		for (Triangle triangle : triangles) 
			triangle.paint(g, engine, projectionMatrix);
	}

	public static final Mesh CUBE = new Mesh(new Triangle[] {
			new Triangle(new Vector3(0.5, 0.5, -0.5), new Vector3(0.5, 0.5, -0.5), new Vector3(0.5, -0.5, 0.5)), // x
			new Triangle(new Vector3(0.5, -0.5, 0.5), new Vector3(0.5, -0.5, -0.5), new Vector3(0.5, 0.5, -0.5)),

			new Triangle(new Vector3(-0.5, 0.5, 0.5), new Vector3(-0.5, 0.5, -0.5), new Vector3(-0.5, -0.5, -0.5)), // -x
			new Triangle(new Vector3(-0.5, -0.5, -0.5), new Vector3(-0.5, -0.5, 0.5), new Vector3(-0.5, 0.5, 0.5)),

			new Triangle(new Vector3(0.5, 0.5, -0.5), new Vector3(-0.5, 0.5, -0.5), new Vector3(-0.5, 0.5, 0.5)), // y
			new Triangle(new Vector3(-0.5, 0.5, 0.5), new Vector3(0.5, 0.5, 0.5), new Vector3(0.5, 0.5, -0.5)),

			new Triangle(new Vector3(-0.5, -0.5, -0.5), new Vector3(0.5, -0.5, -0.5), new Vector3(0.5, -0.5, 0.5)), // -y
			new Triangle(new Vector3(0.5, -0.5, 0.5), new Vector3(-0.5, -0.5, 0.5), new Vector3(-0.5, -0.5, -0.5)),

			new Triangle(new Vector3(-0.5, 0.5, 0.5), new Vector3(-0.5, -0.5, 0.5), new Vector3(0.5, -0.5, 0.5)), // z
			new Triangle(new Vector3(0.5, -0.5, 0.5), new Vector3(0.5, 0.5, 0.5), new Vector3(-0.5, 0.5, 0.5)),

			new Triangle(new Vector3(-0.5, -0.5, -0.5), new Vector3(-0.5, 0.5, -0.5), new Vector3(0.5, 0.5, -0.5)), // -z
			new Triangle(new Vector3(0.5, 0.5, -0.5), new Vector3(0.5, -0.5, -0.5), new Vector3(-0.5, -0.5, -0.5))
	});
}
