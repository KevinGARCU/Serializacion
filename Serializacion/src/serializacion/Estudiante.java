package serializacion;

import java.io.Serializable;

public class Estudiante implements Serializable {

    private static final long serialVersionUID = -1L;

    String nombre;
    String codigo;
    String nota1;
    String nota2;
    String nota3;
    String promedio;

    int aux1, aux2, aux3, prom;

    

    Estudiante(String nombre, String codigo, String nota1, String nota2, String nota3) {
        this.nombre = nombre;
        this.codigo = codigo;
        this.nota1 = nota1;
        this.nota2 = nota2;
        this.nota3 = nota3;

        aux1 = Integer.parseInt(nota1);
        aux2 = Integer.parseInt(nota2);
        aux3 = Integer.parseInt(nota3);

        prom = (aux1 + aux2 + aux3) / 3;

        promedio = Integer.toString(prom);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNota1() {
        return nota1;
    }

    public void setNota1(String nota1) {
        this.nota1 = nota1;
    }

    public String getNota2() {
        return nota2;
    }

    public void setNota2(String nota2) {
        this.nota2 = nota2;
    }

    public String getNota3() {
        return nota3;
    }

    public void setNota3(String nota3) {
        this.nota3 = nota3;
    }

    public String getPromedio() {
        return promedio;
    }

}
