package de.cb.home.offlineftp.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import javax.swing.Icon;
import javax.swing.filechooser.FileSystemView;

import javafx.scene.Node;
import javafx.scene.control.TreeItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import de.cb.home.offlineftp.common.FileUtils;
import de.cb.home.offlineftp.view.StartView;

public class StartController implements StartView.Presenter {

	private static final String FOLDER_SMALL_16_PNG = "folder_small_16.png";
	private static final String HARDDRIVE_SMALL_16_PNG = "harddrive_small_16.png";
	private StartView view;
	private Image folderIconImage = new Image(getClass().getResourceAsStream(FOLDER_SMALL_16_PNG));
	private Image harddriveIconImage = new Image(getClass().getResourceAsStream(HARDDRIVE_SMALL_16_PNG));

	public StartController(StartView view) {
		this.view = view;
		view.setPresenter(this);
		FileSystemView system = FileSystemView.getFileSystemView();
	}

	@Override
	public void setLeftDirectory(String leftDirectoryPath) {
		final File f = new File(leftDirectoryPath);
		FileSystemView system = FileSystemView.getFileSystemView();
		Icon systemIcon = system.getSystemIcon(f);
		view.setLeftBarProgress(FileUtils.getUsedSpaceAsPercent(f));
		view.setLeftBarUsableGB(FileUtils.getUsableSpaceAsGB(f));
		new Thread() {
			@Override
			public void run() {
				view.setLeftTreeRootItem(findRoot(f));
			};
		}.start();

	}

	private TreeItem<String> findRoot(File file) {
		if (file.isFile()) {
			return new TreeItem<String>(file.getName());
		} else {
			boolean isAbs = file.isAbsolute();
			Node dirIcon = new ImageView(folderIconImage);
			if(isAbs) {
				dirIcon = new ImageView(harddriveIconImage);
			}
			TreeItem<String> currentParent = new TreeItem<String>(file.getName(), dirIcon);
			for (File currentChild : file.listFiles()) {
				findItems(currentChild, currentParent);
			}
			return currentParent;
		}
	}

	private void findItems(File file, TreeItem<String> parent) {
		if (file.isFile()) {
			parent.getChildren().add(new TreeItem<String>(file.getName()));
		} else {
			Node dirIcon = new ImageView(folderIconImage);
			TreeItem<String> currentParent = new TreeItem<String>(file.getName(), dirIcon);
			parent.getChildren().add(currentParent);
			if (file.listFiles() == null) {
				return;
			}
			for (File currentChild : file.listFiles()) {
				findItems(currentChild, currentParent);
			}
		}
	}

	@Override
	public void setRightDirectory(String rightDirectoryPath) {
		File f = new File(rightDirectoryPath);
		view.setRightTreeRootItem(findRoot(f));
	}

	@Override
	public void onUpload(List<File> files) {
		// Files.copy(source, out)
		for (File currentFile : files) {
			FileOutputStream fos;
			try {
				// fos = new FileOutputStream(rightDirectoryPath +
				// currentFile.getName());
				// Files.copy(currentFile.toPath(), fos);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void onDownload(List<File> files) {

	}

}
