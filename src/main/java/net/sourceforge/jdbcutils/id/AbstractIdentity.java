package net.sourceforge.jdbcutils.id;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public abstract class AbstractIdentity
    implements Identity {
    
    private static Log log;
    
    public AbstractIdentity() {
    }
    
    public String getIdentity() {
        return getIdentity("STYLE_DEFAULT");
    }
    
    public String[] getIdentitys(int length) {
        return getIdentitys("STYLE_DEFAULT", length);
    }
    
    public String[] getIdentitys(String identity_STYLE, int length) {
        if(length < 0 || length > 10000) {
            log.error("生成的请求的长度超长或超短");
            return new String[0];
        }
        String result[] = new String[length];
        for(int i = 0; i < result.length; i++) {
            result[i] = getIdentity(identity_STYLE);
        }
        
        return result;
    }
    
    
    static
    {
        log = LogFactory.getLog(AbstractIdentity.class);
    }
}
