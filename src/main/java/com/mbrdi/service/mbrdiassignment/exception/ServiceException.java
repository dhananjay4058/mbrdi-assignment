package com.mbrdi.service.mbrdiassignment.exception;

/**
 * 
 * @author dhananjay
 *
 */
public class ServiceException extends RuntimeException {

	private static final long serialVersionUID = 1L;

		private String errorCode;
		private String errormessage;
		
		public String getErrorCode() {
			return errorCode;
		}
		public void setErrorCode(String errorCode) {
			this.errorCode = errorCode;
		}
		public String getErrormessage() {
			return errormessage;
		}
		public void setErrormessage(String errormessage) {
			this.errormessage = errormessage;
		}
		
		public ServiceException() {
			super();
		}
		
		public ServiceException(String errorCode, String errormessage) {
			super();
			this.errorCode=errorCode;
			this.errormessage=errormessage;
		}
		
	}


