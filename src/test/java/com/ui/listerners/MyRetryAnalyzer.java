package com.ui.listerners;

import com.constants.Env;
import com.utility.PropertiesUtils;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class MyRetryAnalyzer implements IRetryAnalyzer {

    private static final int MAX_NUMBER_OF_ATTEMPTS = Integer.parseInt(PropertiesUtils.readProperty(Env.QA,"MAX_NUMBER_OF_ATTEMPTS"));
    private static int currentAttempt = 1;

    @Override
    public boolean retry(ITestResult iTestResult) {
        if (currentAttempt <= MAX_NUMBER_OF_ATTEMPTS) {
            currentAttempt++;
            return true;
        }
        return false;
    }
}
