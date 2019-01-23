package day0123O;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class EXDirTCPServer2 {
	private boolean initalized = false;
	private int port = 5001;
	private ServerSocket serverSocket;
	private static final String FILESAVEPATH = "D:/TCPEX2";
	private static ArrayList<File> fileList = new ArrayList<File>();

	public static void main(String[] args) {
		EXDirTCPServer2 tcpServer = new EXDirTCPServer2();
		try {

			tcpServer.initalize();
			tcpServer.execute();
			tcpServer.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 초기화
	private void initalize() throws IOException {
		if (initalized) {
			return;
		}
		serverSocket = new ServerSocket(port);
		initalized = true;
	}

	// 실행
	private void execute() throws IOException {
		if (!initalized) {
			initalize();
		}

		System.out.println("[연결기다림]");
		// 서버는 계속 돌것이라는 것 //
		while (true) {
			// 클라이언트 죽었을때 계속 while문 뜨니 try해줘야함
			try (Socket socket = serverSocket.accept();
					DataInputStream dis = new DataInputStream(socket.getInputStream());
					DataOutputStream dos = new DataOutputStream(socket.getOutputStream());) {

				fileListReceive(dis, dos);
				receiveFileInto(dis);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	// 소멸
	private void close() {
		if (!initalized) {
			return;
		}
		try {
			serverSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void receiveFileInto(DataInputStream dis) {
		for (File file : fileList) {
//			try (DataOutputStream fos = new DataOutputStream(new FileOutputStream(file));) {
//				int data = 0;
//				int fileSize = dis.readInt();
//				byte[] buf = new byte[fileSize];
//				data = dis.read(buf);
//				System.out.println("파일 사이즈 크기->" + data);
//				fos.write(buf, 0, data);
//				fos.flush();
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
			// ---------------수정중-----------------
			try (DataOutputStream fos = new DataOutputStream(new FileOutputStream(file));) {
				byte[] buf = new byte[4092];
				int count = dis.readInt();
				System.out.println("count->" + count);

				for (int i = 0; i < count; i++) {
					int cnt = dis.read(buf);
					System.out.println("cnt->" + cnt);
					fos.write(buf, 0, cnt);
					fos.flush();
				}
				System.out.println(file.getAbsolutePath() + "[에 데이터받기 성공]");
			} catch (Exception e) {
				e.printStackTrace();
			}
			// ------------------수정2------------------
			// 전체 데이터수에서 읽은 수만큼 빼기
//			try (FileOutputStream fos = new FileOutputStream(file);) {
//				int fileSize = dis.readInt();
//				System.out.println("read file size : " + fileSize);
//
//				byte[] buf = new byte[10];
//				int n = 0;
//				for (int i = n; i < fileSize; i += n) {
//					n = dis.read(buf);
//					fos.write(buf, 0, n);
//					fos.flush();
//					System.out.println(i);
//				}
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
		}
	}

	private static DataInputStream DataInputStream(FileOutputStream fileOutputStream) {
		// TODO Auto-generated method stub
		return null;
	}

	private static void fileListReceive(DataInputStream dis, DataOutputStream dos) throws IOException {
		dos.writeUTF(FILESAVEPATH);
		dos.flush();
		int FileSize = dis.readInt();

		dos.writeUTF("end");
		dos.flush();

		for (int i = 0; i < FileSize; i++) {
			String saveFilePath = dis.readUTF().replace("\\", "/");
			String saveFileName = dis.readUTF();
			if (!new File(saveFilePath).exists()) {
				new File(saveFilePath).mkdirs();
			}
			File file = new File(saveFilePath + saveFileName);
			fileList.add(file);
		}
	}

}