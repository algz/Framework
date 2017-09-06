/**
 * 
 * 3.Gritter
url: https://github.com/jboesch/Gritter
version: 1.7.4
note: 通知插件
 */


//消息提醒 全局设置
$.gritter.options={
//	title:'提示', //消息标题
	//position:'center',//消息位置.(bottom-left, top-left, top-right, bottom-right)
	//class_name: 'gritter-light', //消息的CSS类
	time: 2000   //消息停留时间,例2000=2秒.

}

//发送消息的方法
$.gritter.add('请选择一条数据!');