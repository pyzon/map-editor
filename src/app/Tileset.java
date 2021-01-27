package app;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;

public class Tileset {
	
	private List<Image> tiles;
	private int tileWidth;
	
	public Tileset(String url) {
		Properties p = new Properties();
		
		try (InputStream input = Tileset.class.getClassLoader().getResourceAsStream(url)) {
			p.load(input);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
		Image t = new Image(Tileset.class.getClassLoader().getResourceAsStream(p.getProperty("filename")));
		tileWidth = Integer.parseInt(p.getProperty("width"));
		int h = (int) (t.getHeight()/tileWidth);
		int w = (int) (t.getWidth()/tileWidth);
		
		tiles = new ArrayList<Image>();
		PixelReader reader = t.getPixelReader();
		for (int j = 0; j < h; j++) {
			for (int i = 0; i < w; i++) {
				tiles.add(new WritableImage(reader, i*tileWidth, j*tileWidth, tileWidth, tileWidth));
			}
		}
	}
	public List<Image> getTiles() {
		return tiles;
	}
	public Image getTile(int index) {
		return tiles.get(index);
	}
	public int getTileWidth() {
		return tileWidth;
	}
	public static Image resample(Image input, int scaleFactor) {
		final int W = (int) input.getWidth();
		final int H = (int) input.getHeight();
		final int S = scaleFactor;

		WritableImage output = new WritableImage(W * S, H * S);

		PixelReader reader = input.getPixelReader();
		PixelWriter writer = output.getPixelWriter();

		for (int y = 0; y < H; y++) {
			for (int x = 0; x < W; x++) {
				final int argb = reader.getArgb(x, y);
				for (int dy = 0; dy < S; dy++) {
					for (int dx = 0; dx < S; dx++) {
						writer.setArgb(x * S + dx, y * S + dy, argb);
					}
				}
			}
		}

		return output;
	}
	
}
