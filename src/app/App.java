package app;

import java.lang.reflect.*;
import java.net.URL;
import java.net.URLClassLoader;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Date; 

import java.util.List;

import P1.*;

public class App {

    public static void main(String[] args) throws Exception {
        ExceptionSaver es = new ExceptionSaver();
        String results = System.getProperty("user.dir")+"/results.txt";
        File project = new File(System.getProperty("user.dir")+"/bin/P1");
        
        File[] files = new File(project.getPath()).listFiles();
        File[] dirs = new File(project.getPath()).listFiles(new FileFilter() {
            @Override
            public boolean accept(File file) {
                return file.isDirectory();
            }
        });
        

        try {
            FileWriter fw = new FileWriter(results,false);
            BufferedWriter bw = new BufferedWriter(fw);

            bw.newLine();
			bw.write("Description textuelle -- Date : "+ new Date());
			bw.newLine();
			bw.write("-------------------------------------------------------------------------------------------------------------------------------------");			
			bw.newLine();
			bw.write("Information projet");
			bw.newLine();
			bw.write("-------------------------------------------------------------------------------------------------------------------------------------");			
			bw.newLine();
			bw.write("Nom du projet: "+project.getName());
			bw.newLine();
			bw.write("Nombre de packages: "+(1+dirs.length));
			bw.newLine();
			bw.write("Nombre de classe: "+files.length);
			bw.newLine();
			bw.write("Java version: "+System.getProperty("java.version"));
			bw.newLine();
			bw.write("OS: "+System.getProperty("os.name"));
			bw.newLine();
			bw.write("-------------------------------------------------------------------------------------------------------------------------------------");
			bw.newLine();
			bw.write("Informations classes");
			bw.newLine();
            bw.write("-------------------------------------------------------------------------------------------------------------------------------------");
            bw.newLine();

            int j = 1;
            for (File f : files){
            
                URL[] classURL = {f.toURI().toURL()};
                URLClassLoader urlClassLoader = new URLClassLoader(classURL);
                Class currentClass = urlClassLoader.loadClass("P1."+f.getName().substring(0,f.getName().lastIndexOf('.')));

                try{
                    bw.write("Class "+j+":");
                    bw.newLine();
                    bw.write("Nom Package: "+currentClass.getPackageName());

                    bw.newLine();
                    bw.write("Nom Class: "+ currentClass.getSimpleName());
                    bw.newLine();
                    bw.write("Nombre des attributs publics: "+ currentClass.getFields().length);
                    bw.newLine();
                    bw.write("Liste et types des attributs publics: [ ");
                    Field[] fields = currentClass.getFields();
                    for (Field oneField : fields) {
                        Field field = currentClass.getField(oneField.getName());
                        bw.write(field.getName() + " - " + field.getType().getSimpleName() + ", ");
                    }
                    bw.write("]");
                    bw.newLine();					
                    bw.write("Nombre des attributs privés: "+(currentClass.getDeclaredFields().length - currentClass.getFields().length));
                    bw.newLine();
                    bw.write("Liste et types des attributs privés : [ ");	
                    List<Field> listAtr = new ArrayList<Field>(); 
                    Field[] df = currentClass.getDeclaredFields();
                    Field[] types = currentClass.getFields();
                    for (Field field: df) {
                        listAtr.add(field);
                    }
                    for (Field  t : types) {
                        listAtr.remove(t);
                    }
                    for (int k = 0; k < listAtr.size(); k++) {
                        Field privateField = listAtr.get(k);
                        bw.write(privateField.getName() + " - " + privateField.getType().getSimpleName() + ", ");
                    }
                    bw.write("]");
                    bw.newLine();
                    bw.write("Liste des constructeurs: [ ");
                    Constructor[] constructor = currentClass.getDeclaredConstructors();
                    for (Constructor c: constructor) {
                        bw.write(c.getName() + " ");
                    }
                    bw.write("]");
                    bw.newLine();
                    Method[] declaredMethodes = currentClass.getDeclaredMethods();
                    bw.write("Nombre de méthodes: " + declaredMethodes.length);
                    bw.newLine();
                    bw.write("Liste des méthodes: [ ");
                    for (Method meth : declaredMethodes) {
                        bw.write(meth.getName() + ", ");
                    }
                    bw.write("]");
                    bw.newLine();
                    bw.write("Type de retour des méthodes: [ ");
                    for (Method meth : declaredMethodes) {
                        bw.write(meth.getName() + ": " + meth.getReturnType().getSimpleName() +", ");
                    }
                    bw.write("]");
                    bw.newLine();
                    bw.write("Paramètres et types des méthodes: ");
                    for (Method meth : declaredMethodes) {
                        bw.write(meth.getName()+"[");
                        Class[] pTypes = meth.getParameterTypes();
                        for (int k = 0; k < pTypes.length; k++) {
                            bw.write("param" + (k+1) + ": " + pTypes[k].getSimpleName() +", ");
                        }
                        bw.write("], ");
                    }
                    bw.newLine();
                    bw.write("-------------------------------------------------------------------------------------------------------------------------------------");
                    bw.newLine();			
                } catch (Exception e ){
                    System.out.println("Exception generer dans Class 1");
                    es.save(e);
                }
                j+=1;
            }
            bw.close();
            fw.close();
        }catch (final IOException ioe) {
            es.add(ioe);
            es.save(ioe);

        } catch (final Exception ee) {
            System.out.println("General Exception catched and saved");
            es.save(ee);
            es.add(ee);
        }

        try{
            final FileReader fr = new FileReader(results);
            final BufferedReader br = new BufferedReader(fr);
            try {
                String ln = br.readLine();
                while (ln != null) {
                    System.out.println(ln);
                    ln = br.readLine();
                }
                br.close();
                fr.close();
            } catch (final IOException ioe) {
            
                System.out.println("error while reading");
                es.save(ioe);
                es.add(ioe);
            }

        } catch (final FileNotFoundException fnf) {
            
            es.save(fnf);
            es.add(fnf);
            System.out.println("File not found exception saved create file");
        } catch (final Exception ee) {
            System.out.println("General Exception catched and saved");
            es.save(ee);
            es.add(ee);
        }
    
    
    }

}
