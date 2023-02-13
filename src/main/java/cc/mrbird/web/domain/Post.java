package cc.mrbird.web.domain;

import java.util.Date;

public class Post {
    //主键
    private Integer id;
   //分类id
    private Integer forum;
    //发帖人id
    private Integer userId;
    //标题
    private String title;
    //发布时间
    private Date sendDate;
    //帖子类型 0为普通帖 1为精华帖 2为待审核通过的精华帖 3为驳回
    private Integer postType;
    //评论回复数
    private Integer replyNum;
    //浏览数量
    private Integer viewNum;
    //标题路径
    private String photoUrl;
    //是否审核通过
    private Integer ifPass;
    //是否删除
    private Integer ifDelete;
    //点赞数
    private Integer greatNum;
    //内容
    private String cardContent;
    //分类名称
    private String typeTitle;
    //发帖人
    private String username;

    public String getTypeTitle() {
        return typeTitle;
    }

    public void setTypeTitle(String typeTitle) {
        this.typeTitle = typeTitle;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getForum() {
        return forum;
    }

    public void setForum(Integer forum) {
        this.forum = forum;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public Date getSendDate() {
        return sendDate;
    }

    public void setSendDate(Date sendDate) {
        this.sendDate = sendDate;
    }

    public Integer getPostType() {
        return postType;
    }

    public void setPostType(Integer postType) {
        this.postType = postType;
    }

    public Integer getReplyNum() {
        return replyNum;
    }

    public void setReplyNum(Integer replyNum) {
        this.replyNum = replyNum;
    }

    public Integer getViewNum() {
        return viewNum;
    }

    public void setViewNum(Integer viewNum) {
        this.viewNum = viewNum;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl == null ? null : photoUrl.trim();
    }

    public Integer getIfPass() {
        return ifPass;
    }

    public void setIfPass(Integer ifPass) {
        this.ifPass = ifPass;
    }

    public Integer getIfDelete() {
        return ifDelete;
    }

    public void setIfDelete(Integer ifDelete) {
        this.ifDelete = ifDelete;
    }

    public Integer getGreatNum() {
        return greatNum;
    }

    public void setGreatNum(Integer greatNum) {
        this.greatNum = greatNum;
    }

    public String getCardContent() {
        return cardContent;
    }

    public void setCardContent(String cardContent) {
        this.cardContent = cardContent == null ? null : cardContent.trim();
    }
}