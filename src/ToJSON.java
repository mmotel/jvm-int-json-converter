import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


public class ToJSON {
	
	public Object getFieldValue (Object obj, Field field) throws InvocationTargetException {
		try {
			Object value = field.get(obj);
			return value;
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			//field is private
			String getterName = "get" + field.getName().substring(0,1).toUpperCase() +
					field.getName().substring(1, field.getName().length());
			
			try {
				Method getter = obj.getClass().getMethod(getterName);
				
				try {
					Object value = getter.invoke(obj);
					return value;
				} catch (IllegalAccessException e1) {
					//getter is private
				} catch (IllegalArgumentException e1) {
					//getter with argument(s) ?!
				}
			} catch (NoSuchMethodException e1) {
				//no getter method
				//TODO field is boolean? check 'isName' ?
			}
		}
		
		return null;
	}
	
	public String convert (Object o) throws InvocationTargetException {
		String result = "{";
		
		Class<? extends Object> bean = o.getClass();
		
		Field[] fields = bean.getDeclaredFields();
		
		for(Field field : fields){
			Object value = getFieldValue(o, field);
			if(value != null){
				Class<? extends Object> valueClass = value.getClass();
				if(valueClass.equals(String.class) || valueClass.equals(Character.class)){
					result += " \"" + field.getName() + "\": \"" + value + "\"," ;
				}
				else if(valueClass.equals(Integer.class) || valueClass.equals(Double.class) ||
						valueClass.equals(Float.class) || valueClass.equals(Long.class) ||
						valueClass.equals(Short.class) || valueClass.equals(Byte.class)) {
					result += " \"" + field.getName() + "\": " + value + "," ;
				}
			}
		}
		
		result = result.substring(0, result.length()-1);
		
		result+= " }";
		
		return result;
	}

}
