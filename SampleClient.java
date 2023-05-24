import java.awt.Dimension;

import src.data.Matrix4;
import src.data.Vector3;
import src.engine.FLEngine3D;
import src.engine.Mesh;
import src.engine.Scene;

public class SampleClient extends FLEngine3D {
    Scene scene;
    Mesh cube;

    public SampleClient(Dimension windowSize, int framerate) {
        super(windowSize, framerate);
    }

    // start is called before the first frame update
    public void start() {
        scene = new Scene(this);

        cube = Mesh.CUBE().translate(new Vector3(0, 0, 0));
        scene.addMesh(cube);

        setScene(scene);
    }

    // update is called once per frame
    public void frameUpdate() {
        cube.translate(Matrix4.X_ROTATE(1. / framerate()), Matrix4.Z_ROTATE(1. / framerate()));
    }

    public static void main(String[] args) {
        new SampleClient(new Dimension(1280, 720), 50);
    }
}