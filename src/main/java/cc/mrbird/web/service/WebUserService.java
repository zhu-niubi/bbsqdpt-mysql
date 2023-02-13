package cc.mrbird.web.service;

import cc.mrbird.common.domain.QueryRequest;
import cc.mrbird.common.service.IService;
import cc.mrbird.system.domain.User;
import cc.mrbird.web.domain.WebUser;
import org.springframework.cache.annotation.CacheConfig;

import java.util.List;

/**
 * Created by cui on 2018/10/25.
 */
@CacheConfig(cacheNames = "WebUserService")
public interface WebUserService extends IService<WebUser> {
    /**
     * 分页查询前端用户列表
     * @param user
     * @param request
     * @return
     */
    public List<WebUser>  findWebUserByPage( WebUser user,QueryRequest request);

    /**
     * 新增用户
     * @param user
     */
    public void addUser(WebUser user);

    /**
     * 修改用户
     * @param user
     */
    public void updateUser(WebUser user);

    /**
     * 根据主键查询信息
     * @param userId
     * @return
     */
    public WebUser findById(Integer userId);

    /**
     * 根据用户名查询信息
     * @param userName
     * @return
     */
    public WebUser findUserByName(String userName);
    /**
     * 根据邮箱查询信息
     * @return
     */
    public WebUser findUserByEmail(String email);

    /**
     * 查询主键集合
     * @return
     */
    public List<String> selectAllIds();

    /**
     * 统计用户数量
     * @param user
     * @return
     */
    public int getUserCount(WebUser user);
}
