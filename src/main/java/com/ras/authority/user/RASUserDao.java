package com.ras.authority.user;

public interface RASUserDao {
	public void findAuthrityGrid(RASUserVo<?> vo);
	
	public void saveUserRole(String userid,String roleids,String operate);
}
