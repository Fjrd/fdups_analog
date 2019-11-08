import utils.FileUtils;
import java.io.*;
import java.util.*;

import static utils.Utils.*;

public class Main {
    static File folderWithFiles; //папка с файлами
    static File resultFile;
    static List<File> listOfFiles = new ArrayList<>();
    static Set<File> duplicatesSet = new HashSet<>();

    public static void main(String[] args) throws IOException{
        if(args.length == 0 || args.length == 1
        )
        {
            System.out.println("Missing an arguments: 1.Path to folder with files to deduplicate, 2.Path to result file");
            System.exit(0);
        }
        folderWithFiles = new File(args[0]);
        resultFile = new File(args[1]);

        // 1. create list of files
        listOfFiles = addFilesToList(folderWithFiles);

        // 2. find files with same length, compare hashes, return duplicates
        duplicatesSet = findDuplicates(listOfFiles);

        if (!duplicatesSet.isEmpty()){
            System.out.println("Duplicates found, see result file: " + args[1]);
            writeOutputFile(duplicatesSet, resultFile);
        }
        else {
            System.out.println("No duplicates found");
        }
    }
}
