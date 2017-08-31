package com.hiworld.lintcode.medium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 问题：安排课程
 * 描述：有n们课程编号冲0-(n-1),有些课程需要先上一些前置课程,比如:上编号为0的课程需要先上编号为1的课程,表示为(0,1)
 * 给你总的课程数以及依赖关系,返回你为了学完所有课程所安排的学习顺序
 * 可能会有多个正确的顺序，你只要返回一种就可以了。如果不可能完成所有课程，返回一个空数组
 * 
 * 样例：
 * n=2, prerequisites = [[1,0]] 返回：[0,1]
 * n=4, prerequisites = [1,0],[2,0],[3,1],[3,2]] 返回:[0,1,2,3] or [0,2,1,3]
 * 
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2017年8月31日 下午3:31:40
 */
public class CourseScheduleFindOrder {

    public static void main(String[] args) {
        // TODO Auto-generated method stub

    }
    
    /**
     * @param numCourses a total of n courses
     * @param prerequisites a list of prerequisite pairs
     * @return the course order
     */
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        // Write your code here
        int[] courses = new int[numCourses]; 
        
        //课程和前置课程的对应关系
        Map<Integer, Integer> course2PreCourse = new HashMap<>();
        
        int length = prerequisites.length;
        for(int i=0; i<length; ++i) {
            course2PreCourse.put(prerequisites[i][0], prerequisites[i][1]);
            int preCourseNum = prerequisites[i][1];
            ++courses[preCourseNum];
        }
        
        //不被依赖的课程编号,可以最后在读
        List<Integer> unPreCourse = new ArrayList<>();
        for(int i=0; i<numCourses; ++i) {
            if(courses[i] == 0) {
                unPreCourse.add(i);
            }
        }
        
        
        return null;
    }
}
