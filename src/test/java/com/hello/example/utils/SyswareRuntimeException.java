package com.hello.example.utils;


/**
 * TODO
 * Copyright    :www.sysware.com.cn
 * Company      :sysware Info. Ltd.
 * Created      :2011-12-7上午10:57:54
 * @author      :杨怀
 */
@SuppressWarnings("serial")
public class SyswareRuntimeException extends RuntimeException{
	
	public SyswareRuntimeException(){
		super();
	}
	
	public SyswareRuntimeException(String psMsg){
		super(psMsg);
	}
	
	public SyswareRuntimeException(String psMsg,Throwable t){
		super(psMsg,t );
	}
	
	public SyswareRuntimeException(Throwable t){
		super(t );
	}

}
