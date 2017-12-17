package com.ras.authority.user;

public interface RASUserDao {
	
	enum Operation{
		add,del
	}
	
	public void findAuthrityGrid(RASUserVo<?> vo);
	
	public void saveUserRole(String userid,String roleids,Operation operate);
}
