package com.hiworld.lintcode.medium;

/**
 * 问题：装最多水的容器
 * 描述：给定 n 个非负整数 a1, a2, ..., an, 每个数代表了坐标中的一个点 (i, ai)。画 n 条垂直线，使得 i 垂直线的两个端点分别为(i, ai)和(i, 0)。找到两条线，使得其与 x 轴共同构成一个容器，以容纳最多水
 * 
 * 样例：
 * 给出[1,3,2], 最大的储水面积是2.
 * 
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2017年8月30日 上午10:36:29
 */
public class MaxArea {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        MaxArea maxArea = new MaxArea();
        int[] heights = {1,3,2};
        int maxAreaVal = maxArea.maxArea(heights);
        System.out.println(maxAreaVal);
    }
    
    /*
     * 假如取i,j时面积最大
     * area = min(height[i],height[j]) * (j - i)
     * 
     * @param heights: a vector of integers
     * @return: an integer
     */
    public int maxArea(int[] heights) {
        // write your code here
        int length = 0;
        if(null==heights || (length=heights.length) <= 1) {
            return 0;
        }
        
        int beginIndex = 0;
        int endIndex = length-1;
        
        int maxArea = 0;
        while(beginIndex < endIndex) {
            int minHeight = Math.min(heights[beginIndex], heights[endIndex]);
            int width = endIndex - beginIndex;
            int area = minHeight * width;
            maxArea = maxArea < area ? area : maxArea;
            
            if(heights[beginIndex] <= heights[endIndex]) { //谁小就丢弃谁
                beginIndex++;
            } else {
                endIndex--;
            }
        } 
        
        return maxArea;
    }
}
