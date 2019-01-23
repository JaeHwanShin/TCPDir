package pack.subject1;

public class exception {
public static void main(String[] args) {
		
		String result = new exception().a();
		
		System.out.println("result : [" + result + "]");
		
	}
	
	public String a() {
		return "a" + b();
	}
	
	public String b() {
		
		String cResult = "D";
		
		try {
			cResult = c();
		} catch (Exception e) {
			throw new CException(e);
		}
		
		return "c" + cResult;
		
	}

	public String c() {
		String dResult = "D";
		
		try {
			dResult = d();
		} catch (Exception e) {
			throw new DException(e);
		}
		
		return "c" + dResult;
	}

	public String d() {
		
		throw new IllegalStateException("Hello Exception");
		
	}
	
	private static final class CException extends RuntimeException {
		
		private static final long serialVersionUID = 6295025212890947593L;
		
		public CException() {
			super();
		}

		public CException(Throwable t) {
			super(t);
		}
		
	}
	
	public static final class DException extends RuntimeException {
		
		private static final long serialVersionUID = 6295025212890947592L;

		public DException() {
			super();
		}

		public DException(Throwable t) {
			super(t);
		}
		
	}
}
