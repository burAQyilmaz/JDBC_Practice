package jdbctests;


import java.sql.*;

public class TestConnection {

    public static void main(String[] args) throws SQLException {

        String dbUrl="jdbc:oracle:thin:@54.226.247.73:1521:XE";
        String dbUsername="hr";
        String dbPassword="hr";

        Connection connection= DriverManager.getConnection(dbUrl,dbUsername,dbPassword);

        Statement statement= connection.createStatement();

        ResultSet resultSet= statement.executeQuery("SELECT * FROM regions");
/*
        //move pointer to first row
        resultSet.next();

        //getting information with colum name
        System.out.println(resultSet.getString("region_name"));

        //getting information with colum index(starts 1)
        System.out.println(resultSet.getString(2));
*/
        while(resultSet.next()) System.out.println(resultSet.getInt(1)+"-"+resultSet.getString(2));


        //close connection
        resultSet.close();
        statement.close();
        connection.close();
    }
}
