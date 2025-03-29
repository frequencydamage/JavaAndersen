package org.novak.java.repository;

import org.novak.java.util.databaseUtil.DBUtil;
import org.novak.java.util.databaseUtil.DBUtilResultConsumer;
import org.novak.java.util.databaseUtil.DBUtilStatementConsumer;

public abstract class Repository {

    private final DBUtil dbUtil = DBUtil.getInstance();

    public void executeUpdate(String query, DBUtilStatementConsumer statementSetter) {
        dbUtil.executeUpdate(query, statementSetter);
    }

    public void executeSelect(String query, DBUtilStatementConsumer statementSetter, DBUtilResultConsumer resultProcessor) {
        dbUtil.executeSelect(query, statementSetter, resultProcessor);
    }
}
