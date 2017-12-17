package com.ras.userDataPrivilidge;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserDataPrivilidgeServiceImpl implements UserDataPrivilidgeService {

	@Autowired
	private UserDataPrivilidgeDao dao;
	
	@Override
	@Transactional
	public String saveUserDataPrivilidge(UserDataPrivilidge udp) {
		String msg="";
		try{
			dao.saveUserDataPrivilidge(udp);
		}catch(Exception ex){
			msg=ex.getLocalizedMessage();
		}
		return msg;
	}

	@Override
	@Transactional
	public String delUserDataPrivilidge(UserDataPrivilidge udp) {
		return dao.delUserDataPrivilidge(udp);
	}

}
