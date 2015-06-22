package com.ser.com.ser.util;

import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by Bradley
 * on 6/21/2015.
 */
public enum FileUtility {
    ;

    public static Path getPathForResource(String resource){
        URL url = FileUtility.class.getClassLoader().getResource(resource);
        if(url != null) {
            try {
                return Paths.get(url.toURI());
            } catch (URISyntaxException e) {
                //todo add logging
            }
        }
        return null;
    }
}
