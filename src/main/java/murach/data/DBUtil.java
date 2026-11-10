package murach.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.net.URI;

public class DBUtil {

    private static String env(String key, String def) {
        String v = System.getenv(key);
        return (v == null || v.isBlank()) ? def : v;
    }

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new SQLException("PostgreSQL Driver not found!", e);
        }

        // Lấy biến môi trường DB_URL (dùng trên Render / Neon)
        String dbUrl = System.getenv("DB_URL");

        if (dbUrl != null && !dbUrl.isBlank()) {
            // Trường hợp 1: Nhận URL dạng postgresql:// từ Neon/Render
            if (dbUrl.startsWith("postgresql://")) {
                try {
                    URI uri = URI.create(dbUrl);
                    String userInfo = uri.getUserInfo();
                    String username = userInfo.split(":", 2)[0];
                    String password = userInfo.split(":", 2)[1];

                    // Tạo JDBC URL với sslmode=require bắt buộc cho Neon
                    String url = "jdbc:postgresql://"
                            + uri.getHost() + ":"
                            + (uri.getPort() == -1 ? 5432 : uri.getPort())
                            + uri.getPath() + "?sslmode=require";

                    return DriverManager.getConnection(url, username, password);
                } catch (Exception e) {
                    throw new SQLException("Cấu hình DB_URL không hợp lệ!", e);
                }
            }

            // Trường hợp 2: Nhận URL dạng jdbc:postgresql:// truyền thống
            return DriverManager.getConnection(
                    dbUrl,
                    env("DB_USER", "postgres"),
                    env("DB_PASS", "1234")
            );
        }

        // Trường hợp 3: Chạy localhost ở môi trường Dev máy cá nhân
        String host = env("DB_HOST", "localhost");
        String port = env("DB_PORT", "5432");
        String dbName = env("DB_NAME", "surveyDB");
        String ssl = env("DB_SSL", "disable");

        String url = "jdbc:postgresql://" + host + ":"
                + port + "/" + dbName + "?sslmode=" + ssl;

        return DriverManager.getConnection(
                url,
                env("DB_USER", "postgres"),
                env("DB_PASS", "1234")
        );
    }
}