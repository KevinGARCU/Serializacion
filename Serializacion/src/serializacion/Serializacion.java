/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serializacion;

import java.io.IOException;

/**
 *
 * @author Estudiantes
 */
public class Serializacion {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
       Vista v = new Vista();
        Controlador c = new Controlador(v);
        v.asignarOyentes(c);
    }
    
}
