package rpc;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * @Title
 * @Author Administrator
 * @Date 2021-03-25 14:53
 * @Description
 * @Since V1.0
 */
public class SocketClientDemo {

    public static void main(String[] args) {
        Socket socket = null;
        BufferedOutputStream bos = null;
        BufferedReader br = null;
        BufferedWriter bw = null;
        PrintWriter pw = null;
        try {
            socket = new Socket("127.0.0.1",8889);
            //socket.bind(new InetSocketAddress(8889));
            //客户端写消息
            bos = new BufferedOutputStream(socket.getOutputStream());
            String mess = new String("wushuai!");

//            bw = new BufferedWriter(new OutputStreamWriter(bos));
            pw = new PrintWriter(bos);
            pw.write(mess + "\n");
//            pw.println(mess);
            pw.flush();
            System.out.println("client send mess: "  + mess);

            br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String result = br.readLine();
            System.out.println("client reciev mess:" + result);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(br !=null){
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(bos !=null){
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(socket !=null){
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if(bw !=null){
                try {
                    bw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(pw !=null){
               pw.close();
            }
        }
    }
}
