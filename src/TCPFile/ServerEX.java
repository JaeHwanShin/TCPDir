package TCPFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerEX {
	public static void main(String[] args) {

		File file = new File("D://TCPEX");

		try (ServerSocket serverSocket = new ServerSocket();
				Socket socket = serverSocket.accept();
				InputStream is = socket.getInputStream();
				FileOutputStream fos = new FileOutputStream(file);) {

			serverSocket.bind(new InetSocketAddress("localhost", 5001));

			while (true) {
				System.out.println("[연결기다림]");
				InetSocketAddress isa = (InetSocketAddress) socket.getRemoteSocketAddress();
				System.out.println("[연결 수락함]" + isa.getHostName());

//				byte[] bytes = null;
//				String message = null;

//				bytes = new byte[100];
//				int readByteCount = is.read(bytes);

				int data = 0;
				while ((data = is.read()) != -1) {
					fos.write(data);
				}
				fos.flush();
//				message = new String(bytes, 0, readByteCount, "UTF-8");
				System.out.println("[데이터받기 성공]");

				// 서버가 클라이언트한테 데이터 보내기
//				message = "Hellow Client";
//				bytes = message.getBytes("UTF-8");
//				fos.write(bytes);
//				fos.flush();
//				System.out.println("[서버:데이터 보내기 성공]");

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
