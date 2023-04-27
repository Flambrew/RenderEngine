package src.rendering;

import java.awt.Dimension;

import src.data.Vector3;

public class Camera {
	private Dimension windowSize;
	private Vector3 transform, rotation;

	public Camera(Dimension windowSize, Vector3 transform, Vector3 rotation) {
		this.windowSize = windowSize;
		this.transform = transform;
		this.rotation = rotation;
	}

	public Dimension windowSize() {
		return windowSize;
	}

	public Vector3 transform() {
		return transform;
	}

	public Vector3 rotation() {
		return rotation;
	}
}
