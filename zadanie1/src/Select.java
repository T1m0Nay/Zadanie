import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.ResultSetMetaData;

public class Select extends DataBaseConnection {

    public static String query = "SELECT * FROM public.document";

    public static String SelectRow() {
        StringBuilder htmlTable = new StringBuilder();
        htmlTable.append("<table border=\"1\">");

        try (Connection con = DataBaseConnection.getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();

            // Заголовки
            htmlTable.append("<tr>");
            for (int i = 1; i <= columnCount; i++) {
                htmlTable.append("<th>").append(metaData.getColumnName(i)).append("</th>");
            }
            htmlTable.append("</tr>");

            // Данные
            while (rs.next()) {
                htmlTable.append("<tr>");
                for (int i = 1; i <= columnCount; i++) {
                    htmlTable.append("<td>").append(rs.getString(i)).append("</td>");
                }
                htmlTable.append("</tr>");
            }

            htmlTable.append("</table>");

        } catch (Exception e) {
            e.printStackTrace();
        }

        return htmlTable.toString();
    }

}
