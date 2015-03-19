package com.hello;

import java.util.ArrayList;
import java.util.List;

import algz.platform.core.shiro.authority.resourceManager.Resource;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		class A{
			public void getMenus(List<Resource> list,int rootID){
			    Boolean flag=true;
			    final Resource resRoot;
				for(Resource res:list){
					if(res.getParentId().equals(rootID)){
						if(flag){
							System.out.print("非叶子结点的菜单");
							flag=false;
						}
						getMenus(list,res.getId().intValue());
					}
				}
				if(flag){
					System.out.print("叶子结点的菜单");
				}
			}
		}
		new A().getMenus(null,0);
	}

}
