/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serializacion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Estudiantes
 */
public class Vista extends JFrame implements ActionListener {

    Estudiante c;

    JTabbedPane pestañas = new JTabbedPane();
    JTable tabla = new JTable();
    JScrollPane sTabla = new JScrollPane(tabla);
    
    ArrayList<Estudiante> estudiantes = new ArrayList<>();

    JButton botonIngresar, botonConsultar, botonGuardar;

    JTextField estudiante;
    JTextField codigo;
    JTextField not1;
    JTextField not3;
    JTextField not2;

    File f = new File("\\Datos.txt");
    BufferedWriter bw;

    public Vista() {
        ventana();
        ingresar();
        pestanaCosultar();
        tabla.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new String[]{
                    "Codigo", "Nombre", "Nota 1", "Nota 2", "Nota 3", "Promedio"
                }
        ));
    }

    public void ventana() {
        setBounds(500, 500, 700, 500);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Listado de estudiantes");
        add(pestañas);
    }

    public void ingresar() {

        JPanel panelIngresar = new JPanel();

        panelIngresar.setLayout(null);

        botonIngresar = new JButton("Ingresar datos");
        botonIngresar.setBounds(30, 380, 150, 25);
        botonIngresar.addActionListener(this);

        botonGuardar = new JButton("Guardar datos");
        botonGuardar.setBounds(220, 380, 150, 25);
        botonGuardar.addActionListener(this);

        JLabel mensajeingresoE = new JLabel("Ingrese el nombre del estudiante");
        mensajeingresoE.setBounds(10, 80, 250, 50);

        estudiante = new JTextField();
        estudiante.setBounds(10, 135, 190, 25);

        JLabel mensajeingresoC = new JLabel("Ingrese el codigo del estudiante");
        mensajeingresoC.setBounds(290, 80, 250, 50);

        codigo = new JTextField();
        codigo.setBounds(290, 135, 190, 25);

        JLabel nota1 = new JLabel("Nota1");
        JLabel nota2 = new JLabel("Nota2");
        JLabel nota3 = new JLabel("Nota3");

        nota1.setBounds(40, 185, 50, 30);
        nota2.setBounds(200, 185, 50, 30);
        nota3.setBounds(360, 185, 50, 30);

        not1 = new JTextField();
        not3 = new JTextField();
        not2 = new JTextField();

        not1.setBounds(40, 210, 30, 30);
        not2.setBounds(200, 210, 30, 30);
        not3.setBounds(360, 210, 30, 30);

        panelIngresar.add(botonIngresar);
        panelIngresar.add(botonGuardar);
        panelIngresar.add(mensajeingresoE);
        panelIngresar.add(estudiante);
        panelIngresar.add(mensajeingresoC);
        panelIngresar.add(codigo);
        panelIngresar.add(nota1);
        panelIngresar.add(nota2);
        panelIngresar.add(nota3);
        panelIngresar.add(not1);
        panelIngresar.add(not2);
        panelIngresar.add(not3);

        pestañas.add("REGISTRAR", panelIngresar);
    }

    public void pestanaCosultar() {
        JPanel panelConsultar = new JPanel();
        panelConsultar.setLayout(null);
        botonConsultar = new JButton("Consultar");
        botonConsultar.setBounds(60, 30, 100, 25);
        botonConsultar.addActionListener(this);

        sTabla.setBounds(30, 80, 600, 350);
        tabla.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new String[]{
                    "Codigo", "Nombre", "Nota 1", "Nota 2", "Nota 3", "Promedio"
                }
        ));
        panelConsultar.add(botonConsultar);

        panelConsultar.add(sTabla);

        pestañas.add("CONSULTAR", panelConsultar);
    }

    public void mostrar_tabla(ArrayList Datos) {

        String aux1, aux2, aux3;

        ArrayList<Estudiante> lista = new ArrayList();

        lista = Datos;

        DefaultTableModel model = (DefaultTableModel) tabla.getModel();
        int rows = model.getDataVector().size();
        for (int i = 0; i < rows; i++) {
            model.removeRow(0);
        }
        String[] filas = new String[6];
        for (int i = 0; i < lista.size(); i++) {
            filas[0] = lista.get(i).codigo;
            filas[1] = lista.get(i).nombre;
            filas[2] = lista.get(i).nota1;
            filas[3] = lista.get(i).nota2;
            filas[4] = lista.get(i).nota3;
            filas[5] = lista.get(i).promedio;
            model.addRow(filas);
        }
        tabla.setModel(model);
    }

    public void hacerSerializacion(ArrayList datosEstudiantes) {

        try(ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream("D:\\estudiantes.txt"))){
            //Escribimos en un fichero
            oos.writeObject(datosEstudiantes);
            
        }catch(IOException e){
        }
    }
    
    public void leerArchivo(){
        ArrayList aux;
        try(ObjectInputStream ois=new ObjectInputStream(new FileInputStream("D:\\empleados.ddr"))){
            //Cuando no haya mas objetos saltara la excepcion EOFException
            while(true){
               aux = (ArrayList)ois.readObject(); 
            }
            
        }catch(ClassNotFoundException e){
        }catch(EOFException e){
        }catch(IOException e){
        }
        
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(botonGuardar)){
            c = new Estudiante(estudiante.getText(), codigo.getText(), not1.getText(), not2.getText(), not3.getText());
            estudiantes.add(c);
        }
        if(e.getSource().equals(botonGuardar)){
            hacerSerializacion(estudiantes);
        }
        if(e.getSource().equals(botonConsultar)){
            leerArchivo();
        }
        
        
    }

}
