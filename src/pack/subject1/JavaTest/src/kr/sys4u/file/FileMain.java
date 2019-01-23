package pack.subject1.JavaTest.src.kr.sys4u.file;

import java.io.File;


public class FileMain {
	
	public static void main(String[] args) {
		File file = new File("D:/temp/");	
		
		FileNode node = new FileNode(file);
		FileTree tree= new FileTree(node);
		
		FileTreeStringConverter print = new FileTreeStringConverter(tree);
		print.print(); //그리기
	}
}
