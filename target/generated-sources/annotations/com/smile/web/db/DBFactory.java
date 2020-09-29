/*
 * 주제(Subject): Board Project (Paging) - Maven with Oracle 19, HikariCP 3.4.2	
 * 생성일자(Create Date): 2020-09-30
 * 저자(Author): Dodo (rabbit.white at daum dot net)
 * 파일명(FileName): DBFactory.java
 * 설명(Description):
 * 
 * 
 */

package com.smile.web.db;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.io.IOException;

import java.io.InputStream;

import java.io.Reader;
import java.util.Properties;
import java.sql.Connection;
import java.sql.SQLException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import oracle.jdbc.pool.OracleDataSource;

public class DBFactory {

	private static final Logger logger = LoggerFactory.getLogger(DBFactory.class);

    private static String CLASSNAME;
    private static String JDBC_URL;
    private static String USERNAME;
    private static String PASSWORD;
    private static String CACHE_PREP_STMTS;
    private static HikariDataSource ds;

    private HikariConfig config;

    
    public DBFactory() {

    	InputStream inputStream;
    	config = new HikariConfig();

       	String resource = "db.properties";
        Properties properties = new Properties();

        try {

        	inputStream = getClass().getClassLoader().getResourceAsStream(resource);
            properties.load(inputStream);

            System.out.println("jdbcurl:" + properties.getProperty("jdbcUrl"));
            System.out.println("className" + properties.getProperty("dataSourceClassName"));

            CLASSNAME = properties.getProperty("dataSourceClassName");
            JDBC_URL = properties.getProperty("jdbcUrl");
            USERNAME = properties.getProperty("dataSource.user");
            PASSWORD = properties.getProperty("dataSource.password");
            CACHE_PREP_STMTS = properties.getProperty("cachePrepStmts");

            config.setDriverClassName(CLASSNAME);
            config.setJdbcUrl( JDBC_URL );
            config.setUsername( USERNAME );
            config.setPassword( PASSWORD );
            
            config.addDataSourceProperty( "cachePrepStmts" , CACHE_PREP_STMTS );
            config.addDataSourceProperty( "prepStmtCacheSize" , "250" );
            config.addDataSourceProperty( "prepStmtCacheSqlLimit" , "2048" );
            
            ds = new HikariDataSource( config );
            
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public Connection getConnection() throws SQLException {
    	
    	/*
        try(Connection con = ds.getConnection()){
            System.out.println("연결상태확인:" + con);
            
            String sql = "SELECT * from board where id > 4 and id < 10";
            
            PreparedStatement pstmt = con.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                System.out.println("진짜 연결되었는가:" + rs.getString(2));
            }
        }
        catch(Exception e) {
        	System.out.println("연결실패확인:" + e.getMessage());
        }
    	*/
    	
        return ds.getConnection();
    }
    
    public void close(Connection conn, PreparedStatement ps, ResultSet rs) {

		if ( rs != null ) {

			try {
				rs.close();
			}
			catch(Exception ex) {
				System.out.println("오류 발생: " + ex);
			}
			
			close(conn, ps);	// Recursive 구조 응용(재귀 함수)
		} // end of if

	}	

	public void close(Connection conn, PreparedStatement ps) {

		if (ps != null ) {

			try {
				ps.close();
			}
			catch(Exception ex) {
				System.out.println("오류 발생: " + ex);
			}
		} // end of if

		if (conn != null ) {
			try {
				conn.close();
			}
			catch(Exception ex) {
				System.out.println("오류 발생: " + ex);
			}
		} // end of if

	}
    
}