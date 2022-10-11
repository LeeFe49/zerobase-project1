package db;

import java.sql.*;
import java.util.List;

public class LoadDb {
	public void initDb(List<WifiClass> list) {
		String url = "jdbc:mariadb://localhost:3306/projectdb1";
		String dbUserId = "testuser1";
		String dbPassword = "0409";

		try {
			Class.forName("org.mariadb.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;

		try {
			connection = DriverManager.getConnection(url, dbUserId, dbPassword);

			String sql = "insert into info\n"
					+ "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
			
			int affected;
			
			for (int i = 0; i < list.size(); i++) {
				preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setString(1, "");
				preparedStatement.setString(2, list.get(i).getX_SWIFI_MGR_NO());
				preparedStatement.setString(3, list.get(i).getX_SWIFI_WRDOFC());
				preparedStatement.setString(4, list.get(i).getX_SWIFI_ADRES1());
				preparedStatement.setString(5, list.get(i).getX_SWIFI_ADRES2());
				preparedStatement.setString(6, list.get(i).getX_SWIFI_INSTL_FLOOR());
				preparedStatement.setString(7, list.get(i).getX_SWIFI_INSTL_TY());
				preparedStatement.setString(8, list.get(i).getX_SWIFI_INSTL_MBY());
				preparedStatement.setString(9, list.get(i).getX_SWIFI_SVC_SE());
				preparedStatement.setString(10, list.get(i).getX_SWIFI_CMCWR());
				preparedStatement.setString(11, list.get(i).getX_SWIFI_CNSTC_YEAR());
				preparedStatement.setString(12, list.get(i).getX_SWIFI_INOUT_DOOR());
				preparedStatement.setString(13, list.get(i).getX_SWIFI_REMARS3());
				preparedStatement.setString(14, list.get(i).getX_SWIFI_MAIN_NM());
				preparedStatement.setString(15, list.get(i).getLAT());
				preparedStatement.setString(16, list.get(i).getLNT());
				preparedStatement.setString(17, list.get(i).getWORK_DTTM());
				
				affected = preparedStatement.executeUpdate();
				if(affected<0) {
					System.out.println("저장 실패");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null && rs.isClosed()) {
					rs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (rs != null && !preparedStatement.isClosed()) {
					preparedStatement.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (connection != null && !connection.isClosed()) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void dbSelect() {
		String url = "jdbc:mariadb://localhost:3306/projectdb1";
		String dbUserId = "testuser1";
		String dbPassword = "0409";

		// 드라이버로드
		// 커넥션객체 생성
		// 스테이트먼트 객체 생성
		// 쿼리 실행
		// 결과 수행
		// 객체 연결 해제 (close)

		try {
			Class.forName("org.mariadb.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		// Statement statement = null;
		ResultSet rs = null;

		String memberTypeValue = "email";

		try {
			connection = DriverManager.getConnection(url, dbUserId, dbPassword);

			String sql = "select * from history";

			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, memberTypeValue);

			rs = preparedStatement.executeQuery();

			System.out.println("db load success!");

			while (rs.next()) {
				String X_SWIFI_MGR_NO = rs.getString("X_SWIFI_MGR_NO");
				String WORK_DTTM = rs.getString("WORK_DTTM");
				String LAT = rs.getString("LAT");
				String LNT = rs.getString("LNT");

				System.out.println(X_SWIFI_MGR_NO + ", " + WORK_DTTM + ", " + LAT + ", " + LNT);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null && rs.isClosed()) {
					rs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (rs != null && !preparedStatement.isClosed()) {
					preparedStatement.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (connection != null && !connection.isClosed()) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void register(String X_SWIFI_MGR_NO, String WORK_DTTM, String LAT, String LNT) {
		String url = "jdbc:mariadb://localhost:3306/projectdb1";
		String dbUserId = "testuser1";
		String dbPassword = "0409";

		try {
			Class.forName("org.mariadb.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;

		try {
			connection = DriverManager.getConnection(url, dbUserId, dbPassword);

			String sql = "insert into history(X_SWIFI_MGR_NO, WORK_DTTM, LAT, LNT)\n" + "values (?, ?, ?, ?);";

			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, X_SWIFI_MGR_NO);
			preparedStatement.setString(2, WORK_DTTM);
			preparedStatement.setString(3, LAT);
			preparedStatement.setString(4, LNT);

			int affected = preparedStatement.executeUpdate();

			if (affected > 0) {
				System.out.println("저장 성공");
			} else {
				System.out.println("저장 실패");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null && rs.isClosed()) {
					rs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (rs != null && !preparedStatement.isClosed()) {
					preparedStatement.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (connection != null && !connection.isClosed()) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void withdraw(String X_SWIFI_MGR_NO) {

		String url = "jdbc:mariadb://localhost:3306/projectdb1";
		String dbUserId = "testuser1";
		String dbPassword = "0409";

		try {
			Class.forName("org.mariadb.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		// Statement statement = null;
		ResultSet rs = null;

		try {
			connection = DriverManager.getConnection(url, dbUserId, dbPassword);

			String sql = "delete from history\n" + "where X_SWIFI_MGR_NO=?;";

			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, X_SWIFI_MGR_NO);

			int affected = preparedStatement.executeUpdate();

			if (affected > 0) {
				System.out.println("삭제 성공");
			} else {
				System.out.println("삭제 실패");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null && rs.isClosed()) {
					rs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (rs != null && !preparedStatement.isClosed()) {
					preparedStatement.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (connection != null && !connection.isClosed()) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void test(String X_SWIFI_MGR_NO, String WORK_DTTM, String LAT, String LNT) {
		String url = "jdbc:mariadb://localhost:3306/projectdb1";
		String dbUserId = "testuser1";
		String dbPassword = "0409";

		try {
			Class.forName("org.mariadb.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;

		try {
			connection = DriverManager.getConnection(url, dbUserId, dbPassword);

			String sql = "insert into history\n" + "values (?, ?, ?, ?);";

			int affected;
			for (int i = 0; i < 3; i++) {
				preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setString(1, X_SWIFI_MGR_NO + Integer.toString(i));
				preparedStatement.setString(2, WORK_DTTM + Integer.toString(i));
				preparedStatement.setString(3, LAT + Integer.toString(i));
				preparedStatement.setString(4, "");

				affected = preparedStatement.executeUpdate();
				if (affected < 0) {
					System.out.println("저장 실패");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null && rs.isClosed()) {
					rs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (rs != null && !preparedStatement.isClosed()) {
					preparedStatement.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (connection != null && !connection.isClosed()) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}


}