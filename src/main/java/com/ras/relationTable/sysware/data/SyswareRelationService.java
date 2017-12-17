package com.ras.relationTable.sysware.data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ras.approval.Approval;
import com.ras.approval.ApprovalService;
import com.ras.documnet.dataManager.DataService;
import com.ras.documnet.dataManager.DataVo;
import com.ras.index.Page;
import com.ras.tool.CommonTool;
import com.ras.ws.client.CXFClientUtil;

import algz.platform.util.xml.StAXUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public interface SyswareRelationService {
	
	public void getDataCheckIndex(SyswareRelationVo vo);
	
	public Map getDataApproval(SyswareRelationVo vo);

	public void updateApproval(Approval approval);
}
