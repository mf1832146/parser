package com.tangze;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ParseProblemException;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Node;
import com.github.javaparser.printer.DotPrinter;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class DotMaker {

    public static String toDot(String source){
        try{
            CompilationUnit cu = JavaParser.parse("class DUMMY {" + source + "}");
            Node node = (Node) cu.getChildNodes().get(0).getChildNodes().get(1);
            DotPrinter printer = new DotPrinter(true);
            return printer.output(node);
        }catch (ParseProblemException var4){
            System.out.println(var4);
            return interfaceCase(source);
        }
    }

    public static String interfaceCase(String source){
        try{
            CompilationUnit cu = JavaParser.parse("interface DUMMY {" + source + "}");
            Node node = (Node) ((Node) cu.getChildNodes().get(0)).getChildNodes().get(1);
            DotPrinter printer = new DotPrinter(true);
            return printer.output(node);
        }catch (ParseProblemException var4){
            System.out.println(var4);
            return null;
        }
    }

    public static void main(String[] args) throws FileNotFoundException{
//        String fileName = args[0];
//        Scanner sc = new Scanner(new File(fileName));
//        StringBuilder sb = new StringBuilder();
//
//        while(sc.hasNextLine()){
//            sb.append(sc.nextLine()).append("\n");
//        }

        //String dot = toDot(sb.toString());
        String dot = toDot("public int hashCode(){\n  return value.hashCode();\n}\n");
        System.out.println(dot);
    }

}
