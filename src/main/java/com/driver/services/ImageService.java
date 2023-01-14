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


        int blog_id=blog.getId();
        Blog currBlog=blogRepository.findById(blog_id).get();
        Image newImage=new Image();
        newImage.setDescription(description);
        newImage.setDimensions(dimensions);
        List<Image> listOfImages=currBlog.getImageList();
        listOfImages.add(newImage);
        currBlog.setImageList(listOfImages);
        newImage.setBlog(currBlog);
        blogRepository.save(currBlog);
        return newImage;  //edited by me
    }

    public void deleteImage(Image image){
        if(image!=null) {
            int image_id = image.getId();
            imageRepository2.deleteById(image_id);
        }
         return;
    }

    public Image findById(int id) {
        Image image;
        try {
            image = imageRepository2.findById(id).get();
        }
        catch (Exception e){
            image=null;
        }
      return image; //written by me
    }

    public int countImagesInScreen(Image image, String screenDimensions) {
        //Find the number of images of given dimensions that can fit in a screen having `screenDimensions`
        //In case the image is null, return 0
        int cnt=0;

        int image_id=image.getId();
        Image getImage= imageRepository2.findById(image_id).get();
        String imgDimensions=getImage.getDimensions();
       // String imgDimensions=image.getDimensions();
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
