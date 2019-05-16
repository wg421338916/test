package com.wanggang.shiro.impl;

import com.wanggang.shiro.domain.SysUser;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

/**
 * @program: test
 * @description:
 * @author: Mr.WG
 * @create: 2019-05-13 14:36
 **/
public class MyUserRealm extends AuthorizingRealm {
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        Object primaryPrincipal = principalCollection.getPrimaryPrincipal();

        System.out.println(primaryPrincipal.toString());

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

        String nickName = ((SysUser) primaryPrincipal).getNickName();

        // 管理员拥有所有权限
        if (nickName.equalsIgnoreCase("admin")) {
            info.addRole("admin");
            info.addStringPermission("*:*:*");
        } else {
            info.addRole("guest");
            info.addStringPermission("test:test:test");
        }
        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        SysUser user = new SysUser();

        UsernamePasswordToken upToken = (UsernamePasswordToken) token;

        user.setNickName(upToken.getUsername());

        //交给 AuthenticatingRealm 使用 CredentialsMatcher 进行密码匹配，如果觉得人家
        //的不好可以在此判断或自定义实现
//        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
//                user.getUsername(), //用户名
//                user.getPassword(), //密码
//                ByteSource.Util.bytes(user.getCredentialsSalt()),//salt=username+salt
//                getName() //realm name
//        );


//        user.setSalt(randomNumberGenerator.nextBytes().toHex());
//        String newPassword = new SimpleHash(
//                algorithmName,
//                user.getPassword(),
//                ByteSource.Util.bytes(user.getCredentialsSalt()),
//                hashIterations).toHex();

        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, upToken.getPassword(), user.getNickName());

        return info;
    }

    /**
     * 设置认证加密方式
     */
//    @Override
//    public void setCredentialsMatcher(CredentialsMatcher credentialsMatcher) {
//        HashedCredentialsMatcher md5CredentialsMatcher = new HashedCredentialsMatcher();
//        md5CredentialsMatcher.setHashAlgorithmName("md5");
//        md5CredentialsMatcher.setHashIterations(32);
//        super.setCredentialsMatcher(md5CredentialsMatcher);
//    }
}
