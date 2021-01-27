package app;

public class Layer {
	String name;
	int[][] data;
	
	public Layer(int w, int h, String n) {
		data = new int[w][h];
		for (int i = 0; i < w; i++) {
			for (int j = 0; j < h; j++) {
				// -1 means empty
				data[i][j] = -1;
			}
		}
		name = n;
	}
	
	public void set(int i, int j, int index) {
		data[i][j] = index;
	}
	public int get(int i, int j) {
		return data[i][j];
	}
	public void setName(String n) {
		name = n;
	}
	public String getName() {
		return name;
	}
}
