package cc.mrbird.web.service.impl;

import cc.mrbird.common.domain.QueryRequest;
import cc.mrbird.common.service.impl.BaseService;
import cc.mrbird.web.dao.AdviceMapper;
import cc.mrbird.web.domain.Advice;
import cc.mrbird.web.service.AdviceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: jerry.feng
 * @Date: 2018/10/31
 * @Description: 投诉建议管理
 */

@Service("AdviceService")
public class AdviceServiceImpl extends BaseService<Advice> implements AdviceService {
    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private AdviceMapper adviceMapper;


    @Override
    public List<Advice> AdviceFindPage(Advice advice, QueryRequest request) {
        try {
            return adviceMapper.AdviceFindPage(advice);
        } catch (Exception e) {
            log.error("error", e);
            return new ArrayList<>();
        }
    }


    @Override
    @Transactional
    public void deleteAdviceById(Integer id) {
        adviceMapper.deleteAdviceById(id);
    }
}
