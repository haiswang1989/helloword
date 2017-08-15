package com.hiworld.lintcode.easy;

import java.util.HashSet;
import java.util.Iterator;

/**
 * 问题：岛屿的个数
 * 描述：给一个01矩阵，求不同的岛屿的个数。0代表海，1代表岛，如果两个1相邻，那么这两个1属于同一个岛。我们只考虑上下左右为相邻
 * 
 * 样例：
 * 
 * [
 * 	[1, 1, 0, 0, 0],
 * 	[0, 1, 0, 0, 1],
 * 	[0, 0, 0, 1, 1],
 * 	[0, 0, 0, 0, 0],
 * 	[0, 0, 0, 0, 1]
 * ]
 * 中有3个岛屿
 * 
 * @author Administrator
 *
 */
public class ArrayNumOfLands {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		boolean[][] grid = {
							{true,true}
						   };
		
		ArrayNumOfLands arrayNumOfLands = new ArrayNumOfLands();
		int count = arrayNumOfLands.newMethod(grid);
		System.out.println("land count : " + count);
	}
	
	public int newMethod(boolean[][] grid) {
	    int yLength = -1;
        int xLength = -1;
        if(null==grid || 0==(yLength=grid.length) || 0==(xLength=grid[0].length)) {
            return 0;
        }
        
        HashSet<Coordinate> hasVisit = new HashSet<>(); //标记已经访问过的结点
        
        int landCount = 0;
        for(int i=0; i<yLength; i++) { //y
            for(int j=0; j<xLength; j++) { //x
                Coordinate coordinate = new Coordinate(j, i);
                if(isLand(coordinate, grid) && !hasVisit.contains(coordinate)) { //是岛屿
                    deepRecusion(coordinate, hasVisit, grid, yLength, xLength);
                    landCount++;
                } else { 
                    hasVisit.add(coordinate);
                }
            }
        }
        
        return landCount;
	}
	
	/**
	 * 
	 * 如果是岛屿,访问他的上下左右
	 * @param coordinate
	 * @param hasVisit
	 * @param grid
	 * @param yLength
	 * @param xLength
	 */
	public void deepRecusion(Coordinate coordinate, HashSet<Coordinate> hasVisit, boolean[][] grid, int yLength, int xLength) {
	    hasVisit.add(coordinate);
	    
	    Coordinate up = coordinate.up();
        Coordinate down = coordinate.down();
        Coordinate left = coordinate.left();
        Coordinate right = coordinate.right();
        
        if(isValid(up, xLength, yLength) && !hasVisit.contains(up) && isLand(up, grid)) {
            deepRecusion(up, hasVisit, grid,  yLength, xLength);
        } 
        
        if(isValid(down, xLength, yLength) && !hasVisit.contains(down) && isLand(down, grid)) {
            deepRecusion(down, hasVisit, grid,  yLength, xLength);
        }
        
        if(isValid(left, xLength, yLength) && !hasVisit.contains(left) && isLand(left, grid)) {
            deepRecusion(left, hasVisit, grid,  yLength, xLength);
        }
        
        if(isValid(right, xLength, yLength) && !hasVisit.contains(right) && isLand(right, grid)) {
            deepRecusion(right, hasVisit, grid, yLength, xLength);
        }
	}
	
	/***********************************************自己的写法*******************************************/
	//不能被AC,有超时的情况
	
	/**
     * @param grid a boolean 2D matrix
     * @return an integer
     */
    public int numIslands(boolean[][] grid) {
        int yLength = -1;
        int xLength = -1;
        if(null==grid || 0==(yLength=grid.length) || 0==(xLength=grid[0].length)) {
            return 0;
        }
        
        Coordinate coordinate = new Coordinate(0,0);
        
        //获取所有的岛屿
        HashSet<Coordinate> allLands = new HashSet<>();
        deepLoop(coordinate, allLands, grid, yLength, xLength);
        
        int landCount = 0;
        
        int size = allLands.size();
        while(0 != size) {
            landCount++;
            Iterator<Coordinate> iter = allLands.iterator();
            Coordinate targetCoordinate = iter.next();
            loopRecusion(targetCoordinate, allLands, grid, yLength, xLength);
            size = allLands.size();
        }
        
        return landCount;
    }
    
    /**
     * 遍历整个结构,获取所有的岛屿 
     * @param coordinate
     * @param allLands
     * @param grid
     * @param yLength
     * @param xLength
     */
    public void deepLoop(Coordinate coordinate, HashSet<Coordinate> allLands, boolean[][] grid, int yLength, int xLength) {
        if(isLand(coordinate, grid)) {
            allLands.add(coordinate);
        }
        
        Coordinate down = coordinate.down();
        Coordinate right = coordinate.right();
        
        if(isValid(down, xLength, yLength)) {
            deepLoop(down, allLands, grid,  yLength, xLength);
        }
        
        if(isValid(right, xLength, yLength)) {
            deepLoop(right, allLands, grid, yLength, xLength);
        }
    }
    
    /**
     * 递归遍历岛屿附近的岛屿
     * @param coordinate
     * @param set
     * @param grid
     * @param yLength
     * @param xLength
     */
    public void loopRecusion(Coordinate coordinate, HashSet<Coordinate> allLands, boolean[][] grid, int yLength, int xLength) {
        //删除岛屿
        allLands.remove(coordinate);
        
        Coordinate up = coordinate.up();
    	Coordinate down = coordinate.down();
    	Coordinate left = coordinate.left();
    	Coordinate right = coordinate.right();
    	
    	if(isValid(up, xLength, yLength) && isLand(up, grid) && allLands.contains(up)) {
            loopRecusion(up, allLands, grid,  yLength, xLength);
        }
    	
        if(isValid(down, xLength, yLength) && isLand(down, grid) && allLands.contains(down)) {
            loopRecusion(down, allLands, grid,  yLength, xLength);
        }
        
        if(isValid(left, xLength, yLength) && isLand(left, grid) && allLands.contains(left)) {
            loopRecusion(left, allLands, grid,  yLength, xLength);
        }
        
        if(isValid(right, xLength, yLength) && isLand(right, grid) && allLands.contains(right)) {
            loopRecusion(right, allLands, grid, yLength, xLength);
        }
    }
    
    /**
     * 判断时岛,还是海
     * @param coordinate
     * @param grid
     * @return
     */
    public boolean isLand(Coordinate coordinate, boolean[][] grid) {
    	return grid[coordinate.y][coordinate.x]; //这边搞的比较奇怪
    }
    
    /**
     * 判断坐标是否合法
     * @param coordinate
     * @param xLength
     * @param yLength
     * @return
     */
    boolean isValid(Coordinate coordinate, int xLength, int yLength) {
    	if(coordinate.x < 0 || coordinate.x >= xLength || coordinate.y < 0 || coordinate.y >= yLength) {
    		return false;
    	}
    	
    	return true;
    }
}

/**
 * 坐标
 * @author Administrator
 */
class Coordinate {
	public int x;
	public int y;
	
	public Coordinate(int x, int y) {
		this.x = x;
		this.y = y;
	} 
	
	public Coordinate up() {
		return new Coordinate(x, y-1);
	}
	
	public Coordinate down() {
		return new Coordinate(x, y+1);
	}
	
	public Coordinate left() {
		return new Coordinate(x-1, y);
	}
	
	public Coordinate right() {
		return new Coordinate(x+1, y);
	}
	
	public Coordinate downFirst() {
		return new Coordinate(0,y+1);
	}
	
	@Override
	public boolean equals(Object obj) {
		Coordinate input = (Coordinate)obj;
		return this.x==input.x && this.y == input.y;
	}
	
	@Override
	public int hashCode() { //这个写的比较随意
		return x+y;
	}
}



