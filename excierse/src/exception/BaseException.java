/**
 * 
 */
package exception;

/**
 * @author wushuai
 * @date 2018��11��10��
 * @Description	TODO
 */
public class BaseException extends RuntimeException {
	
	private static final long serialVersionUID = 7634117307226567791L;
	
	public BaseException() {}
	
	public BaseException(String message) {
		super(message);
	}
}
