package com.oggo.auction.codec;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.apache.tomcat.util.codec.binary.Base64;

public class Base64Codec {

	// Base64 -> 파일 (디코딩)
	public static void makeFileWithString(String base64, UUID uuid) throws IOException {
		byte[] byteArray = Base64.decodeBase64(base64);

		// 파일 객체 (저장할 경로, 파일의 이름, 확장자)
		File file = new File("src/main/resources/static/upload/" + uuid.toString() + ".jpg");
		file.createNewFile();
		FileOutputStream fos = new FileOutputStream(file);
		fos.write(byteArray);
		fos.close();
	}

	// 파일 -> Base64 (인코딩)
	public static String makeStringWithFile(String filePath) throws IOException {
		byte[] byteArray = FileUtils.readFileToByteArray(new File(filePath));
		String base64String = Base64.encodeBase64String(byteArray);
		return base64String;

	}

}
