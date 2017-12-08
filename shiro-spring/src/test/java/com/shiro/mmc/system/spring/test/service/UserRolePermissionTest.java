package com.shiro.mmc.system.spring.test.service;

import com.shiro.mmc.system.spring.entity.PermissionEntity;
import com.shiro.mmc.system.spring.entity.RoleEntity;
import com.shiro.mmc.system.spring.entity.UserEntity;
import com.shiro.mmc.system.spring.service.PermissionService;
import com.shiro.mmc.system.spring.service.RoleService;
import com.shiro.mmc.system.spring.service.UserService;
import com.shiro.mmc.system.spring.test.BaseTest;
import junit.framework.Assert;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @packageName：com.shiro.mmc.system.spring.test.service
 * @desrciption: 导入初始化用户角色权限
 * @author: gaowei
 * @date： 2017-12-08 13:48
 * @history: (version) author date desc
 */
public class UserRolePermissionTest extends BaseTest {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private PermissionService permissionService;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // 用户权限
    private PermissionEntity p1;
    private PermissionEntity p2;
    private PermissionEntity p3;
    private PermissionEntity p4;
    private PermissionEntity p5;

    // 用户角色
    private RoleEntity role1;
    private RoleEntity role2;
    private RoleEntity role3;

    // 用户
    private UserEntity user1;
    private UserEntity user2;
    private UserEntity user3;
    private UserEntity user4;


    @Before
    public void setUp() {
        // 清除原保存用户角色权限信息
        jdbcTemplate.update("delete from sys_users");
        jdbcTemplate.update("delete from sys_roles");
        jdbcTemplate.update("delete from sys_permissions");
        jdbcTemplate.update("delete from sys_users_roles");
        jdbcTemplate.update("delete from sys_roles_permissions");

        /**
         * 初始化模拟用户角色权限信息
         */
        // 权限
        p1 = new PermissionEntity();
        p1.setPermission("user:create");
        p1.setDescription("用户新增模块");
        p1.setIsEffect("0");

        p2 = new PermissionEntity();
        p2.setPermission("user:updaet");
        p2.setDescription("用户更新模块");
        p2.setIsEffect("0");

        p3 = new PermissionEntity();
        p3.setPermission("user:delete");
        p3.setDescription("用户删除模块");
        p3.setIsEffect("0");

        p4 = new PermissionEntity();
        p4.setPermission("user:query");
        p4.setDescription("用户查询模块");
        p4.setIsEffect("0");

        p5 = new PermissionEntity();
        p5.setPermission("menu:create");
        p5.setDescription("菜单新增模块");
        p5.setIsEffect("0");

        // 用户角色
        role1 = new RoleEntity();
        role1.setRole("admin");
        role1.setDescription("管理员角色");
        role1.setIsEffect("0");

        role2 = new RoleEntity();
        role2.setRole("user");
        role2.setDescription("普通用户角色");
        role2.setIsEffect("0");

        role3 = new RoleEntity();
        role3.setRole("guest");
        role3.setDescription("游客用户");
        role3.setIsEffect("0");

        // 用户
        user1 = new UserEntity();
        user1.setUsername("admin");
        user1.setPassword("123456");

        user2 = new UserEntity();
        user2.setUsername("guest");
        user2.setPassword("123456");

        user3 = new UserEntity();
        user3.setUsername("test1");
        user3.setPassword("123456");

    }

    @Test
    public void initUserRolePermission() {
        // 新增用户权限
        p1 = permissionService.savePermission(p1);
        p2 = permissionService.savePermission(p2);
        p3 = permissionService.savePermission(p3);
        p4 = permissionService.savePermission(p4);
        p5 = permissionService.savePermission(p5);

        // 新增用户角色
        role1 = roleService.saveRole(role1);
        role2 = roleService.saveRole(role2);
        role3 = roleService.saveRole(role3);

        // 新增用户信息
        user1 = userService.saveUser(user1);
        user2 = userService.saveUser(user2);
        user3 = userService.saveUser(user3);

        // 建立角色与权限之间的关系
        Set<Long> rolePermissions = new HashSet<>();
        rolePermissions.add(p1.getId());
        rolePermissions.add(p2.getId());
        rolePermissions.add(p3.getId());
        rolePermissions.add(p4.getId());
        rolePermissions.add(p5.getId());
        roleService.relationRoleToPermission(role1.getId(), rolePermissions);

        rolePermissions = new HashSet<>();
        rolePermissions.add(p1.getId());
        rolePermissions.add(p4.getId());
        rolePermissions.add(p5.getId());
        roleService.relationRoleToPermission(role2.getId(), rolePermissions);

        rolePermissions = new HashSet<>();
        rolePermissions.add(p4.getId());
        roleService.relationRoleToPermission(role3.getId(), rolePermissions);

        // 建立用户与角色之间的关系
        Set<Long> userRoles = new HashSet<>();
        userRoles.add(role1.getId());
        userService.relationUserToRole(user1.getId(), userRoles);

        userRoles = new HashSet<>();
        userRoles.add(role3.getId());
        userService.relationUserToRole(user2.getId(), userRoles);

        userRoles = new HashSet<>();
        userRoles.add(role3.getId());
        userRoles.add(role2.getId());
        userService.relationUserToRole(user3.getId(), userRoles);
    }

    @Test
    public void mockLogin() {
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken upToken = new UsernamePasswordToken(user1.getUsername(), user1.getPassword());
        try {
            subject.login(upToken);
        } catch (Exception e) {
            log.error("登录失败", e);
        }

        Assert.assertTrue(subject.isAuthenticated());
        subject.checkRole("admin");
        subject.checkPermission("user:create");

    }
}
