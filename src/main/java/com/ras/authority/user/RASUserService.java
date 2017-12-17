package com.ras.authority.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface RASUserService {
	public void findUserGrid(RASUserVo vo);
	
	public void saveUserRole(String userid,String roleids,String operate);
	
	public void saveUser(RASUserVo vo);
	
	public void delUser(RASUserVo vo);
}
