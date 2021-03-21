import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.event.ActionEvent;

import java.util.Optional;

public class MainController {
    @FXML
    private TableView<Employee> tv_employee;

    @FXML
    private TableColumn<Employee, Integer> col_id;

    @FXML
    private TableColumn<Employee, String> col_name;

    @FXML
    private TableColumn<Employee, String> col_email;

    @FXML
    private TableColumn<Employee, Integer> col_salary;

    @FXML
    private Button bSave;

    @FXML
    private Button bdelete;

    @FXML
    private Button bRefresh;

    @FXML
    private Button bSearch;

    @FXML
    private TextField tf_id;

    @FXML
    private TextField tf_name;

    @FXML
    private TextField tf_email;

    @FXML
    private TextField tf_salary;

    @FXML
    private TextField tf_searchId;

    @FXML
    private TextField tf_searchName;

    @FXML
    private TextField tf_showId;

    @FXML
    private TextField tf_showName;

    @FXML
    private TextField tf_showEmail;

    @FXML
    private TextField tf_showSalary;

    private ObservableList<Employee> employees = FXCollections.observableArrayList();

    @FXML
    void refreshTable(ActionEvent event) {
        employees.clear();
        initialize();
    }

    public void showDataInTable() {
        col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        col_email.setCellValueFactory(new PropertyValueFactory<>("email"));
        col_salary.setCellValueFactory(new PropertyValueFactory<>("salary"));
        tv_employee.setItems(employees);
    }

    public void initialize() {
        EmployeeDAO employeeDAOImpl = new EmployeeDAOImpl();
        employees = FXCollections.observableArrayList(employeeDAOImpl.findAll());
        showDataInTable();
    }

    @FXML
    void saveEmployee(ActionEvent event) {
        EmployeeDAO employeeDAOs = new EmployeeDAOImpl();
        Employee employee = new Employee();
        employee.setId(Integer.parseInt(tf_id.getText()));
        employee.setName(tf_name.getText());
        employee.setEmail(tf_email.getText());
        employee.setSalary(Integer.parseInt(tf_salary.getText()));
        employeeDAOs.save(employee);
    }

    @FXML
    void deleteEmployee(ActionEvent event) {
        EmployeeDAO employeeDAOd = new EmployeeDAOImpl();
        Employee employee = new Employee();
        employee.setId(Integer.parseInt(tf_id.getText()));
        employeeDAOd.delete(employee);
    }

    @FXML
    void searchByIdEmployee(ActionEvent event) {
        EmployeeDAO employeeDAOImpl = new EmployeeDAOImpl();
        int war = Integer.parseInt(tf_searchId.getText());
        tf_showId.setText(Integer.toString(employeeDAOImpl.findOne(war).get().getId()));
        tf_showName.setText(employeeDAOImpl.findOne(war).get().getName());
        tf_showEmail.setText(employeeDAOImpl.findOne(war).get().getEmail());
        tf_showSalary.setText(Float.toString(employeeDAOImpl.findOne(war).get().getSalary()));
    }

    @FXML
    void searchByNameEmployee(ActionEvent event) {
        EmployeeDAO employeeDAOImpl = new EmployeeDAOImpl();
        String war = tf_searchName.getText();
        tf_showId.setText(Integer.toString(employeeDAOImpl.findByName(war).get().getId()));
        tf_showName.setText(employeeDAOImpl.findByName(war).get().getName());
        tf_showEmail.setText(employeeDAOImpl.findByName(war).get().getEmail());
        tf_showSalary.setText(Float.toString(employeeDAOImpl.findByName(war).get().getSalary()));
    }


}
