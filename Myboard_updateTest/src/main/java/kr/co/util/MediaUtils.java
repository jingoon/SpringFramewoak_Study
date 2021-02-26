package kr.co.util;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.MediaType;

public class MediaUtils {
	
	private static  Map<String, MediaType> map =new HashMap<String, MediaType>();

	static {
		map.put("JPG", MediaType.IMAGE_JPEG);
		map.put("JPEG", MediaType.IMAGE_JPEG);
		map.put("PNG", MediaType.IMAGE_PNG);
		map.put("GIF", MediaType.IMAGE_GIF);
	}
	
	public static MediaType getMediaType(String type) {
		
		return map.get(type.toUpperCase());
	}
	public static String getType(String fileLink) {
		return fileLink.substring(fileLink.lastIndexOf(".")+1);
	}
}
