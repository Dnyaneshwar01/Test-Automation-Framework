package com.ui.listerners;

import com.aventstack.extentreports.Status;
import com.ui.tests.TestBase;
import com.utility.BrowserUtility;
import com.utility.ExtentReporterUtility;
import com.utility.LoggerUtility;

import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.util.Arrays;

public class TestListener implements ITestListener {

    private final Logger logger = LoggerUtility.getLogger(this.getClass());

    @Override
    public void onStart(ITestContext context) {
        logger.info("Test Suite started");

        String suiteName = context.getSuite().getName();
        String reportName = suiteName + "_Report_" + System.currentTimeMillis() + ".html";

        ExtentReporterUtility.setUpSparkReporter(reportName);
    }

    @Override
    public void onTestStart(ITestResult result) {
        logger.info("Test Started : {}", result.getMethod().getMethodName());
        logger.info("Description  : {}", result.getMethod().getDescription());
        logger.info("Groups       : {}", Arrays.toString(result.getMethod().getGroups()));

        ExtentReporterUtility.createExtentTest(
                result.getMethod().getMethodName(),
                result.getMethod().getDescription()
        );
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        logger.info("{} PASSED", result.getMethod().getMethodName());

        ExtentReporterUtility.getTest().log(Status.PASS, "Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        logger.error("{} FAILED", result.getMethod().getMethodName());
        logger.error("Failure Reason", result.getThrowable());

        ExtentReporterUtility.getTest()
                .log(Status.FAIL, "Test Failed")
                .log(Status.FAIL, result.getThrowable());

        try {
            Object testClass = result.getInstance();
            BrowserUtility browserUtility = ((TestBase) testClass).getInstance();

            logger.info("Capturing screenshot for failed test");

            String screenshotPath = browserUtility.takeScreenshot(result.getMethod().getMethodName());

            if (screenshotPath != null) {ExtentReporterUtility.getTest()
                    .addScreenCaptureFromPath(screenshotPath);
            }

        } catch (Exception e) {
            logger.error("Screenshot capture failed", e);
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        logger.warn("{} SKIPPED", result.getMethod().getMethodName());

        ExtentReporterUtility.getTest()
                .log(Status.SKIP, "Test Skipped")
                .log(Status.SKIP, result.getThrowable());
    }

    @Override
    public void onFinish(ITestContext context) {
        logger.info("Test Suite completed");
        ExtentReporterUtility.flushReport();
    }
}
