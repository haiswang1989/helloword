package com.hiworld.jdk.jvm.allotmemoryinstack;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * java -cp . -Xmx3G -Xmn2G -server -XX:-DoEscapeAnalysis AllotMemoryInStack //-XX:-DoEscapeAnalysis 关闭逃逸分析
 * 2:       2000000       32000000  User //2000000个对象全部在堆上分配
 * --------------------------------------------------------------------------------------------------------------
 * java -cp . -Xmx3G -Xmn2G -server AllotMemoryInStack //开启逃逸分析
 * 4:         36216         579456  User //2000000个对象只有36216个对象在对上分配,其他的对象全被"标量化"了
 * --------------------------------------------------------------------------------------------------------------
 * //开启分层编译,默认 -XX:CompileThreshold的参数10000,也就是被执行10000次才会触发JIT优化
 * java -cp . -Xmx3G -Xmn2G -server -XX:-TieredCompilation AllotMemoryInStack 
 * 7:         13719         219504  User
 * ---------------------------------------------------------------------------------------------------------------
 * //设置JIT优化的阈值为5000,有32234个对象在堆上分配
 * java -cp . -Xmx3G -Xmn2G -server -XX:-TieredCompilation -XX:CompileThreshold=5000 AllotMemoryInStack
 * 4:         32234         515744  User
 * ---------------------------------------------------------------------------------------------------------------
 * //设置JIT优化的阈值为2000,有2803个对象在堆上分配
 * java -cp . -Xmx3G -Xmn2G -server -XX:-TieredCompilation -XX:CompileThreshold=2000 AllotMemoryInStack
 * 10:          2803          44848  User
 * ---------------------------------------------------------------------------------------------------------------
 * //设置JIT优化的阈值为500,有82912个对象在堆上分配
 * java -cp . -Xmx3G -Xmn2G -server -XX:-TieredCompilation -XX:CompileThreshold=500 AllotMemoryInStack
 * 2:         82912        1326592  User
 * 
 * 分层编译的XX:CompileThreshold参数好像与"标量化"没有太直接的联系
 * 
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2018年1月5日 上午10:54:02
 */
public class AllotMemoryInStack {

    public static void main(String[] args) throws InterruptedException, IOException {
        int sum = 0;
        int count = 1000000;
        //预热
        for(int i=0; i<count; i++) {
            sum += fun(i);
        }
        
        TimeUnit.SECONDS.sleep(1);
        
        for(int i=0; i<count; i++) {
            sum += fun(i);
        }
        
        System.out.println(sum);
        System.in.read();
    }
    
    private static int fun(int age) {
        User user = new User(age);
        return user.getAge();
    }
}

/**
 * 用户对象
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2018年1月5日 上午10:48:30
 */
class User {
    
    private int age;
    
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public User(int age) {
        this.age = age;
    }
}
