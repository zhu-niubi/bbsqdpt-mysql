package cc.mrbird.web.service.impl;

import cc.mrbird.common.service.impl.BaseService;
import cc.mrbird.web.dao.SysUserMapper;
import cc.mrbird.web.domain.SysUser;
import cc.mrbird.web.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
@Service("sysUserService")
public class SysUserServiceImpl extends BaseService<SysUser> implements SysUserService {
    @Autowired
    private SysUserMapper sysUserMapper;
    @Override
    public void insertList(List<SysUser> list) {
        sysUserMapper.insertList(list);
    }

    @Override
    public void deleteSysUserBySystemIds(String ids) {
        List<String> list = Arrays.asList(ids.split(","));
        this.batchDelete(list, "sysId", SysUser.class);
    }
}
