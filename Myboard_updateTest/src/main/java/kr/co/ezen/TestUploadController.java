package kr.co.ezen;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import kr.co.util.FileUploadDownloadUtils;

@Controller
public class TestUploadController {

	@Resource(name = "uploadPath")
	private String uploadPath;
	
	
	// ajax를 이용한 파일 업로드
	@ResponseBody
	@RequestMapping(value = "ajaxtest"
					, method = RequestMethod.POST
					, produces = "application/text;charset=utf-8")	// 반환 String이 깨질때
	public String ajaxtest(MultipartHttpServletRequest request, HttpSession session) throws Exception {
		MultipartFile file = request.getFile("file");
		String realPath = session.getServletContext().getRealPath(uploadPath);
		String uploadFileName= FileUploadDownloadUtils.upload(file, realPath);
		System.out.println(uploadFileName);
		return uploadFileName;
	}
	
	@RequestMapping(value = "ajaxtest", method = RequestMethod.GET)
	public void ajaxtest() {
		
	}
	
	
	// 파일 업로드 (iframe을 이용해서 비동기화 통신처럼)
	@RequestMapping(value = "/uploadNoAjax", method = RequestMethod.POST)
	public String uploadNoAjax(MultipartHttpServletRequest request, HttpSession session) throws Exception {
		MultipartFile file = request.getFile("file");
		String uploadpath= session.getServletContext().getRealPath(this.uploadPath);
		FileUploadDownloadUtils.upload(file, uploadpath);
		return "uploadResult";
	}
	@RequestMapping(value = "/uploadNoAjax", method = RequestMethod.GET)
	public void uploadNoAjax() {
	}
	
	// 파일 업로드 
	@RequestMapping(value = "/uploadTest", method = RequestMethod.POST)
	public void uploadTest(MultipartHttpServletRequest request, HttpSession session) throws Exception {
		String id = request.getParameter("id");
		System.out.println(id);
		MultipartFile file= request.getFile("file");
		// realPath 찾기
		String uploadPath = session.getServletContext().getRealPath(this.uploadPath);
		
		// 파일저장 + 유일네임 생성
		FileUploadDownloadUtils.upload(file,uploadPath);
		
	}
	
	@RequestMapping(value = "/uploadTest", method = RequestMethod.GET)
	public void uploadTest() {
		
	}
	
}
