package net.myacxy.jgsq.utils;

import java.io.InputStream;

public class ResourceUtil {

    public ResourceUtil() {
        throw new IllegalAccessError();
    }

    public static InputStream getResourceAsStream(String fileName) {
        return ResourceUtil.class.getResourceAsStream(fileName);
    }
}
