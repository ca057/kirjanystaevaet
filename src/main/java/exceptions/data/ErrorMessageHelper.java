package exceptions.data;

public abstract class ErrorMessageHelper {

	public static String nullOrEmptyMessage(String field) {
		return String.format("%s must not be null or empty string.", field);
	}
	
	public static String primaryKeyViolation(){
		return String.format("PrimaryKeyViolation, Keys must be unique");
	}
	
	public static String generalDatabaseError(String field){
		return String.format("General Database Error: %s", field);
	}
	
	public static String mayContainOnlyNumbers(String field){
		return String.format("String %s may only contain digits", field);
	}
	
	public static String entityDoesNotExist(String entity){
		return String.format("%s does not Exist", entity);
	}

}
