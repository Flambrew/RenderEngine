package src.rendering;

import java.util.Arrays;
import java.util.Comparator;

import src.data.Vector3;

public class Mesh {
	private Triangle[] triangles;

	public Mesh(Triangle[] triangles) {
		this.triangles = triangles;
	}

	public void orderByDistanceFrom(Vector3 point) {
		Arrays.sort(triangles, new Comparator<Triangle>() {
			public int compare(Triangle a, Triangle b) {
				Vector3 A = new Vector3((a.a.x + a.b.x + a.c.x) / 3, (a.a.y + a.b.y + a.c.y) / 3, (a.a.z + a.b.z + a.c.z) / 3);
				Vector3 B = new Vector3((b.a.x + b.b.x + b.c.x) / 3, (b.a.y + b.b.y + b.c.y) / 3, (b.a.z + b.b.z + b.c.z) / 3);
				double distToA = Math.sqrt(A.x * A.x + A.y * A.y + A.z * A.z);
				double distToB = Math.sqrt(B.x * B.x + B.y * B.y + B.z * B.z);
				return distToA > distToB ? 1 : -1;
			}
		});
	}

	public Triangle[] triangles() {
		return triangles;
	}
}
