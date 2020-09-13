package com.ai2331.common.sql.dialect;

/**
 * 属性名-字段名的相互映射.
 * 使用静态实例M_DEFAULT时，字段名缺省为小写；如需大写，设置系统属性pfz.fieldnamemapper.uppercase=true
 */
public interface FieldNameMapper {
	final public static FieldNameMapper M_LOWER_COLUMN = new DefaultMapper(false);
	final public static FieldNameMapper M_UPPER_COLUMN = new DefaultMapper(true);
	
	final public static FieldNameMapper M_DEFAULT = "true".equals(System.getProperty("pfz.fieldnamemapper.uppercase"))?M_UPPER_COLUMN:M_LOWER_COLUMN;
	
	/**
	 * 根据属性名（实体类）返回对应的数据库字段名.
	 * 如taskId -> task_id
	 * @param propertyName 属性名
	 * @return
	 */
	String getColumnName(String propertyName);
	
	/**
	 * 根据数据库字段名返回对应的属性名（实体类）.
	 * 如task_id -> taskId
	 * @param columnName 字段名
	 * @return
	 */
	String getPropertyName(String columnName);
	
	static class DefaultMapper implements FieldNameMapper {
		private boolean columnUpperCase;
		
		public DefaultMapper(boolean columnUpperCase) {
			this.columnUpperCase = columnUpperCase;
		}
		@Override
		public String getColumnName(String propertyName) {
			StringBuilder sb = new StringBuilder();
			boolean prevLowerCase = false;
			boolean prevUnderScore= false;
			for(char c: propertyName.toCharArray()){
				if(c>='A' && c<='Z'){
					if(prevLowerCase && !prevUnderScore){
						sb.append('_');
					}
					sb.append((char)(c^' '));	//to lower case
					
					prevLowerCase = false;
					prevUnderScore = false;
				} else {
					sb.append(c);
					prevLowerCase = true;
					if(c == '_'){
						prevUnderScore = true;
					}
				}
			}
			if(columnUpperCase) {
				return sb.toString().toUpperCase();
			} else {
				return sb.toString();
			}
		}

		@Override
		public String getPropertyName(String columnName) {
			String name = columnName.toLowerCase();
			StringBuilder sb = new StringBuilder();
			boolean nextuc = false;
			for(int i=0,len=name.length();i<len;i++){
				char c = name.charAt(i);
				if(c == '_'){
					nextuc = true;
					continue;
				}
				
				if(nextuc){
					sb.append((""+c).toUpperCase());
				}else{
					sb.append(c);
				}
				
				nextuc = false;
			}
			return sb.toString();
		}
		
	}
}
