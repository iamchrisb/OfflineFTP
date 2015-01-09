package de.cb.home.offlineftp.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import javafx.scene.Node;
import javafx.scene.control.TreeItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import de.cb.home.offlineftp.common.FileUtils;
import de.cb.home.offlineftp.view.StartView;

public class StartController implements StartView.Presenter {

	private static final String FOLDER_SMALL_16_PNG = "folder_small_16.png";
	private StartView view;
	private Image folderIconImage = new Image(getClass().getResourceAsStream(FOLDER_SMALL_16_PNG));

	public StartController(StartView view) {
		this.view = view;
		view.setPresenter(this);
	}

	@Override
	public void setLeftDirectory(String leftDirectoryPath) {
		File f = new File(leftDirectoryPath);
		view.setLeftBarProgress(FileUtils.getUsedSpaceAsPercent(f));
		view.setLeftBarUsableGB(FileUtils.getUsableSpaceAsGB(f));
		view.setLeftTreeRootItem(getRootItem(f));

	}

	private TreeItem<String> getRootItem(final File rootFile) {
		return findRoot(rootFile);
	}

	private TreeItem<String> findRoot(File file) {
		if (file.isFile()) {
			return new TreeItem<String>(file.getName());
		} else {
			Node dirIcon = new ImageView(folderIconImage);
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
		view.setRightTreeRootItem(getRootItem(f));
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
