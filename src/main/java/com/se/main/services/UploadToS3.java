/**
 * 
 */
package com.se.main.services;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.se.main.Repository.CountObjectRepository;
import com.se.main.pojos.CountObject;

/**
 * @author Glen
 *
 */
@Service
public class UploadToS3 {
	@Value("#{environment.accessKey}")
	String accessKey;
	
	@Value("#{environment.privateKey}")
	String privateKey;
	
	@Autowired
	CountObjectRepository countObjectRepository;
	public String upload(String name, InputStream fs) {
		//CountObject co=new CountObject();
		//co=countObjectRepository.save(co);
		BasicAWSCredentials creds = new BasicAWSCredentials(accessKey,privateKey);
		AmazonS3 amazonS3 = AmazonS3ClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider(creds))
				.withRegion(Regions.US_EAST_2).build();
		
		String uniqueName=UUID.randomUUID().toString()+name;
		PutObjectRequest putreq = new PutObjectRequest("myamazonbucketgs794734", uniqueName,
				fs, new ObjectMetadata()).withCannedAcl(CannedAccessControlList.PublicRead);

		amazonS3.putObject(putreq);
		
		String url = "http://" + "myamazonbucketgs794734" + ".s3.amazonaws.com/" + uniqueName;
System.out.println("url:"+url);
		return url;
	}
}
