package pack.subject1;

import java.io.File;

public class Test2 {
	
	private File file;
	private StringBuilder str;
	
	
	public Test2(File file) {
		super();
		
		if (file==null) {
			throw new NullPointerException("null값이 들어갈수 없습니다.");
		}
		this.file = file;
		validate();
	}
	
	public void validate(){
		if (!this.file.exists()) {
			throw new IllegalArgumentException("file이 존재하지 않습니다.");
		}
	}
	
	public String getDirectoryStructureString() {
		str.append(file.getPath()).append("\n");
		
		traverseDirectory(file, 0);
		
		return str.toString();
	}
	
	public void traverseDirectory(File file, int dept) {
		File[] files = file.listFiles();
		dept++;
		for(File eachFile:files) {
			if (eachFile.isDirectory()) {
				str.append(getDepthSpace(dept)).append(eachFile.getName()).append("\r\t");
				traverseDirectory(eachFile,dept);
			}
		}
	}
	
	public String getDepthSpace(int dept) {
		StringBuilder dept_S= new StringBuilder();
		for (int i = 0; i < dept; i++) {
			dept_S.append("\t");
		}
		
		return dept_S.append("ㄴ").toString();
	}
	
	
	public static void main(String[] args) {
		File dir = new File("D:/Temp/");
		Test2 test= new Test2(dir);
		System.out.println(test.getDirectoryStructureString());
	}
}

//-----------------------------
class print{
//	private StringBuilder str;
//
//	public print() {
//		this.str=
//	}
	
}
