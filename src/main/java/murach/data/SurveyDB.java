package murach.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import murach.business.Survey;

public class SurveyDB {

    public static void insert(String email, Survey survey) throws SQLException {
        try (Connection c = DBUtil.getConnection()) {
            try (Statement st = c.createStatement()) {
                st.execute("CREATE TABLE IF NOT EXISTS survey_list ("
                        + "email VARCHAR(255) PRIMARY KEY, "
                        + "hear_about VARCHAR(100), "
                        + "want_update VARCHAR(100), "
                        + "want_announcement VARCHAR(100), "
                        + "contact VARCHAR(100), "
                        + "CONSTRAINT fk_user FOREIGN KEY (email) REFERENCES user_list(email) ON DELETE CASCADE)");
            }

            String sql = "INSERT INTO survey_list (email, hear_about, want_update, want_announcement, contact) "
                    + "VALUES (?, ?, ?, ?, ?) "
                    + "ON CONFLICT (email) DO UPDATE SET "
                    + "hear_about = EXCLUDED.hear_about, "
                    + "want_update = EXCLUDED.want_update, "
                    + "want_announcement = EXCLUDED.want_announcement, "
                    + "contact = EXCLUDED.contact";

            try (PreparedStatement ps = c.prepareStatement(sql)) {
                ps.setString(1, email);
                ps.setString(2, survey.getHearAbout());
                ps.setString(3, survey.getWantUpdate());
                ps.setString(4, survey.getWantAnnouncement());
                ps.setString(5, survey.getContact());
                ps.executeUpdate();
            }
        }
    }
}