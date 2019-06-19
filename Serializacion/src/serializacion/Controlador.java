/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serializacion;

import com.sun.corba.se.impl.io.OutputStreamHook;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Kevin Garcia
 */
public class Controlador implements ActionListener {

    Vista v;
    Estudiante es;
    ArrayList<Estudiante> listaEstudiantes = new ArrayList<>();

    public Controlador(Vista v) throws IOException {
        this.v = v;
        if (fl.exists()) {

        } else {
            fl.createNewFile();
        }
    }

    File fl = new File("Estudiantes.txt");

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if (e.getSource().equals(v.botonIngresar)) {

                es = new Estudiante(v.nombre.getText(), v.codigo.getText(), v.not1.getText(), v.not2.getText(), v.not3.getText());

                ObjectOutputStream oos = null;

                if (fl.length() == 0) {
                    oos = new ObjectOutputStream(new FileOutputStream(fl));
                }else{
                    oos = new MiObjectOutputStream(new FileOutputStream(fl, true));
                }
                oos.writeObject(es);
            }
            
            if(e.getSource().equals(v.botonConsultar)){
                ObjectInputStream ois;
                ois = new ObjectInputStream(new FileInputStream(fl));
                try{
                while(true){
                    es = (Estudiante) ois.readObject();
                    listaEstudiantes.add(es);
                }}catch(EOFException ex){
                    v.mostrar_tabla(listaEstudiantes);
                }
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
        }catch (ClassNotFoundException ex) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
