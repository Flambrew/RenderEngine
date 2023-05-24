package src.rendering;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

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

	public void orderByDistanceFrom(Vector3 point) {
		triangles.sort(new Comparator<Triangle>() {
			public int compare(Triangle a, Triangle b) {
				Vector3 A = new Vector3((a.a.x + a.b.x + a.c.x) / 3, (a.a.y + a.b.y + a.c.y) / 3, (a.a.z + a.b.z + a.c.z) / 3);
				Vector3 B = new Vector3((b.a.x + b.b.x + b.c.x) / 3, (b.a.y + b.b.y + b.c.y) / 3, (b.a.z + b.b.z + b.c.z) / 3);
				double distToA = Math.sqrt(Math.pow(A.x - point.x, 2) + Math.pow(A.y - point.y, 2) + Math.pow(A.z - point.z, 2));
				double distToB = Math.sqrt(Math.pow(B.x - point.x, 2) + Math.pow(B.y - point.y, 2) + Math.pow(B.z - point.z, 2));
				return distToA < distToB ? 1 : -1;
			}
		});
	}

	public void paint(Graphics g, FLEngine3D engine, Matrix4 projectionMatrix) {
		for (Triangle triangle : triangles) 
			triangle.paint(g, engine, projectionMatrix);
	}

	public Mesh translate(Vector3 translation) {
		for (Triangle triangle : triangles) {
			triangle.a = triangle.a.sum(translation);
			triangle.b = triangle.b.sum(translation);
			triangle.c = triangle.c.sum(translation);
		}	
		return this;
	}

	public Mesh translate(Matrix4 translation) {
		for (Triangle triangle : triangles) {
			triangle.a = triangle.a.transform(translation);
			triangle.b = triangle.b.transform(translation);
			triangle.c = triangle.c.transform(translation);
		}	
		return this;
	}

	public static Mesh CUBE() {
		return new Mesh(new Triangle[] {
			new Triangle(new Vector3(1, 1, 0), new Vector3(1, 1, 1), new Vector3(1, 0, 1)), // x
			new Triangle(new Vector3(1, 0, 1), new Vector3(1, 0, 0), new Vector3(1, 1, 0)),

			new Triangle(new Vector3(0, 1, 1), new Vector3(0, 1, 0), new Vector3(0, 0, 0)), // -x
			new Triangle(new Vector3(0, 0, 0), new Vector3(0, 0, 1), new Vector3(0, 1, 1)),

			new Triangle(new Vector3(1, 1, 0), new Vector3(0, 1, 0), new Vector3(0, 1, 1)), // y
			new Triangle(new Vector3(0, 1, 1), new Vector3(1, 1, 1), new Vector3(1, 1, 0)),

			new Triangle(new Vector3(0, 0, 0), new Vector3(1, 0, 0), new Vector3(1, 0, 1)), // -y
			new Triangle(new Vector3(1, 0, 1), new Vector3(0, 0, 1), new Vector3(0, 0, 0)),

			new Triangle(new Vector3(0, 1, 1), new Vector3(0, 0, 1), new Vector3(1, 0, 1)), // z
			new Triangle(new Vector3(1, 0, 1), new Vector3(1, 1, 1), new Vector3(0, 1, 1)),

			new Triangle(new Vector3(0, 0, 0), new Vector3(0, 1, 0), new Vector3(1, 1, 0)), // -z
			new Triangle(new Vector3(1, 1, 0), new Vector3(1, 0, 0), new Vector3(0, 0, 0))
		});
	};
}
