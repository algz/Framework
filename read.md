JAVA命名规则:
1.方法名: 首单词第一个字母小写，后续单词第一个字母大写。例如insertUser

使用插件说明:
1.typeahead.jquery.js :
url: https://github.com/twitter/typeahead.js
version: 0.11.1
note:查询框自动补全功能

2.select控件
(1)chosen.jquery.js min 27K
size: min 27K
note: 美化select控件,只能本地加载(可以通过AJAX修改)

(2)select2.js 
url: https://github.com/select2/select2
size: min 67K
version: 4.0.3
note: 美化select控件,并提供基础加载和AJAX加载功能.

3.Gritter
url: https://github.com/jboesch/Gritter
version: 1.7.4
note: 通知插件

4.ColorBox
url: https://github.com/jackmoore/colorbox ；中文  http://www.jq22.com/jquery-info223
version: 1.6.4
note: jquery弹出层插件jquery.ColorBox ，主要用于显示图片使用。

5.bootstrap-fileinput
url:  https://github.com/kartik-v/bootstrap-fileinput ; 
中文 http://www.cnblogs.com/landeanfen/p/5007400.html http://www.htmleaf.com/html5/html5muban/201505091801.html
version: 4.3.7
note: 上传文件

issu:
1.当div的style="display:none"时，如何获取它的实际宽度？ offsetWidth此时为0，既使设置style的width也不能获取得实际宽度.
方法:
(1).因为隐藏的元素是没有大小的.如果是浮动的，用其他元素把它盖住，那么它的大小就能拿到。
(2).试着让display不为none而让visibility为false来获取offsetwidth。但display和visibility是有区别的.
(3).自行给div内的每个元素设置宽度.

一.开发环境配置
1.github 下载项目
2.配置tamcat 热部署
 在conf文件夹下的server.xml的<Host></Host>标签加上
 <Context docBase="D:\Users\algz\Desktop\资料\java\apache-tomcat-8.0.28\webapps\Framework" path="/algz" reloadable="false" />
3.增加上传文件目录
WEB项目与上传目录分离,重新部署时,不会覆盖.
<!--增加的 path="/虚拟名" docBase="虚拟路径"-->
<Context path="/upload" docBase="D:\upload\"  reloadable="true"/>

二.系统配置



三.shiro
基于注解的授权实现 
1.@RequiresAuthentication 可以用户类/属性/方法，用于表明当前用户需是经过认证的用户。 
@RequiresAuthentication  
public void updateAccount(Account userAccount) {  
    //this method will only be invoked by a   
    //Subject that is guaranteed authenticated  
    ...  
}  

2.@RequiresGuest 表明该用户需为”guest”用户 

3.@RequiresPermissions 当前用户需拥有制定权限 
@RequiresPermissions("account:create")  
public void createAccount(Account account) {  
    //this method will only be invoked by a Subject  
    //that is permitted to create an account  
    ...  
}  

4.@RequiresRoles 当前用户需拥有制定角色 

5.@RequiresUser 当前用户需为已认证用户或已记住用户 

四.spring mvc
1.ModelAndView 对象
第一个参数是视图名/视图对象,第二个参数为模型属性(即传给用户的K-V对象).此类有很多构造函数,可查源码即可.

五.jsp 自定义标签
1.tag文件
650项目使用此方法.

2.tld标签
(1).在编写标签之前，必须引入 jsp-api.jar 文件配置在CLASSPATH；
		<!-- jsp 自定义标签 -->
		<dependency>
			<groupId>javax.servlet.jsp</groupId>
			<artifactId>jsp-api</artifactId>
			<version>2.2.1-b03</version>
		</dependency>
		
(2).编写标签库

(3).引用标签库
(a).web.xml 配置
<!-- 自定义标签库 -->
<jsp-config>
    <taglib>
        <taglib-uri>/mytaglib</taglib-uri>                                     <!-- 标签库名称 -->
        <taglib-location>/WEB-INF/tags/test.tld</taglib-location>   <!-- 标签库路径 -->
    </taglib>
  </jsp-config>
(b).jsp 引用
  <%@ taglib prefix="ex" uri="/mytaglib"%>
  ......
  <ex:hello/>
  
六.CSS类说明
1. class="active"
2. class="disabled"
3. class="hidden"



