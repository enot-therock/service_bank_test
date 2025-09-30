package pro.sky.service_bank_test.util;

public class UtilVariableSQL {

    public final static String INVEST_SQL =
            "SELECT * FROM " +
            "TRANSACTIONS t " +
            "INNER JOIN PRODUCTS p ON t.PRODUCT_ID = p.ID " +
            "WHERE t.USER_ID IN (" +
                    "SELECT t1.USER_ID " +
                    "FROM TRANSACTIONS t1 " +
                    "INNER JOIN PRODUCTS p1 ON t1.PRODUCT_ID = p1.ID " +
                    "WHERE p1.TYPE = 'DEBIT') " +

            "AND t.USER_ID NOT IN (" +
                    "SELECT t2.USER_ID " +
                    "FROM TRANSACTIONS t2 " +
                    "INNER JOIN PRODUCTS p2 ON t2.PRODUCT_ID = p2.ID " +
                    "WHERE p2.TYPE = 'INVEST') " +

            "AND t.USER_ID IN (" +
                    "SELECT t3.USER_ID " +
                    "FROM TRANSACTIONS t3 " +
                    "INNER JOIN PRODUCTS p3 ON t3.PRODUCT_ID = p3.ID " +
                    "WHERE p3.TYPE = 'SAVING' " +
                    "GROUP BY t3.USER_ID " +
                    "HAVING SUM(CASE WHEN t3.TYPE = 'DEPOSIT' THEN t3.AMOUNT ELSE 0 END) > 1000)";

    public final static String SAVING_SQL =
            "SELECT * FROM " +
            "TRANSACTIONS t " +
            "INNER JOIN PRODUCTS p ON t.PRODUCT_ID = p.ID " +
            "WHERE t.USER_ID IN (" +
                    "SELECT t1.USER_ID " +
                    "FROM TRANSACTIONS t1 " +
                    "INNER JOIN PRODUCTS p1 ON t1.PRODUCT_ID = p1.ID " +
                    "WHERE p1.TYPE = 'DEBIT') " +

            "AND t.USER_ID IN (" +
                    "(SELECT t2.USER_ID " +
                    "FROM TRANSACTIONS t2 " +
                    "INNER JOIN PRODUCTS p2 ON t2.PRODUCT_ID = p2.ID " +
                    "WHERE p2.TYPE = 'DEBIT' " +
                    "GROUP BY t2.USER_ID " +
                    "HAVING SUM(CASE WHEN t2.TYPE = 'DEPOSIT' THEN t2.AMOUNT ELSE 0 END) >= 50000) " +

            "OR t.USER_ID IN (" +
                    "SELECT t3.USER_ID " +
                    "FROM TRANSACTIONS t3 " +
                    "INNER JOIN PRODUCTS p3 ON t3.PRODUCT_ID = p3.ID " +
                    "WHERE t3.TYPE = 'SAVING' " +
                    "GROUP BY t3.USER_ID " +
                    "HAVING SUM(CASE WHEN t3.TYPE = 'DEPOSIT' THEN t3.AMOUNT ELSE 0 END) >= 50000)) " +

            "AND t.USER_ID IN (" +
                    "SELECT t4.USER_ID " +
                    "FROM TRANSACTIONS t4 " +
                    "INNER JOIN PRODUCTS p4 ON t4.PRODUCT_ID = p4.ID " +
                    "WHERE p4.TYPE = 'DEBIT' " +
                    "GROUP BY t4.USER_ID " +
                    "HAVING SUM(CASE WHEN t4.TYPE = 'DEPOSIT' THEN t4.AMOUNT ELSE 0 END) > " +
                    "SUM(CASE WHEN t4.TYPE = 'WITHDRAW' THEN t4.AMOUNT ELSE 0 END))";

    public final static String CREDIT_SQL =
            "SELECT * FROM " +
            "TRANSACTIONS t " +
            "INNER JOIN PRODUCTS p ON t.PRODUCT_ID = p.ID " +
            "WHERE t.USER_ID NOT IN (" +
                    "SELECT t1.USER_ID " +
                    "FROM TRANSACTIONS t1 " +
                    "INNER JOIN PRODUCTS p1 ON t1.PRODUCT_ID = p1.ID " +
                    "WHERE p1.TYPE = 'CREDIT') " +

            "AND t.USER_ID IN (" +
                    "SELECT t2.USER_ID " +
                    "FROM TRANSACTIONS t2 " +
                    "INNER JOIN PRODUCTS p2 ON t2.PRODUCT_ID = p2.ID " +
                    "WHERE p2.TYPE = 'DEBIT' " +
                    "GROUP BY t2.USER_ID " +
                    "HAVING SUM(CASE WHEN t2.TYPE = 'DEPOSIT' THEN t2.AMOUNT ELSE 0 END) > " +
                    "SUM(CASE WHEN t2.TYPE = 'WITHDRAW' THEN t2.AMOUNT ELSE 0 END)) " +

            "AND t.USER_ID IN (" +
                    "SELECT t3.USER_ID " +
                    "FROM TRANSACTIONS t3 " +
                    "INNER JOIN PRODUCTS p3 ON t3.PRODUCT_ID = p3.ID " +
                    "WHERE t3.TYPE = 'DEBIT' " +
                    "GROUP BY t3.USER_ID " +
                    "HAVING SUM(CASE WHEN t3.TYPE = 'WITHDRAW' THEN t3.AMOUNT ELSE 0 END) > 100000)";
}
