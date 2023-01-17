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

    public Image createAndReturn(Blog blog, String description, String dimensions){//changed blog to newblog
        Image image = new Image();
        image.setDescription(description);
        image.setDimensions(dimensions);
        image.setBlog(blog);
        imageRepository2.save(image);
        return image;
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
        if(image==null) return 0;
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

        col=imgDimensions.substring(0,i);
        row=imgDimensions.substring(i+1);
        int imgY=Integer.parseInt(row);
        int imgX=Integer.parseInt(col);

        String screenRow="",screenCol="";
        int j=0;
        while(screenDimensions.charAt(j)!='X'){
            j++;
        }
        screenCol=screenDimensions.substring(0,j);
        screenRow=screenDimensions.substring(j+1);

        int scY=Integer.parseInt(screenRow);
        int scX=Integer.parseInt(screenCol);
        for(int y=0;y<=scY;y++){
            for(int x=0;x<=scX;x++) {
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
