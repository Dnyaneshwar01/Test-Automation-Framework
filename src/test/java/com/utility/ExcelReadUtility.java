package com.utility;

import com.ui.pojo.User;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ExcelReadUtility {

    public static Iterator<User> readExcelFile(String fileName) {

        File xlsxfile = new File(System.getProperty("user.dir") + "//testData//" + fileName);

        XSSFSheet xssfSheet;
        FileInputStream fis = null;
        XSSFWorkbook xssfWorkbook = null;
        List<User> userList = null;
        Iterator<Row> rowIterator;
        Cell emailAddressCell;
        Cell passwordCell;
        User user;
        Row row;
        try {
            fis = new FileInputStream(xlsxfile);
            xssfWorkbook = new XSSFWorkbook(fis);
            xssfSheet = xssfWorkbook.getSheet("loginTestData");
            rowIterator = xssfSheet.iterator();
            rowIterator.next();
            while (rowIterator.hasNext()) {
                row = rowIterator.next();
                emailAddressCell = row.getCell(0);
                passwordCell = row.getCell(1);
                user = new User(emailAddressCell.toString(), passwordCell.toString());
                userList.add(user);
                xssfWorkbook.close();
            }
        } catch (FileNotFoundException e) {

            e.printStackTrace();
        } catch (IOException e) {

            e.printStackTrace();
        }

        return userList.iterator();
    }
}
