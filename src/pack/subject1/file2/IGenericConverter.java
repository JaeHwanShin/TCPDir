package pack.subject1.file2;

public interface IGenericConverter<S,R> {
	public R convert(S source); //소스를 받아서 결과를 출력해주는 메서드
}
