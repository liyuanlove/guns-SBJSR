package com.stylefeng.guns.core.shiroext.kit;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.junit.Test;

@Slf4j
public class ShiroKitTest {

    @Test
    public void md5() {

        ByteSource salt = new Md5Hash("nfukw");
        String md5 = new SimpleHash("MD5", "111111", salt, 2).toString();
        log.info(md5);
    }
}