package com.hiworld.jdk.jvm;

/**
 * 测试伪共享
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2017年6月20日 下午4:45:00
 */
public class FalseSharing implements Runnable {
    //测试线程数
    public final static int NUM_THREAD = 4;
    //更新的long值,和线程数匹配,一个线程对应一个VolatileLong
    private static VolatileLong[] longs = new VolatileLong[]{new VolatileLong(), new VolatileLong(), new VolatileLong(), new VolatileLong()};
    //每个线程更新的对应的VolatileLong
    private int index;
    public FalseSharing(int index) {
        this.index = index;
    }
    public static void main(String[] args) throws Exception {
        System.out.println("start...");
        long tt1 = System.currentTimeMillis();
        Thread t1 = new Thread(new FalseSharing(0));
        Thread t2 = new Thread(new FalseSharing(1));
        Thread t3 = new Thread(new FalseSharing(2));
        Thread t4 = new Thread(new FalseSharing(3));
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t1.join();
        t2.join();
        t3.join();
        t4.join();
        
        System.out.println("use " + (System.currentTimeMillis() - tt1) + " ms");
    }
    @Override
    public void run() {
        long loopCount = 500000000l;
        long currLoopCount = 0l;
        while(loopCount != ++currLoopCount) {
            longs[index].value = currLoopCount;
        }
    }
}
class VolatileLong {
    public volatile long value = 0l;
    //禁止伪共享,缓存行填充
    public long l1,l2,l3,l4,l5,l6;
}