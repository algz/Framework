/**
 * 
 */
package com.ras.authority.role;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import algz.platform.core.shiro.authority.resourceManager.Resource;
import algz.platform.core.shiro.authority.resourceManager.ResourceService;
import algz.platform.core.shiro.authority.roleManager.Role;
import algz.platform.core.shiro.authority.roleManager.RoleService;
import net.sf.json.JSONObject;

/**
 * @author algz
 *
 */
@Service
public class RASRoleServiceImpl implements RASRoleService {

	@Autowired
	private RASRoleDao dao;
	
	@Autowired
	private RoleService roleService;
	
	/* (non-Javadoc)
	 * @see com.ras.authority.AuthorityService#findAuthrityGrid(com.ras.authority.AuthorityVo)
	 */
	@Override
	public void findRoleGrid(RASRoleVo vo) {
		List<Role> roleList=roleService.findAll(null,vo.getStart(),vo.getLength());
		vo.setRecordsTotal(roleService.countAll(null));
		
		List<String> userList=dao.findRoleByUserID(vo.getUserid());
		
		List<Map<String,String>> list=new ArrayList<Map<String,String>>();
		for(Role role:roleList){
			Map<String,String> m=new HashMap<String,String>();
			m.put("roleid", role.getId());
			m.put("rolename", role.getRolename());
			m.put("description", role.getDescription());
			String checked="0";
			for(String roleid:userList){
				if(role.getId().equals(roleid)){
					checked="1";
					break;
				}
			}
			m.put("checkbox", checked);
			list.add(m);
		}
		
		
		vo.setData(list);
	}



}
