package org.openjfx.services;

import org.dizitart.no2.objects.ObjectRepository;
import org.openjfx.exceptions.IncorrectConfirmPassword;
import org.openjfx.exceptions.IncorrectLoginException;
import org.openjfx.exceptions.UsernameExists;
import org.openjfx.model.Client;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

public class ClientService {
    private static ObjectRepository<Client> clientRepository;

    public static void initDatabase() {
        clientRepository = DatabaseService.getDatabase().getRepository(Client.class);
    }

    public static String encodePassword(String salt, String password) {
        MessageDigest md = getMessageDigest();
        md.update(salt.getBytes(StandardCharsets.UTF_8));

        byte[] hashedPassword = md.digest(password.getBytes(StandardCharsets.UTF_8));

        // This is the way a password should be encoded when checking the credentials
        return new String(hashedPassword, StandardCharsets.UTF_8)
                .replace("\"", ""); //to be able to save in JSON format
    }

    private static MessageDigest getMessageDigest() {
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("SHA-512");
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("SHA-512 does not exist!");
        }
        return md;
    }

    public static void addClient(String username, String password, String eMail, String role, String country, String town) {
        clientRepository.insert(new Client(username,encodePassword(username, password),eMail,role,country,town));
    }


    public static ObjectRepository<Client> getClientRepository() {
        return clientRepository;
    }

    public static boolean checkUserDoesAlreadyExist(String username, String password) throws IncorrectLoginException {
        for (Client user : clientRepository.find()) {
            if (Objects.equals(username, user.getUsername())) {
                if(!Objects.equals(user.getPassword(), encodePassword(username,password)))
                    throw new IncorrectLoginException(password);
                else return true;
            }
        }
        return false;
    }

    public static boolean checkConfirmPasswordIsCorrect(String password, String confirmPassword) throws IncorrectConfirmPassword {
        if(!password.equals(confirmPassword)) throw new IncorrectConfirmPassword(password);
        else return true;
    }

    public static boolean checkUsernameIsAvailable(String username) throws UsernameExists {
        for(Client user : clientRepository.find()) {
            if(Objects.equals(username, user.getUsername())) throw new UsernameExists(username);
        }
        return true;
    }
}
