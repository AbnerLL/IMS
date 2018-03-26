package net.sourceforge.jdbcutils.id;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
/**
 * 使用的具体的测试类
 *
 * @author zhaody@gmail.com
 * @version 1.0
 */
public class IdentityTests {

    private static Log log = LogFactory.getLog(IdentityTests.class);

    public static void main(String[] args) {
        Identity identity = IdentityFactory.getInstance();
        long i= System.currentTimeMillis();
        // 获得9999个
        String[] ids = identity.getIdentitys(9999);
        i=System.currentTimeMillis()-i;
        log.info("i="+i);
        log.info(identity.getIdentity(Identity.STYLE_DATA_DEFAULT));
        log.info(identity.getIdentity(Identity.STYLE_DEFAULT));
    }
}
