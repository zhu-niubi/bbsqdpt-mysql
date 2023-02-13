package cc.mrbird.web.domain;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
@Table(name = "system")
public class System {
    public  static Integer default_send=0;
    public  static Integer default_delete=0;

    @Id
    @GeneratedValue(generator = "JDBC")
    @Column(name = "ID")
    private Integer id;
    //内容
    @Column(name = "content")
    private String content;
    //是否删除
    @Column(name = "if_delete")
    private Integer ifDelete;
    //创建人编号
    @Column(name = "creater_id")
    private Integer createrId;
    //创建人名称
    @Column(name = "creater_name")
    private String createrName;
    //是否已发送
    @Column(name = "is_send")
    private Integer isSend;
    //创建时间
    @Column(name = "date")
    private Date date;
    //修改时间
    @Column(name = "update_time")
    private Date updateTime;

    public String getCreaterName() {
        return createrName;
    }

    public void setCreaterName(String createrName) {
        this.createrName = createrName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Integer getIfDelete() {
        return ifDelete;
    }

    public void setIfDelete(Integer ifDelete) {
        this.ifDelete = ifDelete;
    }

    public Integer getCreaterId() {
        return createrId;
    }

    public void setCreaterId(Integer createrId) {
        this.createrId = createrId;
    }

    public Integer getIsSend() {
        return isSend;
    }

    public void setIsSend(Integer isSend) {
        this.isSend = isSend ;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}