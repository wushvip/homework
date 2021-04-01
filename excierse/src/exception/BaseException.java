/**
 * 
 */
package exception;

/**
 * @author wushuai
 * @date 2018Äê11ÔÂ10ÈÕ
 * @Description	TODO
 */
public class BaseException extends RuntimeException {
	
	private static final long serialVersionUID = 7634117307226567791L;
	
	public BaseException() {}
	
	public BaseException(String message) {
		super(message);
	}
}
