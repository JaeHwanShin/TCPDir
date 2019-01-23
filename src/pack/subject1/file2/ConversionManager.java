package pack.subject1.file2;


import java.util.HashMap;
import java.util.Map;

import pack.subject1.file2.exception.NoSuchConverterException;

public class ConversionManager { // 매니저는 원래 넣어주고 빼어주는거만 했지만 >>  컨버터 이외의 컨버터맵을 관리하는 객체가 있고 그 아이에게 일을 시키는 일만 해야함
   
	// 원칙대로하면 >> 사실 컨퍼트 레지스트리가 필요함 // 매니저는 그것을 관리하는것일뿐
	 private final Map<TypeOfSourceAndResponse<?, ?>, IGenericConverter<?, ?>> converterMap = new HashMap<>();

	 	// 소스& 결과타입 & 인터페이스를 받아서 map에  put 해주기
	   public void addConverter(Class<?> sourceType, Class<?> returnType, IGenericConverter<?, ?> converter) {
	      converterMap.put(new TypeOfSourceAndResponse<>(sourceType, returnType), converter);
	   }

	   // 요구되는 값 받기
	   @SuppressWarnings({ "unchecked", "rawtypes" })
	   public <S, R> R requestConvert(S source, Class<R> resultTypeClass) {
	      if (source == null || resultTypeClass == null) {
	         throw new IllegalArgumentException();
	      }

	      IGenericConverter converter = converterMap.get(new TypeOfSourceAndResponse<>(source.getClass(), resultTypeClass));
	      if (converter == null) {
	         throw new NoSuchConverterException("No Converter for ResultType: " + resultTypeClass.getName());
	      }
	      
	      return ((IGenericConverter<S, R>)converter).convert(source);  //캐스팅하여 형변환해서 리턴해 주기
	   }

	   //위 ConversionManager 클래스 호출시 TypeOfSourceAndResponse도 같이 클래스를 메모리에 올리기 위해 static을 붙였다고 함
	   private static class TypeOfSourceAndResponse<S, R> {
	      Class<S> s;
	      Class<R> r;
	      
	      public TypeOfSourceAndResponse(Class<S> s, Class<R> r) {
	         this.s = s;
	         this.r = r;
	      }
	      
	      @Override
	      public int hashCode() {
	         final int prime = 31;
	         int result = 1;
	         result = prime * result + ((r == null) ? 0 : r.hashCode());
	         result = prime * result + ((s == null) ? 0 : s.hashCode());
	         return result;
	      }

	      @Override
	      public boolean equals(Object obj) {
	         if (this == obj)
	            return true;
	         if (obj == null)
	            return false;
	         if (getClass() != obj.getClass())
	            return false;
	         
	        //equals재정의
	         @SuppressWarnings("unchecked")
	         TypeOfSourceAndResponse<S, R> other = (TypeOfSourceAndResponse<S, R>) obj;
	         
	         if (r == null) {
	            if (other.r != null)
	               return false;
	         } else if (!r.equals(other.r))
	            return false;
	         if (s == null) {
	            if (other.s != null)
	               return false;
	         } else if (!s.equals(other.s))
	            return false;
	         return true;
	      }

	   
	   }
   
}