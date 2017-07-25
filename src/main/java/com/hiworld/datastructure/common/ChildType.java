package com.hiworld.datastructure.common;

/**
 * 孩子结点类型
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2017年7月25日 上午10:58:54
 */
public enum ChildType {
    
    LEFT("左孩子"),RIGHT("右孩子");
    
    private String desc;
    
    private ChildType(String desc) {
        this.desc = desc;
    }
    
    public String getDesc() {
        return desc;
    }
}
