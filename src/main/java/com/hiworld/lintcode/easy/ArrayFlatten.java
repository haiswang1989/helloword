package com.hiworld.lintcode.easy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

import com.hiworld.lintcode.common.NestedInteger;

/**
 * 问题：给定一个列表，该列表中的每个要素要么是个列表，要么是整数。将其变成一个只包含整数的简单列表
 * 
 * 样例：
 * [1,2,[1,2]]，返回 [1,2,1,2]
 * 
 * [4,[3,[2,[1]]]]，返回 [4,3,2,1]
 * 
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2017年7月14日 上午10:49:29
 */
public class ArrayFlatten {
    
    public static void main(String[] args) {
        
        //[[1,1],2,[1,1]]
        NestedInteger ni1 = new NestedInteger();
        ni1.nestedValue = 1;
        ni1.list = null;
        
        NestedInteger ni2 = new NestedInteger();
        ni2.nestedValue = 2;
        ni2.list = null;
        
        NestedInteger list_1 = new NestedInteger();
        List<NestedInteger> nList_1 = new ArrayList<>();
        nList_1.add(ni1);
        nList_1.add(ni1);
        list_1.list = nList_1;
        
        NestedInteger list_2 = new NestedInteger();
        List<NestedInteger> nList_2 = new ArrayList<>();
        nList_2.add(ni1);
        nList_2.add(ni1);
        list_2.list = nList_2;
        
        
        List<NestedInteger> nList_final = new ArrayList<>();
        nList_final.add(list_1);
        nList_final.add(ni2);
        nList_final.add(list_2);
        
        ArrayFlatten arrayFlatten = new ArrayFlatten();
        List<Integer> ret = arrayFlatten.flatten(nList_final);
        System.out.println(Arrays.toString(ret.toArray()));
    }
    
    public List<Integer> flatten(List<NestedInteger> nestedList) {
        // Write your code here
        if(null==nestedList) {
            return null;
        }
        
//        return flattenWithRecursion(nestedList);
        return flattenWithOutRecursion(nestedList);
    }
    
    /**
     * 非递归的方式实现
     * @param nestedList
     * @return
     */
    public List<Integer> flattenWithOutRecursion(List<NestedInteger> nestedList) {
        List<Integer> ret = new ArrayList<>();
        Stack<Iterator<NestedInteger>> stack = new Stack<>(); //存储迭代器的栈
        Iterator<NestedInteger> iter = nestedList.iterator(); //遍历的时候使用迭代器,因为迭代器知道你遍历到哪个个元素了,这样从栈中pop出来的时候,才可以继续遍历
        while(0!=stack.size() || iter.hasNext()) {
            if(iter.hasNext()) {
                while(iter.hasNext()) {
                    NestedInteger nestedInterger = iter.next();
                    if(nestedInterger.isInteger()) { 
                        ret.add(nestedInterger.getInteger());
                    } else { //如果遇到了新的列表
                        stack.push(iter); //将当前列表压入栈中
                        iter = nestedInterger.getList().iterator(); //遍历当前列表
                        break;
                    }
                }
            } else {
                iter = stack.pop();
            }
        }
        
        return ret;
    }
    
    /**
     * 递归方式
     * @param nestedList
     * @return
     */
    public List<Integer> flattenWithRecursion(List<NestedInteger> nestedList) {
        // Write your code here
        ArrayList<Integer> list = new ArrayList<>();
        for (NestedInteger nestedInteger : nestedList) {
            if(nestedInteger.isInteger()) {
                list.add(nestedInteger.getInteger());
            } else {
                List<Integer> childList = flattenWithRecursion(nestedInteger.getList());
                list.addAll(childList);
            }
        }
        
        return list;
    }
}
