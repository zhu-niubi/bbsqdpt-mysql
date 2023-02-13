package cc.mrbird.web.service;

import cc.mrbird.common.domain.QueryRequest;
import cc.mrbird.common.service.IService;
import cc.mrbird.web.domain.Advice;
import org.springframework.cache.annotation.CacheConfig;

import java.util.List;

/**
 * @Auther: jerry.feng
 * @Date: 2018/10/31
 * @Description: 投诉建议管理
 */
@CacheConfig(cacheNames = "WebUserService")
public interface AdviceService extends IService<Advice> {

    /**
     * @param advice
     * @return 查询结果
     */
    List<Advice> AdviceFindPage(Advice advice, QueryRequest request);

    /**
     *
     * @param id
     * @return 返回受影响的行
     */
     void deleteAdviceById(Integer id);
}
