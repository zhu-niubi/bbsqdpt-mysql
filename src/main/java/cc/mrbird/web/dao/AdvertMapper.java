package cc.mrbird.web.dao;

import cc.mrbird.common.config.MyMapper;
import cc.mrbird.web.domain.Advert;

import java.util.List;

public interface AdvertMapper extends MyMapper<Advert> {
    /**
     * 分页查询前端广告列表
     *
     * @param advert
     * @return
     */
    public List<Advert> findAdvertByPage(Advert advert);

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
     * @param id
     * @return
     */
    public void deleteAdvert(Integer id);

    /**
     * 修改广告信息
     * @param advert
     * @return
     */
    public void updateAdvert(Advert advert);
}