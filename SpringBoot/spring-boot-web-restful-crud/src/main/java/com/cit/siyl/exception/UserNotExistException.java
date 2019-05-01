package com.cit.siyl.exception;

public class UserNotExistException extends RuntimeException {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UserNotExistException() {
        super("用户不存在");
    }
}
