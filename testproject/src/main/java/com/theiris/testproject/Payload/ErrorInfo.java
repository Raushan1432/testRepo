package com.theiris.testproject.Payload;


import java.util.Date;

public class ErrorInfo {
    private Date date;
    private String msg;

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    private String desc;

    public ErrorInfo(Date date, String msg, String desc) {
        this.date = date;
        this.msg = msg;
        this.desc = desc;
    }


}
