package day0121.JavaTest;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;

public class EchoClient {
	public static void main(String[] args) {
		try (Socket socket = new Socket("localhost", 1111);
				BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));) {
//			Scanner scan = new Scanner(System.in);
			System.out.println("Client >> Server 연결 성공");

			String inputData = "";
			while (!inputData.equals("Exit")) {

//				if (!br.readLine().isEmpty()) {
//					System.out.println(br.readLine());
//				}

				System.out.println("보낼 메시지를 입력하시오:");
				Scanner scan = new Scanner(System.in);
				inputData = scan.nextLine();
				bw.write(inputData + "\n"); // readLine은 \n 계행문자 있어야 됨!!! 그라인으로 읽기 때문에!!!!
				bw.flush();

				System.out.println("서버로부터 돌아온 메시지" + br.readLine());
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}