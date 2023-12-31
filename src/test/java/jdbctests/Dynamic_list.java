package jdbctests;

import org.junit.jupiter.api.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Dynamic_list {

    String dbUrl = "jdbc:oracle:thin:@54.226.247.73:1521:XE";
    String dbUsername = "hr";
    String dbPassword = "hr";

    @Test
    public void test2() throws SQLException {

        Connection connection = DriverManager.getConnection(dbUrl,dbUsername,dbPassword);
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT first_name,last_name,salary,job_id\n" +
                "from employees\n" +
                "where rownum <6");

        //in order to get column names we need resultsetmetadata
        ResultSetMetaData rsmd = resultSet.getMetaData();

        //list of maps to keep all information
        List<Map<String,Object>> queryData = new ArrayList<>();

        //number of columns
        int colCount = rsmd.getColumnCount();

        //loop through each row
        while(resultSet.next()){

            Map<String,Object> row = new LinkedHashMap<>();

            //some code to fill the dynamically
            for (int i = 1; i <= colCount; i++) {

                row.put(rsmd.getColumnName(i),resultSet.getObject(i));

            }
            //BREAK UNTIL 3:33

            //add ready map row to the list
            queryData.add(row);
        }


        //print each row inside the list
        for (Map<String, Object> row : queryData) {
            System.out.println(row.toString());
        }


        //close connection
        resultSet.close();
        statement.close();
        connection.close();
    }
}
