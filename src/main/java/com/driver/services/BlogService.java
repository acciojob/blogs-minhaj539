package com.driver.services;

import com.driver.models.Blog;
import com.driver.models.Image;
import com.driver.models.User;
import com.driver.repositories.BlogRepository;
import com.driver.repositories.ImageRepository;
import com.driver.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class BlogService {
    @Autowired
    BlogRepository blogRepository1;

    @Autowired
    ImageService imageService1;

    @Autowired
    ImageRepository imageRepository;
    @Autowired
    UserRepository userRepository1;

    public List<Blog> showBlogs(){
        List<Blog> allBlogs=blogRepository1.findAll();
        return allBlogs;    //written by me
        //find all blogs

    }

    public void createAndReturnBlog(Integer userId, String title, String content) {
        User user=userRepository1.findById(userId).get();
        Blog blog=new Blog();
        blog.setTitle(title);
        blog.setContent(content);
        List<Blog> blogList=user.getListOfBlogs();
        blogList.add(blog);
        user.setListOfBlogs(blogList);
        blog.setUser(user);
        userRepository1.save(user);


        //create a blog at the current time

        //updating the blog details

        //Updating the userInformation and changing its blogs

    }

    public Blog findBlogById(int blogId){

        return null; //written by me
        //find a blog
    }

    public void addImage(Integer blogId, String description, String dimensions){

      Blog newBlog=blogRepository1.findById(blogId).get();
      Image image=imageService1.createAndReturn(newBlog,description,dimensions);
      List<Image> imageList=newBlog.getListOfImages();
      imageList.add(image);
      newBlog.setListOfImages(imageList);
      //blogRepository1.save(newBlog);


        //add an image to the blog after creating it
    }

    public void deleteBlog(int blogId){

        blogRepository1.deleteById(blogId);
        //delete blog and corresponding images
    }
}