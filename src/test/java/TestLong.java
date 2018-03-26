import com.navinfo.core.entity.SysRole;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by luozhihui on 2017/10/27.
 */
public class TestLong {

    public static void main(String[] args){
//        String maxId="2fb454ba-782f-430a-a2b8-c3628ff10506";
//        System.out.print(Arrays.asList(maxId.split(",")));
//        List<SysRole> sysRoleList=new ArrayList();
//        SysRole role1=new SysRole();
//        role1.setRoleId("1001");
//        SysRole role2=new SysRole();
//        role2.setRoleId("1001");
//        sysRoleList.add(role1);
//        sysRoleList.add(role2);
//        Set<SysRole> sysRoleSet=new HashSet<SysRole>();
//        sysRoleSet.addAll(sysRoleList);
//        System.out.print(sysRoleSet.size());
        SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmssSSSSSS");

        System.out.println(sdf.format(new Date()));
    }
}
