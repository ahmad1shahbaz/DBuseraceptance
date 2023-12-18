package co.wedevx.digitalbank.automation.ui.utils;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static co.wedevx.digitalbank.automation.ui.utils.ConfigReader.getPropertiesValues;

public class DBUtils {
    private static Connection connection=null;
    private  static  Statement statement=null;
    private static ResultSet resultSet=null;
    //method to establish connection with db
    public static void establishConnection(){


        try {
            Class.forName("com.mysql.jdbc.Driver");
           connection= DriverManager.getConnection(getPropertiesValues("digitalbank.db.url"),
                   getPropertiesValues("digitalbank.db.username"),
                   getPropertiesValues("digitalbank.db.password"));
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
    //method can dynamically send select statements list of maps of all columns
    public static List<Map<String,Object>> runSQLSelectQuery(String sqlQuery){
        List<Map<String,Object>> dbResultList=new ArrayList<>();
        try {
           statement=connection.createStatement();
           resultSet= statement.executeQuery(sqlQuery);
           //return info about your table
          ResultSetMetaData resultSetMetaData= resultSet.getMetaData();
          int columnCount=resultSetMetaData.getColumnCount();
           while (resultSet.next()){
               Map<String,Object> rowMap=new HashMap<>();
           for(int col=1;col<=columnCount;col++){
               rowMap.put(resultSetMetaData.getColumnName(col),resultSet.getObject(col));
           }
               dbResultList.add(rowMap);
           }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  dbResultList;
    }
    //update method
    public static int  runSQLUpdateQuery(String sqlQuery){
        int rowsAffected=0;
        try{
            statement=connection.createStatement();
           rowsAffected=  statement.executeUpdate(sqlQuery);
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return rowsAffected;
    }

    //create a method inserts into db return numbers of row updated or 0 when action not taken
    //another method for close connection
    public static void closeConnection(){
        try {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

}
