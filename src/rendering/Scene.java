package src.rendering;

import java.awt.Graphics;
import java.util.ArrayList;

public class Scene {
	private ArrayList<Mesh> meshes;
	private Camera camera;

	public Scene(Camera camera) {
		this.camera = camera;
	}

	public void addMesh(Mesh mesh) {
		meshes.add(mesh);
	}

	public void paint(Graphics g) {
		ArrayList<Triangle> triangles = new ArrayList<Triangle>();
		for (Mesh mesh : meshes) 
			triangles.addAll(mesh.triangles());
		Mesh sceneMesh = new Mesh(triangles);
		sceneMesh.orderByDistanceFrom(camera.transform());
		sceneMesh.paint(g);
	}
}
