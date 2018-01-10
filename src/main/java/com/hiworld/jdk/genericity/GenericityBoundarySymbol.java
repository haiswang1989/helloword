package com.hiworld.jdk.genericity;

/**
 * 泛型 边界符
 * 
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2018年1月10日 下午3:18:30
 */
public class GenericityBoundarySymbol {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Integer[] anArray = {1,2,3,4,5,6};
        int count = GenericityBoundarySymbolStaticClass.countGreaterThan(anArray, 4);
        System.out.println("count : " + count);
        
        Double[] anDoubleArray = {1.1,2.2,3.3,4.4,5.5,6.6};
        count = GenericityBoundarySymbolStaticClass.countGreaterThan(anDoubleArray, 4.0);
        System.out.println("count : " + count);
    }
    
}

/**
 * 
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2018年1月10日 下午3:31:24
 */
class GenericityBoundarySymbolStaticClass {
    
//    public static <T> int countGreaterThan(T[] anArray, T ele) {
//        
//        int count = 0;
//        for (T e : anArray) {
//            if(e > ele) { //这边会编译失败,除了java的8大基础类型,其他的类型不一定能够使用">"来比较的
//                count++;
//            }
//        }
//        
//        return count;
//    }
    
    /**
     * <T extends Comparable<T>> 的定义就告诉编译器,T都是实现了Comparable接口的类
     * @param anArray
     * @param ele
     * @return
     */
    public static <T extends Comparable<T>> int countGreaterThan(T[] anArray, T ele) {
        int count = 0;
        for (T e : anArray) {
            if(e.compareTo(ele) > 0) {
                count++;
            }
        }
        
        return count;
    }
    
}
