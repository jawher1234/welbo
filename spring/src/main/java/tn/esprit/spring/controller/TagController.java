package tn.esprit.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.entity.Tag;
import tn.esprit.spring.service.ITagService;

import java.util.List;

@RestController
@RequestMapping(path ="/api/tags")
public class TagController {

    @Autowired
    ITagService iTagService;



   @GetMapping
   @RequestMapping(path ="/")
    public List<Tag> getTags(){
       return iTagService.getTags();
   }

    @GetMapping({"/{id}"})
   public Tag getTag(@PathVariable Long id){
       return iTagService.getTag(id);
   }

    @PostMapping
    @RequestMapping(path = "/new")
    @ResponseBody
    public Tag add(@RequestBody Tag tag){

        return iTagService.ajouterTag(tag);
    }

    @PutMapping
    @RequestMapping(path = "/edit")
    @ResponseBody
    public Tag edit(@RequestBody Tag tag){
       return iTagService.modifierTag(tag);
    }

    @DeleteMapping({"/delete/{id}"})
    public void delete(@PathVariable Long id){
       iTagService.deleteTag(id);
    }

   /* @PostMapping("/NewsfeedPosts/{NewsfeedPostId}/tags")
    public ResponseEntity<Tag> addTag(@PathVariable(value = "NewsfeedPostId") Long NewsfeedPostId, @RequestBody Tag tagRequest) {
        Tag tag = newsFeedPostRepository.findById(NewsfeedPostId).map(NewsfeedPost -> {

            long tagId = tagRequest.getId();

            // tag is existed
            if (tagId != 0L) {
                Tag _tag = tagRepository.findById(tagId).get();
                NewsfeedPost.addTag(_tag);
                newsFeedPostRepository.save(NewsfeedPost);
                return _tag;
            }

            // add and create new Tag
            NewsfeedPost.addTag(tagRequest);
            return tagRepository.save(tagRequest);
        }).get();
        return new ResponseEntity<>(tag, HttpStatus.CREATED);
    }*/
    @PostMapping("/NewsfeedPosts/{NewsfeedPostId}/tags")
    public ResponseEntity<List<Tag>> addTagsToPost(@PathVariable(value = "NewsfeedPostId") Long NewsfeedPostId, @RequestBody List<Tag> tagRequest) {
        return  new ResponseEntity<>(iTagService.addTagsToPost(NewsfeedPostId,  tagRequest), HttpStatus.CREATED);
    }

}
