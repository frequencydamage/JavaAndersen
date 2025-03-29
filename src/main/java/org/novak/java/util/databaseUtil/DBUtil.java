package org.novak.java.util.databaseUtil;

import org.novak.java.util.ConfigReaderUtil;

import java.sql.*;

public class DBUtil {

    private static DBUtil instance;

    private static final String URL = ConfigReaderUtil.getProperty("DB_URL");
    private static final String USER = ConfigReaderUtil.getProperty("DB_USER");
    private static final String PASSWORD = ConfigReaderUtil.getProperty("DB_PASS");

    private DBUtil() {
        createTables();
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public void executeUpdate(String query, DBUtilStatementConsumer statementSetter) {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statementSetter.accept(statement);
            statement.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException(String.format("Query %s execution failed! ", query), ex);
        }
    }

    public void executeSelect(String query, DBUtilStatementConsumer statementSetter, DBUtilResultConsumer resultProcessor) {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statementSetter.accept(statement);
            try (ResultSet results = statement.executeQuery()) {
                resultProcessor.accept(results);
            }
        } catch (SQLException ex) {
            throw new RuntimeException(String.format("Query %s execution failed! ", query), ex);
        }
    }

    private void createTables() {
        createWorkspaceTable();
        createReservationTable();
    }

    private void createWorkspaceTable() {
        String workspaceTableCreationQuery = """
                CREATE TABLE IF NOT EXISTS workspace (
                    id SERIAL PRIMARY KEY,
                    price DOUBLE PRECISION NOT NULL,
                    workspace_type VARCHAR(255) NOT NULL,
                    is_available BOOLEAN NOT NULL
                );
                """;

        executeUpdate(workspaceTableCreationQuery, statement -> {
        });
    }

    private void createReservationTable() {
        String reservationTableCreationQuery = """
                CREATE TABLE IF NOT EXISTS reservation (
                    reservation_id SERIAL PRIMARY KEY,
                    workspace_id INTEGER NOT NULL,
                    workspace_type VARCHAR(255) NOT NULL,
                    FOREIGN KEY (workspace_id) REFERENCES workspace(id) ON DELETE CASCADE
                );
                """;

        executeUpdate(reservationTableCreationQuery, statement -> {
        });
    }

    public static DBUtil getInstance() {
        if (instance == null) {
            instance = new DBUtil();
        }

        return instance;
    }
}