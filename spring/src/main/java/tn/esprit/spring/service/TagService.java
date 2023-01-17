package tn.esprit.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.spring.entity.Tag;
import tn.esprit.spring.repository.NewsFeedPostRepository;
import tn.esprit.spring.repository.TagRepository;

import java.util.List;

@Service
public class TagService implements ITagService {

    @Autowired
    TagRepository tagRepository;
    @Autowired
    NewsFeedPostRepository newsFeedPostRepository;

    @Override
    public Tag ajouterTag(Tag tag) {
        tagRepository.save(tag);
        return tag;
    }



    @Override
    public void deleteTag(Long tagId) {
        tagRepository.deleteById(tagId);

    }

    @Override
    public List<Tag> getTags() {

        return (List<Tag>) tagRepository.findAll();
    }

    @Override
    public Tag modifierTag( Tag tag) {
        tagRepository.save(tag);
        return tag;
    }

    @Override
    public Tag getTag(Long tagId) {
        return tagRepository.findById(tagId).get();
    }

    @Override
    public List<Tag> addTagsToPost(Long NewsfeedPostId, List<Tag> tagRequest) {
        Tag tag = new Tag();
        for (Tag tag12 : tagRequest){
            tag = newsFeedPostRepository.findById(NewsfeedPostId).map(NewsfeedPost -> {

                long tagId = tag12.getId();
                // tag is existed
                if (tagId != 0L) {
                    Tag _tag = tagRepository.findById(tagId).get();
                    NewsfeedPost.addTag(_tag);
                    newsFeedPostRepository.save(NewsfeedPost);
                    return _tag;
                }
                // add and create new Tag
                NewsfeedPost.addTag(tag12);


                return tagRepository.save(tag12);
            }).get();
        }
        return tagRequest;
    }


}
