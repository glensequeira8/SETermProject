/**
 * 
 */
package com.se.main.Controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;

/**
 * @author Glen
 *
 */
@Controller
public class ProfilePageController {
	@Value("#{environment.accessKey}")
	String accessKey;
	
	@Value("#{environment.privateKey}")
	String privateKey;
		
	@PostMapping(value = "/picupload")
	public ModelAndView uploadFile(@RequestParam("file") MultipartFile image) {
		ModelAndView profilepg = new ModelAndView();
/*		accessKey = System.getenv("accessKey");

		privateKey = System.getenv("privateKey");*/	
		BasicAWSCredentials creds = new BasicAWSCredentials(accessKey,privateKey);
		AmazonS3 amazonS3 = AmazonS3ClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider(creds))
				.withRegion(Regions.US_EAST_2).build();
		try {
			PutObjectRequest putreq = new PutObjectRequest("myamazonbucketgs794734", image.getOriginalFilename(),
					image.getInputStream(), new ObjectMetadata()).withCannedAcl(CannedAccessControlList.PublicRead);
			amazonS3.putObject(putreq);
			String imgurl = "http://" + "myamazonbucketgs794734" + ".s3.amazonaws.com/" + image.getOriginalFilename();
			profilepg.addObject("image", imgurl);
			profilepg.setViewName("profilePage");
			return profilepg;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			profilepg.setViewName("errorPage");
			return profilepg;
		}
	}	
	
	@GetMapping(value="/")
	public ModelAndView renderPage() {
		ModelAndView index=new ModelAndView();
		index.setViewName("index");
		return index;
	}
	
	
	
}
