package pack.subject1.file2;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import pack.subject1.file2.exception.ConversionException;

public class DirTreeFileConverterImpl implements IGenericConverter<StringBuilder,File>{
	private File textFile;
	
	public DirTreeFileConverterImpl(String pathname) {
		this.textFile = new File(pathname);
		if (!textFile.exists()) {
			try {
				textFile.mkdirs(); //디렉토리가 새로만들어진경우만 True로 리턴함
				textFile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public File convert(StringBuilder source) {
		//파일 객체 생성
		try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(textFile));
				//()안에 여러 IO 추가적으로 설정가능
				){
		      if(textFile.isFile() && textFile.canWrite()){
		          //쓰기
		          bufferedWriter.write(source.toString());
		          bufferedWriter.close();
		      }
		  }catch (IOException e) {
			 //예외전화:: 내가 언체크익셉션 하나 만들어서 설명을 전달해주기
			 throw new ConversionException(e); 
		  }
		
			return textFile;
	}
	
	
	

}
