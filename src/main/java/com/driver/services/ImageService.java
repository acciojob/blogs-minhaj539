package com.driver.services;

import com.driver.models.*;
import com.driver.repositories.BlogRepository;
import com.driver.repositories.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageService {
    @Autowired
    ImageRepository imageRepository2;

    @Autowired
    BlogRepository blogRepository;

    public Image createAndReturn(Blog blog, String description, String dimensions){
        //create an image based on given parameters and add it to the imageList of given blog
        /* public void createAndReturnBlog(Integer userId, String title, String content) {
        User user=userRepository1.findById(userId).get();
        Blog blog=new Blog();
        blog.setTitle(title);
        blog.setContent(content);
        blog.setPubDate(new Date());
        List<Blog> blogList=user.getBlogList();
        blogList.add(blog);
        user.setBlogList(blogList);
        blog.setUser(user);
        userRepository1.save(user);*/

        Image image=new Image();
        image.setDescription(description);
        image.setDimensions(dimensions);
        List<Image> imageList=blog.getImageList();
        imageList.add(image);
        blog.setImageList(imageList);
        image.setBlog(blog);
        blogRepository.save(blog);
        return image;  //edited by me

    }

    public void deleteImage(Image image){
        if(image!=null) {
            int image_id = image.getId();
            imageRepository2.deleteById(image_id);
        }
         return;
    }

    public Image findById(int id) {
        Image image = imageRepository2.findById(id).get();
      return image; //written by me
    }

    public int countImagesInScreen(Image image, String screenDimensions) {
        //Find the number of images of given dimensions that can fit in a screen having `screenDimensions`
        //In case the image is null, return 0
        int cnt=0;

      //  int image_id=image.getId();
      //  Image getImage= imageRepository2.findById(image_id).get();
       // String imgDimensions=getImage.getDimensions();

        String imgDimensions=image.getDimensions();
        String row="",col="";
        int i=0;
        while(imgDimensions.charAt(i)!='X'){
            i++;
        }

        row=imgDimensions.substring(0,i);
        col=imgDimensions.substring(i+1);
        int imgX=Integer.parseInt(row);
        int imgY=Integer.parseInt(col);

        String screenRow="",screenCol="";
        int j=0;
        while(screenDimensions.charAt(j)!='X'){
            j++;
        }
        screenRow=screenDimensions.substring(0,j);
        screenCol=screenDimensions.substring(j+1);

        int scX=Integer.parseInt(screenRow);
        int scY=Integer.parseInt(screenCol);
        for(int y=0;y<=scX;y++){
            for(int x=0;x<=scY;x++) {
                if((y + imgY <= scY)&&(x + imgX <= scX)) {
                    cnt++;
                    x=x+imgX-1;
                }
            }
            y=y+imgY-1;
        }

        return cnt; //written by me
    }
}
