/**
 * If you really care for the license, look for the LICENSE.txt at the project root. Frankly, I 
 * really don't care.
 **/
package com.reportingtool.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import org.springframework.context.ApplicationContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.entities.dao.usermanager.UserDAO;
import com.entities.dictionary.UserControlTypeEnum;
import com.entities.entity.usermanager.Role;
import com.entities.entity.usermanager.User;
import com.reportingtool.service.UserService;
import com.reportingtool.utilities.ReportUtilities;


@Service("userDetailsService")
public class UserServiceImpl implements UserService, UserDetailsService {
    
    @Autowired
    UserDAO userDao;
    
    @Autowired
    ApplicationContext applicationContext;

    private static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    
    /**
     * Implementation of UserDetailsService interface's loadUserByUsername method. The returned 
     * User object facilitate spring security to validate user credentials and decide on access 
     * to resources.
     * @param username username of the logged in user
     * @return Spring security user object for the logged in user
     */
    public org.springframework.security.core.userdetails.User loadUserByUsername(String username) {
        logger.info("Spring security loading user details for user: " + username);
        User user = userDao.findUserByUsername(username);
        
        // AUDIT
        ReportUtilities.createUserControl(
				applicationContext,
				user,
				UserControlTypeEnum.LOGIN.getDesc()
						+ user.getUsername(), false,
				UserControlTypeEnum.LOGIN.getType());
        // Update user.LastLoginTms
        user.setLastLoginTms(new Date());
        userDao.edit(user);
        
        // get roles from user
        Collection<Role> roles = user.getRoles();
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();
        Iterator<Role> iterator = roles.iterator();
        while(iterator.hasNext()) {
            authorities.add(new SimpleGrantedAuthority(iterator.next().getRoleName()));
        }
        return new org.springframework.security.core.userdetails.User(username, user.getPassword(), authorities);
    }
    
    /**
     * This method returns the User entity object of the session's signed in user
     * @return User entity object of the current session's signed in user
     */
    public User getCurrentUser() {
        return userDao.findUserByUsername(
               ((org.springframework.security.core.userdetails.User)SecurityContextHolder.getContext().
                        getAuthentication().getPrincipal()).getUsername());
    }
}
