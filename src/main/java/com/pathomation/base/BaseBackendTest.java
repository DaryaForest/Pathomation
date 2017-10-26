package com.pathomation.base;


import org.testng.ITest;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

public class BaseBackendTest implements ITest{
    protected String currentAnnotatedMethodName = "";


    @BeforeMethod
    public void setTestName(Method method) {
        currentAnnotatedMethodName = "";
        try {
            String testName = method.getAnnotation(Test.class).testName();
            if (!testName.isEmpty())
                currentAnnotatedMethodName = testName;
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    @Override
    public String getTestName() {
        return currentAnnotatedMethodName;
    }
    @AfterMethod
    public void setNullTestName() {
        currentAnnotatedMethodName = null;
    }

}
