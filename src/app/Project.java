package app;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Project {
	private List<Layer> layers;
	boolean isNew;
	boolean isSaved;
	File filepath;
	
	public Project() {
		layers = new ArrayList<Layer>();
		isNew = true;
		isSaved = false;
	}
	
	public List<Layer> getLayers() {
		return layers;
	}
	public Layer getLayer(int index) {
		return layers.get(index);
	}
	public void addLayer(Layer l) {
		layers.add(l);
	}
	public void deleteLayer(Layer l) {
		if (layers.contains(l)) {
			layers.remove(l);
		}
	}
	public void save(File f) {
		isSaved = true;
		//TODO: serialization
	}
	public void open(File f) {
		
	}
}
