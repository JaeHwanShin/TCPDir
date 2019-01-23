package day0121.JavaTest.dir;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

public class EXDirTCPClient2 {
	private static final String CLIENTFILEPATH = "D:/test";
	private static ArrayList<File> fileList = new ArrayList<File>();
	private static HashMap<String, Object> netWorkMap = new HashMap();

	public static void main(String[] args) {

		connection();
		dirRecursive(new File(CLIENTFILEPATH));
		sendFile();
	}

	// 연결값 저장
	private static void connection() {
		try (Socket socket = new Socket("localhost", 5001);
				DataInputStream dis = new DataInputStream(socket.getInputStream());
				DataOutputStream dos = new DataOutputStream(socket.getOutputStream());) {

			netWorkMap.put("socket", socket);
			netWorkMap.put("dataInput", dis);
			netWorkMap.put("dataOutput", dos);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 파일 정보 모으기
	private static void dirRecursive(File file) {
		if (file.isDirectory()) {
			File[] children = file.listFiles();
			for (File child : children) {
				if (child.isDirectory()) {
					fileList.add(child);
					dirRecursive(child);
				} else if (child.isFile()) {
					fileList.add(child);
				}
			}
		} else {
			fileList.add(file);
		}
	}



	private static void sendFile() {
		try (
				DataInputStream dis = (DataInputStream) netWorkMap.get("dataInput");
				DataOutputStream dos = (DataOutputStream) netWorkMap.get("dataOutput");) {

			String serverFilePath = dis.readUTF(); // 서버 폴더경로

			for (int i = fileList.size() - 1; i >= 0; i--) {
				dos.writeUTF("start");
				dos.flush();

				File sendFile = fileList.get(i);
				dos.writeInt(fileList.size());
				dos.flush();// 몇개 보낼꺼다

				String[] pathEdit = sendFile.getAbsolutePath().replace("\\", "/").split(CLIENTFILEPATH);
				String[] pathEdit2 = pathEdit[1].split(sendFile.getName());
				dos.writeUTF(serverFilePath + pathEdit2[0]);// 서버폴더 수정후 보내기
				System.out.println("serverFilePath + pathEdit2[0]->" + serverFilePath + pathEdit2[0]);
				dos.writeUTF(sendFile.getName()); // 파일명 보내기
				System.out.println(sendFile.getName());

				int len = (int) sendFile.length();
				System.out.println("(int)len->" + len);
				dos.writeInt(len);
				dos.flush();

				int data = 0;
				byte[] buf = new byte[len];
				try (FileInputStream fis = new FileInputStream(sendFile);) {
					while ((data = fis.read(buf)) != -1) {
						dos.write(buf, 0, data);
						dos.flush();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				dos.writeUTF("end");
				dos.flush();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
