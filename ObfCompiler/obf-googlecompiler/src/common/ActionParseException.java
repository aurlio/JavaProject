package common;

/**
 * ActionJS解析异常
 * 
 * @author xunzhe xunzhe.mm@alibaba-inc.com
 * @date 2013-1-11 下午12:22:30
 * 
 */
public class ActionParseException extends Exception {

	private static final long serialVersionUID = -6018535453883517497L;

	public ActionParseException(String str, Throwable throwable) {
		super(str, throwable);
	}

	public ActionParseException(String str) {
		super(str);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Throwable#toString()
	 */
	public String toString() {
		String string = this.getClass().getName()
				+ ": Action Parse Failed due to " + this.getLocalizedMessage();
		if (this.getCause() != null) {
			string += "\nnested exception is "
					+ this.getCause().getClass().getName() + ": "
					+ this.getCause().getLocalizedMessage();
		}
		return string;
	}

}