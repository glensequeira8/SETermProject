/**
 * 
 */
package com.se.main.Controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
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
import com.se.main.Repository.FriendsRepository;
import com.se.main.Repository.UserRepository;
import com.se.main.pojos.Friends;
import com.se.main.pojos.FriendsId;
import com.se.main.pojos.User;




/**
 * @author Glen
 *
 */
@Controller
@SessionAttributes(value= {"user"})
public class ProfilePageController {
	@Value("#{environment.accessKey}")
	String accessKey;
	
	@Value("#{environment.privateKey}")
	String privateKey;
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private FriendsRepository friendsRepo;


	public ProfilePageController() {
		//userService=new UserService();
	}
	public UserRepository getUserRepo() {
		return userRepo;
	}
	public void setUserRepo(UserRepository userRepo) {
		this.userRepo = userRepo;
	}
	@PostMapping(value = "/picupload")
	public ModelAndView uploadFile(@RequestParam("file") MultipartFile image, @RequestParam("description") String description,@ModelAttribute("user") User user) {
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
			
		
		
			user.setPhotoLink(imgurl);
			user.setDescription(description);
			userRepo.save(user);
			profilepg.addObject("image", imgurl);
			profilepg.addObject("description",description);
			profilepg.addObject("name",user.getName());
			profilepg.addObject("friends",user.getFriends());

			profilepg.setViewName("profilePage");
			return profilepg;


	} catch (IOException e) {

			e.printStackTrace();
			ModelAndView errorView = new ModelAndView();
			errorView.setViewName("errorPage");

			return profilepg;
		}
	
	}	
	
	@GetMapping(value="/facebook")
	public ModelAndView renderPage() {
		ModelAndView index=new ModelAndView();
		index.setViewName("facebookIndex");
		return index;
	}
	
	
	
	
	@GetMapping(value="/")
	public ModelAndView renderPage1() {
		ModelAndView index=new ModelAndView();
		index.setViewName("facebookIndex");
		return index;
	}
	
	
	@PostMapping(value="/facebookRedirect")
	public ModelAndView redirectPage(Model model,
			@RequestParam(name="myId") String myId,
			@RequestParam(name="myName") String myName,
			@RequestParam(name="myFriends") String myFriends,
			@RequestParam(name="myEmail") String myEmail,
			HttpServletRequest req
			) {
		String[] splitted= myFriends.split("/");
		System.out.println(splitted);
		ModelAndView index=new ModelAndView();
		User user=userRepo.findByUserid(myId);
		if(user==null) {
			User newUser=new User();
			newUser.setUserid(myId);
			newUser.setDescription("");
			newUser.setEmail(myEmail);
			
			newUser.setName(myName);
			newUser.setPhotoLink("");
			newUser.setFriends(createFriendsSet(newUser, splitted));
			System.out.println("user data:"+newUser);
			if(!model.containsAttribute("user")) {
				model.addAttribute(newUser);
			}

			index.setViewName("createProfile");

		}
		else {
			user.setFriends(checkFriends(user, splitted,myFriends));
			index.addObject("image", user.getPhotoLink());
			index.addObject("name",user.getName());
			index.addObject("description",user.getDescription());
			index.addObject("friends",user.getFriends());
			index.setViewName("profilePage");
		}
		return index;
	}

	private List<Friends> createFriendsSet(User user,String[]  splitted){
		List<Friends> friends= new ArrayList<>();

		for(int i=0;i<splitted.length;i=i+2) {
			if((splitted.length==1) && splitted[0]=="") {
				
			}else {			
			Friends newfriend =new Friends();
			FriendsId fId=new FriendsId();
			fId.setFriendid(splitted[i]);
			fId.setUser(user);
			newfriend.setId(fId);
			newfriend.setFriendName(splitted[i+1]);
			friends.add(newfriend);
			}
		}
		return friends;
	}

	
	private List<Friends> checkFriends(User user,String[] splitted,String myFriends){
		List<Friends> friends= new ArrayList<>();
			List<Friends> savedFriends=user.getFriends();
			for(int i=0;i<splitted.length;i=i+2) {
				FriendsId fId=new FriendsId();
				fId.setUser(user);
				fId.setFriendid(splitted[i]);
				Friends friend=friendsRepo.findById(fId);
				if(friend==null) {
			
					Friends newfriend =new Friends();
			
					newfriend.setId(fId);
					newfriend.setFriendName(splitted[i+1]);
					friendsRepo.save(newfriend);
					friends.add(newfriend);
				
				}else {
					friends.add(friend);
				
				}
			}
				for(Friends f:savedFriends) {
					if(!myFriends.contains(f.getId().getFriendid())) {
					friendsRepo.delete(f);
					friends.remove(f);
					}
				}

		
		return friends;
		
		
	}
}
