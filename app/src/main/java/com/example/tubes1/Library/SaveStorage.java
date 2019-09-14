package com.example.tubes1.Library;

import android.content.Context;

import com.example.tubes1.NumberModel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class SaveStorage {
    private File saveFile;
    public SaveStorage(){

    }
    public void writeFile(List<NumberModel> list,Context context){
        FileOutputStream fop = null;
        String in ="";
        while(list.isEmpty()!=true){
            NumberModel nm = list.get(0);
            list.remove(0);
            in+=nm.getOperator();
            in+=nm.getOperand();
            in+="/n";
        }
        saveFile = new File(context.getFilesDir(), "saveState.txt");
        try {
            if(!saveFile.exists()){
                saveFile.createNewFile();
            }
            fop = new FileOutputStream(saveFile);
            byte[] contentInBytes = in.getBytes();
            fop.write(contentInBytes);
            fop.flush();
            fop.close();
        }catch(IOException e){
            e.printStackTrace();
        }

    }
    public String readFile(Context context){
        File saveFile = new File(context.getFilesDir(),"saveState.txt");
        if(!saveFile.exists()){
            return "";
        }
        FileInputStream fis = null;
        String read = "";
        try{
            fis = new FileInputStream(saveFile);
            int content;
            while((content = fis.read()) != -1){
                read+=(char)content+"";

            }
            fis.close();
        }catch(IOException e){
            e.printStackTrace();
        }
        return read;
    }

    public boolean checkFile(Context context){
        File saveFile = new File(context.getFilesDir(),"saveState.txt");
        return saveFile.exists();
    }
}
