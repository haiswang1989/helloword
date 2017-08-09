package com.hiworld.lintcode;

import java.util.HashSet;

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
		
		Coordinate c1 = new Coordinate(0, 0);
		Coordinate c2 = new Coordinate(0, 0);
		
		HashSet<Coordinate> set = new HashSet<>();
		set.add(c1);
		System.out.println(set.contains(c2));
		
		boolean[][] grid = {
							{true,true,true,true,true,true},
							{true,false,false,false,false,true},
							{true,false,true,true,false,true},
							{true,false,false,false,false,true},
							{true,true,true,true,true,true}
						   };
		
		ArrayNumOfLands arrayNumOfLands = new ArrayNumOfLands();
		int count = arrayNumOfLands.numIslands(grid);
		System.out.println("land count : " + count);
	}
	
	/**
     * @param grid a boolean 2D matrix
     * @return an integer
     */
    public int numIslands(boolean[][] grid) {
        // Write your code here
    	int yLength = -1;
    	int xLength = -1;
    	if(null==grid || 0==(yLength=grid.length) || 0==(xLength=grid[0].length)) {
    		return 0;
    	}
    	
    	HashSet<Coordinate> set = new HashSet<>();
    	Coordinate loopCoordinate = new Coordinate(0, 0);
    	
    	int landCount = 0;
    	
    	while(true) {
    		if(getValue(loopCoordinate, grid) && !set.contains(loopCoordinate)) {
    			loopRecusion(loopCoordinate, set, grid, yLength, xLength);
    			landCount++;
    		}
    		
    		Coordinate right = loopCoordinate.right();
    		Coordinate downFirst = loopCoordinate.downFirst();
    		
    		if(!isValid(right, xLength, yLength) && !isValid(downFirst, xLength, yLength)) {
    			break;
    		} else if(isValid(right, xLength, yLength)) { //右边
    			loopCoordinate = right;
    		} else { //下边
    			loopCoordinate = downFirst;
    		}
    	}
    	
    	return landCount;
    }
    
    public void loopRecusion(Coordinate coordinate, HashSet<Coordinate> set, boolean[][] grid, int yLength, int xLength) {
    	
    	set.add(coordinate);
    	
    	Coordinate up = coordinate.up();
    	Coordinate down = coordinate.down();
    	Coordinate left = coordinate.left();
    	Coordinate right = coordinate.right();
    	
    	if(isValid(up, xLength, yLength) && getValue(up, grid) && !set.contains(up) ) {
    		loopRecusion(up, set, grid, yLength, xLength);
    	}
    	
    	if(isValid(down, xLength, yLength) && getValue(down, grid) && !set.contains(down)) {
    		loopRecusion(down, set, grid,  yLength, xLength);
    	}
    	
    	if(isValid(left, xLength, yLength) && getValue(left, grid) && !set.contains(left)) {
    		loopRecusion(left, set, grid, yLength, xLength);
    	}
    	
    	if(isValid(right, xLength, yLength) && getValue(right, grid) && !set.contains(right)) {
    		loopRecusion(right, set, grid, yLength, xLength);
    	}
    }
    
    public boolean getValue(Coordinate coordinate, boolean[][] grid) {
    	return grid[coordinate.y][coordinate.x];
    }
    
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
 *
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
	public int hashCode() {
		return x+y;
	}
}



