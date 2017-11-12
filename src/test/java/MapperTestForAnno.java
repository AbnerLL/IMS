import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.navinfo.IMS.dao.EmpMapper;

/**
 * 采用spring的单元测试
 * @author luozhihui
 * 1.导入spring的测试jar
 * 2.@ContextConfiguration用来指定spring的配置文件
 * 3.注入要测试的接口文件
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext.xml"})
public class MapperTestForAnno {
	@Autowired
	EmpMapper empMapper;
	
	@Test
	public void findOne(){
		System.out.println(empMapper.selectByPrimaryKey("01722").toString());
	}
}
