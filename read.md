一.JAVA命名规则:
1.方法名: 首单词第一个字母小写，后续单词第一个字母大写。例如insertUser 

二.页面UI
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

6.jsp 自定义标签
(1).tag文件
650项目使用此方法.

(2).tld标签
(2.1).在编写标签之前，必须引入 jsp-api.jar 文件配置在CLASSPATH；
		<!-- jsp 自定义标签 -->
		<dependency>
			<groupId>javax.servlet.jsp</groupId>
			<artifactId>jsp-api</artifactId>
			<version>2.2.1-b03</version>
		</dependency>
		
(2.2).编写标签库

(2.3).引用标签库
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







三.系统框架介绍
框架包含:
springmvc + spring + hibernate + cxf

(一).开发环境配置
1.github 下载项目
2.配置tamcat 热部署
 在conf文件夹下的server.xml的<Host></Host>标签加上
 <Context docBase="D:\Users\algz\Desktop\资料\java\apache-tomcat-8.0.28\webapps\Framework" path="/algz" reloadable="false" />
3.增加上传文件目录
WEB项目与上传目录分离,重新部署时,不会覆盖.
<!--增加的 path="/虚拟名" docBase="虚拟路径"-->
<Context path="/upload" docBase="D:\upload\"  reloadable="true"/>

(二).spring mvc
1.ModelAndView 对象
第一个参数是视图名/视图对象,第二个参数为模型属性(即传给用户的K-V对象).此类有很多构造函数,可查源码即可.

(三).shiro
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
sec_permission表的permissionName属性="user:create"
(1)shiro HTML标签:

<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<shiro:hasPermission name="user:create">
     <a href="admin.jsp">创建用户</a>
</shiro:hasPermission>

(2)JAVA 方法:
@RequiresPermissions("account:create")  
public void createAccount(Account account) {  
    //this method will only be invoked by a Subject  
    //that is permitted to create an account  
    ...  
}  

4.@RequiresRoles 当前用户需拥有制定角色 

5.@RequiresUser 当前用户需为已认证用户或已记住用户 

6.<shiro:hasRole> 有权限才能访问
<shiro:hasRole name="admin">
    Administer the system
</shiro:hasRole>

<shiro:hasRole name="user">
    user role
</shiro:hasRole>

(四).CXF webservice
1. CXF webservice 类也必须添加spring 注解  @Service等.


1.数据来源的"主要功能"重新启用;
2.参数查询时,仅查询的是"主要功能"的数据;
3.参数查询页面,对每个参数增加"对比"功能,作用是仅查询不同机型的指定参数的值;
4.参数查询页面,"对比"按钮增加弹出页,可选择对比的参数
5.参数查询页面,可对每条参数进行修改,关联为机型的"主要功能"的数据来源;
6.参数查询页面,右侧增加浮动表格,显示每个参数的不同来源对应的数据;
7.参数对比页面,增加结论对话框,可保存当前的参数数据作为报告处理;
8.录入三个最全的数据作为示例;
9.首页优化美观.

1.私有数据个人查看
2.公有数据所有人查看
3.查询出来的数据(仅查询主要来源)分为所里(公有)和自己(私有).注:机型名称可以相同,但可视级别不能相同.
4.公有仅且只有一个数据来源
5.公有数据只有"数据管理员"能编辑,但不能删除.即私有数据提交到公有后,数据权限转给数据管理员编辑
6.私有数据提交转变成公有数据后,个人无权再进行编辑,仅数据管理员编辑.
7.数据管理员可以查询所有数据(全部),但不能编辑除其它人的数据(私有)
8.数据编辑页,普通员工只能查看个人编辑数据并且是私有可视.
9.数据范围可视权限,仅范围内和自已可视.

权限管理:
1数据校审角色: 在数据流程审批中,第一个结点,负责对提交的数据进行修改校对.
2数据审核角色: 在数据流程审批中,最后一个结点,负责对提交的数据进行可视权限调整.
3数据管理角色: 不能编辑所有数据,仅能调整各个数据的可视权限范围.

数据编辑页:
1.只能查看自已创建的机型.
2.新增子机型,只能挂在自已创建的机型或公共机型下.

