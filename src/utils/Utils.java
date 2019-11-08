package utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Utils {

    public static List<File> addFilesToList(File folder){
        List<File> list = new ArrayList<>();
        for (File file : folder.listFiles())
        {
            if (file.isDirectory())
            {
                list.addAll(addFilesToList(file));
                continue;
            }

            list.add(file);
        }
        return list;
    }

    public static void sortList(List <File> list){
        Collections.sort(list, new Comparator<File>() {
            @Override
            public int compare(File o1, File o2) {
                if (o1.length() > o2.length())
                    return 1;
                else if (o1.length() == o2.length())
                    return 0;
                else return -1;
            }
        });
    }

    public static Set<File> findDuplicates(List<File> list) throws IOException {
        Set<File> duplicates = new HashSet<>();
        for (int i = 0; i < list.size(); i++) {
            for (int j = i + 1; j < list.size(); j++) {
                if (list.get(i).length() == list.get(j).length()){
                    if (FileUtils.makeMD5Hash(list.get(i)).equals(FileUtils.makeMD5Hash(list.get(j)))){
                        duplicates.add(list.get(j));
                    }
                }
            }
        }
        return duplicates;
    }

    public static void writeOutputFile (Set<File> set, File output) throws IOException {

        //delete file output.txt if such file exists
        if (output.exists()){
            FileUtils.deleteFile(output);
        }

        //write output file
        FileWriter writer = new FileWriter(output, true);
        writer.append("File name" + '\t' + "file size" + '\t' + "path" + '\n');
        for (File file : set) {
            try
            {
                writer.append(file.getName());
                writer.append('\t');
                writer.append(Long.toString(file.length()));
                writer.append('\t');
                writer.append(file.getParent());
                writer.append('\t');
                writer.append('\n');
                writer.flush();
            }
            catch(IOException ex){
                System.out.println(ex.getMessage());
            }
        }
    }
}
