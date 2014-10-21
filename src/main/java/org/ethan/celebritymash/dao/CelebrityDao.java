package org.ethan.celebritymash.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.ethan.celebritymash.model.Celebrity;

public class CelebrityDao {

	private static final String DB_CONNECTION = "jdbc:mysql://localhost:3306/MovieDB?characterEncoding=UTF-8";

	private static CelebrityDao dao = new CelebrityDao();

	private CelebrityDao() {
	}

	public static CelebrityDao getInstance() {
		return dao;
	}
	
	private Connection getDBConnection() throws SQLException {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return 	DriverManager.getConnection(DB_CONNECTION, "root", "");
	}
	
	public void createCelebrity(Celebrity c) {
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = getDBConnection();
			String sql = "Insert into Celebrity (NAME, FOREIGN_NAME, IMAGE) values (?, ?, ?)";
			statement = connection.prepareStatement(sql);
			statement.setString(1, c.getName());
			statement.setString(2, c.getForeignName());
			statement.setString(3, c.getImage());
			statement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (statement != null) {
					statement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public double getScore(int id) {
		Connection connection = null;
		PreparedStatement statement = null;
		double s = 0;

		try {
			connection = DriverManager.getConnection(DB_CONNECTION, "root", "");
			String sql = "SELECT score FROM Celebrity WHERE id = ?";

			statement = connection.prepareStatement(sql);
			statement.setInt(1, id);
			ResultSet rs = statement.executeQuery();
			rs.next();
			s = rs.getDouble(1);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (statement != null) {
					statement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return s;
	}
	
	public Celebrity[] getTwoCelebrities() {
		Celebrity[]	 results = new Celebrity[2];
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			try {
				//TODO
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			connection = DriverManager.getConnection(DB_CONNECTION, "root", "");
			String sql = "SELECT id, name, image, won, lost, score FROM Celebrity ORDER BY RAND() limit 2";

			statement = connection.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();
			int i = 0;
			while (rs.next()) {
				Celebrity c = new Celebrity();
				c.setId(rs.getInt(1));
				c.setName(rs.getString(2));
				c.setImage(rs.getString(3));
				c.setWon(rs.getInt(4));
				c.setLost(rs.getInt(5));
				c.setScore(rs.getDouble(6));
				results[i++] = c;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (statement != null) {
					statement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return results;
	}
	
	public void vs(int wid, int lid) {
		Connection connection = null;
		PreparedStatement statement = null;
		PreparedStatement statement2 = null;
		double[] newScores = defeat(getScore(wid), getScore(lid));
		try {
			connection = DriverManager.getConnection(DB_CONNECTION, "root", "");

			statement = connection.prepareStatement("Update Celebrity Set won = won+1, score = ? where id = ?");
			statement.setDouble(1, newScores[0]);
			statement.setInt(2, wid);
			statement.executeUpdate();
			
			statement2 = connection.prepareStatement("Update Celebrity Set lost = lost+1, score = ? where id = ?");
			statement2.setDouble(1, newScores[1]);
			statement2.setInt(2, lid);
			statement2.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (statement != null) {
					statement.close();
				}
				if (statement2 != null) {
					statement2.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public Celebrity[]	getTopRated(int num) {
		Celebrity[]	 results = new Celebrity[num];
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = DriverManager.getConnection(DB_CONNECTION, "root", "");
			String sql = "SELECT id, name, image, won, lost, score FROM Celebrity ORDER BY score desc limit ?";

			statement = connection.prepareStatement(sql);
			statement.setInt(1, num);
			ResultSet rs = statement.executeQuery();
			int i = 0;
			while (rs.next()) {
				Celebrity c = new Celebrity();
				c.setId(rs.getInt(1));
				c.setName(rs.getString(2));
				c.setImage(rs.getString(3));
				c.setWon(rs.getInt(4));
				c.setLost(rs.getInt(5));
				c.setScore(rs.getDouble(6));
				results[i++] = c;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (statement != null) {
					statement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return results;
	}
	
	/**
	 * User EOL rating system to do the competition result.
	 * @param a winner
	 * @param b loser
	 */
	private double[] defeat(double ra, double rb) {
		double results[] = new double[2];
		double e = expectedScore(ra, rb);
		results[0] = (ra + 16*(1-e));
		results[1] = (rb - 16*e);
		return results;
	}
	
	private double expectedScore(double ra, double rb) {
		return 1.0/(1 + Math.pow(10, (rb-ra)/400));
	}
	
	public static void main(String[] args) {
		CelebrityDao dao = CelebrityDao.getInstance();
		Celebrity[] cs = dao.getTwoCelebrities();
		for (Celebrity celebrity : cs) {
			System.out.println(celebrity.getName());
		}
		dao.vs(1, 2);
	}
}
