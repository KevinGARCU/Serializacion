package serializacion;

import java.io.Serializable;

public class Estudiante implements Serializable {
    
    String nombre;
    String codigo;
    String nota1;
    String nota2;
    String nota3;
    String promedio;
    
    int aux1, aux2, aux3, prom;

    public Estudiante(String nombre, String codigo, String nota1, String nota2, String nota3) {
        this.nombre = nombre;
        this.codigo = codigo;
        this.nota1 = nota1;
        this.nota2 = nota2;
        this.nota3 = nota3;
        this.promedio = promedio;
        
        aux1 = Integer.parseInt(nota1);
        aux2 = Integer.parseInt(nota2);
        aux3 = Integer.parseInt(nota3);
        
        prom = (aux1 + aux2 + aux3)/3;
        
        promedio = Integer.toString(prom);
    }


}
