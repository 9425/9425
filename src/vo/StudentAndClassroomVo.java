package vo;

public class StudentAndClassroomVo {
    //学生相关信息
    private String sid;
    private String sname;
    private int asge;
    private String saddress;

    //班级相关信息
    private String cid;
    private String cname;

    public StudentAndClassroomVo(String sid, String sname, int asge, String saddress, String cid, String cname) {
        this.sid = sid;
        this.sname = sname;
        this.asge = asge;
        this.saddress = saddress;
        this.cid = cid;
        this.cname = cname;
    }

    public StudentAndClassroomVo() {
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public int getAsge() {
        return asge;
    }

    public void setAsge(int asge) {
        this.asge = asge;
    }

    public String getSaddress() {
        return saddress;
    }

    public void setSaddress(String saddress) {
        this.saddress = saddress;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    @Override
    public String toString() {
        return "StudentAndClassroomVo{" +
                "sid='" + sid + '\'' +
                ", sname='" + sname + '\'' +
                ", asge=" + asge +
                ", saddress='" + saddress + '\'' +
                ", cid='" + cid + '\'' +
                ", cname='" + cname + '\'' +
                '}';
    }
}
