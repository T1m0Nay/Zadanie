import java.sql.*;
import java.util.Scanner;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

public class Create extends DataBaseConnection {

    public static void insertRecord(int id, String name, String type) {

        try (Scanner scanner = new Scanner(System.in)) {

            //System.out.print("Введите дату создания (YYYY-MM-DD): ");
            String date_create_str = "2023-10-27 10:30:00+03";

            //System.out.print("Введите дату редактирования (YYYY-MM-DD): ");
            String date_edit_str = "2023-10-27 10:30:00+03";

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ssx");
            OffsetDateTime date_create = OffsetDateTime.parse(date_create_str, formatter);
            OffsetDateTime date_edit = OffsetDateTime.parse(date_edit_str, formatter);

            // Новый док
            try (Connection connection = getConnection()) {
                String query = "INSERT INTO public.document (id, name, type, date_create, date_edit) VALUES (?, ?, ?, ?, ?)";
                try (PreparedStatement statement = connection.prepareStatement(query)) {
                    statement.setInt(1, id);
                    statement.setString(2, name);
                    statement.setString(3, type);
                    statement.setTimestamp(4, Timestamp.valueOf(date_create.toLocalDateTime()));
                    statement.setTimestamp(5, Timestamp.valueOf(date_edit.toLocalDateTime()));

                    int rowsAffected = statement.executeUpdate();
                    if (rowsAffected > 0) {
                        System.out.println("Строка успешно добавлена в таблицу.");
                    } else {
                        System.out.println("Ошибка при добавлении строки.");
                    }
                }
            } catch (SQLException e) {
                System.err.println("Ошибка подключения: " + e.getMessage());
            }
        }
    }

}
