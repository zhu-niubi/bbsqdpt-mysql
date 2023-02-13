package cc.mrbird.web.service;

import cc.mrbird.web.domain.SysUser;

import java.util.List;

public interface SysUserService {
    /**
     * 批量插入
     * @param list
     */
    public void insertList(List<SysUser> list);

    /**
     * 批量删除
     * @param ids
     */
    public void deleteSysUserBySystemIds(String ids);
}
