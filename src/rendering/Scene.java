package src.rendering;

import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;

import src.data.Angle;
import src.data.Matrix4;

public class Scene {
	private ArrayList<Mesh> meshes;
	private Camera camera;

	public Scene(Camera camera) {
		this.camera = camera;
		this.meshes = new ArrayList<Mesh>();
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

		Matrix4 projectionMatrix = Matrix4.PROJECTION_MATRIX(new Dimension(1600, 900), new Angle(90, false), 1000, .1);

		sceneMesh.paint(g, camera, projectionMatrix);
	}
}
