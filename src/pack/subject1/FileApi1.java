package pack.subject1;


import java.io.File;

public class FileApi1 {
   
   public static void main(String[] args) {
      String path="D:/Temp";
      File dir= new File(path);
      if(!dir.exists() || !dir.isDirectory()){
         System.out.println("유효하지않은 디렉토리입니다.");
         System.exit(0);
      }
      
      
      //함수호출
      printFileList(dir,0);
   }
   
   
   
   
   
   
   public static void printFileList(File dir,int deep){
      String[] dirs = dir.list(); //디렉토리안 파일과 디렉토리
      for (int i = 0; i < dirs.length; i++) {
		File file= new File(dir.getPath()+"/"+dirs[i]);
		if (file.isDirectory()) {
			
			String deep_S="";
			for (int j = 0; j < deep; j++) {
				deep_S=deep_S+"\t";
			}
			System.out.println(deep_S+"ㄴ"+file.getName());
			deep++;
			printFileList(file,deep);
		}
      }
   }
   
   
}
