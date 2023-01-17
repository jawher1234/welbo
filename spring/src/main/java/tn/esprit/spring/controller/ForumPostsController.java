package tn.esprit.spring.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import tn.esprit.spring.entity.ForumPosts;
import tn.esprit.spring.entity.Likes;
import tn.esprit.spring.service.IForumPostsService;

@RestController
@RequestMapping("/ForumPosts")
public class ForumPostsController {
	@Autowired
	IForumPostsService ForumPostsService;
	@Value("{file.upload-dir")
	String FILE_DIRECTORY;
	
	//http://localhost:8083/PIDEV/ForumPosts/retrieve-all-ForumPosts
	@GetMapping("/retrieve-all-ForumPosts")
	public List<ForumPosts> getForumPosts() {
	List<ForumPosts> listForumPosts = ForumPostsService.retrieveAllForumPosts();
	return listForumPosts;
	}
	
	//http://localhost:8083/PIDEV/ForumPosts/add-ForumPost
	@PostMapping("/add-ForumPost")
	@ResponseBody
	public ForumPosts addForumPost(@RequestBody ForumPosts c)
	{
		c.setUploadTime(new Timestamp(System.currentTimeMillis()));
		ForumPosts ForumPost = ForumPostsService.addForumPost(c);
		return ForumPost;
	}
	
	//http://localhost:8083/PIDEV/ForumPosts/add-ForumPostWithAttachement
	@PostMapping("/add-ForumPostWithAttachement")
	public @ResponseBody ResponseEntity<?> createForumPost(@RequestParam("userId") Long UserId, @RequestParam("description") String Description,@RequestParam("categorieId") Long CategorieId,Model model, HttpServletRequest request
			,final @RequestParam("attachement") MultipartFile file) {
		try {
			String uploadDirectory = request.getServletContext().getRealPath(FILE_DIRECTORY);
			String fileName = file.getOriginalFilename();
			String filePath = Paths.get("C:\\Products\\sts-bundle\\workspace-sts\\PIDEV\\pide\\src\\main\\resources\\image", fileName).toString();
			if (fileName == null || fileName.contains("..")) {
				model.addAttribute("invalid", "Sorry! Filename contains invalid path sequence \" + fileName");
				return new ResponseEntity<>("Sorry! Filename contains invalid path sequence " + fileName, HttpStatus.BAD_REQUEST);
			}
		
			try {
				File dir = new File(uploadDirectory);
				if (!dir.exists()) {
					dir.mkdirs();
				}
				// Save the file locally
				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(filePath)));
				stream.write(file.getBytes());
				stream.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			byte[] imageData = file.getBytes();
			ForumPosts ForumPost = new ForumPosts();
			ForumPost.setUserId(UserId);
			ForumPost.setAttachement(imageData);
			ForumPost.setDescription(Description);
			ForumPost.setCategorieId(CategorieId);
			ForumPost.setUploadTime(new Timestamp(System.currentTimeMillis()));
			
			
			ForumPostsService.addForumPost(ForumPost);
			return new ResponseEntity<>("ForumPost Saved With file - " + fileName, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	//http://localhost:8083/PIDEV/ForumPosts/remove-ForumPost/{id}
	@DeleteMapping("/remove-ForumPost/{id}")
	@ResponseBody
	public void removeForumPost(@PathVariable("id") Long id) {
		ForumPostsService.deleteForumPost(id);
	}
	
	//http://localhost:8083/PIDEV/ForumPosts/modify-ForumPost
	@PutMapping("/modify-ForumPost")
	@ResponseBody
	public ForumPosts modifyForumPost(@RequestBody ForumPosts ForumPost) {
	return ForumPostsService.updateForumPost(ForumPost);
	}
	
	//http://localhost:8083/PIDEV/ForumPosts/retrieve-ForumPosts-by-category/{idcateg}
		@GetMapping("/retrieve-ForumPosts-by-category/{idcateg}")
		public List<ForumPosts> getForumPostsByCategory(@PathVariable("idcateg") Long idcateg) {
		List<ForumPosts> listForumPosts = ForumPostsService.retrievePostByCateg(idcateg);
		return listForumPosts;
		}
		
		//http://localhost:8083/PIDEV/ForumPosts/retrieve-all-ForumPostLikes/{forumpostid}
		@GetMapping("/retrieve-all-ForumPostLikes/{forumpostid}")
		public List<Likes> getForumPostLikes(@PathVariable("forumpostid") Long forumpostid) {
		List<Likes> PostLikes = ForumPostsService.retrievePostLikes(forumpostid);
		return PostLikes;
		}
}
