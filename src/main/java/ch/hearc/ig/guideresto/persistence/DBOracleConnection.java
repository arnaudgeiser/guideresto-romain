package ch.hearc.ig.guideresto.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBOracleConnection {

    private static final String DBURL = "jdbc:oracle:thin:@localhost:1521:xe";
    private static final String DBUSER = "system";
    private static final String DBPWD = "oracle";
    private static Connection cnn = null;

    public static Connection openConnection() throws SQLException {
        try {
            // Se connecte à la BD Oracle :
            cnn = DriverManager.getConnection(DBURL, DBUSER, DBPWD);
            // Désactive le mode autocommit :
            cnn.setAutoCommit(false);
            // Niveau d'isolation de JDBC :
            // Niveau d'isolation déterminé par une constante de l'interface Connection :
            //cnn.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            return cnn;
        } catch (SQLException e) {
            // Toujours passer la cause (Exception source) en paramètre
            throw new SQLException(e);
        }
    }

    public static void commit() throws SQLException {
        try {
            cnn.commit();
        } catch (SQLException e) {
            throw new SQLException();
        }
    }

    public static void closeConnection() throws SQLException {
        try {
            cnn.close();
        } catch (SQLException e) {
            throw new SQLException();
        }
    }

}
