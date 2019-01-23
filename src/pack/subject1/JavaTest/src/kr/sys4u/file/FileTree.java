package pack.subject1.JavaTest.src.kr.sys4u.file;

import java.io.File;

public class FileTree {

	private final FileNode rootFileNode;
	private boolean initialized =false;

	public FileTree(final String rootDir) {
		if (rootDir == null) {
			throw new IllegalArgumentException();
		}
		this.rootFileNode = new FileNode(new File(rootDir));
	}

	public FileTree(final File rootDir) {
		if (rootDir == null) {
			throw new IllegalArgumentException();
		}
		this.rootFileNode = new FileNode(rootDir);
		initialize(); //eager-initialize
	}

	public FileTree(final FileNode rootFileNode) {
		if (rootFileNode == null) {
			throw new IllegalArgumentException();
		}
		this.rootFileNode = rootFileNode;
		initialize(); //eager-initialize
	}
	
	//초기화는 동기화 처리해야함!!!
	private synchronized void initialize() { //초기화하는것이 public으로 가면 외부에 노출이 되서 또초기화가 되면 문제가 생길수 있음! 
		if (initialized) {
			return;
		}
		rootFileNode.removeChildren(); //초기화 전에 기존의 값을 지워줘야 함
		rootFileNode.setDept(0);
		addChildrenRecursively(rootFileNode,rootFileNode.getDept());
		initialized=true;
	}

	private void addChildrenRecursively(FileNode parentNode,int dept) {
		dept++;
		parentNode.setDept(parentNode.getDept()+dept);
		File[] childrenFile = parentNode.getFile().listFiles();
		
		for (File child : childrenFile) {
			if(child.isFile()) {
				continue;
			}
			addChildrenRecursively(parentNode.addChild(child),dept);
		}
	}
	
	public FileNode getRootFileNode() {
		//lazy-initialize를 사용한다면 이때라고 초기화해야함
		if (!initialized) {
			initialize();
		}
		return rootFileNode;
	}
	
	

}
