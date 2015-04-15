package CMPE275Lab3.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class MyExceptions {

	@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason="Invalid Parameters")
	public static class InvalidParametersException extends RuntimeException
	{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public InvalidParametersException(){}
	}
	
	@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason="Duplicate player email")
	public static class DuplicatePlayerEmailException extends RuntimeException
	{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public DuplicatePlayerEmailException(){}
	}
	
	
	@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason="User not found")
	public static class UserNotFoundException extends RuntimeException
	{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public UserNotFoundException(){}
	}
}
