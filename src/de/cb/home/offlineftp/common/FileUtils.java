package de.cb.home.offlineftp.common;

import java.io.File;
import java.text.DecimalFormat;

public class FileUtils {

	public static DecimalFormat gbFormat = new DecimalFormat("#.00");

	private static final int BYTE_MEASURE_FACTOR = 1024;

	public static double getUsedSpaceAsPercent(File f) {
		long usedSpace = f.getTotalSpace() - f.getUsableSpace();
		return (double) usedSpace / (double) f.getTotalSpace();
	}

	public static double getUsableSpaceAsGB(File f) {
		return (double) f.getUsableSpace() / BYTE_MEASURE_FACTOR / BYTE_MEASURE_FACTOR / BYTE_MEASURE_FACTOR;
	}

	public static double getUsableSpaceAsMB(File f) {
		return (double) f.getUsableSpace() / BYTE_MEASURE_FACTOR / BYTE_MEASURE_FACTOR;
	}

	public static double getTotalSpaceAsGB(File f) {
		return (double) f.getTotalSpace() / BYTE_MEASURE_FACTOR / BYTE_MEASURE_FACTOR / BYTE_MEASURE_FACTOR;
	}

	public static double getTotalSpaceAsMB(File f) {
		return (double) f.getTotalSpace() / BYTE_MEASURE_FACTOR / BYTE_MEASURE_FACTOR;
	}

}
