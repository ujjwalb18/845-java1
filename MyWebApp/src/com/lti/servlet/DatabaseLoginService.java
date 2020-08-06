import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
public class DatabaseLoginService {
    public boolean authenticate(String uname, String pwd) {
        Connection conn = null;
        PreparedStatement stmt =  null;
        ResultSet rs = null;
        try {
        	Class.forName("oracle.jdbc.driver.OracleDriver");
        	conn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL", "system", "Pranjali2");
            String sql = "Select count(user_id) from tbl_users where user_name = ? and user_pass = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, uname);
            stmt.setString(2,  pwd);
            rs = stmt.executeQuery();
            if(rs.next()) {
                int count = rs.getInt(1);
                if(count==1)
                    return true;
            }
            return false;
        }
        catch(Exception e) {        //ClassNotFoundException | SQLException
            e.printStackTrace();
            return false;
        }
        finally {
            try {conn.close(); } catch(Exception e) {}
        }
    }
    
}