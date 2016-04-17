/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IO.CopyFile;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;
import java.util.TreeMap;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

/**
 *
 * @author Administrator
 */
public class CopyFileA {

    TreeMap<String, Integer> tm = new TreeMap<String, Integer>();

    public CopyFileA() {
    }

    ;
    public CopyFileA(String srcFolder, String targetFolder, ArrayList<String> boxArr) throws IOException {
        copy(srcFolder, targetFolder, boxArr);
    }

    private void copy(String srcFolder, String targetFolder, ArrayList<String> boxArr) throws IOException {
        File scrFile = new File(srcFolder);
        File desFile = new File(targetFolder);
        if (!desFile.exists()) {
            desFile.mkdirs();
        }
        fileAction(scrFile, desFile, boxArr);
    }

    private void fileAction(File scrFile, File destFile, ArrayList<String> boxArr) throws IOException {

        if (scrFile.isDirectory()) {

            File[] fileArray = scrFile.listFiles();
            for (File f : fileArray) {
//                System.out.println(f);
                fileAction(f, destFile, boxArr);

            }
        } else {
            for (String name : boxArr) {
                if (scrFile.getName().endsWith(name)) {
                    String newName = check(scrFile.getName(), tm);
                    File newFile = new File(destFile, newName);
                    copyFileStart(scrFile, newFile);
                }
            }
        }
    }

    private String check(String name, TreeMap<String, Integer> tm) {
        Integer i = tm.get(name);
        StringBuilder sb = new StringBuilder();
        if (i == null) {
            tm.put(name, 1);
        } else {
            i++;
            tm.put(name, i);
        }
        Set<String> set = tm.keySet();
        for (String key : set) {
            if (name.equals(key)) {
                Integer value = tm.get(key);
                System.out.println(key);
                String[] tempName = new String[8];
                int index = key.lastIndexOf(".");
                System.out.println(index);
                tempName[0] = key.substring(0,index);
                tempName[1] = key.substring(index+1);
                if (value == 1) {
                    sb.append(tempName[0]).append(".").append(tempName[1]);
                } else {
                    sb.append(tempName[0]).append("(").append(value).append(")").append(".").append(tempName[1]);
                }
            }
        }
        return sb.toString();
    }

    private void copyFileStart(File scrFile, File destFile)
            throws IOException {
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(
                scrFile));
        BufferedOutputStream bos = new BufferedOutputStream(
                new FileOutputStream(destFile));

        byte[] bys = new byte[1024];
        int len = 0;
       
        while ((len = bis.read(bys)) != -1) {
            System.out.println(len);
            bos.write(bys, 0, len);
        }

        bos.close();
        bis.close();
    }
}
