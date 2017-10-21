package db;

import java.sql.*;

import com.microsoft.sqlserver.jdbc.*;

/**
 * Created by Krzysiek on 21.10.2017.
 */
public class Test {

    public static void createTestDbEntries() {
        // Declare the JDBC objects.
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            SQLServerDataSource ds = new SQLServerDataSource();
            ds.setIntegratedSecurity(false);
            ds.setServerName("DESKTOP-17TH797");
            ds.setPortNumber(1433);
            ds.setDatabaseName("REPLENISHME_DB");
            ds.setUser("replenishme_user");
            ds.setPassword("sraczka");
            con = ds.getConnection();

            stmt = con.createStatement();
            stmt.execute("TRUNCATE TABLE programista");
            stmt.executeUpdate("INSERT INTO programista(imie, nazwisko) VALUES ('Andrzej', 'Kowalski')");
            stmt.executeUpdate("INSERT INTO programista(imie, nazwisko) VALUES ('Jan', 'Nowak')");

            rs = stmt.executeQuery("SELECT * FROM programista");
            while(rs.next()) {
                System.out.println(rs.getString("imie") + " " + rs.getString("nazwisko"));
            }
            rs.close();

            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
