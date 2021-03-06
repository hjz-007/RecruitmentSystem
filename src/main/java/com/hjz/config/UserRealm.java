package com.hjz.config;

import com.hjz.model.po.Company;
import com.hjz.model.po.User;
import com.hjz.service.CompanyService;
import com.hjz.service.UserService;
import lombok.NoArgsConstructor;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

@NoArgsConstructor
public class UserRealm extends AuthorizingRealm {
    @Autowired
    UserService userService;
    @Autowired
    CompanyService companyService;

    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.addStringPermission("user");
        return info;
    }

    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        UsernamePasswordToken userToken = (UsernamePasswordToken) authenticationToken;
        User user = userService.getByEmail(userToken.getUsername());
        if(user != null){
            return new SimpleAuthenticationInfo(user, user.getUserPassword(),"");
        }
        Company company = companyService.queryCompanyByEmail(userToken.getUsername());
        if(company != null){
            return new SimpleAuthenticationInfo(company, company.getCompanyPassword(),"");
        }
        return null;
    }
}
