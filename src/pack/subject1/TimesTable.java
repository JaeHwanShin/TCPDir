package pack.subject1;

public class TimesTable {

	
	public static void main(String[] args) {
        
		TimesTable t = new TimesTable();
	         
	    System.out.println(t.printNthTable(4));
	    
		System.out.println();
		
	    System.out.println(t.printNthAndMthTable(2, 4));
	       
		System.out.println();
		
	    System.out.println(t.printFromNthToMthTable(4,9));
	         
	 }
	      
	      
	       /**
	          * 구구단 n단의 계산 결과를 다음과 같은 문자열 형식으로 출력한다.
	          * 

	         2단인 경우
	         
	      2 X 1 = 2   |   2 X 2 = 4   | 2 X 3 = 6
	      2 X 4 = 8   |   2 X 5 = 10   | 2 X 6 = 12
	      2 X 7 = 14   |   2 X 8 = 16   | 2 X 9 = 18

	          *
	          * 즉, 3 by 3 형식으로 출력한다.
	          * 
	          * @param n
	          * @return
	          */
	         public String printNthTable(int n) {
	            
	            String result="";
	            
	            for (int i = 1; i <= 9; i++) {
	               if(i%3==0) {
	                  result=result+" "+n+" X "+i+" = "+n*i+"\n";
	               }else{
	            	   if(n*i>=10) {
	            		   result=result+" "+n+" X "+i+" = "+n*i+" | ";
	            	   }else {
	            		   result=result+" "+n+" X "+i+" = "+n*i+"  | ";
	            	   }
	               }
	            }
	            return result;
	         }
	         
	         
	         /**
	          * 구구단 n단과 m단의 계산결과를 출력하되, 다음과 같은 형식으로 한다.

	         n = 2, m = 5 인 경우
	         
	      2 X 1 = 2   |   2 X 2 = 4   | 2 X 3 = 6      |||      5 X 1 = 5   |   5 X 2 = 10   | 5 X 3 = 15   
	      2 X 4 = 8   |   2 X 5 = 10   | 2 X 6 = 12    |||     5 X 4 = 20   |   5 X 5 = 25   | 5 X 6 = 30
	      2 X 7 = 14   |   2 X 8 = 16   | 2 X 9 = 18    |||     5 X 7 = 35   |   5 X 8 = 40   | 5 X 9 = 45


	          * 
	          * @param n
	          * @param m
	          * @return
	          */
	         public String printNthAndMthTable(int n, int m) {
	           String result="";
	           String n_result="";
	           String m_result="";
	           
	            for (int i = 1; i <= 9; i++) {
	               
	               if(i%3!=0) {
	            	   if(n*i>=10){
	            		   n_result=n_result+" "+n+" X "+i+" = "+n*i+" | ";
	            	   }else{
	            		   n_result=n_result+" "+n+" X "+i+" = "+n*i+"  | ";
	            	   }
	            	   
	            	   if(m*i>=10){
	            		   m_result=m_result+" "+m+" X "+i+" = "+m*i+" | ";
	            	   }else {
	            		   m_result=m_result+" "+m+" X "+i+" = "+m*i+"  | ";
	            	   }
	                  }else{
	                     n_result=n_result+" "+n+" X "+i+" = "+n*i;
	                     m_result=m_result+" "+m+" X "+i+" = "+m*i;
	                     
	                     result=result+n_result+" ||| "+m_result+"\n";
	                     n_result="";
	                     m_result="";
	                  }
	           }
	            return result;
	         }
	         
	         
	         /**
	      2 X 1 = 2   |   2 X 2 = 4   | 2 X 3 = 6     |||     5 X 1 = 5    |   5 X 2 = 10   | 5 X 3 = 15   
	      2 X 4 = 8   |   2 X 5 = 10   | 2 X 6 = 12   |||     5 X 4 = 20   |   5 X 5 = 25   | 5 X 6 = 30
	      2 X 7 = 14  |   2 X 8 = 16   | 2 X 9 = 18   |||     5 X 7 = 35   |   5 X 8 = 40   | 5 X 9 = 45
	          * 
	          * @param n (inclusive)
	          * @param m (inclusive)
	          * @return
	          */
	         public String printFromNthToMthTable(int n, int m) {
	           String result="";
	           int a=0;
	           
	           dd:for (int i = n; i <= m; i++) {
	              int j = 1;
	              int b=3;
	              
	             if (a==0) {
	                j=1;
	            }else if(a==1){
	               j= j+(a*b);
	            }else if(a==2){
	               j= j+(a*b);
	            }
	             
	            for (; j <=9; j++) {
	               if(j%b==0){
	                  if(i==m){
	                	  result=result+i+" X "+j+" = "+ i*j+"\n";
	                     a++;
	                     i=n-1;
	                     if(a==b){
	                        break dd;
	                     }
	                  }else{
	                	  result=result+i+" X "+j+" = "+ i*j+" ||| ";
	                  }
	                  break;
	               }else{
	            	   if(i*j>=10) {
	            		   result=result+i+" X "+j+" = "+ i*j+" | ";
	            	   }else {
	            		   result=result+i+" X "+j+" = "+ i*j+"  | ";
	            	   }
	               }
	            }
	         }
	            return result;
	         }
}
