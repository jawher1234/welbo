package tn.esprit.spring.service;

import tn.esprit.spring.entity.Tag;


import java.util.List;

public interface ITagService {
    public Tag ajouterTag(Tag tag);
    public void deleteTag(Long tagId);
    public List<Tag> getTags();
    public Tag modifierTag(Tag tag);
    public Tag getTag(Long tagId);
    public List<Tag> addTagsToPost (Long NewsfeedPostId, List<Tag> tagRequest);
}
