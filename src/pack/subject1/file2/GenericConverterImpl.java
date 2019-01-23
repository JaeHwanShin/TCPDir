package pack.subject1.file2;
import java.util.List;

public class GenericConverterImpl implements IGenericConverter<FileTree, StringBuilder> {

	private final FileTree fileTree;
	private final StringBuilder resultStringBuilder;
	private static final String CRNL="\r\n";
	private static final String SPACE="\t";
	private static final String CHILD_SYMBOL="└";
	
	public GenericConverterImpl(FileTree fileTree){
		this.fileTree= fileTree;
		this.resultStringBuilder=new StringBuilder();
	}

	@Override
	public StringBuilder convert(FileTree fileTree){
		resultStringBuilder.append(fileTree.getRootFileNode().getFile().getPath()+CRNL);
		StringBuilder convertResult = getResultPrint( fileTree.getRootFileNode());
		return convertResult;
	}
	
	private StringBuilder getResultPrint(FileNode rootFileNode) {
		List<FileNode> childrenFile= rootFileNode.getChildren();
		for (FileNode child : childrenFile) {
			if(child.getFile().isFile()) {
				continue;
			}
			resultStringBuilder.append(getDepthSpace(rootFileNode.getDept())).append(CHILD_SYMBOL).append(child.getFile().getName()).append(CRNL);
			getResultPrint(child);
		}	
			return resultStringBuilder;
	}

	private String getDepthSpace(int depth){
		StringBuilder spaceBuilder = new StringBuilder();
		for(int i=0; i<depth; i++) {
			spaceBuilder.append(SPACE);
		}
		return spaceBuilder.toString();
	}
	
}
