package net.sourceforge.access;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.healthmarketscience.jackcess.Column;
import com.healthmarketscience.jackcess.ColumnBuilder;
import com.healthmarketscience.jackcess.Database;
import com.healthmarketscience.jackcess.Table;
import com.healthmarketscience.jackcess.TableBuilder;

public class AccessUtils {
	
	private static Map<String,String> gasMap=new HashMap();
	
    static{
    	gasMap.put("未调查", "");
    	gasMap.put("柴油", "0");
    	gasMap.put("汽油", "1");
    	gasMap.put("液化石油气", "4");
    	gasMap.put("液化石油气LPG", "4");
    	gasMap.put("天然气", "5");
    	gasMap.put("压缩天然气CNG", "5");
    	gasMap.put("乙醇汽油", "6");
    	gasMap.put("氢燃料", "7");
    	gasMap.put("生物柴油", "8");
    	gasMap.put("液化天然气", "9");
    	gasMap.put("其它燃料类型", "-1");
    }
    
    
    public static String[] transformDPNAME(String dpname){
    	if(dpname==null||"".equals(dpname.trim())){
    		return new String[]{"","",""};
    	}
    	String[] result=new String[3];
    	try{
    		if(true){
        		StringBuilder fuleType=new StringBuilder("");
        		StringBuilder gasonlineType=new StringBuilder("");
        		StringBuilder egType=new StringBuilder("");
        		String[] tempArray=dpname.split("\\|");
        		System.out.println(Arrays.toString(tempArray));
        		for(String str:tempArray){
        			if(dpname==null||"".equals(dpname.trim())){
        				continue;
        			}
        			if(gasMap.get(str)!=null&&fuleType.indexOf(gasMap.get(str))==-1){
        				fuleType.append(gasMap.get(str)+"|");
        			}else if(gasMap.get(str.substring(0, 2))!=null&&fuleType.indexOf(gasMap.get(str.substring(0, 2)))==-1){
        				fuleType.append(gasMap.get(str.substring(0, 2))+"|");
        				String[] tempArray2=str.substring(3).split("\\#");
        				for(String str2:tempArray2){
        					if(gasonlineType.indexOf(str2)==-1){
        						gasonlineType.append(str2+"|");
        	    			}
        				}
        			}else if(gasMap.get(str.substring(0, 4))!=null&&fuleType.indexOf(gasMap.get(str.substring(0, 4)))==-1){
        				fuleType.append(gasMap.get(str.substring(0, 4))+"|");
        				String[] tempArray2=str.substring(5).split("\\#");
        				for(String str2:tempArray2){
        					if(egType.indexOf(str2)==-1){
        						egType.append(str2+"|");
        	    			}
        				}
        			}
        		}
        		if(fuleType.indexOf("1")==-1){
        			gasonlineType=new StringBuilder("");
        		}
        		if(fuleType.indexOf("6")==-1){
        			egType=new StringBuilder("");
        		}
        		result[0]=fuleType.toString().length()>0?fuleType.toString().substring(0, fuleType.toString().length()-1):"";
        		result[1]=gasonlineType.toString().length()>0?gasonlineType.toString().substring(0, gasonlineType.toString().length()-1):"";
        		result[2]=egType.toString().length()>0?egType.toString().substring(0, egType.toString().length()-1):"";
        	}else{
        		return new String[]{"转换失败","转换失败","转换失败"};
        	}
    	}catch(Exception e){
    		return new String[]{"转换失败","转换失败","转换失败"};
    	}
    	System.out.println(Arrays.toString(result));
    	return result;
    }
	
	public static void gasMethod(String fileName) throws SQLException{
		try {
			Database db=Database.open(new File(fileName));
			Set<String> tables=db.getTableNames();
			Iterator<String> it=tables.iterator();
			while(it.hasNext()){
				
				Table table=db.getTable(it.next());
				System.out.println("开始分析表："+table.getName());
//				System.out.println(table.display());
				List<Column> columnList=table.getColumns();
				boolean addFlag=false;
				for(Column column:columnList){
					if(column.getName().equals("DPNAME")){
						addFlag=true;
						break;
					}
				}
				if(addFlag){
					
					TableBuilder tableBilder=new TableBuilder(getUnifyName(table.getName(),tables));
					for(Column column:columnList){
						tableBilder.addColumn(column);
					}
					tableBilder.addColumn(new ColumnBuilder("fuelType").setSQLType(Types.VARCHAR).toColumn());
					tableBilder.addColumn(new ColumnBuilder("gasonlineType").setSQLType(Types.VARCHAR).toColumn());
					tableBilder.addColumn(new ColumnBuilder("egType").setSQLType(Types.VARCHAR).toColumn());
					
					Table copyOfTable=tableBilder.toTable(db);
					String[] strArray;
					for (Map<String, Object> row : table) {
						strArray=transformDPNAME((String)row.get("DPNAME"));
						row.put("fuelType", strArray[0]);
						row.put("gasonlineType", strArray[1]);
						row.put("egType", strArray[2]);
						
						copyOfTable.addRow(row.values().toArray());
					}
					
					db.flush();
				}
			}
			db.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 获得唯一副本Table名
	 * 
	 * @param table
	 * @param tables
	 * @return
	 */
	public static String getUnifyName(String table,Set<String> tables){
		
		String name = table+"_";
        int i = 1;
        String newName;
        do {
            newName = name + new Integer(i);
            if (!tables.contains(newName)) {
                return newName;
            }
            i++;
        } while (true);
		
	}
	
	
	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		String fileName="D:\\qb.accdb";
		
		Database db=Database.open(new File(fileName));
		Set<String> tables=db.getTableNames();
		Iterator<String> it=tables.iterator();
		while(it.hasNext()){
			
			Table table=db.getTable(it.next());
			System.out.println("开始分析表："+table.getName());
//			System.out.println(table.display());
			List<Column> columnList=table.getColumns();
			boolean addFlag=false;
			for(Column column:columnList){
				if(column.getName().equals("DPNAME")){
					addFlag=true;
					break;
				}
			}
		}
	}

}
