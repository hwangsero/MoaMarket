package market.util;

import java.sql.Connection;
import java.sql.Statement;

public class DBClose {
	
	public static void close(Statement stmt, Connection conn) {
		close(stmt);
		close(conn);
	}

	public static void close(Statement stmt) {
		if(stmt != null) {
			try {
				stmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void close(Connection conn) {
		if(conn != null) {
			try {
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
