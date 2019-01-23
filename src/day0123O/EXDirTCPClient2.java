package day0123O;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

public class EXDirTCPClient2 {
	private static final String CLIENTFILEPATH = "D:/test";
	private static ArrayList<File> fileList = new ArrayList<File>();

	public static void main(String[] args) {
		dirRecursive(new File(CLIENTFILEPATH));
		sendFile();
	}

	// 파일 정보 모으기
	private static void dirRecursive(File file) {
		if (file.isDirectory()) {
			File[] children = file.listFiles();
			for (File child : children) {
				if (child.isDirectory()) {
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
		try (Socket socket = new Socket("localhost", 5001);
				DataInputStream dis = new DataInputStream(socket.getInputStream());
				DataOutputStream dos = new DataOutputStream(socket.getOutputStream());) {

			sendFileList(dis, dos);
			fileDataTrans(dos);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 포기..
	private static void fileDataTrans(DataOutputStream dos) throws IOException {
		for (int i = fileList.size() - 1; i >= 0; i--) {
			File file = fileList.get(i);

//			try (DataInputStream fis = new DataInputStream(new FileInputStream(file));) {
//				int data = 0;
//				int fileSize = (int) file.length();
//				System.out.println("fileSize->" + fileSize);
//				byte[] buf = new byte[fileSize];
//				dos.writeInt(fileSize);
//				data = fis.read(buf);
//				dos.write(buf, 0, data);
//				dos.flush();
//			} catch (Exception e) {
//				e.printStackTrace();
//			}

			// ---------------수정중-----------------
			try (DataInputStream fis = new DataInputStream(new FileInputStream(file));
					DataInputStream tempFis = new DataInputStream(new FileInputStream(file));) {
				byte[] buf = new byte[4092];
				byte[] tempBuf = new byte[4092];

				int cnt = 0;
				int readCount = 0;
				while ((cnt = tempFis.read(tempBuf)) != -1) {
					readCount++;
				}
				System.out.println(readCount);
				dos.writeInt(readCount);
				dos.flush();

				while ((cnt = fis.read(buf)) != -1) {
					System.out.println(file.getAbsolutePath() + "보냄");
					dos.write(buf, 0, cnt); // 버퍼에 남은거 쓰레기값 안넘기기
					dos.flush();
				}

			} catch (Exception e) {
				e.printStackTrace();
			}

			// -------------------------수정2---------------------
			// 전체 데이터값에서 읽은 만큼 빼기
//			try (FileInputStream fis = new FileInputStream(file);) {
//				int fileSize = (int) file.length();
//				dos.writeInt(fileSize);
//				System.out.println("send file Size : " + fileSize);
//				dos.flush();
//
//
//				int buffSize = 10; // 버퍼크기설정
//				byte[] buf = new byte[buffSize];
//				int cnt = 0;
//				while ((cnt = fis.read(buf)) != -1) {
//					dos.write(buf, 0, cnt); // 버퍼에 남은거 쓰레기값 안넘기기
//					dos.flush();
//					System.out.println(file.getAbsolutePath() + " 보내기");
//				}
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
		}
	}

	// 파일 리스트만 보내기
	private static void sendFileList(DataInputStream dis, DataOutputStream dos) throws IOException {

		String serverFilePath = dis.readUTF(); // 서버 폴더경로받아오기
		dos.writeInt(fileList.size());// 몇개 보낼꺼다
		dos.flush();
		for (int i = fileList.size() - 1; i >= 0; i--) {
			String[] pathEdit = fileList.get(i).getAbsolutePath().replace("\\", "/").split(CLIENTFILEPATH);
			String[] pathEdit2 = pathEdit[1].split(fileList.get(i).getName());
			dos.writeUTF(serverFilePath + pathEdit2[0]);// 서버폴더 수정후 보내기
			dos.flush();
			dos.writeUTF(fileList.get(i).getName()); // 파일명 보내기
			dos.flush();
		}
	}
}
