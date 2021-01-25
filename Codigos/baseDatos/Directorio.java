package baseDatos;

import Trabajadores.*;
import proyectopoo.*;
import java.io.File;
/**
 *Es el direcotrio de la base de datos
 * @author decodigo.com
 */
public class Directorio {
    /**
     * Constructor vacio
     */
    Directorio() {
    }
    /**
     *Es el directorio de la base de datos
     * @param producto es el nomber de la carpeta
     */
    public void directorio(String producto){
        File directorio = new File( producto );
        if (!directorio.exists()) {
            if (directorio.mkdirs()) {
                System.out.println("Directorio creado");
            } else {
                System.out.println("Error al crear directorio");
            }
        }
    }
}
