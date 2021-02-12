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
import java.util.Optional;

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
    @RequestMapping(value = "/blogPost/{id}", method = RequestMethod.DELETE)
    public String deletePostWithId(@PathVariable Long id, BlogPost blogPost, Model model) {
        service.deleteBlogPost(id);
        posts = service.listAll();
        model.addAttribute("posts", posts);
        return "blogPost/index";
    }

    @RequestMapping(value = "/blogPost/{id}", method = RequestMethod.GET)
    public String editPostWithId(@PathVariable Long id, BlogPost blogPost, Model model) {
        Optional<BlogPost> post = service.findById(id);
        if (post.isPresent()) {
            BlogPost actualPost = post.get();
            model.addAttribute("blogPost", actualPost);
        }

        return "blogPost/edit";
    }
    @RequestMapping(value = "/blogPost/update/{id}", method = RequestMethod.POST)
    public String updateExistingPost(@PathVariable Long id, BlogPost blogPost, Model model) {
        Optional<BlogPost> post = service.findById(id);
        if (post.isPresent()) {
            BlogPost actualPost = post.get();
            actualPost.setTitle(blogPost.getTitle());
            actualPost.setAuthor(blogPost.getAuthor());
            actualPost.setBlogEntry(blogPost.getBlogEntry());
            service.addBlogPost(actualPost);
            model.addAttribute("blogPost", actualPost);
            model.addAttribute("title", blogPost.getTitle());
            model.addAttribute("author", blogPost.getAuthor());
            model.addAttribute("blogEntry", blogPost.getBlogEntry());

        }
        return "blogPost/result";
    }
}

