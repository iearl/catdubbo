package com.stylefeng.guns.rest.modular.vo;
//统一返回前端的样式格式
public class ResponseVO<M> {
    //返回状态[0-成功，1-失败，999-系统异常]
    private int status;
    //返回信息
    private String msg;
    //返回数据实体
    private M date;

    public ResponseVO() {
    }

    //方法正确执行
    public static<M> ResponseVO success(M m){
        ResponseVO responseVO = new ResponseVO();
        responseVO.setStatus(0);
        responseVO.setDate(m);
        responseVO.setMsg("success");
        return responseVO;
    }

    //方法业务异常
    public static<M> ResponseVO fail(String msg){
        ResponseVO responseVO = new ResponseVO();
        responseVO.setStatus(1);
        responseVO.setDate(null);
        responseVO.setMsg(msg);
        return responseVO;
    }

    //系统异常
    public static<M> ResponseVO appFail(String msg){
        ResponseVO responseVO = new ResponseVO();
        responseVO.setStatus(999);
        responseVO.setDate(null);
        responseVO.setMsg(msg);
        return responseVO;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public M getDate() {
        return date;
    }

    public void setDate(M date) {
        this.date = date;
    }
}
