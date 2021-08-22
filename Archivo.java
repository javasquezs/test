/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package archivossecuenciales2;
import java.io.*;
/**
 *
 * @author PERSONal
 */
public class Archivo {
    //rsonas nuevaPersona = new Personas(nombre1, nombre2, apellido1, apellido2, telefono, correo);
    
    public void escribirPersona(Personas nuevaPersona){
        try {
            File f= new File("agenda.txt");
            FileWriter fw;
            BufferedWriter bw;            
            if(f.exists() && f.length()!=0){
                fw =new FileWriter(f,true);
                bw = new BufferedWriter(fw);
                bw.newLine();
                bw.write(nuevaPersona.getNombre1()+"%"+nuevaPersona.getNombre2()+"%"+nuevaPersona.getApellido1()+"%"+nuevaPersona.getApellido2()+"%"+nuevaPersona.getTelefono()+"%"+nuevaPersona.getCorreo()+"%"+nuevaPersona.getEstado());
            } else {
                fw=new FileWriter(f);
                bw = new BufferedWriter(fw);
                bw.write(nuevaPersona.getNombre1()+"%"+nuevaPersona.getNombre2()+"%"+nuevaPersona.getApellido1()+"%"+nuevaPersona.getApellido2()+"%"+nuevaPersona.getTelefono()+"%"+nuevaPersona.getCorreo()+"%"+nuevaPersona.getEstado());
            }
            bw.close();
            fw.close();            
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public void mostrararchivo(){
        try {
            File f=new File("agenda.txt");            
            if (f.exists()){
                FileReader fr=new FileReader(f);
                BufferedReader br=new BufferedReader(fr);
                String linea;                
                while((linea = br.readLine())!=null){
                    String [] contacto = linea.split("%");
                    Personas p = new Personas(contacto[0],contacto[1],contacto[2],contacto[3],contacto[4],contacto[5],contacto[6]);
                    System.out.println(p.toString());
                }
                br.close();
                fr.close();
            } else {
                System.out.println("Agenda no existente");
            }
            
        } catch (IOException e) {
            System.out.println(e);
        }
    }// fin de mostrararchivo
    
    public void mostrararchivologico(){
        try {
            File f=new File("agenda.txt");            
            if (f.exists()){
                FileReader fr=new FileReader(f);
                BufferedReader br=new BufferedReader(fr);
                String linea;                
                int cantregistros = 0;
                boolean existeregistro = false;
                while((linea = br.readLine())!=null){
                    String [] contacto = linea.split("%");
                    if (contacto[6].equals("1")){
                        Personas p = new Personas(contacto[0],contacto[1],contacto[2],contacto[3],contacto[4],contacto[5],contacto[6]);
                        System.out.println(p.toString());
                        existeregistro = true;
                        cantregistros++;
                    }
                }
                br.close();
                fr.close();
                if (existeregistro==false){
                    System.out.println("Agenda no existente");
                } else {
                    System.out.println("Cantidad de registros: "+cantregistros);
                }
            } else {
                System.out.println("Agenda no existente");
            }
            
        } catch (IOException e) {
            System.out.println(e);
        }
    }// fin de mostrararchivologico    
    
    public void buscar(String nombreBuscar){
        try {
            File f= new File("agenda.txt");
            if (f.exists()){
                FileReader fr=new FileReader(f);
                BufferedReader br=new BufferedReader(fr);
                String linea;                
                boolean encontrado = false;
                while((linea = br.readLine())!=null){
                    String [] contacto = linea.split("%");
                    if (contacto[0].equalsIgnoreCase(nombreBuscar)) {
                        encontrado = true;
                        Personas p = new Personas(contacto[0],contacto[1],contacto[2],contacto[3],contacto[4],contacto[5],contacto[6]);
                        System.out.println("Persona Encontrada: ");
                        System.out.println(p.toString());
                    }
                    
                }
                br.close();
                fr.close();
                if (encontrado == false ){
                    System.out.println("Persona no encontrada!");
                }
            } else {
                System.out.println("No hay registros.");
            }
            
        } catch (Exception e) {
            System.out.println(e);
        }
    }// fin de buscar

    public void buscarlogico(String nombreBuscar){
        try {
            File f= new File("agenda.txt");
            if (f.exists()){
                FileReader fr=new FileReader(f);
                BufferedReader br=new BufferedReader(fr);
                String linea;                
                boolean encontrado = false;
                while((linea = br.readLine())!=null){
                    String [] contacto = linea.split("%");
                    if (contacto[0].equalsIgnoreCase(nombreBuscar) && (contacto[6].equals("1")) ) {
                        encontrado = true;
                        Personas p = new Personas(contacto[0],contacto[1],contacto[2],contacto[3],contacto[4],contacto[5],contacto[6]);
                        System.out.println("Persona Encontrada: ");
                        System.out.println(p.toString());
                    }                   
                }
                br.close();
                fr.close();
                if (encontrado == false ){
                    System.out.println("Persona no encontrada!");
                }
            } else {
                System.out.println("No hay registros.");
            }
            
        } catch (Exception e) {
            System.out.println(e);
        }
    }// fin de buscar logico

    
    public void eliminar (String nombreEliminar){
        try {
            File f= new File("agenda.txt");
            if (f.exists()){
                FileReader fr=new FileReader(f);
                BufferedReader br=new BufferedReader(fr);
                String linea;                
                int numeroLineas=0;
                while((linea = br.readLine())!=null){
                    numeroLineas++;
                }//fin del while
                System.out.println("Cantidad de Registros: "+ numeroLineas);
                String contacto[] = new String[numeroLineas];
                //vamos a inicializar un nuevo buffer
                br.close();
                fr.close();
                br = new BufferedReader(new FileReader(f));
                int i = 0;
                while ((linea=br.readLine())!=null) {
                    contacto[i] = linea;
                    i++;
                }//fin del while
                br.close();
                fr.close();                
                FileWriter fw=new FileWriter(f);
                BufferedWriter bw=new BufferedWriter(fw);
                boolean eliminado = false;
                boolean primerLinea = true;
                for (int j = 0; j < contacto.length; j++) {
                    String nuevaLinea [] = contacto[j].split("%");
                    if (nuevaLinea[0].equalsIgnoreCase(nombreEliminar) ){
                        eliminado = true;
                        System.out.println("Registro eliminado!");
                    } else {
                        if (primerLinea == true){
                            bw.write(contacto[j]);
                            primerLinea = false;
                        } else{
                            bw.newLine();
                            bw.write(contacto[j]);
                        }
                        
                    }
                }//fin del for
                if (eliminado==false) {
                    System.out.println("No se encontro registro.");
                }
                bw.close();
                fw.close();                
                if (numeroLineas==1 && eliminado == true) {
                    f.delete();
                }
            } else {
                System.out.println("No hay registros que eliminar.");
            }
        } catch (Exception e) {
        }
    }//fin eliminar
    
        public void eliminarlogico (String nombreEliminar){
        try {
            File f= new File("agenda.txt");
            if (f.exists()){
                FileReader fr=new FileReader(f);
                BufferedReader br=new BufferedReader(fr);
                String linea;                
                int numeroLineas=0;
                while((linea = br.readLine())!=null){
                    numeroLineas++;
                }//fin del while
                System.out.println("Cantidad de Registros: "+ numeroLineas);
                String contacto[] = new String[numeroLineas];
                //vamos a inicializar un nuevo buffer
                br.close();
                fr.close();
                br = new BufferedReader(new FileReader(f));
                int i = 0;
                while ((linea=br.readLine())!=null) {
                    contacto[i] = linea;
                    i++;
                }//fin del while
                br.close();
                fr.close();                
                FileWriter fw=new FileWriter(f);
                BufferedWriter bw=new BufferedWriter(fw);
                boolean eliminado = false;
                boolean primerLinea = true;
                for (int j = 0; j < contacto.length; j++) {
                    String nuevaLinea [] = contacto[j].split("%");
                    if (nuevaLinea[0].equalsIgnoreCase(nombreEliminar) ){
                        eliminado = true;
                        Personas per= new Personas(nuevaLinea[0],nuevaLinea[1],nuevaLinea[2],nuevaLinea[3],nuevaLinea[4],nuevaLinea[5],"0");
                        if (primerLinea == true){
                            bw.write(per.getNombre1()+"%"+per.getNombre2()+"%"+per.getApellido1()+"%"+per.getApellido2()+"%"+per.getTelefono()+"%"+per.getCorreo()+"%"+per.getEstado());                            
                            primerLinea = false;
                        } else {
                            bw.newLine();
                            bw.write(per.getNombre1()+"%"+per.getNombre2()+"%"+per.getApellido1()+"%"+per.getApellido2()+"%"+per.getTelefono()+"%"+per.getCorreo()+"%"+per.getEstado());                                                        
                        }
                        System.out.println("Registro eliminado logicamente!");
                    } else {
                        if (primerLinea == true){
                            bw.write(contacto[j]);
                            primerLinea = false;
                        } else{
                            bw.newLine();
                            bw.write(contacto[j]);
                        }
                        
                    }
                }//fin del for
                if (eliminado==false) {
                    System.out.println("No se encontro registro.");
                }
                bw.close();
                fw.close();                
            } else {
                System.out.println("No hay registros que eliminar.");
            }
        } catch (Exception e) {
        }
    }//fin eliminar logico
            
}

