package pack.subject1;

import java.util.ArrayList;
import java.util.List;

public class TimeTable {
	public static void main(String[] args) {
		TimeTable t = new TimeTable();
        
	    System.out.println(t.printNthTable(4));
	    
		System.out.println();
		
	    System.out.println(t.printNthAndMthTable(4, 8));
	       
//		System.out.println();
		
//	    System.out.println(t.printFromNthToMthTable(4,9));
	}
	
	
	
	/**
	 		  2 X 1 = 2   |   2 X 2 = 4    | 2 X 3 = 6
		      2 X 4 = 8   |   2 X 5 = 10   | 2 X 6 = 12
		      2 X 7 = 14  |   2 X 8 = 16   | 2 X 9 = 18
	 **/
	//1번
	public String printNthTable(int n) {
		 String result="";
		 int row=3;
         String form ="&n X &i = &r";
         List<String> gugudan= new ArrayList<>();
         
         //데이터입력
         for (int i = 1; i <=9; i++) {
        	 gugudan.add(form.replace("&n", n+"").replace("&i", i+"").replace("&r", n*i+""));
		}
         
         //출력
         for (int i = 0,j = 1; i < gugudan.size(); i++,j++){
        	 
        	 if (j%row==0) {
        		result=result+gugudan.get(i)+"\n";
			}else {
				result=result+gugudan.get(i)+" | ";
			}
		}
         return result;
	}
	
	
	
	
	
	
	/**
	 *    n = 2, m = 5 인 경우
	      2 X 1 = 2   |   2 X 2 = 4   | 2 X 3 = 6      |||      5 X 1 = 5   |   5 X 2 = 10   | 5 X 3 = 15   
	      2 X 4 = 8   |   2 X 5 = 10   | 2 X 6 = 12    |||     5 X 4 = 20   |   5 X 5 = 25   | 5 X 6 = 30
	      2 X 7 = 14  |   2 X 8 = 16   | 2 X 9 = 18    |||     5 X 7 = 35   |   5 X 8 = 40   | 5 X 9 = 45
	 */
	//2번
	public String printNthAndMthTable(int n, int m) {
		int row=3;
        String result="";
        String form ="&n X &i = &r";
        List<String> gugudan= new ArrayList<>();
        
        //데이터입력
        for (int i = 1,j = 1; i <=9; i++) {
       	 gugudan.add(form.replace("&n", n+"").replace("&i", i+"").replace("&r", n*i+""));
       	 	if(i%row==0) {
       	 		dd:for (; j <=9; j++) {
       	 			
       	 			if(i%row==0) {
       	 				gugudan.add(form.replace("&n", m+"").replace("&i", j+"").replace("&r", m*j+""));
       	 				if(i==j) {
       	 					j++;
       	 					break dd;
       	 					}
       	 			}
       	 		}
       	 	}
		}
        
        //출력
        boolean spac=false;
        for (int i = 0,j = 1; i < gugudan.size(); i++,j++) {
        	if(j%row==0) {
        		if (spac) {
        			result=result+gugudan.get(i)+"\n";
        			spac=false;
				}else {
					result=result+gugudan.get(i)+" ||| ";
					spac=true;
				}
        	}else {
        		result=result+gugudan.get(i)+" | ";
        	}
		}
         return result;
	}
	
	
	
	
	
	
	
	/**
	 	  2 X 1 = 2   |   2 X 2 = 4    | 2 X 3 = 6     |||     3 X 1 = 3    |   3 X 2 = 6    | 3 X 3 = 9   
	      2 X 4 = 8   |   2 X 5 = 10   | 2 X 6 = 12    |||     3 X 4 = 12   |   3 X 5 = 15   | 3 X 6 = 18
	      2 X 7 = 14  |   2 X 8 = 16   | 2 X 9 = 18    |||     3 X 7 = 21   |   3 X 8 = 24   | 3 X 9 = 27
	 */
	//3번
//	 public String printFromNthToMthTable(int n, int m) {
//		 String result="";
//		 int ii=3;
//         String form ="&n X &i = &r";
//         List<String> gugudan= new ArrayList<>();
//		 
//         //데이터입력
//         for (int i = n; i <=m; i++) {
//        	 for (int j = 1; j <=9; j++) {
//        		 gugudan.add(form.replace("&n", i+"").replace("&i", j+"").replace("&r", i*j+""));
//			}
//		}
//         
//         
//         System.out.println(gugudan);
//         System.out.println();
//         
//         //출력
//
//         
//         
//		 return result;
//	 }
	 
	 
}


