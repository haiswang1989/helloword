package com.hiworld.datastructure.common;

/**
 * 平衡状态
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2017年7月24日 下午5:00:23
 */
public enum ImbalanceType {
    
    LL("左左失衡"), LR("左右失衡"), RR("右右失衡"), RL("右左失衡"), BALANCE("平衡状态");
    
    private String desc;
    
    private ImbalanceType(String _desc) {
        desc = _desc;
    }
    
    public String getDesc() {
        return desc;
    }
}
