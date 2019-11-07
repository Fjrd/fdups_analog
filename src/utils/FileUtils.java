package utils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;

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
                System.out.println("oppa!");
                duplicates.add(file);
            }
        }
        return noDuplSet;
    }

    public static String makeHash(File file) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        try (InputStream is = Files.newInputStream(file.toPath());
             DigestInputStream dis = new DigestInputStream(is, md))
        {

        } catch (IOException e) {
            e.printStackTrace();
        }
        byte[] digest = md.digest();
        BigInteger no = new BigInteger(1, digest);
        String hashtext = no.toString(16);
        while (hashtext.length() < 32) {
            hashtext = "0" + hashtext;
        }
        return hashtext;
    }


}