package cc.mrbird.web.service.impl;

import cc.mrbird.common.domain.QueryRequest;
import cc.mrbird.common.service.impl.BaseService;
import cc.mrbird.system.domain.Dept;
import cc.mrbird.web.dao.WebUserMapper;
import cc.mrbird.web.domain.WebUser;
import cc.mrbird.web.service.WebUserService;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *客户端用户服务层
 * Created by cui on 2018/10/25.
 */

@Service("webUserService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class WebUserServiceImpl extends BaseService<WebUser>  implements WebUserService {
    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private WebUserMapper webUserMapper;

    @Override
    public List<WebUser> findWebUserByPage(WebUser user, QueryRequest request) {
        try {
            return this.webUserMapper.findWebUserByPage(user);
        } catch (Exception e) {
            log.error("error", e);
            return new ArrayList<>();
        }
    }

    @Override
    public void addUser(WebUser user) {
        user.setLevel(WebUser.DEFAULT_LEVEL);
        user.setHasActive(WebUser.DEFAULT_HSACTIVE);
        user.setType(WebUser.DEFAULT_TYP);
        user.setRegisterDate(new Date());
        this.save(user);
    }

    @Override
    public void updateUser(WebUser user) {
        this.updateNotNull(user);
    }

    @Override
    public WebUser findById(Integer userId) {
        Example example = new Example(WebUser.class);
        example.createCriteria().andCondition("id =", userId);
        List<WebUser> list = this.selectByExample(example);
        return list.isEmpty() ? null : list.get(0);
       // return this.selectByKey(userId);
    }

    @Override
    public WebUser findUserByName(String userName) {
        Example example = new Example(WebUser.class);
        example.createCriteria().andCondition("username =", userName);
        List<WebUser> list = this.selectByExample(example);
        return list.isEmpty() ? null : list.get(0);
    }

    @Override
    public WebUser findUserByEmail(String email) {
        Example example = new Example(WebUser.class);
        example.createCriteria().andCondition("email =", email);

        List<WebUser> list = this.selectByExample(example);
        webUserMapper.selectCount(null);
        return list.isEmpty() ? null : list.get(0);
    }

    @Override
    public List<String> selectAllIds() {
        return webUserMapper.selectAllIds();
    }

    @Override
    public int getUserCount(WebUser user) {
        return webUserMapper.selectCount(user);
    }
}
