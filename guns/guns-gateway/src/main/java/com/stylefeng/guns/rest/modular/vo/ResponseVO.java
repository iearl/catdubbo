package com.stylefeng.guns.rest.modular.vo;
/*
统一返回前端的样式格式
  status: 代表执行状态
  msg: 返回信息
  data: 返回前端处理数据
 */
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
    public static<M> ResponseVO fail(M m){
        ResponseVO responseVO = new ResponseVO();
        responseVO.setStatus(1);
        responseVO.setDate(m);
        responseVO.setMsg("error");
        return responseVO;
    }

    //系统异常
    public static<M> ResponseVO appFail(M m){
        ResponseVO responseVO = new ResponseVO();
        responseVO.setStatus(999);
        responseVO.setDate(m);
        responseVO.setMsg("exception");
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
