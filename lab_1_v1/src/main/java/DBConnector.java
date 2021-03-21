import java.sql.*;

public class DBConnector {
    private Connection connection = null;
    private Statement statement = null;
    private String url = null;
    private ResultSet resultSet = null;

    public DBConnector(String jdbc, String path, String user, String password) {
        this.url = "jdbc:"+jdbc+":"+path;
        try{
            connection = DriverManager.getConnection(this.url, user, password);
            statement = connection.createStatement();
            System.out.println("Connected");
        }catch (SQLException e) {
            System.out.println("SQL Exception: "+e.getMessage());
            System.out.println("SQL State: "+e.getSQLState());
            System.out.println("Vendor Error: "+e.getErrorCode());
        }
    }

    void executeStatement(String sqlStatement) {
        try{
            statement.execute(sqlStatement);
        }catch(SQLException e) {
            System.out.println("SQL Exception: "+e.getMessage());
            System.out.println("SQL State: "+e.getSQLState());
            System.out.println("Vendor Error: "+e.getErrorCode());
        }
    }

    ResultSet getResultSet(String sqlStatement) {
        resultSet = null;
        try{
            executeStatement((sqlStatement));
            resultSet =statement.getResultSet();

        }catch(SQLException e) {
            System.out.println("SQL Exception: "+e.getMessage());
            System.out.println("SQL State: "+e.getSQLState());
            System.out.println("Vendor Error: "+e.getErrorCode());
        }
        return resultSet;
    }
}
