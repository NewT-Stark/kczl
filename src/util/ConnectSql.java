package util;

import java.sql.*;
import java.util.ArrayList;
public class ConnectSql {
	
	static ArrayList<Connection> list = new ArrayList<Connection>();

	public synchronized Connection getConn() {
		Connection con = null;
		if (list.size() > 0) {
			return list.remove(0);
		}
		else {
			for (int i = 0; i < 5; i++) {
				try {
					Class.forName("com.mysql.jdbc.Driver");
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
				try {
					con = DriverManager.getConnection(
							"jdbc:mysql://localhost:3306/zzxx?characterEncoding=utf-8&useSSL=false","root","123");
					list.add(con);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

		}
		return list.remove(0);
	}

	public void close(ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void close(PreparedStatement pst) {
		if (pst != null) {
			try {
				pst.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public synchronized void close(Connection con) {
		if (con != null)
			list.add(con);
	}

	public void close(ResultSet rs, PreparedStatement ps, Connection con) {
		close(rs);
		close(ps);
		close(con);
	}

}