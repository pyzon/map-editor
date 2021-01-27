package app;

import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;

public class TilesetCanvas extends Canvas {
	Tileset tileset;
	int zoom;
	public TilesetCanvas(double x, double y, Tileset t, int z) {
		super(x, y);
		tileset = t;
		zoom = z;
	}
	public void draw() {
		// Number of tiles in a row that fits the area
		int n = (int)(this.getWidth()/this.tileset.getTileWidth());
		// Filling the tileset area with the tiles
		int i = 0;
		while (i < tileset.getTiles().size()) {
			for (int j = 0; i < tileset.getTiles().size() && j < n; i++, j++) {
				int w = tileset.getTileWidth();
				Image resizedTile = Tileset.resample(tileset.getTile(i), zoom);
				this.getGraphicsContext2D().drawImage(resizedTile, (i%n)*w*zoom, (int)(i/n)*w*zoom);
			}
		}
	}
}
