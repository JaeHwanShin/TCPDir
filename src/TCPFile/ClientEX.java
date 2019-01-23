package TCPFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

import day0117.JavaTest.src.day0117.subject1.src.day0106.filedirectory.exception.CanNotFileCopyException;

public class ClientEX {
	public static void main(String[] args) {
		File file = new File("D://test/22222.txt");
		try (Socket soket = new Socket();
				FileInputStream fis = new FileInputStream(file);
				OutputStream os = soket.getOutputStream();) {

			System.out.println("[연결 요청]");
			soket.connect(new InetSocketAddress("localhost", 5001));
			System.out.println("연결 성공");
			
			//서버로 데이터 보내기
//			byte[] bytes = null;
//			String message=null;

			int data = 0;
			while ((data = fis.read()) != -1) {
				os.write(data);
			}
			os.flush();
			System.out.println("[클라이언트:데이터 보내기 성공]");
			

			// 서버로부터 데이터 받기
//			bytes = new byte[100];
//			int readByteCount = is.read(bytes);
//			message = new String(bytes, 0, readByteCount, "UTF-8");
//			System.out.println("[데이터받기 성공]" + message);

		} catch (Exception e) {
			e.printStackTrace();
		}


	}

	private void copyFile(File file) {

		if (!file.exists()) {
			throw new NullPointerException("파일이 존재하지않습니다.");
		}


		try (FileInputStream fis = new FileInputStream(file); FileOutputStream fos = new FileOutputStream(file);) {

			int data = 0;
			while ((data = fis.read()) != -1) {
				fos.write(data);
			}

		} catch (IOException e) {
			throw new CanNotFileCopyException("파일 만들기 실패");
		}
	}
}
