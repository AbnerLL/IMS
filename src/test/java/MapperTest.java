import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.navinfo.IMS.dao.EmpMapper;
import com.navinfo.IMS.entity.Emp;

public class MapperTest {
	@Test
	public void findAll() throws Exception{
		//初始化IOC容器
		ApplicationContext ioc=new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		EmpMapper empMapper=ioc.getBean(EmpMapper.class);
		System.out.println(empMapper.selectByPrimaryKey("01722").toString());
		
		//插入数据
//		Emp emp=new Emp();
//		emp.setEmpId("1001003");
//		emp.setEmpName("罗智慧");
//		empMapper.insertSelective(emp);
	}
}
