package com.demo.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Li Luopu
 * @version 1.0
 * @date 2020/11/2 13:39
 */
public class TestMain {
    public static void main(String[] args) throws IOException {

        List<String> lstRetData=new ArrayList<>();
        File file =new File("C:\\Users\\TD\\Desktop\\test.txt");
        BufferedReader bufferedReader=new BufferedReader(new FileReader(file));
        String line = "";
        while ((line=bufferedReader.readLine())!=null){
            System.out.println("@@@@@@@@@@@@@"+line);
            if(line.trim().contains("No Such")){
                //lstRetData.clear();
                break;
            }
            //开头Unknown
            if(line.trim().startsWith("Unknown")){

                lstRetData.clear();
                break;
            }
            if(line.trim().startsWith("error ")){

                lstRetData.clear();
                break;
            }
            if(line.trim().startsWith("not read ")){
                lstRetData.clear();
                break;
            }
            if(line.trim().startsWith("Timeout: No Response")){

                System.out.println("++++++++++++++++++++++返回结果包含 No Response，返回1++++++++++++++++++++++");

                break;
            }
//					包含Timeticks或者开头是SNMPv2、IF-MIB为正确截取返回值
            if(!line.trim().isEmpty() && (line.trim().toLowerCase().contains("sysuptime")
                    || line.trim().startsWith("SNMPv2")
                    || line.trim().startsWith("IF-MIB"))){
                lstRetData.add(line);
            }

        }
        int size = lstRetData.size();

        System.out.println("size="+size);
        if (size>0){
            System.out.println("无异常");
        }else {
            System.out.println("线路存在异常");
        }

    }
}

