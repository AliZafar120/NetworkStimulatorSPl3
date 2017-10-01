package FinalRapidnetOutputAnalyis.Storage;
import java.sql.*;
public class MySql {
    Connection con=null;
    public static void main(String args[]){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/SPL3","root","root");
//here sonoo is database name, root is username and password
            Statement stmt=con.createStatement();
            ResultSet rs=stmt.executeQuery("select * from user");
            while(rs.next())
                System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3));
            con.close();
        }catch(Exception e){ System.out.println(e);}
    }

    public void openDatabase(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
       con=DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","root");
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
    public void clearTable(String tablename){
        try {
            con.createStatement().executeQuery("TRUNCATE "+tablename);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void closeDatabae(){
        try {
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
