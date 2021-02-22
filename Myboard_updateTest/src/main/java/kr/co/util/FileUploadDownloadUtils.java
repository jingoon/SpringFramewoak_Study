package kr.co.util;

import java.io.File;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.UUID;

import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

public class FileUploadDownloadUtils {

	// 유일 파일네임 생성
	public static String makeName(String originalFilename) {
		UUID uid = UUID.randomUUID();
		String savedName = uid.toString()+"_"+originalFilename;
		return savedName;
	}
	
	// 날짜(년,월,일) 추출
	public static String calcPath(String uploadPath) {
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH)+1;
		int date = cal.get(Calendar.DATE);
		
		String yearPath = File.separator+year;
		String monthPath = yearPath + File.separator + new DecimalFormat("00").format(month);
		String datePath = monthPath + File.separator + new DecimalFormat("00").format(date);
		
		// 날짜별 폴더 생성
		makeDir(uploadPath, yearPath, monthPath, datePath);
		
		return datePath;
	}

	// 날짜별 폴더 생성
	private static void makeDir(String uploadPath, String ... arr) {
		
		File fDate = new File(uploadPath, arr[2]);
		
		if(fDate.exists()) {
			return;
		}
		
		for (String path : arr) {
			File f = new File(uploadPath+path);
			if(!f.exists()) {
				f.mkdir();
			}
		}
		
	}

	// 업로드
	public static void upload(MultipartFile file, String uploadPath, String savedName) throws Exception {
		String datePath= calcPath(uploadPath);
		File target = new File(uploadPath+datePath, savedName);
		FileCopyUtils.copy(file.getBytes(), target);
	
	}
	
	

}
