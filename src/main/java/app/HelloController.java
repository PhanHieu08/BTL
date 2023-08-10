package app;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;

import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
    @FXML
    private Label nameTop;
    @FXML
    private Label emailTop;
    @FXML
    private Label level;

    @FXML
    private Button editButton;

    @FXML
    private Button saveButton;
    @FXML
    private TextField name;

    @FXML
    private TextField email;
    @FXML
    private DatePicker dobpicker;
    @FXML
    private TextField dob;
    @FXML
    private TextField cccd;

    @FXML
    private Label gioiTinh;

    @FXML
    private HBox toggleGioiTinh;

    @FXML
    private RadioButton maleRadioButton;

    @FXML
    private RadioButton femaleRadioButton;

    private ToggleGroup toggleGroup;

    @FXML
    private TextField phone;

    @FXML
    private TextField address;

    private Passenger passenger;
    private boolean isEditMode = false;

    private String ID_Account = "ACC1";

    @FXML
    private void handleEditButtonClick() {
        isEditMode = true;
        setEditable(true);
    }

    @FXML
    private void handleSaveButtonClick() {
        if (isEditMode) {
            passenger.setHo_va_Ten(name.getText());
            passenger.setDate(dobpicker.getValue());
            System.out.println(dobpicker.getValue());
            passenger.setCccd(cccd.getText());

            RadioButton selectedRadioButton = (RadioButton) toggleGroup.getSelectedToggle();
            if (selectedRadioButton == maleRadioButton) {
                passenger.setGender("Nam");
            } else if (selectedRadioButton == femaleRadioButton) {
                passenger.setGender("Nữ");
            }
            passenger.setEmail(email.getText());

            // Update the user data in the database
            DBConnect databaseHandler = new DBConnect();
            databaseHandler.updateUser(passenger);

            // Disable editing mode and reload the user data
            isEditMode = false;
            setEditable(false);
            loadUserData(ID_Account);
        }
    }

    private void setEditable(boolean editable) {
        name.setEditable(editable);
        dob.setVisible(!editable);
        dobpicker.setVisible(editable);
        cccd.setEditable(editable);
        gioiTinh.setVisible(!editable);
        toggleGioiTinh.setVisible(editable);
        email.setEditable(editable);
        address.setEditable(editable);
        phone.setEditable(editable);

        editButton.setVisible(!editable);
        saveButton.setVisible(editable);
    }

    private void loadUserData(String ID_Account) {
        DBConnect databaseHandler = new DBConnect();
        passenger = databaseHandler.fetchUser(ID_Account);

        if (passenger != null) {
            name.setText(passenger.getHo_va_Ten());
            nameTop.setText(passenger.getHo_va_Ten() + " #" + passenger.getID_Passenger());
            emailTop.setText(passenger.getEmail());
            email.setText(passenger.getEmail());
            gioiTinh.setText(passenger.getGender());
            if ("Nam".equalsIgnoreCase(passenger.getGender())) {
                toggleGroup.selectToggle(maleRadioButton);
            } else if ("Nữ".equalsIgnoreCase(passenger.getGender())) {
                toggleGroup.selectToggle(femaleRadioButton);
            }
            dob.setText(passenger.getDate());
            level.setText("Khách hàng hạng " + passenger.getLevel());
            cccd.setText(passenger.getCccd());
            phone.setText(passenger.getPhone());
            address.setText(passenger.getAddress());
        }
    }

    @FXML
    public void initialize(URL location, ResourceBundle resources) {

        toggleGroup = new ToggleGroup();
        maleRadioButton.setToggleGroup(toggleGroup);
        femaleRadioButton.setToggleGroup(toggleGroup);
        loadUserData(ID_Account);

        dobpicker.setValue(passenger.getLocalDate());
    }

}


