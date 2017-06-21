package com.ras.relationTable.sysware.user;

import algz.platform.core.shiro.authority.userManager.User;

public interface SyswareUserDao {
	public User findByUsername(String username);
}
