package com.serendipity.controllers;

import com.serendipity.services.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ImagesController {

    private ImageService imageService;

    class ImageResponse {
        String base64Image;

        public String getBase64Image() {
            return base64Image;
        }

        public void setBase64Image(String base64Image) {
            this.base64Image = base64Image;
        }
    }

    @Autowired
    public ImagesController(ImageService imageService) {
        this.imageService = imageService;
    }

    @RequestMapping(value = "/images/{type}/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ImageResponse getImage(
            @PathVariable("type") String type,
            @PathVariable("id") long id) {

        ImageResponse response = new ImageResponse();
        String base64Image = imageService.getImage(type, id);
        response.setBase64Image(base64Image);

        return response;
    }
}
