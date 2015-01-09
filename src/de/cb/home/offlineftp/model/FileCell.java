package de.cb.home.offlineftp.model;

import java.io.File;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class FileCell {

	public String name;
	public BigInteger size;
	private String path;

	public boolean isDirectory;
	public boolean isFile;
	public boolean isHidden;
	public boolean isAbsolute;

	List<FileCell> leafs = new ArrayList<FileCell>();

	public FileCell(String path) {
		this.path = path;
		File file = new File(path);
		name = file.getName();

		isDirectory = file.isDirectory();
		isFile = file.isFile();
		isHidden = file.isHidden();
		isAbsolute = file.isAbsolute();

		for (File leaf : file.listFiles()) {
			leafs.add(new FileCell(leaf.getAbsolutePath()));
		}

		try {
			// FileOutputStream fos = new FileOutputStream(file);
			// byte[] buffer = new byte[1024];
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
