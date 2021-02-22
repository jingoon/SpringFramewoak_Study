package kr.co.ezen;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import kr.co.util.FileUploadDownloadUtils;

@Controller
public class TestUploadController {

	@Resource(name = "uploadPath")
	private String uploadPath;
	
	// 파일 업로드 (iframe을 이용해서 비동기화 통신처럼)
	@RequestMapping(value = "/uploadNoAjax", method = RequestMethod.POST)
	public String uploadNoAjax(MultipartHttpServletRequest request, HttpSession session) throws Exception {
		MultipartFile file = request.getFile("file");
		String uploadpath= session.getServletContext().getRealPath(this.uploadPath);
		String sevedName = FileUploadDownloadUtils.makeName(file.getOriginalFilename());
		FileUploadDownloadUtils.upload(file, uploadpath, sevedName);
		return "uploadResult";
	}
	@RequestMapping(value = "/uploadNoAjax", method = RequestMethod.GET)
	public void uploadNoAjax() {
	}
	
	// 파일 업로드 
	@RequestMapping(value = "/uploadTest", method = RequestMethod.POST)
	public void uploadTest(MultipartHttpServletRequest request, HttpSession session) throws Exception {
		String id = request.getParameter("id");
		MultipartFile file= request.getFile("file");
		// realPath 찾기
		String uploadPath = session.getServletContext().getRealPath(this.uploadPath);

		// 유일 파일네임 생성(메서드
		String savedName = FileUploadDownloadUtils.makeName(file.getOriginalFilename());
		
		// 파일 저장
		FileUploadDownloadUtils.upload(file,uploadPath,savedName );
		
	}
	
	@RequestMapping(value = "/uploadTest", method = RequestMethod.GET)
	public void uploadTest() {
		
	}
	
}
