package com.ui.dataProviders;

import com.google.gson.Gson;
import com.ui.pojo.TestData;
import com.ui.pojo.User;
import com.utility.CSVReaderUtility;
import com.utility.ExcelReadUtility;
import org.testng.annotations.DataProvider;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class LoginDataProvider {


    @DataProvider(name = "LoginTestDataProvider")
    public Iterator<Object[]> loginDataProviser() throws FileNotFoundException {

        Gson gson = new Gson();
        File testDataFile = new File(System.getProperty("user.dir") + "\\testData\\loginData.json");
        FileReader fileReader = new FileReader(testDataFile);
        TestData data = gson.fromJson(fileReader, TestData.class);

        List<Object[]> datatoReturn = new ArrayList<Object[]>();

        for (User user : data.getData()) {
            datatoReturn.add(new Object[]{user});
        }

        return datatoReturn.iterator();
    }

    @DataProvider(name = "LoginTestCSVDataProvider")
    public Iterator<User> loginCSVDataProvider(){
         return CSVReaderUtility.readCSVFile("loginData.csv");
    }

    @DataProvider(name = "LoginTestExcelDataProvider")
    public Iterator<User> loginExcelDataProvider() {
        return ExcelReadUtility.readExcelFile("loginData.xlsx");
    }

}
