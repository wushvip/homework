package com.exmple.socket;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @Title
 * @Author Administrator
 * @Date 2021-03-25 13:57
 * @Description
 * @Since V1.0
 */
public class SocketServer {

    public static void main(String[] args) throws IOException {
        ServerSocket socketServer = null;
        InputStream in = null;
        BufferedReader br = null;
        StringWriter sw = null;
        Socket socket = null;
        BufferedWriter bufferedWriter = null;
        try {
            socketServer = new ServerSocket(8889);
            socket = socketServer.accept();

            System.out.println("客户端已建立连接：" + socket.getPort());
            in = socket.getInputStream();
            br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String clientMessage = "";
            clientMessage += br.readLine();
//            while (br.readLine() !=null) {
//            }
            System.out.println("接受到客户端请求消息： " + clientMessage);

            //返回给客户端
            String result = "hello " + clientMessage;
            bufferedWriter = new BufferedWriter(new OutputStreamWriter
                    (new BufferedOutputStream(socket.getOutputStream())));
//                sw = new StringWriter();
            bufferedWriter.write(result);
            bufferedWriter.flush();
            System.out.println("返回给客户端mes: " + result);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(in !=null){
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(br !=null){
                br.close();
            }

            if(sw !=null){
                sw.close();
            }

            if(socket !=null){
                socket.close();
            }
            if(socketServer !=null){
                socketServer.close();
            }

            if(bufferedWriter !=null){
                bufferedWriter.close();
            }

        }
    }
}
