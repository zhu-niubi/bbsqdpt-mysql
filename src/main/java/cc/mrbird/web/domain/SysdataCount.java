package cc.mrbird.web.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 数据统计实体
 */

public class SysdataCount  implements Serializable {
    private static final long serialVersionUID = -31660349498268403L;
    private Integer id;
    //统计日期
    private Date countDate;
    //统计类型
    private String countType;
    //统计类型描述
    private String countDepict;
    //数量
    private Integer num;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getCountDate() {
        return countDate;
    }

    public void setCountDate(Date countDate) {
        this.countDate = countDate;
    }

    public String getCountType() {
        return countType;
    }

    public void setCountType(String countType) {
        this.countType = countType == null ? null : countType.trim();
    }

    public String getCountDepict() {
        return countDepict;
    }

    public void setCountDepict(String countDepict) {
        this.countDepict = countDepict == null ? null : countDepict.trim();
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }
}