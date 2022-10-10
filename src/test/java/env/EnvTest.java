package env;

import io.github.cdimascio.dotenv.Dotenv;
import marvelos.src.illuminate.database.mysql.Database;
import org.junit.jupiter.api.Test;

public class EnvTest {
    @Test
    public void getEnv() {
        Dotenv dotenv = (Dotenv) Dotenv.configure()
                .directory("src/main/java/assets/")
                .filename(".env")
                .load();
        System.out.println(dotenv.get("DB_DATABASE"));
    }
}
