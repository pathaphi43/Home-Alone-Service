package com.csproject.homealoneservice.prepare;


import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

public class PrepareData {

    public static List<Integer> getRentingStatusList(){
        return Arrays.asList(
                1,2
        );
    }

    public static List<Integer> getSearchStatusList1(){
        return Arrays.asList(
                0
        );
    }

    public static List<Integer> getSearchStatusList2(){
        return Arrays.asList(
                1,2,3
        );
    }
    public static List<Integer> getSearchStatusList3(){
        return Arrays.asList(
                0,1,2,3
        );
    }

}
