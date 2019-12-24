package com.tangze;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;

public class Parser {

    public static void forSummarization(String fileName, String dirName) throws FileNotFoundException{
        ArrayList<String[]> data = JsonReader.read(fileName);
        if(!dirName.endsWith("/")){
            dirName = dirName + "/";
        }

        int cnt = 0;
        Iterator var4 = data.iterator();

        while(var4.hasNext()){
            String[] line = (String[]) var4.next();
            String dot = DotMaker.toDot(line[0]);
            if(dot != null){
                AST ast = AST.parseDot(dot);
                PrintWriter pw = new PrintWriter(new File(dirName + cnt));
                pw.println(ast);
                pw.println(line[1]);
                pw.close();
                if (cnt % 100 == 0){
                    System.out.println(cnt + " / " + data.size());
                }

                ++ cnt;
            }
        }

        System.out.println(data.size() + " / " + data.size());
        System.out.println("COMPLETE");
    }

    public static void main(String[] args) throws FileNotFoundException{
        int index = 0;
        String fileName = null;
        String dirName = null;

        while(index < args.length) {
            if (args[index].equals("-f")) {
                fileName = args[index + 1].trim();
                index += 2;
            } else if (args[index].equals("-d")) {
                dirName = args[index + 1].trim();
                index += 2;
            } else {
                System.out.println(args[index++]);
            }
        }

        if (fileName == null) {
            throw new RuntimeException("Please specify the input file: -f [filename]");
        } else if (dirName == null) {
            throw new RuntimeException("Please specify the output directory: -d [dirname]");
        } else {
            forSummarization(fileName, dirName);
        }
    }

}
