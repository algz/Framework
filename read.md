一。shiro
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

二.spring mvc
1.ModelAndView 对象
第一个参数是视图名/视图对象,第二个参数为模型属性(即传给用户的K-V对象).此类有很多构造函数,可查源码即可.
