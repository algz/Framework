/**
 * 
 */
package com.ras.authority;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author algz
 *
 */
@Service
public class AuthorityServiceImpl implements AuthorityService {

	@Autowired
	private AuthorityDao dao;
	
	/* (non-Javadoc)
	 * @see com.ras.authority.AuthorityService#findAuthrityGrid(com.ras.authority.AuthorityVo)
	 */
	@Override
	public void findAuthrityGrid(AuthorityVo<?> vo) {
		dao.findAuthrityGrid(vo);
	}


}
