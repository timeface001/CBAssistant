package com.crossborder.action;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.crossborder.service.SystemManageService;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by s on 2018/4/4.
 */
@Controller
@RequestMapping("/system")
public class SystemManageController {
    @Resource
    private SystemManageService systemManageService;

    /**
     * 添加公司
     *
     * @param data
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "addCompany", produces = "text/plain;charset=UTF-8")
    public String addCompany(String data) {
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> paramMap = JSON.parseObject(data, Map.class);
        try {
            int count = systemManageService.addCompany(paramMap);
            map.put("count", count);
            map.put("code", "0");
            map.put("msg", "添加成功");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("code", "-10");
            map.put("msg", "添加失败");
        }
        return JSON.toJSONString(map, SerializerFeature.WriteMapNullValue);
    }

    /**
     * 查询公司
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "selectCompanies", produces = "text/plain;charset=UTF-8")
    public String selectCompanies(String id) {
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("id", id);
        try {
            List<Map<String, Object>> list = systemManageService.selectCompanies(paramMap);
            map.put("data", list);
            map.put("code", "0");
            map.put("msg", "查询成功");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("code", "-10");
            map.put("msg", "查询失败");
        }
        return JSON.toJSONString(map, SerializerFeature.WriteMapNullValue);
    }

    /**
     * 查询授权店铺
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "selectShops", produces = "text/plain;charset=UTF-8")
    public String selectShops(String id) {
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("id", id);
        try {
            List<Map<String, Object>> list = systemManageService.selectShops(paramMap);
            map.put("data", list);
            map.put("code", "0");
            map.put("msg", "查询成功");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("code", "-10");
            map.put("msg", "查询失败");
        }
        return JSON.toJSONString(map, SerializerFeature.WriteMapNullValue);
    }

    /**
     * 修改公司信息
     *
     * @param data
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "updateCompany", produces = "text/plain;charset=UTF-8")
    public String updateCompany(String data) {
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> paramMap = JSON.parseObject(data, Map.class);
        try {
            int count = systemManageService.updateCompany(paramMap);
            map.put("count", count);
            map.put("code", "0");
            map.put("msg", "更新成功");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("code", "-10");
            map.put("msg", "更新失败");
        }
        return JSON.toJSONString(map, SerializerFeature.WriteMapNullValue);
    }

    /**
     * 启用或停用
     *
     * @param id
     * @param state
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "updateCompanyState", produces = "text/plain;charset=UTF-8")
    public String updateCompanyState(String id, String state) {
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("id", id);
        paramMap.put("state", state);
        try {
            int count = systemManageService.updateCompanyState(paramMap);
            map.put("count", count);
            map.put("code", "0");
            map.put("msg", "更新成功");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("code", "-10");
            map.put("msg", "更新失败");
        }
        return JSON.toJSONString(map, SerializerFeature.WriteMapNullValue);
    }

    /**
     * 删除公司
     *
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "deleteCompany", produces = "text/plain;charset=UTF-8")
    public String deleteCompany(String id) {
        Map<String, Object> map = new HashMap<>();
        try {
            int count = systemManageService.deleteCompany(id);
            map.put("count", count);
            map.put("code", "0");
            map.put("msg", "删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("code", "-10");
            map.put("msg", "删除失败");
        }
        return JSON.toJSONString(map, SerializerFeature.WriteMapNullValue);
    }

    /**
     * 添加菜单
     *
     * @param data
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "addMenu", produces = "text/plain;charset=UTF-8")
    public String addMenu(String data) {
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> paramMap = JSON.parseObject(data, Map.class);
        try {
            if (paramMap.get("menuPid").toString().equals("0")) {
                paramMap.put("menuId", systemManageService.queryParentMenuSeq());
                paramMap.put("leaf", "0");
                paramMap.put("level", "1");
            } else {
                int pid = Integer.parseInt(paramMap.get("menuPid").toString());
                paramMap.put("menuId", pid + systemManageService.queryChildMenuSeq());
                paramMap.put("leaf", "1");
                paramMap.put("level", "2");
            }
            int count = systemManageService.addMenu(paramMap);
            map.put("count", count);
            map.put("code", "0");
            map.put("msg", "添加成功");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("code", "-10");
            map.put("msg", "添加失败");
        }
        return JSON.toJSONString(map, SerializerFeature.WriteMapNullValue);
    }

    /**
     * 查询菜单
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "selectMenus", produces = "text/plain;charset=UTF-8")
    public String selectMenus(String id) {
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("id", id);
        try {
            List<Map<String, Object>> list = systemManageService.selectMenus(paramMap);
            map.put("data", list);
            map.put("code", "0");
            map.put("msg", "查询成功");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("code", "-10");
            map.put("msg", "查询失败");
        }
        return JSON.toJSONString(map, SerializerFeature.WriteMapNullValue);
    }

    /**
     * 修改菜单信息
     *
     * @param data
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "updateMenu", produces = "text/plain;charset=UTF-8")
    public String updateMenu(String data) {
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> paramMap = JSON.parseObject(data, Map.class);
        try {
            int count = systemManageService.updateMenu(paramMap);
            map.put("count", count);
            map.put("code", "0");
            map.put("msg", "更新成功");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("code", "-10");
            map.put("msg", "更新失败");
        }
        return JSON.toJSONString(map, SerializerFeature.WriteMapNullValue);
    }

    /**
     * 启用或停用
     *
     * @param id
     * @param state
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "updateMenuState", produces = "text/plain;charset=UTF-8")
    public String updateMenuState(String id, String state) {
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("id", id);
        paramMap.put("state", state);
        try {
            int count = systemManageService.updateMenuState(paramMap);
            map.put("count", count);
            map.put("code", "0");
            map.put("msg", "更新成功");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("code", "-10");
            map.put("msg", "更新失败");
        }
        return JSON.toJSONString(map, SerializerFeature.WriteMapNullValue);
    }

    /**
     * 删除菜单
     *
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "deleteMenu", produces = "text/plain;charset=UTF-8")
    public String deleteMenu(String id) {
        Map<String, Object> map = new HashMap<>();
        try {
            int count = systemManageService.deleteMenu(id);
            map.put("count", count);
            map.put("code", "0");
            map.put("msg", "删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("code", "-10");
            map.put("msg", "删除失败");
        }
        return JSON.toJSONString(map, SerializerFeature.WriteMapNullValue);
    }

    /**
     * 添加用户
     *
     * @param data
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "addUser", produces = "text/plain;charset=UTF-8")
    public String addUser(String data) {
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> paramMap = JSON.parseObject(data, Map.class);
        try {
            int count = systemManageService.addUser(paramMap);
            map.put("count", count);
            map.put("code", "0");
            map.put("msg", "添加成功");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("code", "-10");
            map.put("msg", "添加失败");
        }
        return JSON.toJSONString(map, SerializerFeature.WriteMapNullValue);
    }

    /**
     * 查询用户
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "selectUsers", produces = "text/plain;charset=UTF-8")
    public String selectUsers(String id, String companyId) {
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("id", id);
        paramMap.put("companyId", companyId);
        try {
            List<Map<String, Object>> list = systemManageService.selectUsers(paramMap);
            map.put("data", list);
            map.put("code", "0");
            map.put("msg", "查询成功");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("code", "-10");
            map.put("msg", "查询失败");
        }
        return JSON.toJSONString(map, SerializerFeature.WriteMapNullValue);
    }

    /**
     * 修改用户信息
     *
     * @param data
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "updateUser", produces = "text/plain;charset=UTF-8")
    public String updateUser(String data) {
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> paramMap = JSON.parseObject(data, Map.class);
        try {
            int count = systemManageService.updateUser(paramMap);
            map.put("count", count);
            map.put("code", "0");
            map.put("msg", "更新成功");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("code", "-10");
            map.put("msg", "更新失败");
        }
        return JSON.toJSONString(map, SerializerFeature.WriteMapNullValue);
    }

    /**
     * 启用或停用
     *
     * @param id
     * @param state
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "updateUserState", produces = "text/plain;charset=UTF-8")
    public String updateUserState(String id, String state) {
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("id", id);
        paramMap.put("state", state);
        try {
            int count = systemManageService.updateUserState(paramMap);
            map.put("count", count);
            map.put("code", "0");
            map.put("msg", "更新成功");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("code", "-10");
            map.put("msg", "更新失败");
        }
        return JSON.toJSONString(map, SerializerFeature.WriteMapNullValue);
    }

    /**
     * 删除用户
     *
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "deleteUser", produces = "text/plain;charset=UTF-8")
    public String deleteUser(String id) {
        Map<String, Object> map = new HashMap<>();
        try {
            int count = systemManageService.deleteUser(id);
            map.put("count", count);
            map.put("code", "0");
            map.put("msg", "删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("code", "-10");
            map.put("msg", "删除失败");
        }
        return JSON.toJSONString(map, SerializerFeature.WriteMapNullValue);
    }

    /**
     * 添加角色
     *
     * @param data
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "addRole", produces = "text/plain;charset=UTF-8")
    public String addRole(String data) {
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> paramMap = JSON.parseObject(data, Map.class);
        try {
            long roleId = systemManageService.queryRoleSeq();
            paramMap.put("roleId", roleId);
            int count = systemManageService.addRole(paramMap);
            List<String> parentMenus = (List<String>) paramMap.get("parentMenu");
            List<String> childMenus = (List<String>) paramMap.get("childMenu");
            Map<String, Object> menuRoleMap = new HashMap<>();
            menuRoleMap.put("roleId", roleId);
            for (int i = 0; i < parentMenus.size(); i++) {
                menuRoleMap.put("menuId", parentMenus.get(i));
                systemManageService.addMenuRole(menuRoleMap);
            }
            for (int i = 0; i < childMenus.size(); i++) {
                menuRoleMap.put("menuId", childMenus.get(i));
                systemManageService.addMenuRole(menuRoleMap);
            }
            map.put("count", count);
            map.put("code", "0");
            map.put("msg", "添加成功");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("code", "-10");
            map.put("msg", "添加失败");
        }
        return JSON.toJSONString(map, SerializerFeature.WriteMapNullValue);
    }

    /**
     * 查询角色列表
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "selectRoles", produces = "text/plain;charset=UTF-8")
    public String selectRoles(String id) {
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("id", id);
        try {
            List<Map<String, Object>> list = systemManageService.selectRoles(paramMap);
            if (!StringUtils.isEmpty(id)) {
                List<Map<String, Object>> menus = systemManageService.selectMenuRole(id);
                map.put("menus", menus);
            }
            map.put("data", list);
            map.put("code", "0");
            map.put("msg", "查询成功");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("code", "-10");
            map.put("msg", "查询失败");
        }
        return JSON.toJSONString(map, SerializerFeature.WriteMapNullValue);
    }

    /**
     * 修改角色信息
     *
     * @param data
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "updateRole", produces = "text/plain;charset=UTF-8")
    public String updateRole(String data) {
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> paramMap = JSON.parseObject(data, Map.class);
        try {
            int count = systemManageService.updateRole(paramMap);
            systemManageService.dealeteMenuRole(paramMap.get("id").toString());
            List<String> parentMenus = (List<String>) paramMap.get("parentMenu");
            List<String> childMenus = (List<String>) paramMap.get("childMenu");
            Map<String, Object> menuRoleMap = new HashMap<>();
            menuRoleMap.put("roleId", paramMap.get("id").toString());
            for (int i = 0; i < parentMenus.size(); i++) {
                menuRoleMap.put("menuId", parentMenus.get(i));
                systemManageService.addMenuRole(menuRoleMap);
            }
            for (int i = 0; i < childMenus.size(); i++) {
                menuRoleMap.put("menuId", childMenus.get(i));
                systemManageService.addMenuRole(menuRoleMap);
            }
            map.put("count", count);
            map.put("code", "0");
            map.put("msg", "更新成功");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("code", "-10");
            map.put("msg", "更新失败");
        }
        return JSON.toJSONString(map, SerializerFeature.WriteMapNullValue);
    }

    /**
     * 启用或停用
     *
     * @param id
     * @param state
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "updateRoleState", produces = "text/plain;charset=UTF-8")
    public String updateRoleState(String id, String state) {
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("id", id);
        paramMap.put("state", state);
        try {
            int count = systemManageService.updateRoleState(paramMap);
            map.put("count", count);
            map.put("code", "0");
            map.put("msg", "更新成功");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("code", "-10");
            map.put("msg", "更新失败");
        }
        return JSON.toJSONString(map, SerializerFeature.WriteMapNullValue);
    }

    /**
     * 删除角色
     *
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "deleteRole", produces = "text/plain;charset=UTF-8")
    public String deleteRole(String id) {
        Map<String, Object> map = new HashMap<>();
        try {
            int count = systemManageService.deleteRole(id);
            map.put("count", count);
            map.put("code", "0");
            map.put("msg", "删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("code", "-10");
            map.put("msg", "删除失败");
        }
        return JSON.toJSONString(map, SerializerFeature.WriteMapNullValue);
    }

    /**
     * 添加翻译
     *
     * @param data
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "addTranslation", produces = "text/plain;charset=UTF-8")
    public String addTranslation(String data, HttpSession session) {
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> user = (Map<String, Object>) session.getAttribute("user");
        try {
            Map<String, Object> paramMap = JSON.parseObject(data, Map.class);
            paramMap.put("createUser", user.get("USER_ID").toString());
            int count = systemManageService.addTranslation(paramMap);
            map.put("count", count);
            map.put("code", "0");
            map.put("msg", "添加成功");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("code", "-10");
            map.put("msg", "添加失败");
        }
        return JSON.toJSONString(map, SerializerFeature.WriteMapNullValue);
    }

    /**
     * 查询翻译
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "selectTranslations", produces = "text/plain;charset=UTF-8")
    public String selectTranslations(String id) {
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("id", id);
        try {
            List<Map<String, Object>> list = systemManageService.selectTranslations(paramMap);
            map.put("data", list);
            map.put("code", "0");
            map.put("msg", "查询成功");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("code", "-10");
            map.put("msg", "查询失败");
        }
        return JSON.toJSONString(map, SerializerFeature.WriteMapNullValue);
    }

    /**
     * 修改翻译信息
     *
     * @param data
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "updateTranslation", produces = "text/plain;charset=UTF-8")
    public String updateTranslation(String data) {
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> paramMap = JSON.parseObject(data, Map.class);
        try {
            int count = systemManageService.updateTranslation(paramMap);
            map.put("count", count);
            map.put("code", "0");
            map.put("msg", "更新成功");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("code", "-10");
            map.put("msg", "更新失败");
        }
        return JSON.toJSONString(map, SerializerFeature.WriteMapNullValue);
    }

    /**
     * 启用或停用
     *
     * @param id
     * @param state
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "updateTranslationState", produces = "text/plain;charset=UTF-8")
    public String updateTranslationState(String id, String state) {
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("id", id);
        paramMap.put("state", state);
        try {
            int count = systemManageService.updateTranslationState(paramMap);
            map.put("count", count);
            map.put("code", "0");
            map.put("msg", "更新成功");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("code", "-10");
            map.put("msg", "更新失败");
        }
        return JSON.toJSONString(map, SerializerFeature.WriteMapNullValue);
    }

    /**
     * 删除翻译
     *
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "deleteTranslation", produces = "text/plain;charset=UTF-8")
    public String deleteTranslation(String id) {
        Map<String, Object> map = new HashMap<>();
        try {
            int count = systemManageService.deleteTranslation(id);
            map.put("count", count);
            map.put("code", "0");
            map.put("msg", "删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("code", "-10");
            map.put("msg", "删除失败");
        }
        return JSON.toJSONString(map, SerializerFeature.WriteMapNullValue);
    }
}
