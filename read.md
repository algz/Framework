JAVA命名规则:
1.方法名: 首单词第一个字母小写，后续单词第一个字母大写。例如insertUser

一.开发环境配置
1.github 下载项目
2.配置tamcat 热部署
 在conf文件夹下的server.xml的<Host></Host>标签加上
 <Context docBase="D:\Users\algz\Desktop\资料\java\apache-tomcat-8.0.28\webapps\Framework" path="/algz" reloadable="false" />

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

五. jsp 自定义标签
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
  