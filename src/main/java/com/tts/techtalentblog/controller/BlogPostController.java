package com.tts.techtalentblog.controller;

import com.tts.techtalentblog.model.BlogPost;
import org.springframework.stereotype.Controller;

@Controller
public class BlogPostController {

    public String index(BlogPost blogPost) {
        return "blogpost/index";
    }

}
