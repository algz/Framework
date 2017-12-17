package com.ras.relationTable.sysware.user;

import java.math.BigDecimal;
import java.util.Map;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import algz.platform.core.shiro.authority.userManager.User;
import algz.platform.core.shiro.authority.userManager.UserDao;

@Service
public class SyswareUserServiceImpl implements SyswareUserService {

    @Autowired
    private SyswareUserDao dao;
	
	
	@Transactional
	@Override
	public User findByUsername(String loginName) {
		return dao.findByUsername(loginName);
	}

}
