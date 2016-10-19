package z.lib;

public class CoreException extends RuntimeException {

	private static final long serialVersionUID = 8811881540666206738L;

	public static final int CONNECTION = 0;
	public static final int DATABASE = 1;
	public static final int TOP = 2;
	public static final int SOLR = 3;
	public static final int IMAGE = 4;
	public static final int UNKOWN = -1;
	
	public static final int DUPLICATE = -11;
	
	private int type;
	
	public CoreException() {
		this(UNKOWN);
	}
	
	public CoreException(String message) {
		this(UNKOWN, message);
	}
	
	public CoreException(String message, Throwable throwable) {
		this(UNKOWN, message, throwable);
	}
	
	public CoreException(int type) {
		super();
		this.type = type;
	}
	
	public CoreException(int type, String message) {
		super(message);
		this.type = type;
	}
	
	public CoreException(int type, String message, Throwable throwable) {
		super(message, throwable);
		this.type = type;
	}
	
	public CoreException(int type, Throwable throwable) {
		super(throwable);
		this.type = type;
	}
	
	public int getType() {
		return type;
	}
}
