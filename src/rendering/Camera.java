package src.rendering;

import src.data.Vector3;

public class Camera {
	private Vector3 transform, rotation, lightsource;

	public Camera(Vector3 transform, Vector3 rotation, Vector3 lightsource) {
		this.transform = transform;
		this.rotation = rotation;
		this.lightsource = lightsource;
	}

	public Vector3 transform() {
		return transform;
	}

	public Vector3 rotation() {
		return rotation;
	}

	public Vector3 lightsource() {
		return lightsource;
	}
}
