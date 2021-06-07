package com.youceedu.interf.util;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbcp.BasicDataSource;

import com.youceedu.interf.model.AutoLog;

public class DBCPUtil {
	
	private static BasicDataSource pool = null;
	private static String sql = "insert into autolog(testCase,reqType,reqUrl,reqData,expResult,actResult,result,execTime) values(?,?,?,?,?,?,?,?)";
	
	static{
		if(pool==null){
		        
			//第一步 配置基本信息；建立链接基本信息，加载driver驱动类
			pool = new BasicDataSource();
			pool.setDriverClassName("com.mysql.jdbc.Driver");
			
			//设置url
			pool.setUrl("jdbc:mysql://8.131.245.231:3306/interface?characterEncoding=utf-8");
			//设置账号
			pool.setUsername("root");
			//设置密码
			pool.setPassword("123456");
			
			//第二步，数据库池子连接上后的配置,
			//设置10个线程,默认值0
			pool.setInitialSize(10);
			//配置最小空闲，默认值0
			pool.setMinIdle(20);
			//配置最大空闲，默认值8
			pool.setMaxIdle(30);
			//配置最大的连接数，默认值8
			pool.setMaxActive(40);
			
			//第三步 借连接\归还连接相关配置
			//借连接时间默认值是-1，代表一直等,设置2000代表等待两秒
			pool.setMaxWait(2000);
			//在借连接前，自行检查连接的有效性，默认是true（如果是true,和直连没区别，没借一次检查一次，太浪费资源）
			//改为false,代表不检查，节省时间
			pool.setTestOnBorrow(false);
			//再执行sql后归还此线程，需不需要检查有效性，默认值是false
			pool.setTestOnReturn(false);
							
			//其他方法,是否进行预编译
			pool.setPoolPreparedStatements(true);
			}
		}
	
				
	public static synchronized Connection getconnection() throws Exception{
		 return pool.getConnection();			
	}
	
	
	public static int[] paramSqlUpdata(List<AutoLog> list) throws Exception{
		Connection con = getconnection();
		PreparedStatement  ps = con.prepareStatement(sql);
		
		for(AutoLog a:list){
			ps.setString(1, a.getTestCase());
			ps.setString(2, a.getReqType());
			ps.setString(3, a.getReqUrl());
			ps.setString(4, a.getReqData());
			ps.setString(5, a.getExpResult());
			ps.setString(6, a.getActResult());
			ps.setInt(7, a.getResult());
			ps.setString(8, a.getExecTime());
			//批量插入
			ps.addBatch();
			
		}
		int[] count = ps.executeBatch();
		close(con,ps,null);
		return count;
	}
	
	//直接传入一个sql查询语句
	public static void testSqlQuery(String sql) throws Exception{
		//与数据库建立链接，传入url，账号，密码
				Connection connection = getconnection();
				
				
				//把sql存储到此类中，接着pps会发送到数据库中； 针对增删改，因为查询只是查，不会影响数据库里的数据
				PreparedStatement preparedstatement = connection.prepareStatement(sql);
				//设置第一个问号的值,第一个1，代表第一个问号的下标，第二个1是问号的值  int类型

				//只查询
				ResultSet rs = preparedstatement.executeQuery();
				ResultSetMetaData rsmd =rs.getMetaData();
				int colum = rsmd.getColumnCount();
				
				while(rs.next()){
					
					for(int a=1;a<=colum;a++){
						
						String column = rsmd.getColumnName(a);
						Object columnvalue = rs.getObject(column);
						System.out.println(column+":  "+columnvalue+"\n");
					
						
					}
					System.out.println("*************这行结束**********************************");
				}
				close(connection,preparedstatement,rs);
	}
	
	
	
	//此方法开发用的最好的安全方法，如果sql未进行参数化，会出现注入漏洞
		public static List<AutoLog> paramsqlquery(AutoLog autologparam,String sql) throws Exception{
			
			//与数据库建立链接，传入url，账号，密码
			Connection connection = getconnection();
			
			
			//把sql存储到此类中，接着pps会发送到数据库中； 针对增删改，因为查询只是查，不会影响数据库里的数据
			PreparedStatement preparedstatement = connection.prepareStatement(sql);
			//设置第一个问号的值,第一个1，代表第一个问号的下标，第二个1是问号的值  int类型
			preparedstatement.setInt(1, autologparam.getId());
			preparedstatement.setString(2, autologparam.getTestCase());
			//只查询
			ResultSet rs = preparedstatement.executeQuery();
			ResultSetMetaData rsmd =rs.getMetaData();
			int colum = rsmd.getColumnCount();
			
			
			List<AutoLog> list = new ArrayList<AutoLog>();
			while(rs.next()){
				
				AutoLog autolog =AutoLog.class.newInstance();
				
				for(int a=1;a<=colum;a++){
					
					String column = rsmd.getColumnName(a);
					Object columnvalue = rs.getObject(column);
				
					Field f = AutoLog.class.getDeclaredField(column);
					f.setAccessible(true);
					f.set(autolog, columnvalue);
				}
				list.add(autolog);
			}
			close(connection,preparedstatement,rs);
			return list;
		}
		
		
		//归还连接
		public static void close(Connection con,PreparedStatement  ps,ResultSet rs) throws Exception{
			
			if(rs!=null){
				rs.close();
			}
			if(ps!=null){
				ps.close();
			}
			if(con!=null){
				con.close();
			}
			
		}

	public static void main(String[] args)  {
		
	}

}
