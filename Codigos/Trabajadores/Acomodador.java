/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Trabajadores;

import Trabajadores.reproductores.CD_PLAYER;
import Trabajadores.reproductores.DVD_PLAYER;
import java.util.Objects;
import productos.Pnode;
import proyectopoo.KeyboardInput;

/**
 * Esta es la clase acomodador la cual se encarga de atender al cliente ya sea poniendo una cancion
 * o buscando un CD.
 * @author spart
 */
public class Acomodador extends Gerente{
    public Acomodador(){
        
    }
    /**
     * En este metodo el acomodador puede buscar un CD, DVD, o audifonos, ya sea por su nombre o ID. 
     * Una vez encontrado el producto solicitado, se muestra todos los datos que posee dicho producto
     * tales como su precio, su album o color. En caso de ser un precio o
     * @param l variable de tipo gerente, la cual es una lista
     * @param c variable de tipo vendedor, tambien es de tipo lista
     * @param p1 variable de tipo CD_PLAYER en la cual se podra manipular los productos que sean CD
     * @param p2 variable de tipo DVD_PLAYER en la cual se podria manipular lo productos que sean DVD
     * @param mode modo de busqueda
     * @return puede retornan un True, False Null o algun Nodo correspondiente
     */
    @Override
    public Pnode search_node(Gerente l, Vendedor c, CD_PLAYER p1, DVD_PLAYER p2, int mode){
        if(l.is_empty(l)!=false){
            System.out.println("Lista vacia");
            return null;
        }
        int opc;
        System.out.println("Te atiende el acomodador: "+l.getNombreTrabajador());
        Pnode node = new Pnode();
        KeyboardInput in = new KeyboardInput();
        System.out.print("Buscar por:\n1)Nombre\n2)ID\n---> ");
        opc=in.readInteger(); 
        switch(opc)
        {
            case 1:
                String name;
                System.out.println("Ingrese el nombre a buscar: ");
                name = in.readString();
                node=l.search_name(l, name);
            break;
            
            case 2:
                int id;
                System.out.println("Ingrese el ID a buscar: ");
                id = in.readInteger();
                node=l.search_id(l, id);
            break;
            
            default:
                System.out.println("Opcion invalida");
                return null;
            
            
        }
        if(node!=null){
         
           
            
            if(Objects.equals("Audifonos", node.getTipo())!=true){
                if(Objects.equals("DVD", node.getTipo())){
                    System.out.println("Nombre DVD: "+node.getNombre());
                    System.out.println("Precio: $"+node.getPrecio());
                    System.out.println("");
                    System.out.print("\nQue quieres hacer? \n1)Insertar en DVD\n2)Salir\n---> ");
                    opc=in.readInteger();
                    switch(opc){
                        
                        
                        
                        
                        case 1:
                            Pnode t = new Pnode(node.getNombre(), node.getPrecio(), node.getId(), node.getTipo(), node.getAutor(), node.getGenero(), node.getMinutos(), node.getnPistas());
                            p2.add_product(p2, t);
                            p2.rec_list(p2, t);
                            System.out.print("Buscar otro producto?\n1)Si\n2)No\n---> ");
                            opc=in.readInteger();
                            if(opc==1){
                                
                                this.search_node(l, c, p1, p2, mode);
                            }
                            return null;
                        
                        
                        
                        
                        
                        default:
                            return null;
                        
                    }
                }
                else{
                    while(true){
                        
                    
                        System.out.println("-----------Encontre tu producto!!!!!------------\n");
                        System.out.println("Nombre album: "+node.getNombre());
                        System.out.println("Precio: $"+node.getPrecio());
                        System.out.println("");
                        System.out.print("\nQue quieres hacer? \n1)Insertar en CD\nOtro)Salir\n---> ");
                        opc=in.readInteger();
                        if(opc==1 || opc ==2){
                            break;
                        }
                        else{
                            System.out.println("Opcion no valida");
                        }
                    }
                    switch(opc){
                        
                        
                        
                        
                        case 1:
                            Pnode t = new Pnode(node.getNombre(), node.getPrecio(), node.getId(), node.getTipo(), node.getAutor(), node.getGenero(), node.getnPistas(), node.getCanciones(), node.getMinutos());
                            
                            
                          
                            if(p1.add_product(p1, t)){
                                p1.rec_list(p1, t);
                            }
                       
                            System.out.print("Buscar otro producto?\n1)Si\n2)No\n---> ");
                            opc=in.readInteger();
                            if(opc==1){
                                
                                this.search_node(l, c, p1, p2, mode);
                            }
                            return null;
                        
                        default:
                            return null;
                        
                        
                    }
                }
                
                
            }
            else{
                System.out.println("-----------Encontre tu producto!!!!!------------\n");
                System.out.println("Nombre de los audifonos: "+node.getNombre());
                System.out.println("Color: "+node.getColor());
                System.out.println("Precio: $"+node.getPrecio());
                System.out.println("");
                while(true){
                    System.out.print("\nQue quieres salir? 1)Si\n---> ");
                    opc=in.readInteger();
                    if(opc==1){
                        return null;
                    }
                    else{
                        System.out.println("Opcion no valida");
                    }
                }
            }
                
        }
        else{
            System.out.println("No se encontró el producto");
        }
        return node;
        
    }
    
}
