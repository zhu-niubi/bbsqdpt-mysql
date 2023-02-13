package cc.mrbird.web.service.impl;

import cc.mrbird.common.domain.QueryRequest;
import cc.mrbird.common.service.impl.BaseService;
import cc.mrbird.system.domain.User;
import cc.mrbird.web.dao.SysUserMapper;
import cc.mrbird.web.dao.SystemMapper;
import cc.mrbird.web.domain.SysUser;
import cc.mrbird.web.service.SysUserService;
import cc.mrbird.web.service.SystemService;
import cc.mrbird.web.service.WebUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cc.mrbird.web.domain.System;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.util.StringUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service("systemService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class SystemServiceImpl extends BaseService<System> implements SystemService {
    @Autowired
    private SystemMapper systemMapper;
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private WebUserService webUserService;
    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Override
    public List<System> findSystemByPage(System system, QueryRequest request) {
        try{
            List<System> s=systemMapper.findSystemByPage(system);
            return s;
       } catch (Exception e) {
        log.info("error", e);
        return new ArrayList<>();
    }

    }

    @Override
    public System findById(Integer id) {
        return systemMapper.selectByPrimaryKey(id);
    }

    @Override
    @Transactional
    public void sendSystem(String systemId, String userIds) {
        List<String> ids;
        if(StringUtil.isNotEmpty(userIds)) {
            ids= Arrays.asList(userIds.split(","));
        }
        else{//为空则发送给全部用户
            ids=webUserService.selectAllIds();
        }
        List<SysUser> list=new ArrayList<SysUser>();
        for(String id:ids){
            SysUser sysUser=new SysUser();
            sysUser.setSysId(Integer.parseInt(systemId));
            sysUser.setUserId(Integer.parseInt(id));
            sysUser.setIfRead(0);
            sysUser.setIfDelete(0);
            list.add(sysUser);
        }
        sysUserService.insertList(list);
        systemMapper.updateStatusById(Integer.parseInt(systemId));
    }

    @Override
    public void addSystem(System system) {
        system.setDate(new Date());
        system.setUpdateTime(new Date());
        system.setIfDelete(System.default_delete);
        system.setIsSend(System.default_send);
        this.save(system);
    }

    @Override
    @Transactional
    public void deleteSystems(String ids) {
        List<String> list = Arrays.asList(ids.split(","));
        this.batchDelete(list, "id", System.class);
        sysUserService.deleteSysUserBySystemIds(ids);
    }

}
