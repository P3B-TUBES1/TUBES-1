package com.example.tubes1;

import android.content.Context;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class Storage {
    private File file;
    public Storage(){

    }
    public void writeFile(double n,Context context, String fileName){
        FileOutputStream fop = null;
        String in =readFile(context,fileName)+"\n"+n;
        file = new File(context.getFilesDir(), "fileName+'.txt'");
        try {
            if(!file.exists()){
                file.createNewFile();
            }
            fop = new FileOutputStream(file);
            byte[] contentInBytes = in.getBytes();
            fop.write(contentInBytes);
            fop.flush();
            fop.close();
            Log.d("TESTT",n+"");
        }catch(IOException e){
            e.printStackTrace();
        }

    }
    public String readFile(Context context,String fileName){
        FileInputStream fis = null;
        File file = new File(context.getFilesDir(),"fileName+'.txt'");
        String read = "";
        try{
            fis = new FileInputStream(file);
            int content;
            while((content = fis.read()) != -1){
                read+=(char)content+"";

            }
        }catch(IOException e){
            e.printStackTrace();
        }
        return read;
    }
}
