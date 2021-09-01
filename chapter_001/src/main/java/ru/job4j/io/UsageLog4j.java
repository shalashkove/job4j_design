package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        int i = 0;
        long l = Long.valueOf(Long.MAX_VALUE);
        byte b = 0x11;
        short s = -10000;
        char c = 'd';
        float f = 3.5f;
        double d = 1.0;
        boolean bln = true;
        LOG.debug("Primitive types : int : {}, long : {}, byte : {}, short : {}, "
                + "char : {}, float : {}, double : {}, boolean : {}", i, l, b, s, c, f, d, bln);
    }
}
