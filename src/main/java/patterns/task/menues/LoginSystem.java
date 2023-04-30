package patterns.task.menues;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import patterns.task.Adapters.AudienceTypeAdapter;
import patterns.task.Adapters.MovieClassificationAdapter;
import patterns.task.Customer;
import patterns.task.MovieClassification.MovieClassification;
import patterns.task.audienceType.AudienceType;

import java.io.FileWriter;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class LoginSystem {
    private static final String LOGIN_REGEX = "^[a-zA-Z0-9]*$";
    private static final int MIN_LOGIN_LENGTH = 5;
    private static final int MIN_PASSWORD_LENGTH = 5;
    private static Map<String, String> userCredentials = new HashMap<>();
    private static Map<String, Customer> customers = new HashMap<>();


    public static void writeToFileCustomers() {
        try {
            Gson gson = new GsonBuilder().setPrettyPrinting()
                    .registerTypeAdapter(MovieClassification.class, new MovieClassificationAdapter())
                    .registerTypeAdapter(AudienceType.class, new AudienceTypeAdapter())
                    .create();
            String json = gson.toJson(customers);
            FileWriter writer = new FileWriter("customers.json");
            writer.write(json);
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void readFromFileToCustomers() {
        try {
            Gson gson = new GsonBuilder().setPrettyPrinting()
                    .registerTypeAdapter(MovieClassification.class, new MovieClassificationAdapter())
                    .registerTypeAdapter(AudienceType.class, new AudienceTypeAdapter())
                    .create();
            String jsonContent = new String(Files.readAllBytes(Paths.get("customers.json")));
            Type type = new TypeToken<HashMap<String, Customer>>(){}.getType();
            customers = gson.fromJson(jsonContent, type);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void writeUserCredentialsToFile() {
        try {
            Gson gson = new Gson();
            String json = gson.toJson(userCredentials);
            FileWriter writer = new FileWriter("loginspaswwords.json");
            writer.write(json);
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void readFromFileToUserCredentials() {
        try {
            Gson gson = new Gson();
            String jsonContent = new String(Files.readAllBytes(Paths.get("loginspaswwords.json")));
            Type type = new TypeToken<HashMap<String, String>>(){}.getType();
            userCredentials = gson.fromJson(jsonContent, type);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void run() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1.Sign in 2.Register new acc 3.admin menu 4.exit");
            int option = scanner.nextInt();

            switch (option) {
                case 1 -> signIn();
                case 2 -> registerNewAccount();
                case 3 -> AdminMenu.showMenu();
                case 4 -> {
                    return;
                }
                default -> System.out.println("Invalid input");
            }
        }
    }

    private static void signIn() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter your login");
        String login = scanner.nextLine().trim();

        if (userCredentials.containsKey(login) && !login.equalsIgnoreCase("admin")) {
            System.out.println("Please enter your password");
            String password = scanner.nextLine().trim();
            if (isValidCredentials(login, password)) {
                System.out.println("Hello " + login);
                Customer customer = customers.get(login);
                UserMenu.showMenu(customer);
            }
        } else {
            System.out.println("Your account name has already taken,or you use invalid login");
            System.out.println("Do you want to create an account? (yes/no)");
            String answer = scanner.nextLine().trim();

            if (answer.equalsIgnoreCase("yes")) {
                registerNewAccount();
            }
        }
    }

    private static void registerNewAccount() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter your login");
        String login = scanner.nextLine().trim();

        if (isValidLogin(login)) {
            System.out.println("Ok, your login is - " + login);
            String password;

            do {
                System.out.println("Please enter your password");
                password = scanner.nextLine().trim();
            } while (!isValidPassword(password));

            System.out.println("Ok, your password is - " + password);
            System.out.println("Account created, please don't forget your password");

            userCredentials.put(login, password);
            customers.put(login, new Customer(login, new ArrayList<>()));
            writeToFileCustomers();
            writeUserCredentialsToFile();
        }
    }
    private static boolean isValidLogin(String login) {
        if (!login.matches(LOGIN_REGEX)) {
            System.out.println("Login must contain only latin letters and numbers");
            return false;
        }

        if (login.length() < MIN_LOGIN_LENGTH) {
            System.out.println("Login must be at least 5 characters");
            return false;
        }

        if (userCredentials.containsKey(login)) {
            System.out.println("This login already exists");
            return false;
        }

        if (login.equalsIgnoreCase("admin")) {
            System.out.println("You cannot use this login");
            return false;
        }

        return true;
    }

    private static boolean isValidCredentials(String login, String password) {
        if (!userCredentials.containsKey(login)) {
            System.out.println("This login does not exist");
            return false;
        }

        String expectedPassword = userCredentials.get(login);

        if (!expectedPassword.equals(password)) {
            System.out.println("Incorrect password");
            return false;
        }

        return true;
    }

    private static boolean isValidPassword(String password) {
        if (!password.matches(LOGIN_REGEX)) {
            System.out.println("Password must contain only latin letters and numbers");
            return false;
        }

        if (password.length() < MIN_PASSWORD_LENGTH) {
            System.out.println("Password must be at least 5 characters");
            return false;
        }

        return true;
    }

    public static Map<String, Customer> getCustomers() {
        return customers;
    }
}
