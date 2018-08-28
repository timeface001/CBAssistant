package com.crossborder.dao;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by s on 2018/4/4.
 */
@Repository
public interface SystemManageDao {
    public int addCompany(Map<String, Object> map);

    public List<Map<String, Object>> selectCompanies(Map<String, Object> map);

    public int updateCompanyState(Map<String, Object> map);

    public int deleteCompany(String id);

    public int updateCompany(Map<String, Object> map);

    public int queryParentMenuSeq();

    public int queryChildMenuSeq();

    public int addMenu(Map<String, Object> map);

    public List<Map<String, Object>> selectMenus(Map<String, Object> map);

    public int updateMenuState(Map<String, Object> map);

    public int deleteMenu(String id);

    public int updateMenu(Map<String, Object> map);

    public int addUser(Map<String, Object> map);

    public List<Map<String, Object>> selectUsers(Map<String, Object> map);

    public int updateUserState(Map<String, Object> map);

    public int deleteUser(String id);

    public int updateUser(Map<String, Object> map);

    public long queryRoleSeq();

    public int addRole(Map<String, Object> map);

    public List<Map<String, Object>> selectRoles(Map<String, Object> map);

    public int updateRoleState(Map<String, Object> map);

    public int deleteRole(String id);

    public int updateRole(Map<String, Object> map);

    public int deleteMenuRole(String roleId);

    public int addMenuRole(Map<String, Object> map);

    public List<Map<String, Object>> selectMenuRole(String roleId);

    public List<Map<String, Object>> selectShops(Map<String, Object> paramMap);

    public int addTranslation(Map<String, Object> map);

    public List<Map<String, Object>> selectTranslations(Map<String, Object> map);

    public int updateTranslationState(Map<String, Object> map);

    public int deleteTranslation(String id);

    public int updateTranslation(Map<String, Object> map);

}
