package org.openjfx.services;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class LoginService {
    public static String LOGIN_FOLDER = ".login-database";
    private static final String USER_FOLDER = System.getProperty("user.home");

    public static Path getPathToFile(String... path) {
        return getLoginFolder().resolve(Paths.get(".", path));
    }

    public static Path getLoginFolder() {
        return Paths.get(USER_FOLDER, LOGIN_FOLDER);
    }

    public static void initDirectory() {
        Path applicationHomePath = LoginService.getLoginFolder();
        if (!Files.exists(applicationHomePath))
            applicationHomePath.toFile().mkdirs();
    }

}
