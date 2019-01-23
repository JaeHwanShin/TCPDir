package day0117.JavaTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;

import day0117.JavaTest.src.day0117.subject1.src.day0106.filedirectory.exception.CanNotFileCopyException;

public class FileCopyAndMove implements Convertable<Map<String, String>, String> {

	static final String input = "I";
	static final String output = "O";
	static final String Move = "M";

	@Override
	public String convert(Map<String, String> source) {
		copyFile(source);
		fileDelete(source);
		return "end";
	}

	private void fileDelete(Map<String, String> source) {
		if ("Y".equals(source.get(Move))) {
			File originalFile = new File(source.get(input));
			originalFile.delete();
		}
	}

	private void copyFile(Map<String, String> source) {
		if (source.get(input) == null && source.get(output) == null) {
			throw new NullPointerException("null 예외");
		}

		File originalFile = new File(source.get(input));
		File copyFile = new File(source.get(output));

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

}
