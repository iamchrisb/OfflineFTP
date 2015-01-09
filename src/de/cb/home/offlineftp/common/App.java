package de.cb.home.offlineftp.common;

import java.io.File;

import javafx.application.Application;
import javafx.stage.Stage;

import javax.swing.filechooser.FileSystemView;

import de.cb.home.offlineftp.controller.StartController;
import de.cb.home.offlineftp.view.StartView;
import de.cb.home.offlineftp.view.StartViewImpl;

public class App extends Application {
	@Override
	public void start(Stage stage) throws Exception {
		StartView startView = new StartViewImpl(stage);
		stage.show();
		StartController startController = new StartController(startView);
		FileSystemView system = FileSystemView.getFileSystemView();
		File[] roots = system.getRoots();
		for (File file : roots) {
			System.out.println(file.getAbsolutePath());
			System.out.println(file.getTotalSpace());
			System.out.println(file.getUsableSpace());
			System.out.println(file.getFreeSpace());
		}

	}
}
