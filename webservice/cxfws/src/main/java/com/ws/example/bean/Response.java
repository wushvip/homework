package com.ws.example.bean;

/**
 * @Author: wushuai
 * @Date: 2020/4/28 8:59
 * @Description:
 */
public abstract class Response {

    private int code;

    private String messsage;

//    private T data;

    private ResponseBuilder responseBuilder;

    public static Response.ResponseBuilder init(Object entity) {
        return ResponseBuilder.newInstance().setEntity(entity);
    }


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMesssage() {
        return messsage;
    }

    public void setMesssage(String messsage) {
        this.messsage = messsage;
    }


    public abstract static class ResponseBuilder{

        protected ResponseBuilder(){}

        public static Response.ResponseBuilder newInstance(){
            return ResponseBuilder.newInstance();
        };

        protected abstract ResponseBuilder setEntity(Object entity);

        public abstract Response build();


    }
}
