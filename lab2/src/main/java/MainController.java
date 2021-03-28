import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

public class MainController {
    @FXML
    private ListView<String> ListLeft;

    @FXML
    private ListView<String> ListRight;

    @FXML
    private TextField LeftSource;

    @FXML
    private TextField RightSource;

    @FXML
    private TextField DirectoryFileNameLeft;

    @FXML
    private TextField DirectoryFileNameRight;

    @FXML
    private Label spaceLeft;

    @FXML
    private Label spaceRight;


    @FXML
    private ObservableList<String> OListLeft = FXCollections.observableArrayList();
    private ObservableList<String> OListRight = FXCollections.observableArrayList();

    public Controller controller = new Controller();

    public void showDataInTable() {
        ListLeft.setItems(OListLeft);
        ListRight.setItems(OListRight);
    }

    public void initialize() {
        OListLeft = FXCollections.observableArrayList(controller.showFiles(controller.file));
        OListRight = FXCollections.observableArrayList(controller.showFiles(controller.file2));
        showDataInTable();
        LeftSource.setText(controller.file.getPath());
        RightSource.setText(controller.file2.getPath());
        spaceLeft.setText(controller.getSpace(controller.file));
        spaceRight.setText(controller.getSpace(controller.file2));
    }

    public void LeftGoBack() {
        String dir = controller.file.getParent();
        controller.file = new File(dir);
        initialize();

    }

    public void RightGoBack() {
        String dir = controller.file2.getParent();
        controller.file2 = new File(dir);
        initialize();

    }

    public void OpenFolderOrFileLeft (MouseEvent event) {
        if (event.getClickCount() == 2 && !event.isConsumed()) {
            event.consume();

            String path = ListLeft.getSelectionModel().getSelectedItem();
            if(controller.OpenFile(controller.file, Paths.get(controller.file.getPath() + "/" + path)) == 0) { //funkcja do otwierania plików
                controller.file = new File(controller.file.getPath() + "/" + path); //jesli zwróci 0 to mamy doczynienia z folderem
            }
            initialize();
        }

    }

    public void OpenFolderOrFileRight (MouseEvent event) {
        if (event.getClickCount() == 2 && !event.isConsumed()) {
            event.consume();

            String path = ListRight.getSelectionModel().getSelectedItem();
            if(controller.OpenFile(controller.file2, Paths.get(controller.file2.getPath() + "/" + path)) == 0) { //funkcja do otwierania plików
                controller.file2 = new File(controller.file2.getPath() + "/" + path); //jesli zwróci 0 to mamy doczynienia z folderem
            }
            initialize();
        }
    }

    public void CreateDirectoryLeft (Event event) {
        controller.CreateDirectory(controller.file, DirectoryFileNameLeft.getText());
        initialize();
    }

    public void CreateDirectoryRight (Event event) {
        controller.CreateDirectory(controller.file2, DirectoryFileNameRight.getText());
        initialize();
    }

    public void CreateFileLeft (Event event) {
        controller.CreateFile(controller.file, DirectoryFileNameLeft.getText());
        initialize();
    }

    public void CreateFileRight (Event event) {
        controller.CreateFile(controller.file2, DirectoryFileNameRight.getText());
        initialize();
    }

    public void Delete (Event event) {
        if(ListLeft.isManaged()) {
            String dirToDelete = ListLeft.getSelectionModel().getSelectedItem();
            Path dir = Paths.get(controller.file + "/" + dirToDelete);
            controller.DeleteDirectory(dir);
        }
        if(ListRight.isManaged()) {
            String dirToDelete = ListRight.getSelectionModel().getSelectedItem();
            Path dir = Paths.get(controller.file2 + "/" + dirToDelete);
            controller.DeleteDirectory(dir);
        }
        initialize();
    }

    public void CopyDirectoryEvent (Event event) {
        if(ListLeft.isManaged()) {
            String dir = ListLeft.getSelectionModel().getSelectedItem();
            controller.CopyDirectory(controller.file.getPath() + "/" + dir, controller.file2.getPath() + "/" + dir);
        }
        if(ListRight.isManaged()) {
            String dir = ListRight.getSelectionModel().getSelectedItem();
            controller.CopyDirectory(controller.file2.getPath() + "/" + dir, controller.file.getPath() + "/" + dir);
        }
        initialize();
    }

    public void MoveDirectoryEvent (Event event) {
        if(ListLeft.isManaged()) {
            String dir = ListLeft.getSelectionModel().getSelectedItem();
            controller.MoveDirectory(controller.file.getPath() + "/" + dir, controller.file2.getPath() + "/" + dir);
        }
        if(ListRight.isManaged()) {
            String dir = ListRight.getSelectionModel().getSelectedItem();
            controller.MoveDirectory(controller.file2.getPath() + "/" + dir, controller.file.getPath() + "/" + dir);
        }
        initialize();
    }





}
