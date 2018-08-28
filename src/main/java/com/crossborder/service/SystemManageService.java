package com.crossborder.service;

import com.crossborder.dao.SystemManageDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by s on 2018/4/4.
 */
@Service
public class SystemManageService {
    @Resource
    private SystemManageDao systemManageDao;

    public int addCompany(Map<String, Object> map) {
        return systemManageDao.addCompany(map);
    }

    public List<Map<String, Object>> selectCompanies(Map<String, Object> map) {
        return systemManageDao.selectCompanies(map);
    }

    public int updateCompanyState(Map<String, Object> map) {
        return systemManageDao.updateCompanyState(map);
    }

    public int deleteCompany(String id) {
        return systemManageDao.deleteCompany(id);
    }

    public int updateCompany(Map<String, Object> map) {
        return systemManageDao.updateCompany(map);
    }

    public int queryParentMenuSeq() {
        return systemManageDao.queryParentMenuSeq();
    }

    public int queryChildMenuSeq() {
        return systemManageDao.queryChildMenuSeq();
    }

    public int addMenu(Map<String, Object> map) {
        return systemManageDao.addMenu(map);
    }

    public List<Map<String, Object>> selectMenus(Map<String, Object> map) {
        return systemManageDao.selectMenus(map);
    }

    public int updateMenuState(Map<String, Object> map) {
        return systemManageDao.updateMenuState(map);
    }

    public int deleteMenu(String id) {
        return systemManageDao.deleteMenu(id);
    }

    public int updateMenu(Map<String, Object> map) {
        return systemManageDao.updateMenu(map);
    }

    public int addUser(Map<String, Object> map) {
        return systemManageDao.addUser(map);
    }

    public List<Map<String, Object>> selectUsers(Map<String, Object> map) {
        return systemManageDao.selectUsers(map);
    }

    public int updateUserState(Map<String, Object> map) {
        return systemManageDao.updateUserState(map);
    }

    public int deleteUser(String id) {
        return systemManageDao.deleteUser(id);
    }

    public int updateUser(Map<String, Object> map) {
        return systemManageDao.updateUser(map);
    }

    public long queryRoleSeq() {
        return systemManageDao.queryRoleSeq();
    }

    public int addRole(Map<String, Object> map) {
        return systemManageDao.addRole(map);
    }

    public List<Map<String, Object>> selectRoles(Map<String, Object> map) {
        return systemManageDao.selectRoles(map);
    }

    public int updateRoleState(Map<String, Object> map) {
        return systemManageDao.updateRoleState(map);
    }

    public int deleteRole(String id) {
        return systemManageDao.deleteRole(id);
    }

    public int updateRole(Map<String, Object> map) {
        return systemManageDao.updateRole(map);
    }

    public int dealeteMenuRole(String roleId) {
        return systemManageDao.deleteMenuRole(roleId);
    }

    public int addMenuRole(Map<String, Object> map) {
        return systemManageDao.addMenuRole(map);
    }

    public List<Map<String, Object>> selectMenuRole(String roleId) {
        return systemManageDao.selectMenuRole(roleId);
    }

    public List<Map<String,Object>> selectShops(Map<String, Object> paramMap) {
        return systemManageDao.selectShops(paramMap);
    }
    public int addTranslation(Map<String, Object> map) {
        return systemManageDao.addTranslation(map);
    }

    public List<Map<String, Object>> selectTranslations(Map<String, Object> map) {
        return systemManageDao.selectTranslations(map);
    }

    public int updateTranslationState(Map<String, Object> map) {
        return systemManageDao.updateTranslationState(map);
    }

    public int deleteTranslation(String id) {
        return systemManageDao.deleteTranslation(id);
    }

    public int updateTranslation(Map<String, Object> map) {
        return systemManageDao.updateTranslation(map);
    }
}
