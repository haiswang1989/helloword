package com.hiworld.lintcode.easy;

/**
 * 矩阵面积
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2017年7月4日 上午10:00:13
 */
public class Rectangle {
    
    private int width;
    private int height;
    
    public Rectangle(int width, int height) {
        this.width = width;
        this.height = height;
    }
    
    public int getArea() {
        return width * height;
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub

    }

}
