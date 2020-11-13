package com.dwill.demo.excel;

import com.alibaba.excel.EasyExcel;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class TestEasyExcel {
    @Test
    public  void writeExcel(){
        //实现excel写的操作
        //1.设置写入文件夹的地址和excel文件名称
        String filename="D:\\write.xlsx";
        //调用easyexcel里面的方法实现写操作
        //write方法两个参数：第一个参数路径名称，第二个参数实体类class
        EasyExcel.write(filename,DemoData.class).sheet("学生列表").doWrite(getData());
    }
    @Test
    public void read(){
        //实现excel读操作
        String filename = "D:\\write.xlsx";
        EasyExcel.read(filename,DemoData.class,new ExcelListener()).sheet().doRead();
    }
    private  List<DemoData> getData(){
        List<DemoData> list=new ArrayList<>();
        for (int i = 0; i <10 ; i++) {
            DemoData data=new DemoData();
            data.setSno(i);
            data.setName("lucy"+i);
            list.add(data);
        }
        return list;
    }
}
