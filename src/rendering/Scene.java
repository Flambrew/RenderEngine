package src.rendering;

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
}
