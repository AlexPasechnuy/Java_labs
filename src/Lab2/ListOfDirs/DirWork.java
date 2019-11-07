package Lab2.ListOfDirs;

import java.io.*;
import java.util.*;

public class DirWork {
    public static void showFiles(String path, int tabs){
        File dir = new File(path);
        if (!dir.isDirectory()) {
            System.out.println("Wrong name");
            return;
        }
        File[] list = dir.listFiles();
            for (File file : list) {
                for (int i = 0; i < tabs; i++){System.out.print("   ");}
                System.out.println(file.getName());
                if (file.isDirectory())
                    showFiles(file.getPath(), tabs + 1);
            }
    }

    public static void main(String[] args) {
        showFiles("D:\\Alex\\Photos", 0);
    }
}
