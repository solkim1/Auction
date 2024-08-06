package com.oggo.auction.codec;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.UUID;
import org.apache.commons.io.FileUtils;

public class Base64Codec {

    /**
     * Base64 문자열을 디코딩하여 파일로 저장합니다.
     * 
     * @param base64 Base64로 인코딩된 문자열
     * @param uuid 파일 이름에 사용할 UUID
     * @throws IOException 파일 생성 및 쓰기 과정에서 발생하는 예외
     */
    public static void makeFileWithString(String base64, UUID uuid) throws IOException {
        // Base64 문자열을 바이트 배열로 디코딩
        byte[] byteArray = Base64.getDecoder().decode(base64);

        // 파일 객체 (저장할 경로, 파일의 이름, 확장자)
        File file = new File("src/main/resources/static/images/" + uuid.toString() + ".jpg");
        
        // 부모 디렉토리 생성 (필요한 경우)
        file.getParentFile().mkdirs();
        
        // 파일 생성 및 바이트 배열을 파일에 씀
        try (FileOutputStream fos = new FileOutputStream(file)) {
            fos.write(byteArray);
        }
    }

    /**
     * 파일을 Base64로 인코딩된 문자열로 변환합니다.
     * 
     * @param filePath 파일 경로
     * @return Base64로 인코딩된 문자열
     * @throws IOException 파일을 읽는 중 발생하는 예외
     */
    public static String makeStringWithFile(String filePath) throws IOException {
        // 파일을 읽어 바이트 배열로 변환
        byte[] byteArray = FileUtils.readFileToByteArray(new File(filePath));
        
        // 바이트 배열을 Base64 문자열로 인코딩
        return Base64.getEncoder().encodeToString(byteArray);
    }
}

