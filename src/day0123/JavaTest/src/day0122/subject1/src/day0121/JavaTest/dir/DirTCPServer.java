package day0123.JavaTest.src.day0122.subject1.src.day0121.JavaTest.dir;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class DirTCPServer {
	public static final int PORTNUMBER = 5001;
	public static final String FILESAVEPATH = "D:/TCPEX";

	public static void main(String[] args) {

		System.out.println("[연결기다림]");
		while (true) {
			try (ServerSocket serverSocket = new ServerSocket(PORTNUMBER);
					Socket socket = serverSocket.accept();
					DataInputStream dis = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
					DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));) {

				InetSocketAddress isa = (InetSocketAddress) socket.getRemoteSocketAddress();
				System.out.println("[연결 수락함]" + isa.getHostName());

				dos.writeUTF(FILESAVEPATH);
				dos.flush();

				String saveFilePath = dis.readUTF();
				String saveFileName = dis.readUTF();

				if (!new File(saveFilePath).exists()) {
					new File(saveFilePath).mkdirs();
				}

				File file = new File(saveFilePath + saveFileName);

				int data = 0;
				byte[] buf = new byte[100];

				try (FileOutputStream fos = new FileOutputStream(file);) {
					while ((data = dis.read(buf)) != -1) {
						fos.write(buf, 0, data);
						fos.flush();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}
}