package net.sourceforge.jdbcutils.id.impl;


// Referenced classes of package org.navwork.frameworkimpl.id.uuidimpl:
//            UUIDFactory, UUID

public class Test {
    
    public Test() {
    }
    
    public static void main(String args[]) {
        long l = System.currentTimeMillis();
        UUIDFactory generator = UUIDFactory.getInstance();
        for(int i = 0; i < 100; i++) {
            UUID uuid = generator.generateRandomBasedUUID();
            String str = uuid.toString().toUpperCase();
            System.out.println("" + str);
        }
        
        l = System.currentTimeMillis() - l;
        System.out.println("l=" + l);
    }
}
