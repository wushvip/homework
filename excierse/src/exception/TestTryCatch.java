/**
 * 
 */
package exception;

import javax.net.SocketFactory;
import javax.xml.ws.http.HTTPException;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * @author wushuai
 * @date 2018��11��10��
 * @Description	TODO
 */
public class TestTryCatch {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		check();
		System.out.println("23334");

	}



	public static  void check(){
		try {
			Socket socket = new Socket();
			socket.bind(new InetSocketAddress(123));
			if(true) {
				throw new BaseException("123");
			}
			System.out.println("try do ^^^^^");
		}catch (BaseException e){
			e.printStackTrace();
			System.out.println("抛出baseException------");
			throw new BaseException(e.getMessage());
		} catch (IOException e){
			e.printStackTrace();
			System.out.println("抛出IOException------");
			throw new BaseException(e.getMessage());
		}catch(Exception e) {
			e.printStackTrace();
			throw new BaseException("check is fail");
		}finally {
//			if(true) {
//				throw new BaseException("11111");
//			}
			System.out.println("________________");
		}
		System.out.println("5555555555555");
	}

}
