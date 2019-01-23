package day0123.JaaTest;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPFileServerEX {
	public static void main(String[] args) {

		File saveDir = new File("D://testTCP/");

		System.out.println("[연결기다림]");
		try (ServerSocket serverSocket = new ServerSocket(5001);
				Socket socket = serverSocket.accept();
				DataInputStream dis = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
				DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));) {

			if (!saveDir.exists()) {
				saveDir.mkdirs();
			}
			InetSocketAddress isa = (InetSocketAddress) socket.getRemoteSocketAddress();
			System.out.println("[연결 수락함]" + isa.getHostName());

			File file = new File(saveDir + "/" + dis.readUTF());
			
			int n = 0;
			byte[] buf = new byte[100];

			try (FileOutputStream fos = new FileOutputStream(file);) {
				while ((n = dis.read(buf)) != -1) {
					fos.write(buf, 0, n);
					fos.flush();
				}
				System.out.println("[데이터받기 성공]");
			} catch (Exception e) {
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
