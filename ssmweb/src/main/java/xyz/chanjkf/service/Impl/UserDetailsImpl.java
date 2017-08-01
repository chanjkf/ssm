package xyz.chanjkf.service.Impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import xyz.chanjkf.entity.RoleEntity;
import xyz.chanjkf.entity.UserEntity;
import xyz.chanjkf.service.IRoleService;
import xyz.chanjkf.service.IUserService;
import xyz.chanjkf.utils.DXPConst;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service("UserDetailsImpl")
public class UserDetailsImpl implements UserDetailsService {

    @Autowired
    IUserService userService;

    @Autowired
    IRoleService userModelRoleService;

    @Autowired
    IRoleService roleService;

    @Autowired
    private HttpSession session;

    @Autowired
    private HttpServletRequest request;


    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Set<GrantedAuthority> auths = new HashSet<GrantedAuthority>();

//        userService = (UserService) ToolSpring.getBean("userService");
        UserEntity userEntity = userService.findUser(userName);

        if (userEntity == null) {
            throw new UsernameNotFoundException("user not found");
        }
        //获取角色
        List<RoleEntity> roleEn = roleService.getRoleByUser(userEntity.getId());
        if (null != roleEn) {
            auths.add(new SimpleGrantedAuthority(roleEn.get(0).getName()));
        }

        User user = new User(userEntity.getUserName(), userEntity.getUserPassword(), auths);

        if (null != session) {
            session.setAttribute(DXPConst.SESSION_USERID, userEntity.getId());
        }
        return user;
    }



}
