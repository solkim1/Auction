package com.oggo.auction.codec;

import java.io.File;
import java.io.IOException;
import java.util.Base64;
import org.apache.commons.io.FileUtils;

public class Base64Codec {
    
    public static String makeStringWithFile(String filePath) throws IOException {
        File file = new File(filePath);
        if (!file.exists()) {
            throw new IOException("File not found: " + filePath);
        }
        byte[] fileContent = FileUtils.readFileToByteArray(file);
        return Base64.getEncoder().encodeToString(fileContent);
    }

    public static void makeFileWithString(String base64String, File outputFile) throws IOException {
        byte[] decodedBytes = Base64.getDecoder().decode(base64String);
        FileUtils.writeByteArrayToFile(outputFile, decodedBytes);
    }
}
