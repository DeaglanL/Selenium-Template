package util;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import java.util.HashMap;
import java.util.Map;

public class Reports {

    private ExtentReports report;

    public Reports(String reportName, String reportTitle, String reportFilePath){
        report = new ExtentReports();
        ExtentHtmlReporter extentHtmlReporter = new ExtentHtmlReporter(reportFilePath);
        extentHtmlReporter.config().setReportName(reportName);
        extentHtmlReporter.config().setDocumentTitle(reportTitle);
        report.attachReporter(extentHtmlReporter);
    }

    public Map<Integer, ExtentTest> createReportTests(String[] testNames){
        Map<Integer, ExtentTest> tests = new HashMap<>();

        for (int i = 0; i < testNames.length; i++) {

            tests.put(i, report.createTest(testNames[i]));

        }
        return tests;
    }


    public void closeReport(){
        report.flush();
    }



}
