package hashmapex;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.sql.Statement;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Cofee {

    private static String dbName = "dreamhome";
    private static Connection con = ConnectionDB.getConnection();

    public static void main(String[] args) throws SQLException {
        HashMap<String, Integer> salesForWeek = new HashMap<>();
        salesForWeek.put("Coffe1", 15);
        salesForWeek.put("Coffe2", 22);
        salesForWeek.put("Coffe3", 55);
        salesForWeek.put("Coffe4", 10);

       // updateCoffeeSales(salesForWeek);
        
        modifyPricesByPercentage("Coffe4", 0.5f, 50f);
    }

    public static void updateCoffeeSales(HashMap<String, Integer> salesForWeek)
            throws SQLException {

        PreparedStatement updateSales = null;
        PreparedStatement updateTotal = null;

        String updateString
                = "update " + dbName + ".COFFEES "
                + "set SALES = ? where COF_NAME = ?";

        String updateStatement
                = "update " + dbName + ".COFFEES "
                + "set TOTAL = TOTAL + ? "
                + "where COF_NAME = ?";

        try {
            con.setAutoCommit(false);
            updateSales = con.prepareStatement(updateString);
            updateTotal = con.prepareStatement(updateStatement);

            for (Map.Entry<String, Integer> e : salesForWeek.entrySet()) {
                updateSales.setInt(1, e.getValue().intValue());
                updateSales.setString(2, e.getKey());
                updateSales.executeUpdate();
                updateTotal.setInt(1, e.getValue().intValue());
                updateTotal.setString(2, e.getKey());
                updateTotal.executeUpdate();
                con.commit();
            }
        } catch (SQLException e) {
            System.err.println(Arrays.toString(e.getStackTrace()));
            if (con != null) {
                try {
                    System.err.print("Transaction is being rolled back");
                    con.rollback();
                } catch (SQLException excep) {
                    System.err.println(Arrays.toString(excep.getStackTrace()));
                }
            }
        } finally {
            if (updateSales != null) {
                updateSales.close();
            }
            if (updateTotal != null) {
                updateTotal.close();
            }
            con.setAutoCommit(true);
        }
    }

    public static void modifyPricesByPercentage(
            String coffeeName,
            float priceModifier,
            float maximumPrice)
            throws SQLException {

        con.setAutoCommit(false);

        Statement getPrice = null;
        Statement updatePrice = null;
        ResultSet rs = null;
        String query
                = "SELECT COF_NAME, PRICE FROM COFFEES "
                + "WHERE COF_NAME = '" + coffeeName + "'";

        try {
            Savepoint save1 = con.setSavepoint();
            getPrice = con.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            updatePrice = con.createStatement();

            if (!getPrice.execute(query)) {
                System.out.println(
                        "Could not find entry "
                        + "for coffee named "
                        + coffeeName);
            } else {
                rs = getPrice.getResultSet();
                rs.first();
                float oldPrice = rs.getFloat("PRICE");
                float newPrice = oldPrice + (oldPrice * priceModifier);
                System.out.println(
                        "Old price of " + coffeeName
                        + " is " + oldPrice);

                System.out.println(
                        "New price of " + coffeeName
                        + " is " + newPrice);

                System.out.println(
                        "Performing update...");

                updatePrice.executeUpdate(
                        "UPDATE COFFEES SET PRICE = "
                        + newPrice
                        + " WHERE COF_NAME = '"
                        + coffeeName + "'");

                System.out.println(
                        "\nCOFFEES table after "
                        + "update:");

                //CoffeesTable.viewTable(con);

                if (newPrice > maximumPrice) {
                    System.out.println(
                            "\nThe new price, "
                            + newPrice
                            + ", is greater than the "
                            + "maximum price, "
                            + maximumPrice
                            + ". Rolling back the "
                            + "transaction...");

                    con.rollback(save1);

                    System.out.println(
                            "\nCOFFEES table "
                            + "after rollback:");

                   // CoffeesTable.viewTable(con);
                }
                con.commit();
            }
        } catch (SQLException e) {
            System.err.println(Arrays.toString(e.getStackTrace()));
        } finally {
            if (getPrice != null) {
                getPrice.close();
            }
            if (updatePrice != null) {
                updatePrice.close();
            }
            con.setAutoCommit(true);
        }
    }
}
