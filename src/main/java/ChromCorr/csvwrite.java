package ChromCorr;

import java.io.File;
import com.opencsv.CSVWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Locale;

public class csvwrite {
    private File file;
    private String[] header;
    private double[][] data; //[row] [column]
    
    public csvwrite(File name){
        file = name;
    }
    public void setheader(String[] h) {
        header=h;
    }
    public void setdata(double[][] d) {
        data=d;
    }
    public void setdata(String colname, double[] d) {
        //find row in the header
        int col = 0;
        for (int i = 0; i < header.length; i++) {
            if (colname.equals(header[i])) {
                col = i;
            }
        }
        //set column
        for (int i=0; i<data.length;i++){
            data[i][col]=d[i]; //for all rows
        }
    }
    public void writeall(){
        try {
            file.createNewFile();
            CSVWriter writer = new CSVWriter(new FileWriter(file));
            
            writer.writeNext(header);
            for (int i=0;i<data.length;i++){
                String[] s = new String[data[0].length];
                for (int j=0;j<data[0].length;j++) {
                    s[j] = String.format(Locale.ROOT, "%.4f",data[i][j]);
                }
                writer.writeNext(s,false);
            }
            writer.close();
        } catch (FileNotFoundException ex) {
            
        } catch (IOException ex) {
            
        }
    }
}