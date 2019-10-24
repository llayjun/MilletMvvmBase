package com.millet.z_basic.net.common;

import java.util.HashMap;

/**
 * Created by leo
 * on 2019/7/3.
 */
public class HEADS {
    /**
     * 添加头部信息
     */
    public static HashMap<String, String> login(String token) {
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("Token", token);
        return map;
    }
}
