package com.hiworld.btrace;

import com.sun.btrace.BTraceUtils;
import com.sun.btrace.annotations.OnMethod;

public class BTraceWhoCallSystemGC {
    
    @OnMethod(clazz="java.lang.System", method="gc")
    public static void onSystemGC() {
        BTraceUtils.println("entre system.gc()");
        BTraceUtils.jstack();
    }
    
}
