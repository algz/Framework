/**
 * 
 */
package com.ras.authority.resource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import algz.platform.core.shiro.authority.resourceManager.Resource;
import algz.platform.core.shiro.authority.resourceManager.ResourceService;
import algz.platform.core.shiro.authority.roleManager.Role;

/**
 * @author algz
 *
 */
@Service
public class RASResourceServiceImpl implements RASResourceService {

	@Autowired
	private RASResourceDao dao;
	
	@Autowired
	private ResourceService resourceService;
	
	/* (non-Javadoc)
	 * @see com.ras.authority.AuthorityService#findAuthrityGrid(com.ras.authority.AuthorityVo)
	 */
	@Override
	public void findResourceGrid(RASResourceVo vo) {
		List<Resource> resourceList=dao.findResourceByRoleid(vo);

		List<String> roleList=dao.findResourceByRoleID(vo.getRoleid());
		
		List<Map<String,String>> list=new ArrayList<Map<String,String>>();
		for(Resource resource:resourceList){
			Map<String,String> m=new HashMap<String,String>();
			m.put("id", resource.getId());
			m.put("name", resource.getName());
			m.put("url", resource.getUrl());
			m.put("icon", resource.getIcon());
			m.put("ispublic", resource.getIsPublic());
			String checked="0";
			for(String sourceid:roleList){
				if(resource.getId().equals(sourceid)){
					checked="1";
					break;
				}
			}
			m.put("checkbox", checked);
			list.add(m);
		}
		
		
		vo.setData(list);
	}

	@Override
	public void saveRoleResource(String roleid, String resourceid, String operate) {
		dao.saveRoleResource(roleid, resourceid, operate);
	}


}
