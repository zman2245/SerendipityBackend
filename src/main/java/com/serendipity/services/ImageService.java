package com.serendipity.services;

import com.serendipity.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Service
public class ImageService {

    public static final String IMG_BASE_NAME = "SERIMG_";
    public static final String IMG_MEMORIES_DIR = "memories";
    public static final String IMG_HOTSPOTS_DIR = "hotspots";
    public static final String IMG_USERS_DIR = "users";

    private final Environment env;
    private final String baseDir;
    private final String baseUrl;

    @Autowired
    public ImageService(Environment env) {
        this.env = env;

        baseDir = env.getProperty("service.image.basedir");
        baseUrl = env.getProperty("service.image.host");

        if (StringUtils.isEmpty(baseDir)) {
            throw new IllegalStateException("Environment missing service.image.basedir configuration");
        }
        if (StringUtils.isEmpty(baseUrl)) {
            throw new IllegalStateException("Environment missing service.image.host configuration");
        }

        // init directories as needed
        makeDirsAsNeeded(IMG_MEMORIES_DIR);
        makeDirsAsNeeded(IMG_HOTSPOTS_DIR);
        makeDirsAsNeeded(IMG_USERS_DIR);
    }

    private void makeDirsAsNeeded(String lastDir) {
        File dir = new File(baseDir, lastDir);
        if (!dir.exists()) {
            dir.mkdirs();
        }
    }

    /**
     * TODO: handle overwrite
     *
     * @param memoryId
     * @param base64Image
     * @return Url to the memory (must match up with ImageRestController)
     */
    public String saveImageForMemory(long memoryId, String base64Image) {
        String filename = IMG_BASE_NAME + memoryId;
        decodeToFile(base64Image,
                baseDir + File.separator + IMG_MEMORIES_DIR,
                filename);

        return baseUrl + "/images/memories/" + memoryId;
    }

    public String getImage(String type, long id) {
        String typeDir = "";
        switch (type) {
            case "memories":
                typeDir = IMG_MEMORIES_DIR;
                break;

            case "users":
                typeDir = IMG_USERS_DIR;
                break;

            case "hotspots":
                typeDir = IMG_HOTSPOTS_DIR;
                break;

            default:
                throw new ResourceNotFoundException(type + " not recognized");
        }

        String results = encodeFromFile(baseDir + File.separator + typeDir,
                IMG_BASE_NAME + id);

        return results;
    }

    private String encodeFromFile(String dir, String imagePath) {
        File file = new File(dir, imagePath);
        try (FileInputStream imageInFile = new FileInputStream(file)) {
            // Reading a Image file from file system
            String base64Image = "";
            byte imageData[] = new byte[(int) file.length()];
            imageInFile.read(imageData);
            base64Image = Base64.getEncoder().encodeToString(imageData);
            return base64Image;
        } catch (FileNotFoundException e) {
            throw new ResourceNotFoundException("Image not found");
        } catch (IOException ioe) {
            throw new RuntimeException(ioe);
        }
    }

    private void decodeToFile(String base64Image, String dir, String filename) {
        File f = new File(dir, filename);
        try {
            FileOutputStream imageOutFile = new FileOutputStream(f);
            // Converting a Base64 String into Image byte array
            byte[] imageByteArray = Base64.getDecoder().decode(base64Image.getBytes(StandardCharsets.UTF_8));
            imageOutFile.write(imageByteArray);
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Problem writing file (FileNotFound). Probably configuration issue.", e);
        } catch (IOException ioe) {
            throw new RuntimeException("Problem writing file", ioe);
        }
    }
}
