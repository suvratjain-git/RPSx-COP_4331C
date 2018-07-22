//Connecting java to SQL database

package com.example.suvratjain.firstapp;
    import java.sql.Connection;
    import java.sql.DriverManager;
    import java.sql.ResultSet;
    import java.sql.SQLException;
    import java.sql.Statement;
    import java.util.logging.Level;

    public class DBconnector{
        private Connection connection = null; //init a connection and set to null
        private String dbUser = "RudeDude"
        private String dbPass = "cop4331!"
        private String url = "jdbc:mysql://localhost:3306/RPSx"
        private String name = "RPSx"

        public boolean isConnected(){ //check if element is connected
            if (this.connection == null){
                return false //if its null we cant reach DB
            } try{
                return !this.connection.isClosed();
            } catch (SQLException ex){} //who cares what error says it dont work either way
            return false;
    }

    public void connect(String url, String username, String Password, String dbName)
            throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException{
                this.connection(url, dbUser, dbPass, name)
    }

        //prob dont need since we have our own commmands
        public ResultSet executeQuery(String command)
                throws SQLException {
            Statement statement = this.connection.createStatement(1004, 1007); //forgot what these do
            return statement.executeQuery(command);
        }

        public int executeUpdate(String command)
                throws SQLException {
            Statement statement = this.connection.createStatement();
            int result = statement.executeUpdate(command);
            statement.close();

            return result;
        }

        //terminate db connection
        public void disconnect() {
            if (this.connection != null) {
                try
                {
                    this.connection.close();
                }
                catch (SQLException ex)
                {
                    Logger.getLogger(DBConnector.class.getName()).log(Level.SEVERE, "Failed to close connection", ex);
                }
            }
        }
    }