package com.hrm.scripts;
import org.testng.annotations.Test;

import com.hrm.base.BaseTest;

public class LoginLogoutTest extends BaseTest{
	@Test(priority=1)
	public void testAutoLoginLogout1(){
		log.info("This script will automatically login and logout");
	}
}
