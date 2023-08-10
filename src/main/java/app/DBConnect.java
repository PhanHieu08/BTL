package app;

import java.sql.*;
import java.time.LocalDate;

public class DBConnect {
    private final String  _url = "jdbc:mysql://127.0.0.1:3306/quanlyvetau";
    private final String _userName = "root";
    private final String _password = "phanhieu08";

    static Connection connection;
    public DBConnect() {
        try {
            connection = DriverManager.getConnection(_url, _userName, _password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Account fetchAccount(String ID_Account) {
        Account account = null;
        try {
            String query = "SELECT * FROM account_user WHERE ID_Account = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, ID_Account);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String UserName = resultSet.getString("Username");
                String Password = resultSet.getString("Password");
                System.out.println(UserName + " " + Password);
                account = new Account(ID_Account, UserName, Password);
                System.out.println(account);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return account;
    }

    public Passenger fetchUser(String ID_Account) {
        Passenger passenger = null;
        Account account = fetchAccount(ID_Account);
        try {
            // Execute a SQL query to retrieve user data
            String query = "SELECT ID_Khachhang, Ten, CCCD, Ngay_sinh, Gioi_tinh, ID_Account, Email, Level, Dia_chi, SDT FROM khach_hang WHERE ID_Account = ?";

            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, ID_Account);

            ResultSet resultSet = statement.executeQuery();

            // Fetch the user data from the result set
            if (resultSet.next()) {
                String ID_Passenger = resultSet.getString("ID_Khachhang");
                String Ten = resultSet.getString("Ten");
                String CCCD = resultSet.getString("CCCD");
                LocalDate Ngay_sinh = resultSet.getDate("Ngay_sinh").toLocalDate();
                String Gioi_tinh = resultSet.getString("Gioi_tinh");
                String Email = resultSet.getString("Email");
                int Level = resultSet.getInt("Level");
                String Dia_chi = resultSet.getString("Dia_chi");
                String SDT = resultSet.getString("SDT");

                passenger = new Passenger(ID_Passenger, Ten, CCCD, Gioi_tinh, Ngay_sinh, Dia_chi, SDT, Email, Level, account);
                System.out.println(passenger);
            }

            statement.close();
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return passenger;
    }

    public void updateUser(Passenger passenger) {
        try {
            // Execute a SQL update query to update the user data
            String query = "UPDATE khach_hang SET Ten = ?, Ngay_sinh = ?, CCCD = ?, Email = ?, Dia_chi = ?, SDT = ?, Gioi_tinh = ? WHERE ID_Khachhang = \"" + passenger.getID_Passenger() + '"';
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, passenger.getHo_va_Ten());
            statement.setDate(2, java.sql.Date.valueOf(passenger.getLocalDate()));
            statement.setString(3, passenger.getCccd());
            statement.setString(4, passenger.getEmail()); // Assuming the User class has an "id" property
            statement.setString(5, passenger.getAddress());
            statement.setString(6, passenger.getPhone());
            statement.setString(7, passenger.getGender());
            System.out.println(query);
            statement.executeUpdate();

            // Close the statement
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
