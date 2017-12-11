package ChromCorr;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    private static File[] files = new File[3];
    private static double[][] Q = new double[3][2];
    
    public static void init() {
        Q = new double[3][2];
        Q[0][0] = 1; Q[1][1] = 1;
        String lastaffine = ChromCorr.ij.prefs().get("ChromCorr_AffinePath", null);
        if (lastaffine != null){
            ChromCorr.log.info("Open " + lastaffine);
            File a = new File(lastaffine);
            if (a.canRead()){
                Main.OpenAffine(a);
            }
        }
    }
    
    public static void setfile(File file, int val) {
        files[val] = file;
    }

    public static String getfilename(int val) {
        return files[val].getName();
    }
    public static void CalcAffine()   {
        csvread file1 = new csvread(files[0]);
        csvread file2 = new csvread(files[1]);
        String[] h = file1.getheader();
        double[] x1 = file1.getdata("x [nm]");
        double[] y1 = file1.getdata("y [nm]");
        double[] x2 = file2.getdata("x [nm]");
        double[] y2 = file2.getdata("y [nm]");
        AffineTransform atrans = new AffineTransform();
        atrans.loadpositions(x1, y1, x2, y2);
        Q = atrans.getAffine();
    }
    public static String GetAffineS(int i,int j){
        return String.format("%.4f",Q[i][j]);
    }
    public static void SaveAffine(File file){
        DataOutputStream os;
        try {
            os = new DataOutputStream(new FileOutputStream(file));
            for (int i =0;i<3;i++){
                for (int j=0;j<2;j++){
                    os.writeDouble(Q[i][j]);
                }
            }            
            os.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static void OpenAffine(File file){
        ChromCorr.log.info("saving file path " + file.getAbsolutePath());
        ChromCorr.ij.prefs().put("ChromCorr_AffinePath", file.getAbsolutePath());
        DataInputStream is;
        try {
            is = new DataInputStream(new FileInputStream(file));
            for (int i =0;i<3;i++){
                for (int j=0;j<2;j++){
                    Q[i][j]=is.readDouble();
                }
            }            
            is.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void DoAffine(){
        csvread file1 = new csvread(files[0]);
        double[] x1 = file1.getdata("x [nm]");
        double[] y1 = file1.getdata("y [nm]");
        AffineTransform atrans = new AffineTransform();
        atrans.setAffine(Q);
        double[][] res = atrans.correctpositions(x1, y1);
        csvwrite file3 = new csvwrite(files[2]);
        file3.setheader(file1.getheader());
        file3.setdata(file1.getdata());
        file3.setdata("x [nm]",res[0]);
        file3.setdata("y [nm]",res[1]);
        file3.writeall();
    }
}
