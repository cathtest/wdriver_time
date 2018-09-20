package com.kate.mentoring.java.listeners.test;

import com.epam.reportportal.message.ReportPortalMessage;
import com.epam.reportportal.message.TypeAwareByteSource;
import com.epam.reportportal.utils.MimeTypeDetector;
import com.kate.mentoring.java.unit.DriverManager;
import com.kate.mentoring.java.utils.KathyLog;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import rp.com.google.common.io.Files;

import java.io.File;
import java.io.IOException;

public class ListenerTest implements ITestListener{

    private static final String SCREENSHOTS_NAME_TPL = "screenshots/scr";

    @Override
    public void onFinish(ITestContext Result)
    {

    }

    @Override
    public void onStart(ITestContext Result)
    {

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult Result)
    {

    }


    @Override
    public void onTestFailure(ITestResult Result){
        File screenshot = ((TakesScreenshot) DriverManager.getInstance().getDriver()).getScreenshotAs(OutputType.FILE);

        TypeAwareByteSource typeAwareByteSource = null;
        try {
            typeAwareByteSource = new TypeAwareByteSource(Files.asByteSource(screenshot), MimeTypeDetector.detect(screenshot));
        } catch (IOException e) {
            e.printStackTrace();
        }
        KathyLog.info(new ReportPortalMessage(typeAwareByteSource, " -> Webdriver screenshot captured: " + screenshot.getName()));
    }


    @Override
    public void onTestSkipped(ITestResult Result)
    {

    }


    @Override
    public void onTestStart(ITestResult Result)
    {

    }


    @Override
    public void onTestSuccess(ITestResult Result)
    {

    }

}
