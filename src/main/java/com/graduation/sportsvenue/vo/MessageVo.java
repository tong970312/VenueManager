package com.graduation.sportsvenue.vo;

public class MessageVo {

    private Integer id;

    private String noticemsg;

    private String adminname;

    private String createtime;

    public MessageVo() {
    }

    @Override
    public String toString() {
        return "MessageVo{" +
                "id=" + id +
                ", noticemsg='" + noticemsg + '\'' +
                ", adminname='" + adminname + '\'' +
                ", createtime='" + createtime + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNoticemsg() {
        return noticemsg;
    }

    public void setNoticemsg(String noticemsg) {
        this.noticemsg = noticemsg;
    }

    public String getAdminname() {
        return adminname;
    }

    public void setAdminname(String adminname) {
        this.adminname = adminname;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }
}
