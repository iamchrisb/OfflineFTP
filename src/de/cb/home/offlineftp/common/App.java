package de.cb.home.offlineftp.common;

import java.io.File;

import javafx.application.Application;
import javafx.stage.Stage;

import javax.swing.filechooser.FileSystemView;

import de.cb.home.offlineftp.controller.StartController;
import de.cb.home.offlineftp.view.StartView;
import de.cb.home.offlineftp.view.StartViewImpl;

public class App extends Application {
	
	public static void main(String[] args) {
		launch(args);
	}
	@Override
	public void start(Stage stage) throws Exception {
		StartView startView = new StartViewImpl(stage);
		stage.show();
		StartController startController = new StartController(startView);
		FileSystemView system = FileSystemView.getFileSystemView();
		File[] roots = system.getRoots();
	}
}
