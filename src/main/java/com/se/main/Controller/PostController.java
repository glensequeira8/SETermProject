/**
 * 
 */
package com.se.main.Controller;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.Base64.Decoder;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.se.main.Repository.UserRepository;
import com.se.main.services.UploadToS3;

/**
 * @author Glen
 *
 */
@Controller
public class PostController {

	
	@Autowired
	private UploadToS3 upload;
/*	@Autowired
	private UserRepository userRepo;
*/	
	
	
	@GetMapping(value="/createpost")
	public ModelAndView redirectPage(Model model) {
	return new ModelAndView("createPost");	
	}
	
	
	@PostMapping(value="/savepost")
	public ModelAndView redirectPage1(HttpServletRequest req, @RequestParam("recording") String recording )throws Exception{
		ModelAndView postView=new ModelAndView("postView");

		if(recording.isEmpty()) {
			throw new Exception("No message recorded!!");
		}
		Decoder decoder = Base64.getDecoder();


		byte[] decodedByte=decoder.decode(recording.split(",")[1]);
		FileOutputStream fos;
		try {
			fos=new FileOutputStream("myAudio.webm");
			fos.write(decodedByte);
			fos.close();
		}catch(IOException ex) {
			
		}
		String audioURL =upload.upload("test.webm", new FileInputStream("myAudio.webm"));
		postView.addObject("audioURL",audioURL);
		return postView;
		
	}

}
