package org.novak.java.util.databaseUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Made just to prettify the code (resolve infinite try-catch blocks);
 */
@FunctionalInterface
public interface DBUtilResultConsumer {

    void accept(ResultSet resultSet) throws SQLException;
}
