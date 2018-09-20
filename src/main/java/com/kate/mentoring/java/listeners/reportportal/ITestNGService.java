package com.kate.mentoring.java.listeners.reportportal;

import org.testng.ISuite;
import org.testng.ITestContext;
import org.testng.ITestResult;


public interface ITestNGService {

    void startLaunch();

    void finishLaunch();

    void startTestSuite(ISuite suite);

    void finishTestSuite(ISuite suite);

    void startTest(ITestContext testContext);

    void finishTest(ITestContext testContext);

    void startTestMethod(ITestResult testResult);

    void finishTestMethod(String status, ITestResult testResult);

    void startConfiguration(ITestResult testResult);

    void sendReportPortalMsg(ITestResult testResult);
}