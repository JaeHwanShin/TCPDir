package day0121.JavaTest;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.Socket;

public class TCPFileClientEX {
	public static void main(String[] args) {
		File file = new File("D://test/22222.txt");
		
		System.out.println("[연결 요청]");
		try (
				Socket socket = new Socket("localhost",5001);
				DataInputStream dis = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
		        DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
				){
			System.out.println("연결 성공");
			dos.writeUTF(file.getName());
            dos.flush();
            
			//서버로 데이터 보내기
			byte[] buf  = new byte[100];
			 int n = 0;
			FileInputStream fis = new FileInputStream(file);
			while ((n = fis.read(buf )) != -1) {
				dos.write(buf,0,n);
			}
			
			dos.flush();
			System.out.println("[클라이언트:데이터 보내기 성공]");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
