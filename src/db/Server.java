package db;

import java.sql.*;
import com.microsoft.sqlserver.jdbc.*;

/**
 * Created by Krzysiek on 22.10.2017.
 */
public class Server {

    public static SQLServerDataSource getServer() {
        SQLServerDataSource ds = new SQLServerDataSource();
        ds.setIntegratedSecurity(false);
        ds.setServerName("DESKTOP-6CCU8TS");
        ds.setPortNumber(1433);
        ds.setDatabaseName("REPLENISHME_DB");
        ds.setUser("replenishme_user");
        ds.setPassword("sraczka");
        return ds;
    }

}
