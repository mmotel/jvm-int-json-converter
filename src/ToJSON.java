import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;

public class ToJSON {
	
	public Object getFieldValue (Object obj, Field field) throws InvocationTargetException {
		try {
			Object value = field.get(obj);
			return value;
		} catch (IllegalArgumentException e) {
			// you're a moron and can't even pass object to get method
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
				String isName = "is" + field.getName().substring(0,1).toUpperCase() +
						field.getName().substring(1, field.getName().length());
				try {
					Method isbool = obj.getClass().getMethod(isName);
					try {
						Object value = isbool.invoke(obj);
						return value;
					} catch (IllegalAccessException e2) {
						//no boolean getter
					} catch (IllegalArgumentException e2) {
						//boolean getter with arguments ?
					}
				} catch (NoSuchMethodException e2) {
					//no getter method
				}
			}
		}
		
		return null;
	}
	
	public String wrapPrimitiveType (Object value) {
		Class<? extends Object> valueClass = value.getClass();
		if(valueClass.equals(String.class) || valueClass.equals(Character.class)){
			return "\"" + value + "\"";
		}
		else if(valueClass.equals(Integer.class) || valueClass.equals(Double.class) ||
				valueClass.equals(Float.class) || valueClass.equals(Long.class) ||
				valueClass.equals(Short.class) || valueClass.equals(Byte.class)) {
			return value.toString();
		}
		else if(valueClass.equals(Boolean.class)) {
			return value.toString();
		}
		
		return null;
	}
	
	public String convert (Object o) throws InvocationTargetException {
		String result = "{";
		
		Field[] fields = o.getClass().getDeclaredFields();
		
		for(Field field : fields){
			Object value = getFieldValue(o, field);
			if(value != null){
				result += " \"" + field.getName() + "\": ";
				String primitive = wrapPrimitiveType(value);
				if(primitive != null){
					result += primitive + ",";
				}
				else if(Collection.class.isAssignableFrom(value.getClass())){
					result += "[ ";
					Collection<?> coll = (Collection<?>) value;
					for(Object item : coll.toArray()) {
						String primitiveItem = wrapPrimitiveType(item);
						if(primitiveItem != null) {
							result += primitiveItem + ", ";
						} else {
							result += convert(item) + ", ";
						}
					}
					result = result.substring(0, result.length()-2) + " ],";
				}
			}
		}
		
		result = result.substring(0, result.length()-1) + " }";
		
		return result;
	}

}
