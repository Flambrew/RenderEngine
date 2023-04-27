package src.rendering;

import java.awt.Graphics;
import java.util.ArrayList;

import src.FLEngine;
import src.data.Angle;
import src.data.Matrix4;

public class Scene {
	private ArrayList<Mesh> meshes;
	private FLEngine engine;

	public Scene(FLEngine engine) {
		this.engine = engine;
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
		sceneMesh.orderByDistanceFrom(engine.camera().transform());

		Matrix4 projectionMatrix = Matrix4.PROJECTION_MATRIX(engine.windowSize(), new Angle(90, false), 1000, .1);

		sceneMesh.paint(g, engine.camera(), projectionMatrix);
	}
}
