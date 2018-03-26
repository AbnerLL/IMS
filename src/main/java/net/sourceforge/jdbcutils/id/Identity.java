package net.sourceforge.jdbcutils.id;

import java.io.Serializable;

/**
 * ����Ψһ��ID�Ľӿ�����
 *
 * @author zhaody@gmail.com
 * @version 1.0
 */
public interface Identity extends Serializable {

    /**
     * Ĭ�ϵľ������ʽ������32λ��Ψһ��ID��
     * ����:3914A8AFA4FD464585F3DD023CDAF5CC
     *
     */
    public static final String STYLE_DEFAULT = "STYLE_DEFAULT";

    /**
     * ���ɵ�ID�ĳ�����40λ�ģ�ΨһID. <br/>
     * ����:2007051670E919EC6F3B4534AFDF4FF420A0CD8A
     */
    public static final String STYLE_DATA_DEFAULT = "STYLE_DATA_DEFAULT";

    /**
     * �������ɵ���󳤶�
     */
    public static final int LEGNTH_MAX=10000;

    /**
     * �������ɵ���С����
     */
    public static final int LEGNTH_MIN=0;
    /**
     * ���Ĭ�ϵ�ΨһID
     * @return String
     */
    public String getIdentity();

    /**
     * ������ʽ���ΨһID
     * @param identity_STYLE String
     * @return String
     */
    public String getIdentity(String identity_STYLE);

    /**
     * ���Ĭ�ϵ�ΨһID������
     * @param length int
     * @return String[]
     */
    public String[] getIdentitys(int length);

    /**
     * ������ʽ���ΨһID���б�
     * @param identity_STYLE String
     * @param length int
     * @return String[]
     */
    public String[] getIdentitys(String identity_STYLE, int length);

}
