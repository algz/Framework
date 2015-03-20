package com.hello.example.properties;

import java.util.Properties;

import com.hello.example.utils.PropertiesUtil;



public class PropertiesTest {
	// 按IP锁定开关
		private static boolean iPLockEnable;

		// 按登录名锁定开关
		private static boolean loginNameLockEnable;

		// 尝试登录次数
		private static int attempts;

		// 登录时间间隔（秒）
		private static int interval;

		// 锁定时间长度（秒）
		private static int lockedTime;

		// 提示信息
		private static String message;

		static {
			Properties p = PropertiesUtil.newInstance().getPropertiesByFilename(
					"lockedConfig.properties");
			iPLockEnable = new Boolean(p.getProperty("IPLockEnable"))
					.booleanValue();
			loginNameLockEnable = new Boolean(p.getProperty("LoginNameLockEnable"))
					.booleanValue();
			attempts = new Integer(p.getProperty("Attempts")).intValue();
			interval = new Integer(p.getProperty("Interval")).intValue();
			lockedTime = new Integer(p.getProperty("LockedTime")).intValue();
			message = p.getProperty("Message");
		}

		/**
		 * 按IP锁定是否打开
		 * 
		 * @since : 2012-5-24:下午09:06:25
		 * @return true打开；false关闭
		 */
		public static boolean isIplockenable() {
			return iPLockEnable;
		}

		/**
		 * 按登录名锁定是否打开
		 * 
		 * @since : 2012-5-24:下午09:06:55
		 * @return true打开；false关闭
		 */
		public static boolean isLoginNameLockEnable() {
			return loginNameLockEnable;
		}

		/**
		 * 获取尝试登录次数
		 * 
		 * @since : 2012-5-24:下午09:07:14
		 * @return 尝试登录次数
		 */
		public static int getAttempts() {
			return attempts;
		}

		/**
		 * 获取登录时间间隔
		 * 
		 * @since : 2012-5-24:下午09:07:16
		 * @return 登录时间间隔
		 */
		public static int getInterval() {
			return interval;
		}

		/**
		 * 获取锁定时间长度
		 * 
		 * @since : 2012-5-24:下午09:07:19
		 * @return 锁定时间长度
		 */
		public static int getLockedtime() {
			return lockedTime;
		}

		/**
		 * 获取提示信息
		 * 
		 * @since : 2012-5-25:下午09:06:32
		 * @return 提示信息
		 */
		public static String getMessage() {
			return message;
		}
}
