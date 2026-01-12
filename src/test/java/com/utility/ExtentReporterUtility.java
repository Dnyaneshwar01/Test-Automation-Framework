package com.utility;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReporterUtility {

    private static ExtentReports extentReports;
    private static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();

    private ExtentReporterUtility() {
        // Prevent object creation
    }

    public static synchronized void setUpSparkReporter(String reportName) {
        try {
            if (extentReports == null) {

                Path reportDir = Paths.get(System.getProperty("user.dir"), "target", "ExtentReports");

                Files.createDirectories(reportDir);

                Path reportPath = reportDir.resolve(reportName);

                ExtentSparkReporter sparkReporter =
                        new ExtentSparkReporter(reportPath.toString());

                // Spark configurations
                sparkReporter.config().setTheme(Theme.STANDARD);
                sparkReporter.config().setDocumentTitle("Automation Test Report");
                sparkReporter.config().setReportName("TestNG Data Driven Framework");
                sparkReporter.config().setTimeStampFormat("dd-MM-yyyy HH:mm:ss");

                extentReports = new ExtentReports();
                extentReports.attachReporter(sparkReporter);

                // System info (Jenkins friendly)
                extentReports.setSystemInfo("OS", System.getProperty("os.name"));
                extentReports.setSystemInfo("Java Version", System.getProperty("java.version"));
                extentReports.setSystemInfo("User", System.getProperty("user.name"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // For DataProvider & Parallel execution
    public static void createExtentTest(String testName) {
        ExtentTest test = extentReports.createTest(testName);
        extentTest.set(test);
    }

    // Overloaded method with description
    public static void createExtentTest(String testName, String description) {
        ExtentTest test = extentReports.createTest(testName, description);
        extentTest.set(test);
    }

    public static ExtentTest getTest() {
        return extentTest.get();
    }

    public static synchronized void flushReport() {
        if (extentReports != null) {
            extentReports.flush();
        }
    }
}

