package com.ras.authority.role;

import com.ras.authority.user.RASUserVo;

public interface RASRoleService {
	public void findRoleGrid(RASRoleVo vo);
	
	
	public void saveRole(RASRoleVo vo);
	
	public void delRole(String roleid);
}
