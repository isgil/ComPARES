package es.um.fcd.web.util;

import java.io.File;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class FileLoader {

	private static final int THRESHOLD_SIZE = 1024 * 1024 * 40; // 3MB originally, 20 currently
	private static final int MAX_FILE_SIZE = 1024 * 1024 * 40; // 40MB
	private static final int REQUEST_SIZE = 1024 * 1024 * 50; // 50MB

	public static ServletFileUpload getUpload(String uploadPath) {

		// configures some settings
		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setSizeThreshold(THRESHOLD_SIZE);
		factory.setRepository(new File(System.getProperty("java.io.tmpdir")));

		ServletFileUpload upload = new ServletFileUpload(factory);
		upload.setFileSizeMax(MAX_FILE_SIZE);
		upload.setSizeMax(REQUEST_SIZE);

		// creates the directory if it does not exist
		File uploadDir = new File(uploadPath);
		if (!uploadDir.exists()) {
			uploadDir.mkdir();
		}

		return upload;
	}
	
	public static void saveFile(FileItem item, String filePath) throws Exception {
		File storeFile = new File(filePath);
		// saves the file on disk
		item.write(storeFile);
	}

}
