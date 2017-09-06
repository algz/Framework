/**
 * 
 */
package com.ras.authority.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import algz.platform.core.shiro.authority.resourceManager.Resource;
import algz.platform.core.shiro.authority.resourceManager.ResourceService;
import algz.platform.core.shiro.authority.userManager.User;
import algz.platform.core.shiro.authority.userManager.UserService;

/**
 * @author algz
 *
 */
@Service
public class RASUserServiceImpl implements RASUserService {

	@Autowired
	private RASUserDao dao;
	
	@Autowired
	private UserService userService;
	
	/* (non-Javadoc)
	 * @see com.ras.authority.AuthorityService#findAuthrityGrid(com.ras.authority.AuthorityVo)
	 */
	@Override
	public void findUserGrid(RASUserVo vo) {
		List<User> list=userService.findAll(null,vo.getStart(),vo.getLength());
		vo.setRecordsTotal(userService.countAll(null));
		vo.setData(list);
	}


	@Override
	public void saveUserRole(String userid,String roleids,String operate) {
		dao.saveUserRole(userid,roleids,operate);
	}
	
}
