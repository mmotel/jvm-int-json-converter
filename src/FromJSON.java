import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


public class FromJSON {
	
	public Boolean setFieldValue(Object obj, Field field, Object value){
		//try by public
		try {
			field.set(obj, value);
			return true;
		} catch (IllegalArgumentException e) {
			// type mismatch ?
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// field is private, try by setter
			String setterName = "set" + field.getName().substring(0,1).toUpperCase() +
					field.getName().substring(1);
			try {
				Method setter = obj.getClass().getMethod(setterName, field.getType());
				try {
					setter.invoke(obj, value);
				} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e1) {
					// setter is private
					// wrong setter argument
					e1.printStackTrace();
					return false;
				}
			} catch (NoSuchMethodException | SecurityException e1) {
				// no setter
				// setter is private ?
				e1.printStackTrace();
				return false;
			}
		}
		return false;
	}
	
	public Object castFieldValue(String value, Class<?> c){
		System.out.println(c + " : " + value);
		if(c.equals(Integer.class) || c.equals(int.class)){
			if(value == null){ 
				value = "0";
			}
			return Integer.parseInt(value);
		}
		else if(c.equals(Float.class) || c.equals(float.class)){
			if(value == null){ 
				value = "0";
			}
			return Float.parseFloat(value);
		}
		else if(c.equals(Double.class) || c.equals(double.class)){
			if(value == null){ 
				value = "0";
			}
			return Double.parseDouble(value);
		}
		else if(c.equals(Short.class) || c.equals(short.class)){
			if(value == null){ 
				value = "0";
			}
			return Short.parseShort(value);
		}
		else if(c.equals(Long.class) || c.equals(long.class)){
			if(value == null){ 
				value = "0";
			}
			return Long.parseLong(value);
		}
		else if(c.equals(Byte.class) || c.equals(byte.class)){
			if(value == null){ 
				value = "0";
			}
			return Byte.parseByte(value);
		}
		else if(c.equals(Character.class)  || c.equals(char.class)){
			if(value == null){
				return '\u0000';
			}
			return (Character) value.charAt(0);
		}
		else if(c.equals(String.class)){
			return value;
		}
		else if(c.equals(Boolean.class)  || c.equals(boolean.class)){
			if(value == null){
				value = "";
			}
			return (Boolean) (value.compareTo("true")==0) ? true : false;
		}
		return null;
	}
	
	public Object findFieldValue(Field field, String json){
		Class<?> fieldClass = field.getType();
		Integer position = json.indexOf(field.getName());
		System.out.println(field.getName() + "[" + fieldClass + "] @" + position);
		if(position == -1){
			return castFieldValue(null, fieldClass);
		}
		Integer valuePostion = position + field.getName().length() + 2;
		String subJson = json.substring(valuePostion);
		
		if(fieldClass.equals(Integer.class) || fieldClass.equals(Float.class) || 
				fieldClass.equals(Double.class) || fieldClass.equals(Short.class) || 
				fieldClass.equals(Long.class) || fieldClass.equals(Byte.class) ||
				fieldClass.equals(int.class) || fieldClass.equals(float.class) || 
				fieldClass.equals(double.class) || fieldClass.equals(short.class) || 
				fieldClass.equals(long.class) || fieldClass.equals(byte.class))
		{
			Integer endPosition = subJson.indexOf(",");
			if(endPosition == -1){
				endPosition = subJson.indexOf("}");
			}
			String value = subJson.substring(0, endPosition);
			return castFieldValue(value, fieldClass);
		}
		else if(fieldClass.equals(String.class) || fieldClass.equals(Character.class) ||
				fieldClass.equals(char.class)){ 
			Integer endPosition = subJson.substring(1).indexOf("\",");
			if(endPosition == -1){
				endPosition = subJson.substring(1).indexOf("\"}");
			}
			String value = subJson.substring(1, endPosition + 1);
			return castFieldValue(value, fieldClass);
		}
		else if(fieldClass.equals(Boolean.class) || fieldClass.equals(boolean.class)){
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
					System.out.println(field.getName() + " : " + value);
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
