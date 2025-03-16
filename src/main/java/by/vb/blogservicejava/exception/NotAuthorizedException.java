package by.vb.blogservicejava.exception;

public class NotAuthorizedException extends RuntimeException {
	public NotAuthorizedException() {
		super("User is not authorized.");
	}
}
