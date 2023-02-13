package cc.mrbird.web.service.impl;

import cc.mrbird.common.domain.QueryRequest;
import cc.mrbird.common.service.impl.BaseService;
import cc.mrbird.web.dao.AdvertMapper;
import cc.mrbird.web.domain.Advert;
import cc.mrbird.web.service.AdvertService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *客户端用户服务层
 * Created by cui on 2018/10/25.
 */

@Service("advertService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class AdvertServiceImpl extends BaseService<Advert>  implements AdvertService {
    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private AdvertMapper advertMapper;

    @Override
    public List<Advert> findAdvertByPage(Advert advert, QueryRequest request) {
        try {
            return this.advertMapper.findAdvertByPage(advert);
        } catch (Exception e) {
            log.error("error", e);
            return new ArrayList<>();
        }
    }
    /**
     * 根据id查询公告信息
     * @param id
     * @return
     */
    @Override
    public Advert findById(Integer id) {
        try {
            return this.advertMapper.findById(id);
        } catch (Exception e) {
            log.error("error", e);
            return null;
        }
    }
    /**
     * 新增公告信息
     * @param advert
     * @return
     */
    @Override
    public void addAdvert(Advert advert) {
        try {
             this.advertMapper.addAdvert(advert);
        } catch (Exception e) {
            log.error("error", e);
        }
    }
    /**
     * 删除公告信息
     * @param ids
     * @return
     */
    @Override
    public void deleteAdverts(String ids) {
        try {
            List<String> list = Arrays.asList(ids.split(","));
            for(String idd:list){
                int id = Integer.parseInt(idd);
                this.advertMapper.deleteAdvert(id);
            }
        } catch (Exception e) {
            log.error("error", e);
        }
    }
    /**
     * 修改公告信息
     * @param advert
     * @return
     */
    @Override
    public void updateAdvert(Advert advert) {
        try {
            this.advertMapper.updateAdvert(advert);
        } catch (Exception e) {
            log.error("error", e);
        }
    }
}
