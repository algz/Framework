package com.ras.authority.resource;

import java.util.List;

import algz.platform.core.shiro.authority.resourceManager.Resource;

public interface RASResourceDao {
	
	public List<Resource> findResourceByRoleid(RASResourceVo vo);
	
	public List<String> findResourceByRoleID(String roleid);
	
	public void saveRoleResource(String roleid,String resourceid,String operate);
}
