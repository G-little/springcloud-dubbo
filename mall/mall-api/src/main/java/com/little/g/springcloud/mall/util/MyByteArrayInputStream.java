package com.little.g.springcloud.mall.util;

import java.io.ByteArrayInputStream;
import java.io.Serializable;

public class MyByteArrayInputStream extends ByteArrayInputStream implements Serializable {

    public MyByteArrayInputStream(byte[] buf) {
        super(buf);
    }

    public MyByteArrayInputStream(byte[] buf, int offset, int length) {
        super(buf, offset, length);
    }

}
