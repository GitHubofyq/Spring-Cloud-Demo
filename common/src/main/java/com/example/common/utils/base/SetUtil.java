package com.example.common.utils.base;

import java.util.*;

/**
 * @ ClassName  ：SetUtil
 * @ Description：TODO
 * @ Author     ：yuqin
 * @ createTime : 2018-12-22
 */
public class SetUtil {

    public static void main(String[] args) {
        System.out.println("-6>>>1:"+(6>>>1));

        Long[] arr1 = new Long[]{1L, 3L, 5L, 6L};
        Long[] arr2 = new Long[]{1L, 3L, 2L, 4L};

        System.out.println("----------并集------------");
        Long[] andArr = getAndArr(arr1, arr2);
        for (Long a : andArr) {
            System.out.println("=>"+a);
        }
        System.out.println("----------交集------------");
        Long[] intersectArr = getIntersect(arr1, arr2);
        for (Long a : intersectArr) {
            System.out.println("=>"+a);
        }
        System.out.println("----------差集(相对补集, 绝对补集)------------");
        Long[] subtractionArr = getSubtraction(arr1, arr2);
        for (Long a : subtractionArr) {
            System.out.println("=>"+a);
        }

        System.out.println("----------差集2------------");
        String[] a1 = {"a", "b", "c"};
        String[] a2 = {"a", "d", "e"};
        List<String> list = Subtraction2(a1, a2);
        for (String a : list) {
            System.out.println("==>"+a);
        }
    }



    /**
     * 求差集(相对补集, 绝对补集)
     * minuendArr - subArr
     **/
    public static Long[] getSubtraction(Long[] subArr, Long[] minuendArr) {
        LinkedList<Long> list = new LinkedList<>();
        Set<Long> set = new HashSet<>(Arrays.asList(subArr)); // 减数转为Set
        for (Long b : minuendArr) { // 遍历被减数
            if (!set.contains(b)) {
                list.add(b);    // 累加与减数不同的元素
            }
        }
        Long[] result = {};
        return list.toArray(result);
    }

    /**
     * 差集, 默认:大-小
     **/
    public static <T> T[] Subtraction(T[] arr1, T[] arr2) {
        List<T> result = null;
//        result.toArray(T[10]);
        return null;
    }

    public static <T> List<T> Subtraction2(T[] arr1, T[] arr2) {
        int n1 = arr1.length;
        int n2 = arr2.length;
        T[] longArr = arr1;
        T[] shortArr = arr2;
        if (n1 < n2) {
            longArr = arr2;
            shortArr = arr1;
        }

        // 将较长的数组转为Set
        HashSet<T> set = new HashSet<>(Arrays.asList(longArr));
        // 遍历较短的数组
        for (T t : shortArr) {
            if (set.contains(t)) {
                set.remove(t);
            } else {
                set.add(t);
            }
        }
        return new ArrayList<>(set);
    }

    /**
     * 求交集
     **/
    public static Long[] getIntersect(Long[] arr1, Long[] arr2) {
        List<Long> rs = new ArrayList<>();
        Long[] longArr;
        Long[] shortArr;
        int n1 = arr1.length;
        int n2 = arr2.length;
        if (n1 < n2) {
            longArr = arr2;
            shortArr = arr1;
        } else {
            longArr = arr1;
            shortArr = arr2;
        }
        // 将较长的数组转为set
        Set<Long> set = new HashSet<>(Arrays.asList(longArr));

        // 遍历较短的数组
        for (Long a : shortArr) {
            if (!set.contains(a)) {
                continue;
            } else {
                rs.add(a);
            }
        }
//        rs.removeAll(Collections.singleton(null));// 去除List 的null
        Long[] resType = {};
        return rs.toArray(resType);
    }

    /**
     * 求并集
     **/
    private static Long[] getAndArr(Long[] arr1, Long[] arr2) {
        // 将数组转换为set集合
        Set<Long> set1 = new HashSet<>(Arrays.asList(arr1));
        Set<Long> set2 = new HashSet<>(Arrays.asList(arr2));
        // 合并两个集合
        set1.addAll(set2);
        Long[] arr = {};
        return set1.toArray(arr);
    }
}
