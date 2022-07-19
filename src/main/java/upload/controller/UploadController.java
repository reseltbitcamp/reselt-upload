package upload.controller;

import java.io.File;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import style.bean.StyleDTO;
import style.service.StyleService;

@Controller
public class UploadController {
	@Autowired
	private StyleService styleService;
	
	@PostMapping(value = "/")
	public ResponseEntity<?> upload(@ModelAttribute StyleDTO styleDTO,
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
		
		styleDTO.setStyle_image(fileName);
		styleService.styleWriteForm(styleDTO);
		
		return ResponseEntity.ok("이미지를 업로드하였습니다.");
	}
}
