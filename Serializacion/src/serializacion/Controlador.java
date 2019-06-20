/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serializacion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Kevin Garcia
 */
public class Controlador implements ActionListener {

    Vista v;

    public Controlador(Vista v) throws IOException {
        this.v = v;

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource().equals(v.botonIngresar)) {
            try {
                insertarEstudiante();
            } catch (IOException ex) {
                Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if (e.getSource().equals(v.botonConsultar)) {
            if (v.datoConsultar.getText().equals("")) {
                try {
                    //v.mostrar_tabla(listaEstudiantes);
                    v.mostrar_tabla(leerArchivo());
                } catch (IOException | ClassNotFoundException ex) {
                    Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
                }
                v.datoConsultar.setText("");
            } else {
                try {
                    if(existeCodigo(v.datoConsultar.getText())){
                        v.mostrar_tabla(leerUnoSolo(v.datoConsultar.getText()));
                    }else{
                        JOptionPane.showMessageDialog(null, "El codigo no existe");
                    }
                    
                } catch (IOException | ClassNotFoundException ex) {
                    Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
                }
                v.datoConsultar.setText("");
            }

        }

        if (e.getSource().equals(v.botonEditar)) {
            try {
                if(existeCodigo(v.datoConsultar2.getText())){
                    
                
                editar(v.datoConsultar2.getText());
                }else{
                        JOptionPane.showMessageDialog(null, "El codigo no existe");
                    }
            } catch (IOException ex) {
                Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if (e.getSource().equals(v.botonBorrar)) {
            try {
                borrar(v.datoConsultar.getText());
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Ingrese un codigo valido");
            }
            v.datoConsultar.setText("");
        }

    }

    public void insertarEstudiante() throws IOException {
        ArrayList<Estudiante> listaEstudiantes = new ArrayList<>();

        try {
            File fl = new File("Estudiantes.txt");
            OutputStream file;
            ObjectOutput output;

            if (fl.exists()) {
                InputStream filAux;
                ObjectInput input1;
                filAux = new FileInputStream("Estudiantes.txt");
                input1 = new ObjectInputStream(filAux);
                listaEstudiantes = (ArrayList<Estudiante>) input1.readObject();
                input1.close();
                fl.delete();
            }

            file = new FileOutputStream("Estudiantes.txt");
            output = new ObjectOutputStream(file);

            ArrayList<Estudiante> aux = new ArrayList<>();
            Estudiante es = new Estudiante();
            Iterator it = listaEstudiantes.iterator();
            while (it.hasNext()) {
                es = (Estudiante) it.next();
                aux.add(es);
            }

            for (int i = 0; i < aux.size(); i++) {
                if (aux.get(i).getCodigo() == null ? v.codigo.getText() == null : aux.get(i).getCodigo().equals(v.codigo.getText())) {
                    output.writeObject(aux);
                    output.close();
                    JOptionPane.showMessageDialog(null, "El codigo ya existe");
                    break;
                }
            }

            es = new Estudiante();
            es.setNombre(v.nombre.getText());
            es.setCodigo(v.codigo.getText());
            es.setNota1(v.not1.getText());
            es.setNota2(v.not2.getText());
            es.setNota3(v.not3.getText());
            es.promedio();
            aux.add(es);

            output.writeObject(aux);
            output.close();
        } catch (IOException | ClassNotFoundException | NumberFormatException e) {
            System.out.print(e);
        }
    }

    public void editar(String codigo) throws IOException {
        ArrayList<Estudiante> listaEstudiantes = new ArrayList();
        try {
            File fl = new File("Estudiantes.txt");
            OutputStream file;
            ObjectOutput output;

            if (fl.exists()) {
                InputStream fileAux;
                ObjectInput input1;
                fileAux = new FileInputStream("Estudiantes.txt");
                input1 = new ObjectInputStream(fileAux);
                listaEstudiantes = (ArrayList<Estudiante>) input1.readObject();
                input1.close();
                fl.delete();
            }

            file = new FileOutputStream("Estudiantes.txt");
            output = new ObjectOutputStream(file);

            ArrayList<Estudiante> aux = new ArrayList<>();
            Estudiante es = new Estudiante();
            Iterator it = listaEstudiantes.iterator();
            while (it.hasNext()) {
                es = (Estudiante) it.next();
                aux.add(es);
            }

            for (int i = 0; i < aux.size(); i++) {
                if (aux.get(i).getCodigo() == null ? codigo == null : aux.get(i).getCodigo().equals(codigo)) {
                    es = new Estudiante();
                    es.setNombre(v.nombre2.getText());
                    es.setCodigo(v.datoConsultar2.getText());
                    es.setNota1(v.not12.getText());
                    es.setNota2(v.not22.getText());
                    es.setNota3(v.not32.getText());
                    es.promedio();
                    aux.set(i, es);
                }
            }

            output.writeObject(aux);
            output.close();
        } catch (IOException | ClassNotFoundException | NumberFormatException e) {
            System.out.print(e);
        }
    }

    public void borrar(String codigo) throws IOException {
        ArrayList<Estudiante> listaEstudiantes = new ArrayList<>();

        try {
            File fl = new File("Estudiantes.txt");
            OutputStream file;
            ObjectOutput output;

            if (fl.exists()) {
                InputStream filAux;
                ObjectInput input1;
                filAux = new FileInputStream("Estudiantes.txt");
                input1 = new ObjectInputStream(filAux);
                listaEstudiantes = (ArrayList<Estudiante>) input1.readObject();
                input1.close();
                fl.delete();
            }

            file = new FileOutputStream("Estudiantes.txt");
            output = new ObjectOutputStream(file);

            ArrayList<Estudiante> aux = new ArrayList<>();
            Estudiante es = new Estudiante();
            Iterator it = listaEstudiantes.iterator();
            while (it.hasNext()) {
                es = (Estudiante) it.next();
                aux.add(es);
            }

            for (int i = 0; i < aux.size(); i++) {
                if (aux.get(i).getCodigo() == null ? codigo == null : aux.get(i).getCodigo().equals(codigo)) {
                    aux.remove(i);
                    break;
                }
            }
            output.writeObject(aux);
            output.close();

        } catch (IOException | ClassNotFoundException | NumberFormatException e) {
            System.out.print(e);
        }
    }

    public ArrayList leerArchivo() throws FileNotFoundException, IOException, ClassNotFoundException {
        File fl = new File("Estudiantes.txt");
        ArrayList<Estudiante> listaEstudiantes = new ArrayList<>();
        ObjectInputStream ois;
        ois = new ObjectInputStream(new FileInputStream(fl));
        try {
            while (true) {
                listaEstudiantes = (ArrayList) ois.readObject();

            }
        } catch (EOFException ex) {

        }

        return listaEstudiantes;
    }

    public ArrayList leerUnoSolo(String codigo) throws FileNotFoundException, IOException, ClassNotFoundException {
        File fl = new File("Estudiantes.txt");
        ArrayList<Estudiante> listaEstudiantes = new ArrayList<>();
        ArrayList<Estudiante> aux2 = new ArrayList<>();
        ObjectInputStream ois;
        ois = new ObjectInputStream(new FileInputStream(fl));
        try {
            while (true) {
                listaEstudiantes = (ArrayList) ois.readObject();

            }
        } catch (EOFException ex) {

        }
        aux2.add(new Estudiante());
        for (int i = 0; i < listaEstudiantes.size(); i++) {
            String codigocom = listaEstudiantes.get(i).getCodigo();
            if(codigocom.equals(codigo)){
                aux2.set(0, listaEstudiantes.get(i));
            }
        }
        return aux2;

    }
    
    public boolean existeCodigo(String codigo) throws FileNotFoundException, IOException, ClassNotFoundException{
        File fl = new File("Estudiantes.txt");
        ArrayList<Estudiante> listaEstudiantes = new ArrayList<>();
        ArrayList<Estudiante> aux2 = new ArrayList<>();
        ObjectInputStream ois;
        ois = new ObjectInputStream(new FileInputStream(fl));
        try {
            while (true) {
                listaEstudiantes = (ArrayList) ois.readObject();
            }
        } catch (EOFException ex) {
            
        }
        aux2.add(new Estudiante());
        for (int i = 0; i < listaEstudiantes.size(); i++) {
            String codigocom = listaEstudiantes.get(i).getCodigo();
            if(codigocom.equals(codigo)){
                return true;
            }
        }
        return false;
    }

}
