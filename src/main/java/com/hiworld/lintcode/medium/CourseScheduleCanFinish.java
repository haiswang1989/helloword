package com.hiworld.lintcode.medium;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 问题：课程安排
 * 描述：有n们课程编号冲0-(n-1),有些课程需要先上一些前置课程,比如:上编号为0的课程需要先上编号为1的课程,表示为(0,1)
 * 给你总的课程数以及依赖关系,判断是否可以完成课程
 * 
 * 样例：
 * n=2,依赖关系[[0,1]] 返回true
 * n=2,依赖关系[[1,0],[0,1]] 返回false
 * 
 * 
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2017年8月30日 上午11:32:12
 */
public class CourseScheduleCanFinish {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        int[][] prerequisites = {{0,1},{1,2},{2,3}};
        
        CourseScheduleCanFinish courseScheduleCanFinish = new CourseScheduleCanFinish();
        boolean canFinish = courseScheduleCanFinish.canFinish(4, prerequisites);
        System.out.println(canFinish);
    }
    
    /**
     * @param numCourses a total of n courses
     * @param prerequisites a list of prerequisite pairs
     * @return true if can finish all courses or false
     */
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // Write your code here
        return topologicalSort(numCourses, prerequisites);
    }
    
    /*******************************自己的方式(一层一层的往下,如果出现了环那么就出现了相互依赖)*******************************/
    /**
     * 
     * @param numCourses
     * @param prerequisites
     * @return
     */
    public boolean solution_1(int numCourses, int[][] prerequisites) {
        int length = prerequisites.length;
        HashMap<Integer, Integer> course2Precourse = new HashMap<>();
        
        for(int i=0; i<length; i++) {
            int course = prerequisites[i][0]; //课程
            int precourse = prerequisites[i][1]; //依赖课程
            
            if(course == precourse) { //如果课程和依赖课程编号一样,那么就已经出现了环
                return false;
            }
            
            course2Precourse.put(course, precourse); 
            Integer childPrecourse = course2Precourse.get(precourse); //依赖的课程继续往下获取依赖关系
            while(null!=childPrecourse) {
                course2Precourse.put(course, childPrecourse);
                if(course == childPrecourse) { //如果
                    return false;
                } else {
                    childPrecourse = course2Precourse.get(childPrecourse);
                } 
            }
        }
        
        return true;
    }
    
    
    /**
     * 拓扑排序(不能被AC)
     * @param numCourses
     * @param prerequisites
     * @return
     */
    public boolean topologicalSort(int numCourses, int[][] prerequisites) {
        int[] map = new int[numCourses];
        int length = prerequisites.length;
        //map的索引:为课程的编号
        //map的值: 为被前置的次数,0：表示没有课程依赖他 n:表示其他课程依赖他的次数
        for(int i=0; i<length; i++) { 
            ++map[prerequisites[i][1]];
        }
        
        //没有被前置的课程的编号
        Queue<Integer> notPre = new LinkedList<>();
        for(int i=0; i<map.length; i++) {
            if(map[i] == 0) {
                notPre.add(i); //所有"非前置课程"
            }
        }
        
        int count = notPre.size();
        while(!notPre.isEmpty()) {
            //获取一个没有被前置的课程编号
            Integer notPreNum = notPre.remove();
            for(int i=0; i<length; i++) {
                //找到该课程
                if(prerequisites[i][0] == notPreNum) {
                    //将该课程的"前置课程"的依赖次数减一
                    --map[prerequisites[i][1]];
                    if(map[prerequisites[i][1]] == 0) { //如果该"前置课程"的依赖次数变为0了,
                        notPre.offer(prerequisites[i][1]);
                        count++;
                    }
                    
                    break;
                }
            }
        }
        
        return count == numCourses;
    }  
}
