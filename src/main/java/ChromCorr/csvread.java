package ChromCorr;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import com.opencsv.CSVReader;
import java.util.Iterator;
import java.util.List;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author r.harkes
 */
public class csvread {
    private File file;
    private String[] header;
    private double[][] data; //[row] [column]

    public csvread(File name) {
        file = name;
        try (CSVReader reader = new CSVReader(new FileReader(file))) {
            header = getcsvheader(reader);
            data = getcsvdata(reader);
            reader.close();
        } catch (FileNotFoundException ex) {
            
        } catch (IOException ex) {
            
        }
    }

    public String[] getheader() {
        return header;
    }

    public double[][] getdata() {
        return data;
    }

    public double[] getdata(int row) {
        return data[row];
    }
    
    public double getdata(int row, int col) {
        return data[row][col];
    }

    public double[] getdata(String colname) {
        //find row in the header
        int col = 0;
        for (int i = 0; i < header.length; i++) {
            if (colname.equals(header[i])) {
                col = i;
            }
        }
        //return column
        double[] out = new double[data.length];
        for (int i=0; i<data.length;i++){
            out[i]=data[i][col]; //for all rows
        }
        return out;
    }

    private String[] getcsvheader(CSVReader reader) {
        try {
            String[] head = reader.readNext();
            return head;
        } catch (IOException ex) {
            return null;
        }
    }

    private double[][] getcsvdata(CSVReader reader) {
        try {
            List<String[]> csvdat = reader.readAll();
            Iterator<String[]> itr = csvdat.iterator();
            double[][] out = new double[csvdat.size()][header.length];
            int ct = 0;
            while(itr.hasNext()){ 
                String[] line = itr.next();
                for (int i = 0; i < header.length; i++) {
                    out[ct][i] = Double.valueOf(line[i]);
                }
                ct++;
            }
            return out;
        } catch (IOException ex) {
            return null;
        }
    }
}
