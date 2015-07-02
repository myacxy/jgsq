package net.myacxy.jgsq.misc;

import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Utilities
{
    /**
     * ^0black ^1red ^2green ^3yellow ^4blue ^5cyan ^6purple ^7white ^8orange ^9gray
     *
     * @param coloredString
     * @return uncolored string
     */
    public static String removeColorCode(String coloredString)
    {
        return coloredString.replaceAll("(\\^\\d)|(\\$\\d)", "");
    }

    public static Path getAbsoluteResourceFilePath(String fileName)
    {
        URL resource = Utilities.class.getClassLoader().getResource(fileName);
        return Paths.get(resource.getPath().substring(1));
    }
}
