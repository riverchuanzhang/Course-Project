/**
 * 
 */
package com.example.learning_platform.exception;

import java.io.PrintStream;
import java.io.PrintWriter;

import org.apache.commons.lang.StringUtils;

/**
 * @author zdk
 * 
 */
public class AppException extends Exception {

	private static final long serialVersionUID = 1029570212429763063L;

	private Status status;

	public final Status getStatus() {
		return status;
	}

	public AppException(Status status) {
		super((StringUtils.isNotEmpty(status.getType())) ? status.getType()
				: ExceptionInfo.Type.UNKNOWN);
		this.status = status;
	}

	public void printStackTrace() {
		printStackTrace(System.err);
	}

	/**
	 * Prints a stack trace out for the exception, and any nested exception that
	 * it may have embedded in its Status object.
	 * 
	 * @param output
	 *            the stream to write to
	 */
	public void printStackTrace(PrintStream output) {
		PrintWriter writer = new PrintWriter(output);
		printStackTrace(writer);
	}

	/**
	 * Prints a stack trace out for the exception, and any nested exception that
	 * it may have embedded in its Status object.
	 * 
	 * @param output
	 *            the stream to write to
	 */
	public void printStackTrace(PrintWriter output) {
		synchronized (output) {
			if (status.getCause() != null) {
				output.println(getClass().getName() + "[ErrorType="
						+ status.getType() + "]: ");
				status.getCause().printStackTrace(output);
			} else
				super.printStackTrace(output);
		}
		output.flush();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Throwable#getLocalizedMessage()
	 */
	public String getMessage() {
		return status.getMessage();
	}

}
