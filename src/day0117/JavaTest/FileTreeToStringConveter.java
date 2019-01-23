package day0117.JavaTest;

public class FileTreeToStringConveter implements Convertable<FileTree,String> {

	private String converted;

	public FileTreeToStringConveter() {
		this.converted = "";
	}
	
	@Override
	public String convert(final FileTree source) {
		converted += "StringConverter : "+source.toString();
		return converted;
	}
}
