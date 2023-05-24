package src.engine;

import src.data.Vector3;

public class Camera {
	private Vector3 transform, rotation;

	public Camera(Vector3 transform, Vector3 rotation) {
		this.transform = transform;
		this.rotation = rotation;
	}

	public Vector3 transform() {
		return transform;
	}

	public Vector3 rotation() {
		return rotation;
	}
}