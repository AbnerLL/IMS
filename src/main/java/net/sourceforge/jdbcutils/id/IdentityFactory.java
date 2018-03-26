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
     * ��þ����ʵ��
     * @return Identity
     */
    public static Identity getInstance() {
        if (identity == null)
            initialize();
        return identity;
    }

    /**
     * ����������
     */
    public static void refresh() {
        identity = null;
        initialize();
    }

    //��ʼ���ľ�̬ģʽ
    protected static void initialize() {
        synchronized (lock) {
            if (identity == null)
                identity = DefaultIdentity.getInstance();
        }
    }

}
