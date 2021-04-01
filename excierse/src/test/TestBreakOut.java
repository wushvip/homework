/**
 * 
 */
package test;

/**
 * @author wushuai
 * @date 2018Äê9ÔÂ28ÈÕ
 * @Description	TODO
 */
public class TestBreakOut {
	public static void main(String[] args) {
		for(int i=0;i<10;i++) {
			out: for(int j=0;j<10;j++) {
				if(j==5) {
					break out;
				}
				System.out.println(j);
			}
		
			out: for(int j=0;j<10;j++) {
				if(j==4) {
					break out;
				}
				System.out.println(j);
			}
		}
	}

}
