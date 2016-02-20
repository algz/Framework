package algz.platform.admin;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import net.sf.json.JSONArray;

@Repository
public class ManagerDaoImpl implements ManagerDao {

	@Autowired
	private SessionFactory sf;
	
	public JSONArray getSilderNav() {
		String s="[{'header':'Beaut-zihan','text':'超级管理员1S',img:'img/profile_small.jpg','menus':["+
    			"{'text':'修改头像',url:'form_avatar.html'},"+
    			"{'text':'个人资料',url:'profile.html'},"+
    			"{'text':'联系我们',url:'contacts.html'},"+
    			"{'text':'信箱',url:'mailbox.html'},"+
    			"'divider',"+
    			"{'text':'安全退出',url:'login.html'}"+
    			"]},"+

    			"{'text':'主页','menus':["+
    			"{'text':'主页示例一','url':'index_v1.html'},"+
    			"{'text':'主页示例二','url':'index_v2.html'},"+
    			"{'text':'主页示例三','url':'index_v3.html'},"+
    			"{'text':'主页示例四','url':'index_v4.html'},"+
    			"{'text':'主页示例五','url':'index_v5.html','target':'_blank'}"+
    			"]},"+

    			"{'text':'布局',url:'layouts.html',msg:'12'},"+

    			"{'text':'表单','menus':["+
    			"{'text':'基本表单','url':'form_basic.html'},"+
    			"{'text':'表单验证','url':'form_validate.html'},"+
    			"{'text':'高级插件','url':'form_advanced.html'},"+
    			"{'text':'表单向导','url':'form_wizard.html'},"+
    			"{'text':'文件上传','url':'#','menus':["+
    				"{'text':'百度WebUploader','url':'form_webuploader.html'},"+
    				"{'text':'DropzoneJS','url':'form_file_upload.html'},"+
    				"{'text':'头像裁剪上传','url':'form_avatar.html',msg:0}"+
    			"]}"+
    			"]}"+
    			"]" ;
    			JSONArray ja=JSONArray.fromObject(s);
    			return ja;
	}

}
