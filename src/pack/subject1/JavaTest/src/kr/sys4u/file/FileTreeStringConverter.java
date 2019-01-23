package pack.subject1.JavaTest.src.kr.sys4u.file;

import java.io.File;
import java.util.List;

public class FileTreeStringConverter {

	private final FileTree fileTree;
	private final StringBuilder converted = new StringBuilder(); //정보가 변하는 변수StringBuilde은 static을 쓰면 안좋음
	private static final String CRNL="\r\n";
	private static final String SPACE="\t";
	private static final String CHILD_SYMBOL="└";
	
	public FileTreeStringConverter(final FileTree fileTree){
		this.fileTree= fileTree;
//		fileTree.initialize(); // *초기화해버리면 fileTree와 밀접한 관께를 가지게 되어 별로 좋지않다.!	
		convert();
	}
	
	private String getDepthSpace(int depth){
		StringBuilder spaceBuilder = new StringBuilder();
		
		for(int i=0; i<depth; i++) {
			spaceBuilder.append(SPACE);
		}
		return spaceBuilder.toString();
	}
	
	
	private String convert() {
		
		converted.append(fileTree.getRootFileNode().getFile().getPath()+CRNL);
		convertFileNodeRecursively(fileTree.getRootFileNode());
		return converted.toString();
	}

	public String convertFileNodeRecursively(FileNode rootFileNode) {
		List<FileNode> childrenFile= rootFileNode.getChildren();
		
		for (FileNode child : childrenFile) {
			if(child.getFile().isFile()) {
				continue;
			}
			converted.append(getDepthSpace(rootFileNode.getDept()))
					 .append(CHILD_SYMBOL)
					 .append(child.getFile().getName())
					 .append(CRNL);
			convertFileNodeRecursively(child);
		}	
			return converted.toString();
	}
	
	public void print() {
		System.out.println(converted.toString());
	}
	
	
}
