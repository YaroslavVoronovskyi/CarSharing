package carsharing;

import java.util.regex.Pattern;

public class Constants {

    public static final String PROPERTIES = "application.properties";
    public static final String LINE_SEPARATOR = System.lineSeparator();
    public static final String DB_DRIVER = "DB_DRIVER";
    public static final String DB_URL = "DB_URL";
    public static final String READ_FROM_CONSOLE_ERROR_MESSAGE = "Sorry, something went wrong while reading from the console ... ";
    public static final int FIRST_COLUMN_INDEX = 1;
    public static final int SECOND_COLUMN_INDEX = 2;
    public static final int THIRD_COLUMN_INDEX = 3;
    public static final int FOURTH_COLUMN_INDEX = 4;
    public static final String DOT_SEPARATOR = ". ";
    public static final String DELIMETER = " ";
    public static final String QUOTE = "'";
    public static final String QUOTE_WITH_COLON = "' :";
    public static final String CORRECT_TITLE_MESSAGE = "Enter the correct title";
    public static final String WRONG_NAME_FORMAT_ERROR = "Wrong name format!";
    public static final String WRONG_TITLE_ERROR = "Wrong title!";
    public static final String CUSTOMERS_LIST_EMPTY_MESSAGE = "The customers list is empty!";
    public static final String CARS_LIST_EMPTY_MESSAGE = "The cars list is empty!";
    public static final String COMPANIES_LIST_EMPTY_MESSAGE = "The companies list is empty!";
    public static final String APP_PROCESSOR_MESSAGE = "1. Log in as a manager" + LINE_SEPARATOR +
            "2. Log in as a customer" + LINE_SEPARATOR + "3. Create a customer" + LINE_SEPARATOR + "0. Exit";
    public static final String CUSTOMER_PROCESSOR_MENU_MESSAGE = LINE_SEPARATOR + "1. Rent a car" + LINE_SEPARATOR +
            "2. Return a rented car" + LINE_SEPARATOR + "3. My rented car" + LINE_SEPARATOR + "0. Back";
    public static final String COMPANY_PROCESSOR_MENU_MESSAGE = "1. Company list" + LINE_SEPARATOR + "2. Create a company" +
            LINE_SEPARATOR + "0. Back";
    public static final String CAR_PROCESSOR_MENU_MESSAGE = LINE_SEPARATOR + "1. Car list" + LINE_SEPARATOR +
            "2. Create a car" + LINE_SEPARATOR + "0. Back";
    public static final Pattern NAME_CHECK_PATTERN = Pattern.compile("^([a-zA-Z]{2,}?)");

    public static class Queries {

        public static final String CREATE_TABLE_COMPANY_SQL_QUERY =
                "CREATE TABLE IF NOT EXISTS company (" +
                        "    id INT NOT NULL AUTO_INCREMENT," +
                        "    name VARCHAR(20) UNIQUE NOT NULL," +
                        "    PRIMARY KEY(id));";
        public static final String CREATE_TABLE_CAR_SQL_QUERY =
                "CREATE TABLE IF NOT EXISTS car (" +
                        "    id INT NOT NULL AUTO_INCREMENT," +
                        "    name VARCHAR(30) UNIQUE NOT NULL," +
                        "    company_id INT NOT NULL," +
                        "    rented BOOLEAN," +
                        "    PRIMARY KEY(id)," +
                        "    CONSTRAINT car_company_id_fkey FOREIGN KEY (company_id) REFERENCES company (id));";
        public static final String CREATE_TABLE_CUSTOMER_SQL_QUERY =
                "CREATE TABLE IF NOT EXISTS customer (" +
                        "    id INT NOT NULL AUTO_INCREMENT," +
                        "    name VARCHAR(30) UNIQUE NOT NULL," +
                        "    rented_car_id INT DEFAULT NULL," +
                        "    PRIMARY KEY(id)," +
                        "    CONSTRAINT customer_car_id_fkey FOREIGN KEY (rented_car_id) REFERENCES car (id));";
        public static final String COMPANY_SAVE_QUERY = "INSERT INTO company (name) VALUES (?)";
        public static final String COMPANY_GET_QUERY = "SELECT * FROM company WHERE id = ?";
        public static final String COMPANY_GET_ALL_QUERY = "SELECT * FROM company";
        public static final String CAR_SAVE_QUERY = "INSERT INTO car (name, company_id, rented) VALUES (?, ?, ?)";
        public static final String CAR_GET_QUERY = "SELECT * FROM car WHERE id = ?";
        public static final String CAR_UPDATE_QUERY = "UPDATE car SET rented = ? WHERE id = ?";
        public static final String CAR_GET_ALL_QUERY_BY_COMPANY_ID = "SELECT * FROM car WHERE rented = false AND company_id = ?";
        public static final String CUSTOMER_SAVE_QUERY = "INSERT INTO customer (name) VALUES (?)";
        public static final String CUSTOMER_GET_QUERY = "SELECT * FROM customer WHERE id = ?";
        public static final String CUSTOMER_GET_ALL_QUERY = "SELECT * FROM customer";
        public static final String CUSTOMER_UPDATE_QUERY = "UPDATE customer SET rented_car_id = ? WHERE id = ?";
    }
}
