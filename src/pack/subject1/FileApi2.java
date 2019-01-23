package pack.subject1;

import java.io.File;
import java.util.ArrayList;

import com.sun.org.apache.bcel.internal.generic.NEWARRAY;

public class FileApi2 {

   public static void main(String[] args) {
      String path="D:/"
            + "";
//      File f= new File(path);
//      System.out.println(f.exists());
//      String[] aa= f.list();
//      for (int i = 0; i < aa.length; i++) {
//         System.out.println(path+aa[i]+"/");
//         String[] bb= new File(path+aa[i]+"/").list();
//         for (int j = 0; j < bb.length; j++) {
//            System.out.println(path+aa[i]+"/"+bb[i]);
//         }
//      }
      
      //--------------------------------------------
      File dir= new File(path);
      if(!dir.exists() || !dir.isDirectory()){
         System.out.println("유효하지않은 디렉토리입니다.");
         System.exit(0);
      }
      
      //함수호출
      printFileList(dir);
      System.out.println("총"+totalFiles+"개의 파일");
      System.out.println("총"+totalDirs+"개의 디렉토리");
      
   }
   
   static int totalFiles=0;
   static int totalDirs=0;
   
   public static void printFileList(File dir) {
      System.out.println("디렉토리:"+"["+dir.getAbsolutePath()+"]");
      File[] files= dir.listFiles();
      
      ArrayList subDir= new ArrayList<>();
      
      for (int i = 0; i < files.length; i++) {
         String filename= files[i].getName();
         
         if (files[i].isDirectory()) {
            filename="["+filename+"]";
            subDir.add(i+"");
            System.out.println(filename);
         }
         
         int dirNum= subDir.size();
         int fileNum=files.length-dirNum;
         
         totalFiles += fileNum;
         totalDirs += dirNum;
         
         System.out.println(fileNum+"개의 파일 \t"+dirNum+"개의 디렉토리");
         System.out.println();
         
         for (int j = 0; j < subDir.size(); j++) {
            int index = Integer.parseInt((String)subDir.get(i));
            printFileList(files[index]);
         }
      }
   }
   
}




