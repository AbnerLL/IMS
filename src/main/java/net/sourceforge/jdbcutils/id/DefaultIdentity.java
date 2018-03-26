package net.sourceforge.jdbcutils.id;

import java.util.Date;

import net.sourceforge.jdbcutils.context.FactoryContext;
import net.sourceforge.jdbcutils.id.impl.UUID;
import net.sourceforge.jdbcutils.id.impl.UUIDFactory;
import net.sourceforge.jdbcutils.utils.DateUtil;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;



public class DefaultIdentity extends AbstractIdentity
    implements FactoryContext {
    
    private static UUIDFactory uuidFactory = null;
    private static DefaultIdentity defaultIdentity = null;
    private static Log log;
    
    private DefaultIdentity() {
    }
    
    protected static void initialize() {
        synchronized(lock) {
            if(defaultIdentity == null) {
                uuidFactory = UUIDFactory.getInstance();
                defaultIdentity = new DefaultIdentity();
            }
        }
    }
    
    public static DefaultIdentity getInstance() {
        if(defaultIdentity == null) {
            initialize();
        }
        return defaultIdentity;
    }
    
    public String getIdentity(String identity_STYLE) {
        String result;
        if(identity_STYLE.equals("STYLE_DEFAULT")) {
            result = do_STYLE_DEFAULT();
        } else
            if(identity_STYLE.equals("STYLE_DATA_DEFAULT")) {
            result = do_STYLE_DATA_DEFAULT();
            } else {
            result = do_STYLE_DEFAULT();
            }
        return result;
    }
    
    private String do_STYLE_DEFAULT() {
        UUID uuid = uuidFactory.generateRandomBasedUUID();
        return uuid.toString().toUpperCase();
    }
    
    private String do_STYLE_DATA_DEFAULT() {
        StringBuffer sb = new StringBuffer();
        sb.append(DateUtil.getString(new Date(), "yyyyMMdd"));
        sb.append(do_STYLE_DEFAULT().toUpperCase());
        return sb.toString();
    }
    
    
    static
    {
        log = LogFactory.getLog(DefaultIdentity.class);
    }
}
