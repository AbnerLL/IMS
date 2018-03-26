package net.sourceforge.jdbcutils.id;

import net.sourceforge.jdbcutils.context.FactoryContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public abstract class IdentityFactory implements FactoryContext {
    // log
    private static final Log log = LogFactory.getLog(IdentityFactory.class);

    private IdentityFactory() {}

    private static Identity identity = null;

    /**
     * 获得具体的实现
     * @return Identity
     */
    public static Identity getInstance() {
        if (identity == null)
            initialize();
        return identity;
    }

    /**
     * 工厂的重载
     */
    public static void refresh() {
        identity = null;
        initialize();
    }

    //初始化的静态模式
    protected static void initialize() {
        synchronized (lock) {
            if (identity == null)
                identity = DefaultIdentity.getInstance();
        }
    }

}
