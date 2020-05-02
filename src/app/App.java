package app;

import java.lang.reflect.*;
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
        File project = new File(System.getProperty("user.dir")+"/src/P1");
        File[] files = new File(project.getPath()).listFiles();
        File[] dirs = new File(project.getPath()).listFiles(new FileFilter() {
            @Override
            public boolean accept(File file) {
                return file.isDirectory();
            }
        });
        
        Class employe = Employee.class;
        Class entreprise = Entreprise.class;
        
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
			bw.write("Nombwe de packages: "+(1+dirs.length));
			bw.newLine();
			bw.write("Nombwe de classe: "+files.length);
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
            try{
                bw.write("Class 1:");
                bw.newLine();
                bw.write("Nom Package: "+employe.getPackageName());

                bw.newLine();
                bw.write("Nom Class: "+ employe.getSimpleName());
                bw.newLine();
                bw.write("Nombre des attributs publics: "+ employe.getFields().length);
                bw.newLine();
                bw.write("Liste et types des attributs publics: [ ");
                Field[] fields = employe.getFields();
                for (Field oneField : fields) {
                    Field field = employe.getField(oneField.getName());
                    bw.write(field.getName() + " - " + field.getType().getSimpleName() + ", ");
                }
                bw.write("]");
                bw.newLine();					
                bw.write("Nombre des attributs privés: "+(employe.getDeclaredFields().length - employe.getFields().length));
                bw.newLine();
                bw.write("Liste et types des attributs privés : [ ");	
                List<Field> listAtr = new ArrayList<Field>(); 
                Field[] df = employe.getDeclaredFields();
                Field[] types = employe.getFields();
                for (Field f : df) {
                    listAtr.add(f);
                }
                for (Field f : types) {
                    listAtr.remove(f);
                }
                for (int k = 0; k < listAtr.size(); k++) {
                    Field privateField = listAtr.get(k);
                    bw.write(privateField.getName() + " - " + privateField.getType().getSimpleName() + ", ");
                }
                bw.write("]");
                bw.newLine();
                bw.write("Liste des constructeurs: [ ");
                Constructor[] constructor = employe.getDeclaredConstructors();
                for (Constructor c: constructor) {
                    bw.write(c.getName() + " ");
                }
                bw.write("]");
                bw.newLine();
                Method[] declaredMethodes = employe.getDeclaredMethods();
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

            try {
                bw.write("Class 2:");
                bw.newLine();
                bw.write("Nom Package: "+entreprise.getPackageName());
    
                bw.newLine();
                bw.write("Nom Class: "+ entreprise.getSimpleName());
                bw.newLine();
                bw.write("Nombre des attributs publics: "+ entreprise.getFields().length);
                bw.newLine();
                bw.write("Liste et types des attributs publics: [ ");
                Field[] fields = entreprise.getFields();
                for (Field oneField : fields) {
                    Field field = entreprise.getField(oneField.getName());
                    bw.write(field.getName() + " - " + field.getType().getSimpleName() + ", ");
                }
                bw.write("]");
                bw.newLine();					
                bw.write("Nombre des attributs privés: "+(entreprise.getDeclaredFields().length - entreprise.getFields().length));
                bw.newLine();
                bw.write("Liste et types des attributs privés : [ ");	
                List<Field> listAtr = new ArrayList<Field>(); 
                Field[] df = entreprise.getDeclaredFields();
                Field[] types = entreprise.getFields();
                for (Field f : df) {
                    listAtr.add(f);
                }
                for (Field f : types) {
                    listAtr.remove(f);
                }
                for (int k = 0; k < listAtr.size(); k++) {
                    Field privateField = listAtr.get(k);
                    bw.write(privateField.getName() + " - " + privateField.getType().getSimpleName() + ", ");
                }
                bw.write("]");
                bw.newLine();
                bw.write("Liste des constructeurs: [ ");
                Constructor[] constructor = entreprise.getDeclaredConstructors();
                for (Constructor c: constructor) {
                    bw.write(c.getName() + " ");
                }
                bw.write("]");
                bw.newLine();
                Method[] declaredMethodes = entreprise.getDeclaredMethods();
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
            }
            catch (Exception e ){
                System.out.println("Exception génerer dans class 2");
                es.save(e);
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
