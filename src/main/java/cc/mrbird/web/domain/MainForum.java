package cc.mrbird.web.domain;

import java.util.Date;

public class MainForum {
    //分类id
    private Integer id;
    //分类名
    private String title;
    //分类描述
    private String info;
    //添加时间
    private Date addDate;
    //修改时间
    private Date editDate;
    //序号
    private Integer order;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info == null ? null : info.trim();
    }

    public Date getAddDate() {
        return addDate;
    }

    public void setAddDate(Date addDate) {
        this.addDate = addDate;
    }

    public Date getEditDate() {
        return editDate;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public void setEditDate(Date editDate) {
        this.editDate = editDate;
    }
}