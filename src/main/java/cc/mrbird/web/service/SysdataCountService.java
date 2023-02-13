package cc.mrbird.web.service;

import cc.mrbird.common.service.IService;
import cc.mrbird.web.dao.SysdataCountMapper;
import cc.mrbird.web.domain.SysdataCount;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

public interface SysdataCountService extends IService<SysdataCount> {
    /**
     * 获取每日数据
     * @return
     */
    public String dayCount();

}
