package cc.mrbird.web.domain;

import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Date;
@Table(name = "advice")
public class Advice {
    private Integer id;

    private Integer userId;

    private String content;


//    @DateTimeFormat(pattern="yyyy-MM-dd")
//    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
//    @Column(name = "adviceDate")
//    @ExportConfig(value = "创建时间", convert = "c:cc.mrbird.common.util.poi.convert.TimeConvert")
    private Date advicedate;

    private String title;

    private String userName;

    // 创建的时间
    @Transient
    private String createDate;

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Date getAdvicedate() {
        return advicedate;
    }

    public void setAdvicedate(Date advicedate) {
        this.advicedate = advicedate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "Advice{" +
                "id=" + id +
                ", userId=" + userId +
                ", content='" + content + '\'' +
                ", advicedate=" + advicedate +
                ", title='" + title + '\'' +
                ", userName='" + userName + '\'' +
                ", createDate='" + createDate + '\'' +
                '}';
    }
}