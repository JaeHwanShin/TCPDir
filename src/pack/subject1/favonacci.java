package pack.subject1;

import java.util.Scanner;

public class favonacci {
	public static void main(String[] args) {
		
		//Scanner sc = new Scanner(System.in);
		
		//System.out.println("입력해라~?");
		int input = 10;
		
		System.out.println("---------------------[재귀함수O]------------------------");
		for (int i = 1; i <= input; i++) {
			System.out.print(fibo1(i)+"\t");
		}

		System.out.println();
		System.out.println("---------------------[재귀함수X]------------------------");
		for (int i = 1; i <= input; i++) {
			System.out.print(fibo2(i)+"\t");
		}
	}
	
	//����Լ�O
	public static int fibo1(int n) {
		if (n <= 1)
			return n;
		else 
            return fibo1(n-2) + fibo1(n-1);
	}
	
	
	//����Լ�X
	public static int fibo2(int n) {
		int h=0,t=1,temp;
		while(n >=1) {
			    temp = t;
		        t = t + h;
		        h = temp;
		        n--;
		}
		return h;
	}

	
}
