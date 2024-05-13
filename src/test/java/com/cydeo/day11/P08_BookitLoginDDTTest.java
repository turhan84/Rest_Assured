package com.cydeo.day11;

import com.cydeo.utilities.BookItQA3TestBase;
import com.cydeo.utilities.BookItTestBase;
import com.cydeo.utilities.BookItUtils;
import com.cydeo.utilities.ExcelUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.Map;

public class P08_BookitLoginDDTTest  extends BookItQA3TestBase {


    @ParameterizedTest
    @MethodSource("getExcelData")
    public void test1(Map<String, String> row){

        System.out.println("row = " + row);

        BookItUtils.getToken(row.get("email"),row.get("password"));



    }


    public static List<Map<String,String>> getExcelData(){

        ExcelUtil excelUtil = new ExcelUtil("src/test/resources/BookItQa3.xlsx","QA3");

        return excelUtil.getDataList();
    }
}
