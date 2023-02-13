package cc.mrbird.web.domain;

/**
 * 每日数据统计类型枚举
 */
public enum CountTypeEnum {
    USERADD_NUM("useradd","每日新增用户"),
    POSTADD_NUM("postadd","每日发帖数量"),
    LOGINUSER_NUM("loginuser","每日登录人数");
    private String code;
    private String description;
    private CountTypeEnum(String code,String description){
        this.code=code;
        this.description=description;
    }
    public static CountTypeEnum find(String code){
        for(CountTypeEnum e:CountTypeEnum.values()){
            if(e.getCode().equals(code)){
                return e;
            }
        }
        return null;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
