package ts_ec_kadai.exception;

public class NotSetParameterException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private static final String ERROR_MESSAGE = "パラメータが正しく設定されていません。";

	public NotSetParameterException() {
		this(ERROR_MESSAGE);
	}

	public NotSetParameterException(String msg) {
		super(msg);
	}

	public NotSetParameterException(Object obj) {
		this(ERROR_MESSAGE + "渡されたオブジェクト=" + obj.toString());
	}
}
