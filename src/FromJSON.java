import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;


public class FromJSON {
	
	public Boolean setFieldValue(Object obj, Field field, Object value){
		//try by public
		//try by setter
		return true;
	}
	
	public Object castFieldValue(String value, Class<?> c){
		if(c.equals(Integer.class)){
			if(value == null){ 
				value = "0";
			}
			return Integer.parseInt(value);
		}
		else if(c.equals(Float.class)){
			if(value == null){ 
				value = "0";
			}
			return Float.parseFloat(value);
		}
		else if(c.equals(Double.class)){
			if(value == null){ 
				value = "0";
			}
			return Double.parseDouble(value);
		}
		else if(c.equals(Short.class)){
			if(value == null){ 
				value = "0";
			}
			return Short.parseShort(value);
		}
		else if(c.equals(Long.class)){
			if(value == null){ 
				value = "0";
			}
			return Long.parseLong(value);
		}
		else if(c.equals(Byte.class)){
			if(value == null){ 
				value = "0";
			}
			return Byte.parseByte(value);
		}
		else if(c.equals(Character.class)){
			if(value == null){
				return '\u0000';
			}
			return (Character) value.charAt(0);
		}
		else if(c.equals(String.class)){
			return value;
		}
		else if(c.equals(Boolean.class)){
			return (Boolean) (value.compareTo("true")==0) ? true : false;
		}
		return null;
	}
	
	public Object findFieldValue(Field field, String json){
		Class<?> fieldClass = field.getType();
		Integer position = json.indexOf(field.getName());
		if(position == -1){
			return castFieldValue(null, fieldClass);
		}
		Integer valuePostion = position + field.getName().length() + 2;
		String subJson = json.substring(valuePostion);
		
		System.out.println(json);
		
		if(fieldClass.equals(Integer.class) || fieldClass.equals(Float.class) || fieldClass.equals(Double.class)
				|| fieldClass.equals(Short.class) || fieldClass.equals(Long.class) || fieldClass.equals(Byte.class))
		{
			Integer endPosition = subJson.indexOf(",");
			if(endPosition == -1){
				endPosition = subJson.indexOf("}");
			}
			String value = subJson.substring(0, endPosition);
			return castFieldValue(value, fieldClass);
		}
		else if(fieldClass.equals(String.class)){ 
			Integer endPosition = subJson.substring(1).indexOf("\",");
			if(endPosition == -1){
				endPosition = subJson.substring(1).indexOf("\"}");
			}
			String value = subJson.substring(1, endPosition + 1);
			return castFieldValue(value, fieldClass);
		}
		else if(fieldClass.equals(Character.class)){ 
			Integer endPosition = subJson.substring(1).indexOf("\',");
			if(endPosition == -1){
				endPosition = subJson.substring(1).indexOf("\'}");
			}
			String value = subJson.substring(1, endPosition + 1);
			return castFieldValue(value, fieldClass);
		}
		else if(fieldClass.equals(Boolean.class)){
			Integer endPosition = subJson.indexOf(",");
			if(endPosition == -1){
				endPosition = subJson.indexOf("}");
			}
			String value = subJson.substring(0, endPosition);
			return castFieldValue(value, fieldClass);
		}
		
		return null;
	}
	
	public Object convert (String json, Class<?> c) {
		Object result = null;
		try {
			Constructor<?> constructor = c.getConstructor();
			try {
				result = constructor.newInstance();
				
				for(Field field : c.getDeclaredFields()){
					Object value = findFieldValue(field, json);
					setFieldValue(result, field, value);
				}
				
			} catch (IllegalAccessException | IllegalArgumentException e) {
				// no argument constructor is private
				// no argument constructor needs arguments ?!
				e.printStackTrace();
			} catch (InstantiationException | InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (NoSuchMethodException | SecurityException e) {
			// no argument constructor does not exist? 
			// no argument constructor is private?
			e.printStackTrace();
		}
		
		return result;
	}

}
