package com.ras.authority.user;

public interface RASUserService {
	public void findUserGrid(RASUserVo vo);
	
	public void saveUserRole(String userid,String roleids,String operate);
}
