import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;

import Cars.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.TableData;

public class MainController {

    @FXML
    private TextField tf_className;

    @FXML
    private TextField fieldName;

    @FXML
    private TextField value;

    @FXML
    private TextField methodName;

    @FXML
    private Label resultLabel;

    @FXML
    private TableView<TableData> tableFields;

    @FXML
    private TableColumn<TableData, Integer> col_id;

    @FXML
    private TableColumn<TableData, String> col_type;

    @FXML
    private TableColumn<TableData, String> col_name;

    @FXML
    private TableColumn<TableData, String> col_value;

    @FXML
    private TableView<TableData> tableMethods;

    @FXML
    private TableColumn<TableData, Integer> col_id2;

    @FXML
    private TableColumn<TableData, String> col_type2;

    @FXML
    private TableColumn<TableData, String> col_name2;

    private ObservableList<TableData> OTableFields = FXCollections.observableArrayList();
    private ObservableList<TableData> OTableMethods = FXCollections.observableArrayList();

    Object classInstance = null;

    public void initialize() {
        col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        col_type.setCellValueFactory(new PropertyValueFactory<>("type"));
        col_value.setCellValueFactory(new PropertyValueFactory<>("value"));
        tableFields.setItems(OTableFields);

        col_id2.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_name2.setCellValueFactory(new PropertyValueFactory<>("name"));
        col_type2.setCellValueFactory(new PropertyValueFactory<>("type"));
        tableFields.setItems(OTableMethods);
    }

    public void CreateListFields() {
        OTableFields.clear();
        System.out.println("\nListowanie pól\n");
        int counter = 0;
        try {
            Field fields[] = classInstance.getClass().getDeclaredFields();

            for(Field field : fields) {
                counter++;
                field.setAccessible(true);
                OTableFields.add(new TableData(counter, field.getGenericType().getTypeName(),field.getName(), field.get(classInstance).toString()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        tableFields.setItems(OTableFields);

    }

    public void CreateListMethods() {
        OTableMethods.clear();
        System.out.println("\nListowanie metod\n");
        int counter = 0;
        try {
            Method methods[] = classInstance.getClass().getDeclaredMethods();
            for(Method method : methods) {
                counter++;
                method.setAccessible(true);
                OTableMethods.add(new TableData(counter, method.getReturnType().toString(), method.getName()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        tableMethods.setItems(OTableMethods);
    }

    public void EditField(String MethodName, String value, String type) {
        MethodName = "set" + MethodName.substring(0,1).toUpperCase() + MethodName.substring(1);
        Object object = classInstance;
        if (type.equals("java.lang.String")) {
            try {
                Method m = object.getClass().getMethod(MethodName, String.class);
                System.out.println(m.invoke(object, value));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else if (type.equals("double")) {
            try {
                Method m = object.getClass().getMethod(MethodName, double.class);
                System.out.println(m.invoke(object, Double.parseDouble(value)));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else if (type.equals("int")) {
            try {
                Method m = object.getClass().getMethod(MethodName, int.class);
                System.out.println(m.invoke(object, Integer.parseInt(value)));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else if (type.equals("java.lang.Boolean")) {
            try {
                Method m = object.getClass().getMethod(MethodName, Boolean.class);
                System.out.println(m.invoke(object, Boolean.valueOf(value)));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else if (type.equals("java.lang.Enum")) {
            try {
                Method m = object.getClass().getMethod(MethodName, Enum.class);
                System.out.println(m.invoke(object, Enum.valueOf(Cars.CarType.class, value)));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else if (type.equals("java.util.Date")) {
            try {
                Method m = object.getClass().getMethod(MethodName, java.util.Date.class);
                Date date = new SimpleDateFormat("dd/MM/yyyy").parse(value);
                System.out.println(m.invoke(object, date));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


    }

    public String GetFieldType(String name) {
        Field field = null;
        String type = null;
        try {
            field = classInstance.getClass().getDeclaredField(name);
            System.out.println(field.getGenericType().getTypeName());
            type = field.getGenericType().getTypeName();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return type;

    }
    public void ChangeFieldValue() {
        String type = GetFieldType(fieldName.getText());
        EditField(fieldName.getText(), value.getText(), type);
        CreateListFields();
    }

    public void ExecuteMethod() {
        System.out.println("\nWywolanie metody po nazwie\n");
        try {
            Method m = classInstance.getClass().getDeclaredMethod(methodName.getText());
            m.setAccessible(true);
            resultLabel.setText("  " + m.invoke(classInstance, null).toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void CreateObject() {
        String className = tf_className.getText();
        try {
            Class cls = Class.forName(className);
            classInstance = (Object) cls.newInstance();
            System.out.println("instance: " + classInstance.getClass());
        } catch (Exception e) {
            e.printStackTrace();
        }
        CreateListFields();
        CreateListMethods();
    }



    public static void main(String[] args) {

    }


}
