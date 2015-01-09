package de.cb.home.offlineftp.view;

import java.io.File;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import de.cb.home.offlineftp.common.Messages;

public class StartViewImpl implements StartView {

	private Presenter presenter;
	private TreeView<String> rightTree;
	private TreeView<String> leftTree;
	private ProgressBar leftBar;
	private ProgressBar rightBar;
	private Stage stage;

	public StartViewImpl(Stage stage) {
		this.stage = stage;
		VBox verticalContainer = new VBox(10);
		HBox horizontalContainer = new HBox(10);
		Scene scene = new Scene(verticalContainer, 750, 500, Color.web("#666970"));
		stage.setScene(scene);
		stage.setTitle(Messages.APPLICATION_TITLE);

		TreeItem<String> rootItem = new TreeItem<String>("");
		rootItem.setExpanded(true);

		rightTree = new TreeView<String>(rootItem);
		leftTree = new TreeView<String>(rootItem);

		horizontalContainer.getChildren().add(leftTree);
		horizontalContainer.getChildren().add(rightTree);

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
		verticalContainer.getChildren().add(leftDirBtn);

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

		verticalContainer.getChildren().add(rightDirBtn);

		leftBar = new ProgressBar();
		rightBar = new ProgressBar();
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
}
