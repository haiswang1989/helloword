package com.for4.mt;

/**
 * 第K大
 * 
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2018年4月2日 下午5:45:17
 */
public class KLargest {

    public static void main(String[] args) {
        Integer[] inputs = new Integer[]{1,8,9,5,6,3,7,2,10,4};
        int kLargest = 10;
        
        KLargestByQuickSort kLargestByQuickSort = new KLargestByQuickSort(inputs, kLargest);
        int ret = kLargestByQuickSort.kLargest();
        System.out.println(ret);
    }
}


/**
 * 通过"堆排序"实现第K大元素
 * 
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2018年4月9日 上午11:04:44
 */
class KLargestByHeapsort {
    
}


/**
 * 通过快速排序获取第K大元素
 * 
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2018年4月2日 下午6:08:51
 */
class KLargestByQuickSort {
    
    private Integer[] inputs;
    private int kLargest;
    
    public KLargestByQuickSort(Integer[] inputsArgs, int kLargestArgs) {
        this.inputs = inputsArgs;
        this.kLargest = kLargestArgs;
    }
    
    public Integer kLargest() {
        return byQuickSort(inputs, 0, inputs.length-1);
    }
    
    /**
     * 
     * @param input
     * @param fromIndex
     * @param endIndex
     * @return
     */
    public Integer byQuickSort(Integer[] input, int fromIndex, int toIndex) {
        
        //如果范围已经很小了,直接做一个快排
        //当数据量很大的时候,可以调整触发"快排的范围"
        if((toIndex - fromIndex) <= 2) {
            //局部做一个快速排序
            quickSort(input, fromIndex, toIndex);
            //返回第K大元素
            return input[kLargest-1];
        }
        
        if(fromIndex < toIndex) {
            
            int beginIndex = fromIndex;
            int endIndex = toIndex;
            
            int baseIndex = beginIndex;
            int baseVal = input[baseIndex];
            
            while(beginIndex < endIndex) {
                while(beginIndex < endIndex && baseVal < input[beginIndex]) {
                    beginIndex++;
                }
                
                if(beginIndex < endIndex) {
                    input[baseIndex] = input[beginIndex];
                    baseIndex = beginIndex;
                }
                
                while(beginIndex < endIndex && baseVal >= input[endIndex]) {
                    endIndex--;
                }
                
                if(beginIndex < endIndex) {
                    input[baseIndex] = input[endIndex];
                    baseIndex = endIndex;
                }
            }
            
            input[baseIndex] = baseVal;
            
            //从1开始,baseIndex的位置
            int trueBaseIndex = baseIndex + 1;
            if(trueBaseIndex == kLargest) {
                return baseVal;
            } else if(trueBaseIndex > kLargest) {
                //在前半部分
                return byQuickSort(input, fromIndex, baseIndex-1);
            } else {
                //后半部分
                return byQuickSort(input, baseIndex+1, toIndex);
            }
        } else {
            throw new RuntimeException();
        }
    }
    
    /**
     * 快速排序
     * @param input
     * @param fromIndex
     * @param toIndex
     */
    public void quickSort(Integer[] input, int fromIndex, int toIndex) {
        if(fromIndex < toIndex) {
            int beginIndex = fromIndex;
            int endIndex = toIndex;
            
            int baseIndex = beginIndex;
            int baseVal = input[baseIndex];
            
            while(beginIndex < endIndex) {
                while(beginIndex < endIndex && baseVal < input[beginIndex]) {
                    beginIndex++;
                }
                
                if(beginIndex < endIndex) {
                    input[baseIndex] = input[beginIndex];
                    baseIndex = beginIndex;
                }
                
                while(beginIndex < endIndex && baseVal >= input[endIndex]) {
                    endIndex--;
                }
                
                if(beginIndex < endIndex) {
                    input[baseIndex] = input[endIndex];
                    baseIndex = endIndex;
                }
            } 
            
            input[baseIndex] = baseVal;
            quickSort(input, fromIndex, baseIndex-1);
            quickSort(input, baseIndex+1, toIndex);
        }
    }
}
