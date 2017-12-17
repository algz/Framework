/**
 * 
 */
package com.ras.authority.user;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ras.authority.user.RASUserDao.Operation;

import algz.platform.core.shiro.authority.resourceManager.Resource;
import algz.platform.core.shiro.authority.resourceManager.ResourceService;
import algz.platform.core.shiro.authority.userManager.AUserService;
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
	
	@Autowired
	private AUserService auserService;
	
	/* (non-Javadoc)
	 * @see com.ras.authority.AuthorityService#findAuthrityGrid(com.ras.authority.AuthorityVo)
	 */
	@Override
	public void findUserGrid(RASUserVo vo) {
		List<User> list=userService.findAll(null,vo.getStart(),vo.getLength());
		vo.setRecordsTotal(userService.countAll(null));
		vo.setData(list);
	}


	/**
	 * @param operate add,del
	 */
	@Override
	public void saveUserRole(String userid,String roleids,String operate) {
		if(operate.equals("add")){
			dao.saveUserRole(userid,roleids,Operation.add);
		}else if(operate.equals("del")){
			dao.saveUserRole(userid,roleids,Operation.del);
		}
		
	}

	@Transactional
	@Override
	public void saveUser(RASUserVo vo) {
		User user=new User();
		user.setUserid(vo.getUserid());
		user.setUsername(vo.getUsername());
		user.setCname(vo.getCname());
		user.setPassword(vo.getPassword());
		user.setDepartment(vo.getDepartment());
//		auserService.saveUser(user);
		userService.saveUser(user);
	}


	@Override
	public void delUser(RASUserVo vo) {
		userService.deleteUser(vo.getUserid());
	}
	
}
