package day0117.JavaTest;

import static day0117.JavaTest.FileTree.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import day0117.JavaTest.src.day0117.subject1.src.day0106.filedirectory.exception.CanNotReadFileException;

public class FileToFileTreeConverter implements Convertable<File, FileTree> {
	private static final int SPACE_LENGTH = SPACE.length();

	@Override
	public FileTree convert(File source) {
		FileNode rootFileNode = null;
		try (BufferedReader reader = new BufferedReader(new FileReader(source))) {

			String readLine = reader.readLine();

			rootFileNode = new FileNode(new File(readLine));
			rootFileNode.setDepth(0);

			int beforeDepth = rootFileNode.getDepth();
			int currentDepth = 0;

			FileNode beforeNode = rootFileNode;
			FileNode currentNode = null;

			while ((readLine = reader.readLine()) != null) {

				currentDepth = readLine.substring(0, readLine.indexOf(CHILD_SYMBOL)).length() / SPACE_LENGTH;
				String currentStr = readLine.substring(readLine.lastIndexOf(CHILD_SYMBOL) + 1);

				beforeNode = findParentNode(beforeNode, beforeDepth - currentDepth + 1);

				currentNode = new FileNode(new File(beforeNode.getFile().getAbsoluteFile() + "/" + currentStr));
				beforeNode.addChild(currentNode, currentDepth);

				beforeDepth = currentDepth;
				beforeNode = currentNode;

			}
		} catch (IOException e) {
			throw new CanNotReadFileException(e);
		}
		return new FileTree(rootFileNode);
	}

	private FileNode findParentNode(FileNode beforeNode, int count) {
		if (beforeNode.getDepth() <= count) {
			// throw
		}
		for (int i = 0; i < count; i++) {
			beforeNode = beforeNode.getParentNode();
		}
		return beforeNode;
	}
}
