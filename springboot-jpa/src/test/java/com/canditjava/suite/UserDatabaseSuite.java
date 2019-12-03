package com.canditjava.suite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.candidjava.spring.controller.UserControllerTest;
import com.candidjava.spring.service.UserServiceTest;



@RunWith(Suite.class)
@SuiteClasses({ UserServiceTest.class, UserControllerTest.class

})
public class UserDatabaseSuite {

}
