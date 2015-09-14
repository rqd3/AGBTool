package com.server.backend.tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ DBDriverTest.class, APIControllerTest.class})
public class TestRunner {

}
