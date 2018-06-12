
import java.sql.*;
/**
 * Description:
 * <br/>網站: <a href="http://www.crazyit.org">瘋狂Java聯盟</a>
 * <br/>Copyright (C), 2001-2016, Yeeku.H.Lee
 * <br/>This program is protected by copyright laws.
 * <br/>Program Name:
 * <br/>Date:
 * @author Yeeku.H.Lee kongyeeku@163.com
 * @version 1.0
 */
public class Updatesql
{
	public static void main(String[] args) throws Exception
	{
		
		
		try {
             

            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
        } catch (Exception ex) {
             
        }
		
		Connection conn = null;
		
		try {
		    conn =
		       DriverManager.getConnection("jdbc:mysql://localhost/ball_test?" +
		                                   "user=root&password=0000&serverTimezone=UTC&useSSL=false");
		    Statement stmt = conn.createStatement();
		    
		    /*String sql = "UPDATE student_table SET student_name = 'David',java_teacher=4 WHERE student_id = 6;";
			stmt.executeUpdate(sql);
		    */
		    
		    ballboucing bb = new ballboucing();
		    
		    PreparedStatement ps = conn.prepareStatement("INSERT INTO mom_table (momx, momy,momvx,momvy) VALUES(?, ?,?,?);");
            ps.setInt(1, bb.momx);
            ps.setInt(2, bb.momy);
            ps.setInt(3, bb.momvx);
            ps.setInt(4, bb.momvy);
            ps.executeUpdate();
		    
			ResultSet rs = stmt.executeQuery("SELECT * FROM ball_test.mom_table;");
		    
					while(rs.next())
					{
						System.out.println(rs.getInt(1) + "\t"
							+ rs.getString(2) + "\t"
							+ rs.getString(3) + "\t"
							+ rs.getString(4));
					}

		    // Do something with the Connection

		   
		} catch (SQLException ex) {
		    // handle any errors
		    System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
		}
		
	}
}
