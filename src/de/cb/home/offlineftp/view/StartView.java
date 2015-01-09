package de.cb.home.offlineftp.view;

import java.io.File;
import java.util.List;

import javafx.scene.control.TreeItem;

public interface StartView {

	void setPresenter(Presenter presenter);

	interface Presenter {
		void onUpload(List<File> files);

		void onDownload(List<File> files);

		void setRightDirectory(String rightDirectoryPath);

		void setLeftDirectory(String leftDirectoryPath);
	}

	void setLeftTreeRootItem(TreeItem<String> root);

	void setRightTreeRootItem(TreeItem<String> root);

	void setRightBar(double progress);

	void setLeftBarProgress(double progress);

	void setLeftBarUsableGB(double gb);

}
