package cc.mrbird.web.dao;

import cc.mrbird.common.config.MyMapper;
import cc.mrbird.web.domain.Advice;

import java.util.List;

public interface AdviceMapper extends MyMapper<Advice> {

    void deleteAdviceById(Integer adviceId);

    List<Advice> AdviceFindPage(Advice advice);
}