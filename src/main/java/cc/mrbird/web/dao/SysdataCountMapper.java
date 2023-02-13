package cc.mrbird.web.dao;

import cc.mrbird.common.config.MyMapper;
import cc.mrbird.web.domain.SysdataCount;

import java.util.List;

public interface SysdataCountMapper  extends MyMapper<SysdataCount> {

   public List<Integer> getDayCountByType(String countType);

}