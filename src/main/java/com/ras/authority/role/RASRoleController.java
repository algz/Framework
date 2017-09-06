/**
 * 
 */
/**
 * @author algz
 *
 */
package com.ras.authority.role;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ras.index.Page;
import com.ras.tool.CommonTool;

@Controller
@RequestMapping(value="/ras/authority/role")
public class RASRoleController{
	
	@Autowired
	private RASRoleService service;
	
	@RequestMapping("/findrolegrid")
	public void findRoleGrid(RASRoleVo vo,HttpServletResponse response){
		service.findRoleGrid(vo);
		CommonTool.writeJSONToPage(response, vo);
	}
	

}