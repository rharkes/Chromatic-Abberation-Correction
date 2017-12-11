package GUI;
import java.io.IOException;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.TilePane;
import javax.swing.JFrame;
import net.imagej.ImageJ;
public class MainAppFrame extends JFrame {

    private ImageJ ij;

    private JFXPanel fxPanel;

    public MainAppFrame(ImageJ ij) {
        ij.context().inject(this);
        this.ij = ij;
    }

    /**
     * Create the JFXPanel that make the link between Swing (IJ) and JavaFX plugin.
     */
    public void init() {
        this.fxPanel = new JFXPanel();
        this.add(this.fxPanel);
        this.setVisible(true);

        // The call to runLater() avoid a mix between JavaFX thread and Swing thread.
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                initFX(fxPanel);
            }
        });

    }

    public void initFX(JFXPanel fxPanel) {
        // Init the root layout
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/GUI/RootLayout.fxml"));
            TilePane rootLayout = (TilePane) loader.load();

            // Get the controller and add an ImageJ context to it.
            RootLayoutController controller = loader.getController();
            controller.setContext(ij.context());

            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            this.fxPanel.setScene(scene);
            this.fxPanel.show();

            // Resize the JFrame to the JavaFX scene
            this.setSize((int) scene.getWidth(), (int) scene.getHeight());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
