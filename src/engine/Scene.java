package src.engine;

import java.awt.Graphics;
import java.util.ArrayList;

import src.data.Angle;
import src.data.Matrix4;
import src.rendering.Triangle;

public class Scene {
	private ArrayList<Mesh> meshes;
	private FLEngine3D engine;

	public Scene(FLEngine3D engine) {
		this.engine = engine;
		this.meshes = new ArrayList<Mesh>();
	}

	public void addMesh(Mesh mesh) {
		meshes.add(mesh);
	}

	public ArrayList<Mesh> getMeshes() {
		return meshes;
	}

	public void paint(Graphics g) {
		ArrayList<Triangle> triangles = new ArrayList<Triangle>();
		for (Mesh mesh : meshes) 
			triangles.addAll(mesh.triangles());
		Mesh sceneMesh = new Mesh(triangles);
		sceneMesh.orderByDistanceFrom(engine.camera().transform());

		// TODO move projection settings to engine
		Matrix4 projectionMatrix = Matrix4.PROJECTION_MATRIX(engine.windowSize(), new Angle(90, false), 1000., .1);

		sceneMesh.paint(g, engine, projectionMatrix);
	}
}
