import db.Database;
import init.DataLoader;
import db.Test;

public class Main {

    public static void main(String[] args) {
        Test.createTestDbEntries();
        DataLoader.loadMaterialMaster();
        DataLoader.loadForecast();
        Database.generateMRPLists();
        Database.exportMRPLists();
    }
}
