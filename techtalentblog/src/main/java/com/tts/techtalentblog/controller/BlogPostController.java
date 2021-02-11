package com.tts.techtalentblog.controller;

import com.tts.techtalentblog.model.BlogPost;
import com.tts.techtalentblog.repo.BlogPostRepository;
import com.tts.techtalentblog.service.BlogPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class BlogPostController {

    @Autowired
    private BlogPostService service;
    private static List<BlogPost> posts = new ArrayList<>();

    @GetMapping(value="/")
    public String index(BlogPost blogPost, Model model) {
        posts = service.listAll();
        model.addAttribute("posts", posts);
        return "blogPost/index";
    }

    private BlogPost blogPost;

    @PostMapping(value = "/blogPost")
    public String addNewBlogPost(BlogPost blogPost, Model model) {
        service.addBlogPost(new BlogPost(blogPost.getTitle(), blogPost.getAuthor(), blogPost.getBlogEntry()));
        posts = service.listAll();
        model.addAttribute("title", blogPost.getTitle());
        model.addAttribute("author", blogPost.getAuthor());
        model.addAttribute("blogEntry", blogPost.getBlogEntry());
        return "blogPost/result";
    }
    @GetMapping(value="/blogPost/CreatePost")
    public String newBlog (BlogPost blogPost){
        return "blogPost/CreatePost";
    }
    @RequestMapping(value="/blogPost/{id}", method= RequestMethod.DELETE)
    public String deletePostWithId(@PathVariable Long id, BlogPost blogPost){
        service.deleteBlogPost(id);
        return "blogPost/index";
    }

}

