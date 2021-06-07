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
		        
			//��һ�� ���û�����Ϣ���������ӻ�����Ϣ������driver������
			pool = new BasicDataSource();
			pool.setDriverClassName("com.mysql.jdbc.Driver");
			
			//����url
			pool.setUrl("jdbc:mysql://8.131.245.231:3306/interface?characterEncoding=utf-8");
			//�����˺�
			pool.setUsername("root");
			//��������
			pool.setPassword("123456");
			
			//�ڶ��������ݿ���������Ϻ������,
			//����10���߳�,Ĭ��ֵ0
			pool.setInitialSize(10);
			//������С���У�Ĭ��ֵ0
			pool.setMinIdle(20);
			//���������У�Ĭ��ֵ8
			pool.setMaxIdle(30);
			//����������������Ĭ��ֵ8
			pool.setMaxActive(40);
			
			//������ ������\�黹�����������
			//������ʱ��Ĭ��ֵ��-1������һֱ��,����2000����ȴ�����
			pool.setMaxWait(2000);
			//�ڽ�����ǰ�����м�����ӵ���Ч�ԣ�Ĭ����true�������true,��ֱ��û����û��һ�μ��һ�Σ�̫�˷���Դ��
			//��Ϊfalse,������飬��ʡʱ��
			pool.setTestOnBorrow(false);
			//��ִ��sql��黹���̣߳��費��Ҫ�����Ч�ԣ�Ĭ��ֵ��false
			pool.setTestOnReturn(false);
							
			//��������,�Ƿ����Ԥ����
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
			//��������
			ps.addBatch();
			
		}
		int[] count = ps.executeBatch();
		close(con,ps,null);
		return count;
	}
	
	//ֱ�Ӵ���һ��sql��ѯ���
	public static void testSqlQuery(String sql) throws Exception{
		//�����ݿ⽨�����ӣ�����url���˺ţ�����
				Connection connection = getconnection();
				
				
				//��sql�洢�������У�����pps�ᷢ�͵����ݿ��У� �����ɾ�ģ���Ϊ��ѯֻ�ǲ飬����Ӱ�����ݿ��������
				PreparedStatement preparedstatement = connection.prepareStatement(sql);
				//���õ�һ���ʺŵ�ֵ,��һ��1�������һ���ʺŵ��±꣬�ڶ���1���ʺŵ�ֵ  int����

				//ֻ��ѯ
				ResultSet rs = preparedstatement.executeQuery();
				ResultSetMetaData rsmd =rs.getMetaData();
				int colum = rsmd.getColumnCount();
				
				while(rs.next()){
					
					for(int a=1;a<=colum;a++){
						
						String column = rsmd.getColumnName(a);
						Object columnvalue = rs.getObject(column);
						System.out.println(column+":  "+columnvalue+"\n");
					
						
					}
					System.out.println("*************���н���**********************************");
				}
				close(connection,preparedstatement,rs);
	}
	
	
	
	//�˷��������õ���õİ�ȫ���������sqlδ���в������������ע��©��
		public static List<AutoLog> paramsqlquery(AutoLog autologparam,String sql) throws Exception{
			
			//�����ݿ⽨�����ӣ�����url���˺ţ�����
			Connection connection = getconnection();
			
			
			//��sql�洢�������У�����pps�ᷢ�͵����ݿ��У� �����ɾ�ģ���Ϊ��ѯֻ�ǲ飬����Ӱ�����ݿ��������
			PreparedStatement preparedstatement = connection.prepareStatement(sql);
			//���õ�һ���ʺŵ�ֵ,��һ��1�������һ���ʺŵ��±꣬�ڶ���1���ʺŵ�ֵ  int����
			preparedstatement.setInt(1, autologparam.getId());
			preparedstatement.setString(2, autologparam.getTestCase());
			//ֻ��ѯ
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
		
		
		//�黹����
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
