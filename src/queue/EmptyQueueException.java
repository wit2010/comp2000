package queue;

@SuppressWarnings("serial")
public class EmptyQueueException extends RuntimeException {

	public EmptyQueueException() {
		super();
	}

	public EmptyQueueException(String info) {
		super(info);
	}

	public String getMessage() {
		return super.getMessage();
	}

}
