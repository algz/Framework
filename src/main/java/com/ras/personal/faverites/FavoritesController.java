package com.ras.personal.faverites;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ras.comparison.ComparisonVo;
import com.ras.personal.report.ReportVo;
import com.ras.tool.CommonTool;

@Controller
@RequestMapping("/ras/personal/favorites")
public class FavoritesController {
	
	@Autowired
	private FavoritesService service;
	
    @RequestMapping(value={"/findfavoritesgrid"}) //@RequestMapping 注解的方法才是真正处理请求的处理器
    public void  findFavoritesGrid(FavoritesVo vo,HttpServletRequest request,HttpServletResponse response) {
    	service.findFavoritesGrid(vo);
    	CommonTool.writeJSONToPage(response,vo );
    }
	
    @RequestMapping(value={"/showfavorites"}) //@RequestMapping 注解的方法才是真正处理请求的处理器
    public void  showFavorites(FavoritesVo vo,HttpServletRequest request,HttpServletResponse response) {
    	boolean isShow=service.showFavorites(vo);
     	CommonTool.writeJSONToPage(response,"{\"success\":"+isShow+"}" );
    }
    
    @RequestMapping(value={"/addfavorites"}) //@RequestMapping 注解的方法才是真正处理请求的处理器
    public void  addFavorites(FavoritesVo vo,HttpServletRequest request,HttpServletResponse response) {
    	if(vo.getUrl()!=null&&!vo.getUrl().equals("")){
        	String favoritesID=service.addFavorites(vo);
        	CommonTool.writeJSONToPage(response,"{\"success\":true,\"favoritesID\":\""+favoritesID+"\"}" );
    	}
    }
	
    @RequestMapping(value={"/delfavorites"}) //@RequestMapping 注解的方法才是真正处理请求的处理器
    public void  delFavorites(FavoritesVo vo,HttpServletRequest request,HttpServletResponse response) {
        	service.delFavorites(vo);
        	CommonTool.writeJSONToPage(response,"{\"success\":true}" );
    }
}
