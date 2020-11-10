package cn.itcast.util;
/**
 * 
 * JDBC工具類
 *
 */

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.sql.DataSource;

import com.alibaba.druid.pool.DruidAbstractDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;

public class JDBCUtils {

	public static DataSource ds;
	
	static {
		try {
		//1.加載配置文件
		Properties pro=new Properties();
		//2.使用ClassLoader加載配置文件，獲取字節輸入流
		InputStream is= JDBCUtils.class.getClassLoader().getResourceAsStream("config.properties");
		
			pro.load(is);
		//初始化連接池對象
			ds=DruidDataSourceFactory.createDataSource(pro);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static DataSource getDs() {
		return ds;
	}

	public static void setDs(DataSource ds) {
		JDBCUtils.ds = ds;
	}
	
}
