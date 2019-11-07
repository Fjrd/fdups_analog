import utils.FileUtils;

import java.io.*;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

public class Main {
    static File folderWithFiles; //папка с файлами
    static File resultFile;
    static List<File> listOfFiles = new CopyOnWriteArrayList<File>();
    static Set<File> noDuplicatesList = new HashSet<File>();

    public static List<File> addFilesToList(File folder){
        for (File file : folder.listFiles())
        {
            if (file.isDirectory())
            {
                addFilesToList(file);
                continue;
            }

            listOfFiles.add(file);
        }
        return listOfFiles;
    }

    //?удалить из списка файлы с размером > 50 байт
    public static void removeFileWithLengthMore50(List<File> listOfFiles) {
        for (File file : listOfFiles) {
            if (file.length() > 50){
                FileUtils.deleteFile(file);
                listOfFiles.remove(file);
            }
        }
    }

    public static void main(String[] args) throws IOException, NoSuchAlgorithmException {
        folderWithFiles = new File(args[0]);
        resultFile = new File(args[1]);

        //?
        listOfFiles = addFilesToList(folderWithFiles);

        //?сортировка файлов по размеру
        Collections.sort(listOfFiles, new Comparator<File>() {
            @Override
            public int compare(File o1, File o2) {
                if (o1.length() > o2.length())
                    return 1;
                else if (o1.length() == o2.length())
                    return 0;
                else return -1;
            }
        });

        noDuplicatesList = FileUtils.findDuplicatesOld(listOfFiles);

        for (File file :
                listOfFiles) {
            System.out.println(file.getName());
            System.out.println(FileUtils.makeHash(file));
        }

        //удалить файл output.txt, если он существует
        if (FileUtils.isExist(resultFile)){
            FileUtils.deleteFile(resultFile);
        }

        //write output file
        FileWriter writer = new FileWriter(resultFile, true);
        //writer.write("dir" + '\t' + "file name" + '\t' + "file size" + '\n');
        for (File file : noDuplicatesList) {
            try
            {
                writer.append(file.getName());
                writer.append('\t');
                writer.append(Long.toString(file.length()));
                writer.append('\t');
                writer.write(file.getParent());
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
