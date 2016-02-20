package algz.platform.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class HelloTag extends SimpleTagSupport {
	  public void doTag() throws JspException, IOException {

		    JspWriter out = getJspContext().getOut();
		    out.println("Hello Custom Tag!");
		  }
}
