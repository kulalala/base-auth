package com.weiwu.base.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PageUtil {

    /**
     * @param dataList  待分页集合
     * @param pageSize  每页显示条数
     * @param pageIndex 页码
     * @param <T>       范型
     * @return Map<String, Object>结果集
     */
    public static <T> Map<String, Object> pageMap(List<T> dataList, int pageSize, int pageIndex) {
        HashMap<String, Object> map = new HashMap<>();
        int size = dataList.size();//总条数
        //总页数
        int totalPage = size / pageSize;//取整
        int a = size % pageSize;//取余
        if (a > 0) {
            totalPage += 1;
        }
        map.put("pages", totalPage);//总页数
        map.put("pageNum", pageIndex);//页码
        map.put("pageSize", pageSize);//每页显示条数
        map.put("total", size);//总条数
        int startNum = (pageIndex - 1) * pageSize + 1;                     //起始截取数据位置
        if (startNum > dataList.size()) {
            return null;
        }
        List<T> res = new ArrayList<>();
        int rum = dataList.size() - startNum;
        if (rum < 0) {
            return null;
        }
        if (rum == 0) {                                               //说明正好是最后一个了
            int index = dataList.size() - 1;
            res.add(dataList.get(index));
        }
        if (rum / pageSize >= 1) {                                    //剩下的数据还够1页，返回整页的数据
            for (int i = startNum; i < startNum + pageSize; i++) {          //截取从startNum开始的数据
                res.add(dataList.get(i - 1));
            }
        } else if ((rum / pageSize == 0) && rum > 0) {                 //不够一页，直接返回剩下数据
            for (int j = startNum; j <= dataList.size(); j++) {
                res.add(dataList.get(j - 1));
            }
        }
        map.put("size", res.size());//当前页总条数
        map.put("data", res); //分页当前页list数据
        return map;
    }


}