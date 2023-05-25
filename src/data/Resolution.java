package src.data;

import java.awt.Dimension;

/** Defines viewport and texture resolution enums
 * @since 24 May 2023
 * @author Andrew Matherne (Flambrew) */
public class Resolution {
	/** Viewport resolution enum */
	public enum Viewport {
		nHD360(new Dimension(640, 360)),
		SVGA600(new Dimension(800, 600)),
		XGA768(new Dimension(1024, 768)),
		WXGA720(new Dimension(1280, 720)),
		WXGA800(new Dimension(1280, 800)),
		SXGA1024(new Dimension(1280, 1024)),
		HD768(new Dimension(1366, 768)),
		WXGAp900(new Dimension(1440, 900)),
		HDp900(new Dimension(1600, 900)),
		UXGA1200(new Dimension(1600, 1200)),
		WSXGAp1050(new Dimension(1050, 1680)),
		FHD1080(new Dimension(1920, 1080)),
		WUXGA1200(new Dimension(1920, 1200)),
		QWXGA1152(new Dimension(2048, 1152)),
		UWFHD1080(new Dimension(2560, 1080)),
		QHD1440(new Dimension(2560, 1440)),
		WQXGA1600(new Dimension(2560, 1600)),
		UWQHD1440(new Dimension(3440, 1440)),
		UHD4K2160(new Dimension(3840, 2160));	

		private Dimension resolution;

		private Viewport(Dimension resolution) {
			this.resolution = resolution;
		}

		public int x() {
			return (int) resolution.getWidth();
		}

		public int y() {
			return (int) resolution.getHeight();
		}
	}	

	/** Texture resolution enum */
	public enum Texture {
		_256(256),
		_512(512),
		_1024(1024),
		_2048(2048),
		_4096(4096),
		_8192(8192);

		private int resolution;

		private Texture(int resolution) {
			this.resolution = resolution;
		}

		public int resolution() {
			return resolution;
		}
	}
}
