package algz.platform.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

public class TableTagSupport extends TagSupport {
	private String row;  
    private String col;  
    public String getRow(){  
        return row;  
    }  
    public String getCol(){  
        return col;  
    }  
    public void setRow(String row){  
        this.row = row;  
    }  
    public void setCol(String col){  
        this.col = col;  
    }  
    public int doStartTag()throws JspException{  
        JspWriter out = super.pageContext.getOut();  
        try{  
            out.println("<table border=\"3\"> ");  
            for(int i=0;i<Integer.parseInt(row);i++){  
                out.println("<tr>");  
                for(int j=0;j<Integer.parseInt(col);j++){  
                    out.println("<td>"+i*j+"</td>");  
                }  
                out.println("</tr>");  
            }  
            out.println("</table>");  
        }  
        catch(Exception e){}  
        return TagSupport.SKIP_BODY;  
    }  
}
