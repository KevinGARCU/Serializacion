/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serializacion;

import java.awt.event.ActionListener;
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
public final class Vista extends JFrame {

    JTabbedPane pestañas = new JTabbedPane();
    JTable tabla = new JTable();
    JScrollPane sTabla = new JScrollPane(tabla);

    ArrayList<Estudiante> estudiantes = new ArrayList<>();

    JButton botonIngresar, botonConsultar, botonBorrar, botonEditar;

    JTextField nombre;
    JTextField codigo;
    JTextField not1;
    JTextField not3;
    JTextField not2;
    JTextField datoConsultar;


    public Vista() {
        ventanaPrincipal();
       // ventanaEditar();
        ingresar();
        pestanaCosultar();
        tabla.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new String[]{
                    "Codigo", "Nombre", "Nota 1", "Nota 2", "Nota 3", "Promedio"
                }
        ));
    }

    public void ventanaPrincipal() {
        setBounds(500, 500, 700, 700);
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

        JLabel mensajeingresoE = new JLabel("Ingrese el nombre del estudiante");
        mensajeingresoE.setBounds(10, 80, 250, 50);

        nombre = new JTextField();
        nombre.setBounds(10, 135, 190, 25);

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
        panelIngresar.add(mensajeingresoE);
        panelIngresar.add(nombre);
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

        JLabel labelDato = new JLabel("Ingrese el dato a Consultar");
        labelDato.setBounds(60, 10, 250, 25);

        datoConsultar = new JTextField();
        datoConsultar.setBounds(60, 50, 150, 25);

        botonConsultar = new JButton("Consultar");
        botonConsultar.setBounds(60, 100, 100, 25);

        botonBorrar = new JButton("Borrar");
        botonBorrar.setBounds(190, 100, 100, 25);

        botonEditar = new JButton("Editar");
        botonEditar.setBounds(310, 100, 100, 25);

        sTabla.setBounds(30, 150, 600, 350);
        tabla.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new String[]{
                    "Codigo", "Nombre", "Nota 1", "Nota 2", "Nota 3", "Promedio"
                }
        ));
        panelConsultar.add(botonConsultar);
        panelConsultar.add(botonBorrar);
        panelConsultar.add(botonEditar);
        panelConsultar.add(labelDato);
        panelConsultar.add(datoConsultar);

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

    public void ventanaEditar() {
        JPanel panelEditar = new JPanel();
        panelEditar.setLayout(null);

        JButton botonGuardar;

        JTextField estudiante2;
        JTextField codigo2;
        JTextField not12;
        JTextField not32;
        JTextField not22;
        
        botonGuardar = new JButton("Ingresar datos");
        botonGuardar.setBounds(30, 380, 150, 25);

        JLabel mensajeingresoE = new JLabel("Ingrese el nombre del estudiante");
        mensajeingresoE.setBounds(10, 80, 250, 50);

        estudiante2 = new JTextField();
        estudiante2.setBounds(10, 135, 190, 25);

        JLabel mensajeingresoC = new JLabel("Ingrese el codigo del estudiante");
        mensajeingresoC.setBounds(290, 80, 250, 50);

        codigo2 = new JTextField();
        codigo2.setBounds(290, 135, 190, 25);

        JLabel nota1 = new JLabel("Nota1");
        JLabel nota2 = new JLabel("Nota2");
        JLabel nota3 = new JLabel("Nota3");

        nota1.setBounds(40, 185, 50, 30);
        nota2.setBounds(200, 185, 50, 30);
        nota3.setBounds(360, 185, 50, 30);

        not12 = new JTextField();
        not32 = new JTextField();
        not22 = new JTextField();

        not12.setBounds(40, 210, 30, 30);
        not22.setBounds(200, 210, 30, 30);
        not32.setBounds(360, 210, 30, 30);
        
        panelEditar.add(botonGuardar);
        panelEditar.add(mensajeingresoE);
        panelEditar.add(estudiante2);
        panelEditar.add(mensajeingresoC);
        panelEditar.add(codigo2);
        panelEditar.add(nota1);
        panelEditar.add(nota2);
        panelEditar.add(nota3);
        panelEditar.add(not12);
        panelEditar.add(not22);
        panelEditar.add(not32);
        
    }
    
    public void asignarOyentes(Controlador c){
        botonBorrar.addActionListener((ActionListener) c);
        botonConsultar.addActionListener((ActionListener) c);
        botonEditar.addActionListener((ActionListener) c);
        botonIngresar.addActionListener((ActionListener) c);
    }
}
