package com.example.servlet;

public class MemberVO {
    private String mid;
    private String mpw;
    private String mname;
    private String uuid;



    public String getUuid() {
        return uuid;
    }
    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
    public MemberVO() {
        super();
        // TODO Auto-generated constructor stub
    }
    public String getMid() {
        return mid;
    }
    public String getMpw() {
        return mpw;
    }
    public String getMname() {
        return mname;
    }
    public void setMid(String mid) {
        this.mid = mid;
    }
    public void setMpw(String mpw) {
        this.mpw = mpw;
    }
    public void setMname(String mname) {
        this.mname = mname;
    }

    @Override
    public String toString() {
        return "MemberVO [mid=" + mid + ", mpw=" + mpw + ", mname=" + mname + "]";
    }


}

