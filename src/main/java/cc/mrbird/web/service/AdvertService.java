package cc.mrbird.web.service;

import cc.mrbird.common.domain.QueryRequest;
import cc.mrbird.common.service.IService;
import cc.mrbird.web.domain.Advert;

import java.util.List;

public interface AdvertService extends IService<Advert> {
    /**
     * 分页查询前端广告列表
     *
     * @param advert
     * @param request
     * @return
     */
    public List<Advert> findAdvertByPage(Advert advert, QueryRequest request);

    /**
     * 根据id查询广告信息
     *
     * @param id
     * @return
     */
    public Advert findById(Integer id);
    /**
     * 新增广告信息
     * @param advert
     * @return
     */
    public void addAdvert(Advert advert);

    /**
     * 删除广告信息
     * @param ids
     * @return
     */
    public void deleteAdverts(String ids);

    /**
     * 修改广告信息
     * @param advert
     * @return
     */
    public void updateAdvert(Advert advert);

}
