package day0123.JavaTest.src.day0122.subject1.src.day0121.JavaTest.dir;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class DirTCPClient {
	public static final String IPADDRESS = "localhost";
	public static final int PORTNUMBER = 5001;
	public static final String CLIENTFILEPATH = "D:/test";
	public static List<File> fileList = new ArrayList<File>();

	public static void main(String[] args) {
		File file = new File(CLIENTFILEPATH);
		dirRecursive(file);
	}

	private static void dirRecursive(File file) {
		if (file.isDirectory()) {
			int index = 0;
			File[] children = file.listFiles();
			for (File child : children) {
				if (child.isDirectory()) {
					index++;
				}
			}
			for (File child : children) {
				if (child.isDirectory()) {
					dirRecursive(child);
				} else if (child.isFile()) {
					if (index == 0) {
						fileList.add(child);

						for (int i = fileList.size() - 1; i >= 0; i--) {
							sendFile(fileList.get(i));
						}
					}
					fileList.add(child);
				}
			}
		} else {
			fileList.add(file);
		}
	}

	private static void sendFile(File sendFile) {
		try (Socket socket = new Socket(IPADDRESS, PORTNUMBER);
				DataInputStream dis = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
				DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));) {

			String serverFilePath = dis.readUTF(); // 서버 폴더경로
			String[] pathEdit = sendFile.getAbsolutePath().replace("\\", "/").split(CLIENTFILEPATH);
			String[] pathEdit2 = pathEdit[1].split(sendFile.getName());

			dos.writeUTF(serverFilePath + pathEdit2[0]);// 서버폴더 수정후 보내기
			System.out.println("serverFilePath + pathEdit2[0]->" + serverFilePath + pathEdit2[0]);
			dos.writeUTF(sendFile.getName()); // 파일명 보내기
			System.out.println(sendFile.getName());

			int data = 0;
			byte[] buf = new byte[100];
			try (FileInputStream fis = new FileInputStream(sendFile);) {
				while ((data = fis.read(buf)) != -1) {
					dos.write(buf, 0, data);
					dos.flush();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
