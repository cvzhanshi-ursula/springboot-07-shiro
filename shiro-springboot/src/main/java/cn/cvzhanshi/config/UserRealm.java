package cn.cvzhanshi.config;

import cn.cvzhanshi.entity.User;
import cn.cvzhanshi.service.Impl.UserServiceImpl;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author cVzhanshi
 * @create 2021-06-19 22:01
 */
//自定义的UserRealm
public class UserRealm extends AuthorizingRealm{
    @Autowired
    UserServiceImpl userService;

    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("执行了 => 授权doGetAuthorizationInfo");
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        //info.addStringPermission("user:add");

        //拿到当前登录的对象
        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getPrincipal();



        //设置当前用户权限
        info.addStringPermission(user.getPerms());



        return info;
    }

    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("执行了 => 认证doGetAuthenticationInfo");


        UsernamePasswordToken userToken = (UsernamePasswordToken) authenticationToken;

        //连接真是数据库
        User user = userService.queryByUserName(userToken.getUsername());

        if(user == null){//没有这个人
            return null;//报异常
        }
        // 登录成功 将用户信息存入session
        Subject currentSubject = SecurityUtils.getSubject();
        Session session = currentSubject.getSession();
        session.setAttribute("loginUser",user.getUsername());

        //密码认证，Shiro帮我们认证
        return new SimpleAuthenticationInfo(user,user.getPassword(),"");
    }
}
