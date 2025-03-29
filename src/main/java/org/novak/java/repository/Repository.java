package org.novak.java.repository;

import org.novak.java.util.databaseUtil.DBUtil;
import org.novak.java.util.databaseUtil.DBUtilResultConsumer;
import org.novak.java.util.databaseUtil.DBUtilStatementConsumer;

abstract class Repository {

    private final DBUtil dbUtil = DBUtil.getInstance();

    protected void executeUpdate(String query, DBUtilStatementConsumer statementSetter) {
        dbUtil.executeUpdate(query, statementSetter);
    }

    protected void executeSelect(String query, DBUtilStatementConsumer statementSetter, DBUtilResultConsumer resultProcessor) {
        dbUtil.executeSelect(query, statementSetter, resultProcessor);
    }
}
