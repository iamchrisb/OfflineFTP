package de.cb.home.offlineftp.view;

import java.io.File;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import de.cb.home.offlineftp.common.FileUtils;
import de.cb.home.offlineftp.common.Messages;

public class StartViewImpl implements StartView {

	private Presenter presenter;
	private TreeView<String> rightTree;
	private TreeView<String> leftTree;
	private ProgressBar leftBar;
	private ProgressBar rightBar;
	private Stage stage;
	private Label leftbarLabel;

	public StartViewImpl(Stage stage) {
		this.stage = stage;
		VBox verticalContainer = new VBox(10);
		HBox horizontalContainer = new HBox(10);
		VBox leftContainer = new VBox(10);
		VBox rightContainer = new VBox(10);

		Scene scene = new Scene(verticalContainer, 750, 500, Color.web("#666970"));
		stage.setScene(scene);
		stage.setTitle(Messages.APPLICATION_TITLE);

		TreeItem<String> rootItem = new TreeItem<String>("");
		rootItem.setExpanded(true);

		rightTree = new TreeView<String>(rootItem);
		leftTree = new TreeView<String>(rootItem);

		horizontalContainer.getChildren().addAll(leftContainer, rightContainer);
		verticalContainer.getChildren().add(horizontalContainer);

		Button leftDirBtn = new Button("leftdir");
		leftDirBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				File pickedFile = new DirectoryChooser().showDialog(StartViewImpl.this.stage);
				if (pickedFile != null) {
					presenter.setLeftDirectory(pickedFile.getAbsolutePath());
				}
			}
		});

		Button rightDirBtn = new Button("rightdir");
		rightDirBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				File pickedFile = new DirectoryChooser().showDialog(StartViewImpl.this.stage);
				if (pickedFile != null) {
					presenter.setRightDirectory(pickedFile.getAbsolutePath());
				}
			}
		});

		HBox leftBarContainer = new HBox(10);
		leftBar = new ProgressBar(100);
		leftBar.setProgress(0);
		leftbarLabel = new Label("-/- GB free");
		leftBarContainer.getChildren().addAll(leftBar, leftbarLabel);

		rightBar = new ProgressBar(100);
		rightBar.setProgress(0);

		leftContainer.getChildren().addAll(leftDirBtn, leftBarContainer, leftTree);
		rightContainer.getChildren().addAll(rightDirBtn, rightBar, rightTree);
	}

	@Override
	public void setPresenter(Presenter presenter) {
		this.presenter = presenter;
	}

	@Override
	public void setLeftTreeRootItem(final TreeItem<String> root) {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				root.setExpanded(true);
				leftTree.setRoot(root);
			}
		});
	}

	@Override
	public void setRightTreeRootItem(final TreeItem<String> root) {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				root.setExpanded(true);
				rightTree.setRoot(root);
			}
		});
	}

	@Override
	public void setRightBar(final double progress) {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				rightBar.setProgress(progress);
			}
		});
	}

	@Override
	public void setLeftBarProgress(final double progress) {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				leftBar.setProgress(progress);
			}
		});
	}

	@Override
	public void setLeftBarUsableGB(final double gb) {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				leftbarLabel.setText(FileUtils.gbFormat.format(gb) + "GB free");
			}
		});
	}
}
