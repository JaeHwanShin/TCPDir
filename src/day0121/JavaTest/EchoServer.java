package day0121.JavaTest;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {

	public static void main(String[] args) {
		System.out.println("Server");
		try (ServerSocket serverSocket = new ServerSocket(1111);
				Socket socket = serverSocket.accept();
				BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));) {
			bw.flush();

			String line = null;
			while ((line = br.readLine()) != null) {
				System.out.println("클라이언트로 부터 전송받은 문자열 : " + line);
				bw.write(line + "\n"); // 다시 보내기
				bw.flush();
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
