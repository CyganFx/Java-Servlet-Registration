package Classes;

import Additional.SQLQueriesShortcutter;

import java.sql.*;

import static java.lang.System.exit;

public class Dao {
    protected Connection connection;
    protected PreparedStatement statement;
    protected ResultSet resultSet;
    private final String USERNAME = "postgres";
    private final String PASSWORD = "duman070601";
    private String query;


    private void getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        connection = DriverManager
                .getConnection("jdbc:postgresql://localhost:5432/users",
                        USERNAME, PASSWORD);
    }

    public boolean checkLogin(String email, String password){
        try {
            getConnection();
            connection.setAutoCommit(false);
            query = SQLQueriesShortcutter.select(
                    User.TABLE_NAME,
                    User.EMAIL + " = ? " + " and " + User.PASSWORD + " = ? ",
                    "*");
            statement = connection.prepareStatement(query);
            statement.setString(1, email);
            statement.setString(2, password);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return true;
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            exit(0);
        } finally {
            finallyBlock(statement, connection);
        }
        return false;
    }

    public User setUserValues(User user) {
        try {
            getConnection();
            connection.setAutoCommit(false);
            query = SQLQueriesShortcutter.select(
                    User.TABLE_NAME,
                    User.EMAIL + " = ? " + " and " + User.PASSWORD + " = ? ",
                    "*");
            statement = connection.prepareStatement(query);
            statement.setString(1, user.getEmail());
            statement.setString(2, user.getPassword());
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                user.setId(resultSet.getInt(1));
                user.setFirstName(resultSet.getString(2));
                user.setLastName(resultSet.getString(3));
                user.setJob(resultSet.getString(4));
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            exit(0);
        } finally {
            finallyBlock(statement, connection);
        }
        return user;
    }

    private void finallyBlock(PreparedStatement statement, Connection connection) {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
