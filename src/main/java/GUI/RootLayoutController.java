package GUI;

import ChromCorr.*;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.scijava.Context;

public class RootLayoutController implements Initializable {    
    @FXML
    private Button csvButton;
    
    @FXML
    private Text filepth1;
    
    @FXML
    private Text A00,A10,A20,A01,A11,A21,A001,A101,A201,A011,A111,A211;
    
    @FXML
    private Stage stage;
    
    @FXML
    private void handlecsvButton(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open CSV File");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("CSV-files", "*.csv")
            );
        Main.setfile(fileChooser.showOpenDialog(stage), 0);
        filepth1.setText(Main.getfilename(0));
    }
    
    @FXML
    private void handlecsvButton1(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open CSV File");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("CSV-files", "*.csv")
            );
        Main.setfile(fileChooser.showOpenDialog(stage), 0);
    }
    @FXML
    private void handlecsvButton2(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open CSV File");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("CSV-files", "*.csv")
            );
        Main.setfile(fileChooser.showOpenDialog(stage), 1);
        Main.CalcAffine();
        A001.setText(Main.GetAffineS(0, 0));
        A011.setText(Main.GetAffineS(0, 1));
        A101.setText(Main.GetAffineS(1, 0));
        A111.setText(Main.GetAffineS(1, 1));
        A201.setText(Main.GetAffineS(2, 0));
        A211.setText(Main.GetAffineS(2, 1));
    }
    @FXML
    private void handleSaveAffine(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save Affine Transform");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Affine-file", "*.aft")
            );
        Main.SaveAffine(fileChooser.showSaveDialog(stage));
    }
    @FXML
    private void handleOpenAffine(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Affine Transform");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Affine-file", "*.aft")
            );
        Main.OpenAffine(fileChooser.showOpenDialog(stage));
        A00.setText(Main.GetAffineS(0, 0));
        A01.setText(Main.GetAffineS(0, 1));
        A10.setText(Main.GetAffineS(1, 0));
        A11.setText(Main.GetAffineS(1, 1));
        A20.setText(Main.GetAffineS(2, 0));
        A21.setText(Main.GetAffineS(2, 1));
    }
    @FXML
    private void handleDoAffine(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save CSV-file");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("New CSV-file", "*.csv")
            );
        Main.setfile(fileChooser.showSaveDialog(stage),2);
        Main.DoAffine();
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        A00.setText(Main.GetAffineS(0, 0));
        A01.setText(Main.GetAffineS(0, 1));
        A10.setText(Main.GetAffineS(1, 0));
        A11.setText(Main.GetAffineS(1, 1));
        A20.setText(Main.GetAffineS(2, 0));
        A21.setText(Main.GetAffineS(2, 1));
    }

    public void setContext(Context context) {
        context.inject(this);
    }

}
