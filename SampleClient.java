import java.awt.Dimension;

import src.FLEngine3D;

public class SampleClient extends FLEngine3D {
    public SampleClient(Dimension windowSize, int framerate) {
        super(windowSize, framerate);
    }

    // start is called before the first frame update
    public void start() {

    }

    // update is called once per frame
    public void frameUpdate() {
        
    }

    public static void main(String[] args) {
        new SampleClient(new Dimension(1280, 720), 50);
    }
}