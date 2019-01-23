package day0121.JavaTest.dir;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

public class EXDirTCPServer2 {
	public static final String FILESAVEPATH = "D:/TCPEX2";
	private static HashMap<String, Object> netWorkMap = new HashMap();
	private static ArrayList<File> fileList = new ArrayList<File>();

	public static void main(String[] args) {
		connection();
		System.out.println("[연결기다림]");
		
		while (true) {
			try (Socket socket = (Socket) netWorkMap.get("socket");
					DataInputStream dis = (DataInputStream) netWorkMap.get("dataInput");
					DataOutputStream dos = (DataOutputStream) netWorkMap.get("dataOutput");) {

				InetSocketAddress isa = (InetSocketAddress) socket.getRemoteSocketAddress();
				System.out.println("[연결 수락함]" + isa.getHostName());

				dos.writeUTF(FILESAVEPATH);
				dos.flush();

//			while (true) {
				while (true) {
					String endCheck = dis.readUTF();
					if (endCheck.equals("end") || endCheck.equals("start")) {
						break;
					}
				}
				int FileSize = dis.readInt();
				for (int i = 0; i < FileSize; i++) {
					String saveFilePath = dis.readUTF();
					String saveFileName = dis.readUTF();

					if (!new File(saveFilePath).exists()) {
						new File(saveFilePath).mkdirs();
					}
					File file = new File(saveFilePath + saveFileName);
					int len = dis.readInt();
					int data = 0;
					byte[] buf = new byte[len];

					try (FileOutputStream fos = new FileOutputStream(file);) {
						fos.write(buf, 0, dis.read(buf));
					} catch (Exception e) {
						e.printStackTrace();
					}

				}

//			}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	// 연결값 저장
	private static void connection() {
		try (ServerSocket serverSocket = new ServerSocket(5001);
				Socket socket = serverSocket.accept();
				DataInputStream dis = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
				DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));) {

			netWorkMap.put("socket", socket);
			netWorkMap.put("dataInput", dis);
			netWorkMap.put("dataOutput", dos);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}



}