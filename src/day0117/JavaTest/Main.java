package day0117.JavaTest;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

public class Main {

	public static void main(String[] args) throws FileNotFoundException {
//		final String dirPath = "D:/test/";


//		FileTree fileTree = new FileTree(dirPath);
		ConvertManager manager = new ConvertManager();
		
//		//과제1
//		manager.addConverter(FileTree.class, Boolean.class, new FileTreeToDirectoryConverter());
//		System.out.println(manager.requestConvert(fileTree, Boolean.class));
//		
//		//과제2
//		manager.addConverter(FileTree.class, File.class, new FileTreeToFileConverter());
//		manager.addConverter(File.class, FileTree.class, new FileToFileTreeConverter());
//		
//		File file = manager.requestConvert(fileTree, File.class);
//		FileTree newFileTree = manager.requestConvert(file, FileTree.class);
//		System.out.println(newFileTree.toString());
//		
//		
//		manager.addConverter(FileTree.class, Boolean.class, new FileTreeToDirectoryConverter());
//		System.out.println(manager.requestConvert(newFileTree, Boolean.class));
//		
//		
//		new FileToFileTreeConverter()
//				.convert(new File("C:/Users/notebiz7750/Downloads/FileConverter/ConvertResult.txt"));
//		
//		manager.addConverter(FileTree.class, String.class, new FileTreeToStringConveter());
//		manager.addConverter(FileTree.class, StringBuilder.class, new FileTreeToStringBuilderConveter());
//		
//		manager.addConverter(File.class, FileTree.class, new FileToDirectoryConverter());

//		System.out.println(manager.requestConvert(fileTree, String.class).toString());
//		System.out.println(manager.requestConvert(fileTree, StringBuilder.class).toString());
//		System.out.println(manager.requestConvert(fileTree, File.class).toString());

		// ---------------파일 복사,이동--------------------------
//		String originalFilePath = "D:/test/aaaaa.zip";
//		String copyPath = "D:/test2/aaaaa.zip";
//
//		Map<String, String> infoMap = new HashMap<>();
//		infoMap.put("i", originalFilePath);
//		infoMap.put("o", copyPath);
//		infoMap.put("m", "Y");
//
//		manager.addConverter(HashMap.class, String.class, new FileCopy());
//		manager.requestConvert(infoMap, String.class);
		// ----------------------------------------------------

		// -----------------폴더 복사----------------------------
		String originaDirPath = "D:/test4/";
		String copyDirPath = "D:/test/";

		Map<String, String> dirInfoMap = new HashMap<>();
		dirInfoMap.put("I", originaDirPath);
		dirInfoMap.put("O", copyDirPath);
		dirInfoMap.put("M", "Y"); // 이동여부
		manager.addConverter(HashMap.class, String.class, new DirCopy());
		manager.requestConvert(dirInfoMap, String.class);

		// ----------------------------------------------------
	}
}
