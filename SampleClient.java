import java.awt.Dimension;

import src.FLEngine;

public class SampleClient extends FLEngine {
    public SampleClient(Dimension windowSize, int framerate) {
        super(windowSize, framerate);
    }

    // start is called before the first frame update
    public void start() {

    }

    // update is called once per frame
    public void update() {
        
    }

    public static void main(String[] args) {
        TestClient client = new TestClient(new Dimension(1280, 720), 50);
    }
}