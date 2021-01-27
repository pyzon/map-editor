package app;

import java.util.Optional;

import javafx.application.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public final class Main extends Application{
	Stage window;
	Button b;
	Tileset tileset;

	@Override
	public void start(Stage window) throws Exception {
		this.window = window;
		window.setOnCloseRequest(e -> {
			e.consume();
			closeWindow();
		});
		window.setTitle("Map Editor v1.0");
		b = new Button();
		b.setText("OK");
		b.setOnAction(e -> System.out.println("asdf"));
		/*
		b.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				if (event.getSource() == b) {
					System.out.println("You clicked me!");
				}
				
			}
		});*/
		// Tileset area
		tileset = new Tileset("app/resources/tileset.properties");
		TilesetCanvas tilesetCanvas = new TilesetCanvas(160, 1000, tileset, 2);
		tilesetCanvas.draw();
		ScrollPane tilesetArea = new ScrollPane(tilesetCanvas);
		tilesetArea.setFitToWidth(true);
		tilesetArea.setMinViewportWidth(160);
		
		// Editing area
		Canvas editingArea = new Canvas(500, 500);
        GraphicsContext editingAreaGC = editingArea.getGraphicsContext2D();
        
        // Layer area
        /*
        ListView<String> layerList = new ListView<String>();
        ObservableList<String> items =FXCollections.observableArrayList (
            "Single", "Double", "Suite", "Family App");
        list.setItems(items);*/
        
        // Main border layout
		BorderPane mainLayout = new BorderPane();
		mainLayout.setLeft(tilesetArea);
		mainLayout.setCenter(editingArea);
		
		Scene scene = new Scene(mainLayout, 800, 500);
		window.setScene(scene);
		window.show();
	}
	public static void main(String[] args) {
		launch(args);
	}
	
	private void closeWindow() {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Map Editor");
		alert.setHeaderText(null);
		alert.setContentText("Do you want to exit WITHOUT saving?");
		ButtonType buttonTypeExit = new ButtonType("Exit", ButtonData.RIGHT);
		ButtonType buttonTypeSave = new ButtonType("Save changes", ButtonData.RIGHT);
		ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonData.RIGHT);

		alert.getButtonTypes().setAll(buttonTypeExit, buttonTypeSave, buttonTypeCancel);

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == buttonTypeExit) {
			window.close();
			// TODO: end program
		} else if (result.get() == buttonTypeSave) {
			// TODO: save or save as if not saved yet
			window.close();
			// TODO: end program
		} else {
		    // do nothing
		}
	}

}
