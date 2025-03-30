package org.novak.java.util.databaseUtil;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Made just to prettify the code (resolve infinite try-catch blocks);
 */
@FunctionalInterface
public interface DBUtilStatementConsumer {

    void accept(PreparedStatement statement) throws SQLException;
}
