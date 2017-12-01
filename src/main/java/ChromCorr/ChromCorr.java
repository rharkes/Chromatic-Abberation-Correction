
package ChromCorr;

import java.io.File;

import net.imagej.ImageJ;
import GUI.MainAppFrame;

import org.scijava.command.Command;
import org.scijava.log.LogService;
import org.scijava.plugin.Parameter;
import org.scijava.plugin.Plugin;

@Plugin(type = Command.class, menuPath = "NKI>Chromatic Aberration Correction")
public class ChromCorr implements Command {

    @Parameter
    private ImageJ ij;

    @Parameter
    private LogService log;

    public static final String PLUGIN_NAME = "Chromatic Aberration Correction";

    @Override
    public void run() {

        log.info("Running " + PLUGIN_NAME);
               
        // Launch JavaFX interface
        MainAppFrame app = new MainAppFrame(ij);
        app.setTitle(PLUGIN_NAME);
        app.init();
        
    }

    public static void main(final String... args) throws Exception {
        // Launch ImageJ as usual.
        final ImageJ ij = net.imagej.Main.launch(args);

        // Launch the command.
        ij.command().run(ChromCorr.class, true);
    }
}
