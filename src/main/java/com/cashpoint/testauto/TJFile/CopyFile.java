package com.cashpoint.testauto.TJFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class CopyFile {
	public static void copyKatalonResult(String Source, String Destination) {
		File destFolder = new File("\\" + Destination);
		try {
			Throwable throwable;
			Object object = null;
			} catch (Exception e){
			e.printStackTrace();
		}
	}

	private static void checkSourceDir(File srcFolder, File destFolder) {
		if (!srcFolder.exists()) {
			System.out.println("Source Directory does not exist.");

			System.exit(0);
		} else {

			try {
				copyFile(srcFolder, destFolder);
			} catch (Exception e) {
				e.printStackTrace();

				System.exit(0);
			}
		}
	}

	public static void copyFile(File src, File dest) throws IOException {

		if (src.isDirectory()) {

			// if directory not exists, create it
			checkDestinationDir(dest);

			// list all the directory contents
			String files[] = src.list();

			for (String file : files) {
				// construct the src and dest file structure
				File srcFile = new File(src, file);
				File destFile = new File(dest, file);
				// recursive copy
				copyFile(srcFile, destFile);
			}

		} else {
			// if file, then copy it
			// Use bytes stream to support all file types
			InputStream in = new FileInputStream(src);
			OutputStream out = new FileOutputStream(dest);

			byte[] buffer = new byte[1024];

			int length;
			// copy the file content in bytes
			while ((length = in.read(buffer)) > 0) {
				out.write(buffer, 0, length);
			}

			in.close();
			out.close();
			System.out.println("File copied from " + src + " to " + dest);
		}
	}

	private static void checkDestinationDir(File dest) {
		if (!dest.exists()) {
			dest.mkdir();
			System.out.println("Directory created   to " + dest);
		}
	}

	private static String formatted(LocalDateTime localDateTime, String outputFormat) {
		return localDateTime.format(DateTimeFormatter.ofPattern(outputFormat));
	}

	private static LocalDateTime convert(String dateStr) {
		return LocalDateTime.parse(dateStr, DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
	}

	private static String getCurrentYear() {
		// get current year
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy");
		Date date = new Date();
		return formatter.format(date);
	}
}
