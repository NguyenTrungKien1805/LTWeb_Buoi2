package murach.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import murach.business.User;

public class UserDB {

    public static void insert(User user) throws SQLException {
        try (Connection c = DBUtil.getConnection()) {
            try (Statement st = c.createStatement()) {
                st.execute("CREATE TABLE IF NOT EXISTS user_list ("
                        + "email VARCHAR(255) PRIMARY KEY, "
                        + "first_name VARCHAR(100) NOT NULL, "
                        + "last_name VARCHAR(100) NOT NULL, "
                        + "date_of_birth VARCHAR(50))");
            }

            String sql = "INSERT INTO user_list (email, first_name, last_name, date_of_birth) "
                    + "VALUES (?, ?, ?, ?) "
                    + "ON CONFLICT (email) DO UPDATE SET "
                    + "first_name = EXCLUDED.first_name, "
                    + "last_name = EXCLUDED.last_name, "
                    + "date_of_birth = EXCLUDED.date_of_birth";

            try (PreparedStatement ps = c.prepareStatement(sql)) {
                ps.setString(1, user.getEmail());
                ps.setString(2, user.getFirstName());
                ps.setString(3, user.getLastName());
                ps.setString(4, user.getDateOfBirth());
                ps.executeUpdate();
            }
        }
    }
}