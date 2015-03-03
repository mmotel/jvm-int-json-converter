import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


public class ToJSON {
	
	public String SimpleProperites (Object o) throws IllegalArgumentException, SecurityException, InvocationTargetException {
		String result = "{";
		
		Class<? extends Object> bean = o.getClass();
		
		Field[] fields = bean.getDeclaredFields();
		
		for(Field field : fields){
			try {
				Object value = field.get(o);
				result += " \"" + field.getName() + "\": " + value.toString() + ",";
			}
			catch (IllegalAccessException illAccExp) {
				//field is private
				String getterName = "get" + field.getName().substring(0,1).toUpperCase() +
						field.getName().substring(1, field.getName().length());
				try{
					Method getter = bean.getMethod(getterName);
	
					try {
						Object value = getter.invoke(o);
						result += " \"" + field.getName() + "\": " + value.toString() + ",";
					} catch (IllegalAccessException IllAccExp2) {
						//getter is private
					}
				}
				catch (NoSuchMethodException NoSuchMetdExp) {
					//no getter for field
				}
				
			}
		}
		
		result = result.substring(0, result.length()-1);
		
		result+= " }";
		
		return result;
	}

}
