package com.ras.ws.service;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public interface RASWebservice {

	@WebMethod
	public String endApproval(String paramToken_ras,String username,String approvalresulte,String returnreason);
	
}
