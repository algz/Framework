package algz.platform.admin;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.sf.json.JSONArray;

@Service
public class ManagerServiceImpl implements ManagerService {

	@Autowired
	public ManagerDao dao;
	
	public Map<String,?> getSilderNav() {
		return dao.getSilderNav();
	}

}
