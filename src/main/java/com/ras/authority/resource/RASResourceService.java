package com.ras.authority.resource;

public interface RASResourceService {
	public void findResourceGrid(RASResourceVo vo);
	
	public void saveRoleResource(String roleid,String resourceid,String operate);
}
