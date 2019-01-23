package day0117.JavaTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;

import day0117.JavaTest.src.day0117.subject1.src.day0106.filedirectory.exception.CanNotFileCopyException;

public class DirCopy implements Convertable<Map<String, String>, String> {

	@Override
	public String convert(Map<String, String> dirInfoMap) {
		String result = "";
		File input = new File(dirInfoMap.get(FileCopyAndMove.input));
		File output = new File(dirInfoMap.get(FileCopyAndMove.output));

		// 이것도 boolean으로 받아서 사용자에게 알려줄수 있음
		dirSearchAndCopyRecursive(input, output);

		if ("Y".equals(dirInfoMap.get(FileCopyAndMove.Move))) {
			if (dirDelete(new File(dirInfoMap.get(FileCopyAndMove.input)))) {
				result = "삭제성공";
			}
		}
		return result;
	}

	private void dirSearchAndCopyRecursive(File input, File output) {
		if (!output.exists()) {
			output.mkdirs();
		}
		if (input.isDirectory()) {
			File[] children = input.listFiles();
			for (File child : children) {
				String newFilePath = output.getAbsolutePath() + "/" + child.getName();
				File newFile = new File(newFilePath.replace("\\", "/"));
				if (child.isDirectory()) {
					dirSearchAndCopyRecursive(child, newFile);
				} else if (child.isFile()) {
					copyFile(new File(child.getAbsolutePath()), new File(newFile.getAbsolutePath()));
				}
			}
		} else if (input.isFile()) {
			copyFile(new File(input.getAbsolutePath()), new File(output.getAbsolutePath()));
		}
	}

	// 복사메소드
	private void copyFile(File originalFile, File copyFile) {

		if (!originalFile.exists()) {
			throw new NullPointerException("복사할 파일이 존재하지않습니다.");
		}
		if (copyFile.exists()) {
			return;
		}

		try (FileInputStream fis = new FileInputStream(originalFile);
				FileOutputStream fos = new FileOutputStream(copyFile);) {
			int data = 0;
			while ((data = fis.read()) != -1) {
				fos.write(data);
			}
		} catch (IOException e) {
			throw new CanNotFileCopyException("파일 만들기 실패");
		}
	}

	private boolean dirDelete(File originalFile) {
		File[] children = originalFile.listFiles();
		for (File child : children) {
			if (child.isFile()) {
				child.delete();// 하위파일 있어서 삭제안되..재귀로 처리해야함
			} else if (child.isDirectory()) {
				dirDelete(child);
			}
		}
		return originalFile.delete();
	}
}
