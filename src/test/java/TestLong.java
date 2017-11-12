import java.math.BigDecimal;

/**
 * Created by luozhihui on 2017/10/27.
 */
public class TestLong {

    public static void main(String[] args){
        String maxId="12345001";
        BigDecimal bigDecimal=new BigDecimal(maxId);
        System.out.println(bigDecimal.add(new BigDecimal(1)).toString());
    }
}
