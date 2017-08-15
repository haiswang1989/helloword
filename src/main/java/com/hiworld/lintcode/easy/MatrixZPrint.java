package com.hiworld.lintcode.easy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Stack;

/**
 * 问题：矩阵的之字型遍历 
 * 描述：给你一个包含 m x n 个元素的矩阵 (m 行, n 列), 求该矩阵的之字型遍历。
 * 
 * 样例：
 * 1, 2, 3, 4
 * 5, 6, 7, 8
 * 9, 10, 11, 12
 * 
 * 返回[1, 2, 5, 9, 6, 3, 4, 7, 10, 11, 8, 12]
 * 
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2017年7月27日 下午1:36:08
 */
public class MatrixZPrint {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        int[][] matrix = {{1, 2, 3, 4},{5, 6, 7, 8},{9, 10, 11, 12}};
        
        MatrixZPrint print = new MatrixZPrint();
        System.out.println(Arrays.toString(print.printZMatrix_1(matrix)));
        
    }
    
    /****************************我的走法,但是比较搓****************************/
    
    public int[] printZMatrix(int[][] matrix) {
        if(null == matrix) {
            return new int[0];
        }
        
        int length = matrix.length; //长度 
        int breadth = matrix[0].length; //宽度 
        
        int[] ret = new int[length * breadth];
        
        List<MatrixNode> currentHierarchy = new ArrayList<>();
        List<MatrixNode> nextHierarchy = new ArrayList<>();
        
        currentHierarchy.add(new MatrixNode(0,0));
        
        boolean flag = true;
        
        int index = 0;
        while(0!=currentHierarchy.size()) {
            for (MatrixNode matrixNode : currentHierarchy) {
                int x = matrixNode.x;
                int y = matrixNode.y;
                
                //纵向结点
                MatrixNode downNode = getDownNode(x, y);
                putIfLegal(downNode, length, breadth, nextHierarchy);
                
                //横向结点
                MatrixNode crosswiseNode = getCrosswiseNode(x, y);
                putIfLegal(crosswiseNode, length, breadth, nextHierarchy);
            }
            
            if(flag) { //正向遍历
                for (MatrixNode matrixNode : currentHierarchy) {
                    ret[index++] = matrix[matrixNode.x][matrixNode.y];
                }
            } else { //反向遍历
                int size = currentHierarchy.size();
                for(int i=size-1; i>=0; --i) {
                    MatrixNode matrixNode = currentHierarchy.get(i);
                    ret[index++] = matrix[matrixNode.x][matrixNode.y];
                }
            }
            
            flag = !flag; //调整方向
            
            List<MatrixNode> tmp = currentHierarchy;
            currentHierarchy = nextHierarchy;
            nextHierarchy = tmp;
            nextHierarchy.clear();
        }
        
        return ret;
    }
    
    public MatrixNode getDownNode(int x, int y) {
        return new MatrixNode(x+1, y);
    }
    
    public MatrixNode getCrosswiseNode(int x, int y) {
        return new MatrixNode(x, y+1);
    }
    
    public void putIfLegal(MatrixNode node, int length, int breadth, List<MatrixNode> list) {
        if(node.x < length && node.y < breadth && !list.contains(node)) {
            list.add(node);
        }
    }
    
    class MatrixNode {
        public int x;
        public int y;
        
        public MatrixNode(int x, int y) {
            this.x = x;
            this.y = y;
        }
        
        @Override
        public boolean equals(Object obj) {
            if(obj instanceof MatrixNode) {
                MatrixNode input = (MatrixNode)obj;
                return input.x==this.x && input.y==this.y;
            }
            
            return false;
        }
        
        @Override
        public String toString() {
            return "["+x+":"+y+"]";
        }
    }
    
    /************************网上看到的解决方案*************************/
    public int[] printZMatrix_1(int[][] matrix) {
        if(null==matrix) {
            return new int[0];
        }
        
        int length = matrix.length; //长度 
        int breadth = matrix[0].length; //宽度 
        
        boolean isUp = true;
        int x = 0;
        int y = 0;
        
        int newX = 0;
        int newY = 0;
        
        int[] ret = new int[length * breadth]; 
        int index = 0;
        
        while(true) {
            ret[index++] = matrix[x][y];
            
            if(isUp) { //向上,边界优先横着,再向下
                newX = x - 1;
                newY = y + 1;
                if(!isLegal(newX, newY, length, breadth)) {
                    isUp = !isUp;
                    newX = x; //优先横着
                    newY = y + 1;
                    if(!isLegal(newX, newY, length, breadth)) {
                        newX = x+1; //横着没有,在往下
                        newY = y;
                        if(!isLegal(newX, newY, length, breadth)) {
                            break; //如果移动结束,没有合法的结点,那么就跳出,遍历结束
                        }
                    }
                }
            } else { //向下,边界优先向下,然后再横着
                newX = x+1;
                newY = y-1;
                if(!isLegal(newX, newY, length, breadth)) {
                    isUp = !isUp;
                    newX = x+1; //优先向下
                    newY = y;
                    if(!isLegal(newX, newY, length, breadth)) {
                        newX = x; //向下没有就横着
                        newY = y+1;
                        if(!isLegal(newX, newY, length, breadth)) {
                            break; //如果移动结束,没有合法的结点,那么就跳出,遍历结束
                        }
                    }
                }
            }
            
            x = newX;
            y = newY;
        }
        
        return ret;
    }
    
    boolean isLegal(int x, int y, int length, int breadth) {
        return x<length && x>=0 && y<breadth && y>=0;
    }
}
