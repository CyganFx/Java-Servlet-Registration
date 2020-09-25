package Additional;

import java.util.StringJoiner;

public class SQLQueriesShortcutter {
    private static String query;

    public static String selectAll(String tableName) {
        return "SELECT * FROM " + tableName;
    }

    public static String selectWithoutCondition(String tableName, String... columns) {
        query = "SELECT ";
        StringJoiner selectedColumns = new StringJoiner(", ");
        for (String column : columns) {
            selectedColumns.add(column);
        }
        return query.concat(selectedColumns + " FROM " + tableName);
    }

    public static String select(String tableName, String condition, String... columns) {
        query = "SELECT ";
        StringJoiner selectedColumns = new StringJoiner(", ");
        for (String column : columns) {
            selectedColumns.add(column);
        }
        return query.concat(selectedColumns + " FROM " + tableName + " WHERE " + condition);
    }
}
