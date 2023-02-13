package cc.mrbird.web.dao;

import cc.mrbird.common.config.MyMapper;
import cc.mrbird.system.domain.User;
import cc.mrbird.web.domain.WebUser;

import java.util.List;

public interface WebUserMapper extends MyMapper<WebUser> {

    public List<WebUser> findWebUserByPage(WebUser user);

    public List<String> selectAllIds();

}