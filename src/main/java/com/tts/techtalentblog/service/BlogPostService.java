package com.tts.techtalentblog.service;


import com.tts.techtalentblog.model.BlogPost;
import com.tts.techtalentblog.repo.BlogPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BlogPostService {
    @Autowired
    private BlogPostRepository repo;

    public void addBlogPost(BlogPost blogPost){
        repo.save(blogPost);
    }

    public List<BlogPost> listAll(){
        return (List<BlogPost>)
                repo.findAll();
    }

    public List<BlogPost> searchByKeyword(String keyword){
        return repo.search(keyword);
    }
    public Optional<BlogPost> findById(Long id){
        return repo.findById(id);
    }

    public BlogPost getBlogPost(Long id){
        return repo.findById(id).get();
    }

    public void deleteBlogPost(Long id){
        repo.deleteById(id);
    }



}

