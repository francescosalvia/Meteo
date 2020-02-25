package com.meteo.meteo.data;

public class GenericResponse {

    private String statusMessage;
    private Object data;
    private String result;


    public GenericResponse(String statusMessage, Object data) {
        this.statusMessage = statusMessage;
        this.data = data;
    }

    public GenericResponse(String statusMessage, String result) {
        this.statusMessage = statusMessage;
        this.result = result;
    }

    public GenericResponse() {

    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public static GenericResponse ok(String str){
        return new GenericResponse(str,null);
    }

    public static  GenericResponse failed(String str){
        return new GenericResponse(str,null);
    }

    public static GenericResponse okObject(String statusMessage,Object object){
        return new GenericResponse(statusMessage,object);
    }

    public static GenericResponse okResult(String statusMessage,String result){
        return new GenericResponse(statusMessage,result);
    }
}