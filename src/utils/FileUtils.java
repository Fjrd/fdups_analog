package utils;

import java.io.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.apache.commons.codec.digest.DigestUtils;

public class FileUtils {

    public static void deleteFile(File file) {
        if (!file.delete())
            System.out.println("Can not delete file with name " + file.getName());
    }

    public static void renameFile(File source, File destination) {
        if (!source.renameTo(destination))
            System.out.println("Can not rename file with name " + source.getName());
    }

    public static boolean isExist(File file) {
        return file.exists();
    }

    public static Set<File> findDuplicatesOld(List<File> list){
        Set<File> noDuplSet = new HashSet<File>();
        Set<File> duplicates = new HashSet<File>();
        for (File file :
                list) {
            if (!noDuplSet.add(file)) {
                duplicates.add(file);
            }
        }
        return noDuplSet;
    }

    public static String makeMD5Hash(File file) throws IOException {
        //Using Apache Commons Codec 1.8
        //From <!-- https://mvnrepository.com/artifact/commons-codec/commons-codec -->
        String checksum = DigestUtils.md5Hex(new FileInputStream(file));
        return checksum;
    }


}