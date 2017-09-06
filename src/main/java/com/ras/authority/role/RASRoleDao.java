package com.ras.authority.role;

import java.util.List;

public interface RASRoleDao {
	public void findAuthrityGrid(RASRoleVo<?> vo);
	
	public List<String> findRoleByUserID(String userid);
}
