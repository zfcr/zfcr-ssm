package cn.zfcr.system.utils;

/**
 * 类型转换
 * @author zhangfeng
 *
 */
public class ConvertUtils {

	public static final String DATA_TYPE_STRING = "String";
	
	public static final String DATA_TYPE_BYTE = "Byte";
	
	public static final String DATA_TYPE_SHORT = "Short";
	
	public static final String DATA_TYPE_INGETER = "Ingeter";
	
	public static final String DATA_TYPE_LONG = "Long";
	
	public static final String DATA_TYPE_FLOAT = "Float";
	
	public static final String DATA_TYPE_DOUBLE = "Double";
	
	public static final String DATA_TYPE_BOOLEAN = "Boolean";
	
	public static final String DATA_TYPE_CHARACTER = "Character";
	
	public static final String DATA_TYPE_DATE = "Date";
	
	/**
	 * 判断指定的值是否为空或null
	 * @param value
	 * @return true:空,false:不为空
	 */
	public static boolean isEmptyOrDefault(Object value){
		if(value == null){
			return true;
		}
		switch (value.getClass().getSimpleName()) {
			case DATA_TYPE_STRING:
				if(value==null || "".equals(value.toString())){
					return true;
				}
				break;
		}
		return false;
	}
}
