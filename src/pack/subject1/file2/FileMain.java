package pack.subject1.file2;

import java.io.File;

public class FileMain {
	
	public static void main(String[] args) {
		//Arrays.deepToString(args); //알아두기 Arrays.deepToString써서 for문 써서 toString 하지말기 : 배열args안에 있는 것들을 ,로 구별해서 보여준다.
		
		File file = new File("D:/temp/");	
		FileNode node = new FileNode(file);
		FileTree tree= new FileTree(node);
		
		//트리구조 String으로 변환
		ConversionManager manager = new ConversionManager();
		manager.addConverter(FileTree.class, StringBuilder.class, new GenericConverterImpl(tree));
		StringBuilder result = manager.requestConvert(tree, StringBuilder.class);
		System.out.println(result);
		
		//File IO사용하여 파일생성하기
		manager.addConverter(StringBuilder.class, File.class, new DirTreeFileConverterImpl("D:/temp/file-structure.txt"));
		File textFile=manager.requestConvert(result, File.class);
		System.out.println(textFile+"파일생성");
	}
}
