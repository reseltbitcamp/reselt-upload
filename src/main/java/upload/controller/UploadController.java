package upload.controller;

import java.io.File;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import style.bean.StyleDTO;
import style.service.StyleService;

@Controller
public class UploadController {
	
	@CrossOrigin(origins = "http://localhost:8080")
	@PostMapping(value = "style")
	public ResponseEntity<?> uploadStyle(@ModelAttribute StyleDTO styleDTO,
									@RequestParam MultipartFile img,
									HttpSession session) {
		String filePath = session.getServletContext().getRealPath("/resources/img/style");
		String fileName = img.getOriginalFilename();
		
		File file = new File(filePath, fileName);
		System.out.println(filePath);
		
		try {
			img.transferTo(file);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		
		return ResponseEntity.ok("이미지를 업로드하였습니다.");
	}
	
	@CrossOrigin(origins = "http://localhost:8080")
	@PostMapping(value = "myPage")
	public ResponseEntity<?> uploadProfile(@ModelAttribute StyleDTO styleDTO,
									@RequestParam MultipartFile img,
									HttpSession session) {
		String filePath = session.getServletContext().getRealPath("/resources/img/myPage");
		String fileName = img.getOriginalFilename();
		
		File file = new File(filePath, fileName);
		System.out.println(filePath);
		
		try {
			img.transferTo(file);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		
		return ResponseEntity.ok("이미지를 업로드하였습니다.");
	}
}
