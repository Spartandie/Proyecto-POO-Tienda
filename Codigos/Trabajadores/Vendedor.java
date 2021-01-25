/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Trabajadores;

import Trabajadores.reproductores.CD_PLAYER;
import Trabajadores.reproductores.DVD_PLAYER;
import productos.Cnode;
import java.util.Objects;
import productos.Pnode;
import proyectopoo.KeyboardInput;



/**
 * Esta clase es una lista para poder manejar los datos almacenados, en la cual se maneja a base de 
 * listas. Se designan nodos los cuales hacen referencia al primer y ultimo elemento. Tambien, 
 * se encuentran todos los metodos referentes a la compra, agregar al carrito y pagar todos los
 * productos que se deseen adquirir.
 * @author spart
 */
public class Vendedor extends Gerente{
    KeyboardInput in = new KeyboardInput();
    private Cnode head;
    private Cnode tail;
    int nProd;

    public Vendedor(){
        
    }
    /**
     * Constructor el cual asigna a la lista la cabeza, talon y el producto
     * @param head es el primer elemento
     * @param tail es el ultimo elemento
     * @param nProd producto que se va a almacenar
     */
    public Vendedor(Cnode head, Cnode tail, int nProd) {
        this.head = head;
        this.tail = tail;
        this.nProd = nProd;
    }

   
    public KeyboardInput getIn() {
        return in;
    }

    @Override
    public Cnode getHead() {
        return head;
    }

    @Override
    public Cnode getTail() {
        return tail;
    }

    public int getnProd() {
        return nProd;
    }

    public void setIn(KeyboardInput in) {
        this.in = in;
    }

    public void setHead(Cnode head) {
        this.head = head;
    }

    public void setTail(Cnode tail) {
        this.tail = tail;
    }

    public void setnProd(int nProd) {
        this.nProd = nProd;
    }

    

    /**
     * Este metodo es para ingresar elementos que el usuario desee comprar. Todo se hace mediante una lista
     * Si la lista esta vacia, el elemento a entrar quedara almacenado y sera la nueva cabeza.En caso de 
     * que la lista no este vacia, simplemente se agrega
     * @param c Es una variable de tipo Clista
     * @param t variable de tipo Pnode
     */
    public void add_carrito(Vendedor c, Pnode t){
        
        
        Cnode n = new Cnode(1, t.getNombre(), t.getPrecio(), t.getId(), t.getTipo(), t.getAutor(), t.getGenero());
        System.out.println(n.getCant());
        Cnode t2 = new Cnode();
        if(c.getHead()!=null){
            t2=c.getHead();
            for(; t2!=c.getTail();t2=t2.getNext())
            {
                if(Objects.equals(n.getNombre(), t2.getNombre())==true){
                    System.out.println(n.getCant());
                    t2.setCant(t2.getCant()+1);
                    
                    return;
                }
            }
            if(Objects.equals(n.getNombre(), t2.getNombre())==true){
                t2.setCant(t2.getCant()+1);
                
                return;
            }
        }
       
        if(c.getHead()==null){
            c.setHead(n);
            c.setTail(n);
            return;
        }
        n.setNext(c.getHead());
        c.setHead(n);
       
       
    }
    /**
     * Este metodo es para ver los articulos que haya en el carrito. Si la lista tiene elementos, se 
     * mandara a llamar a la funcion de imprimir la cual, imprime todos los elementos que haya dentro 
     * de la lista. En caso de que este vacia, mandara un mensaje de que el carrito esta vacio
     * @param c variable de tipo Clist
     * @param l variable de tipo Plist
     * @return un True o False
     */
    public boolean see_carrito(Vendedor c, Gerente l ){
        if(!c.is_empty_clist(c))
        {
            
            System.out.println("\n-----------Articulos en el carrito----------\n\n");
            print_clist(c, l);
            return true;
        }
        System.out.println("Carrito vacio\n");
       
        return false;
    }
    
    /**
     * Este es el metodo con el cual se imprime los datos almacenados que son el precio, la cantidad
     * el ID, el nombre, etc.
     * @param c recibe la lista pasada en el metodo anterior
     * @param l variable de tipo Plit
     */
    public void print_clist( Vendedor c, Gerente l)
    {
        int  prod = 0, opc;
        float precio=0, total = 0;
        Cnode t = new Cnode();

        if( c == null )
        {
            System.out.println("No existe");
            return;
        }
        else if( is_empty_clist(c) ){
            return;
        }
        
        for( t = c.getHead(); t!= null; t = t.getNext() )
        {
            prod++;
            System.out.println("----------- Producto "+prod+" -----------\n");
            System.out.println("=>Nombre: " + t.getNombre() );
            System.out.println("=>Precio: " + t.getPrecio());
            System.out.println("=>ID: "+ t.getId());
            System.out.println("=>Tipo: " + t.getTipo() );
            System.out.println("Cantidad: "+t.getCant());
            precio = t.getPrecio()*t.getCant();
            total += precio;
        }
        t = c.getHead();
        System.out.println("\nTotal: $" + total );
        System.out.print("\n1)Confirmar compra\n2)Eliminar prodcuto\n3)Vaciar carrito\nOtro)Seguir explorando Catalogo\n=>");
        opc = in.readInteger();
        switch(opc){
            case 1:
                c.buy(c, l, total);
            break;
            
            case 2:
                System.out.print("Que producto quieres borrar?\n----> ");
                
                opc = in.readInteger();
                if(opc>prod){
                    System.out.println("Numero no valido");
                    return;
                }                
                t.del_cnode(c, opc, l);
               
                
                
                
            break;
            
            
            
            case 3:
                l.del_list(l, c, 1);
                break;
            
            default:
                System.out.println("Regresando al menu");
                return;
           
            
        }
    }
    /**
     * El metodo is empty clist solo verifica que haya elementos dentro de la lista, si si los hay
     * devuelve un true, en caso de que no, un false
     * @param l la lista de la cual se desea ver si hay datos o no
     * @return true o false
     */
    public boolean is_empty_clist( Vendedor l ){
        if( l.getHead() == null &&  l.getTail()== null ) return true;
        return false;
    }
    /**
     * Para este metodo que es para comprar, se muestra en pantalla toda la informacion de los 
     * productos que se han agregado al carrito y que se van a comprar. Se muestra su informacion
     * tanto de los audifonos como los datos del disco y del DVD que se va a adquirir. Una vez
     * que todos los productos fueron mostrados con su respectiva informacion, se imprime el 
     * total de los datos que se debera pagar. A nivel de codigo, todos los atributos
     * vuelven a pasar a Null y la lista con los elementos almacenados vuelve a quedar vacia.
     * @param c lista donde esta almacenados todos nuestros datos
     * @param l variable de tipo Plist
     * @param total variable donde se acomulara el total de la compra realizada
     */
    public void buy(Vendedor c, Gerente l, float total ){
        
        Cnode n = new Cnode();
        Pnode n2 = new Pnode();
        int i;
        
        System.out.println("\n------------------GRACIAS POR SU COMPRA VUELVA PRONTO---------------\n");
        for(n=c.getHead(), i=1;n!=c.getTail();n=n.getNext(), i++){
            
            for(n2=l.getHead();n2!=l.getTail();n2 = n2.getNext()){
                if(Objects.equals(n2.getNombre(), n.getNombre())){
                    n2.setExistencias(n2.getExistencias()-n.getCant());
                }
                if(n2.getExistencias()==0){
                    l.del_node(l, n2);
                }
            }
            if(Objects.equals(n2.getNombre(), n.getNombre())){
                    n2.setExistencias(n2.getExistencias()-n.getCant());
                }
            if(n2.getExistencias()==0){
                    l.del_node(l, n2);
            }
            if(Objects.equals(n.getTipo(), "Audifonos")){
                System.out.println("-------------PRODUCTO "+i+"----------------\n");
                System.out.println("Nombre de los audifonos: "+n.getNombre());
                System.out.println("Color: "+n.getColor());
                System.out.println("Precio: "+n.getPrecio());
            }
            else{
                if(Objects.equals(n.getTipo(), "CD")){
                    System.out.println("-------------PRODUCTO "+i+"----------------\n");
                    System.out.println("Album: "+n.getNombre());
                    System.out.println("Artista: "+n.getAutor());
                    System.out.println("Genero: "+n.getGenero());
                    System.out.println("Precio: "+n.getPrecio());
                }
                else{
                    System.out.println("-------------PRODUCTO "+i+"----------------\n");
                    System.out.println("Album: "+n.getNombre());
                    System.out.println("Artista: "+n.getAutor());
                    System.out.println("Genero: "+n.getGenero());
                    System.out.println("Duracion: Minutos["+n.getMinutos()+"]  Segundos["+n.getSegundos()+"]");
                    System.out.println("Precio: "+n.getPrecio());
                }
            }
        }
       
        for(n2=l.getHead();n2!=l.getTail();n2 = n2.getNext()){
                if(Objects.equals(n2.getNombre(), n.getNombre())){
                    n2.setExistencias(n2.getExistencias()-n.getCant());
                }
                if(n2.getExistencias()==0){
                    l.del_node(l, n2);
                }
        }
        if(Objects.equals(n2.getNombre(), n.getNombre())){
            n2.setExistencias(n2.getExistencias()-n.getCant());
        }
        if(n2.getExistencias()==0){
            l.del_node(l, n2);
        }
        
        if(Objects.equals(n.getTipo(), "Audifonos")){
                System.out.println("-------------PRODUCTO "+i+"----------------\n");
                System.out.println("Nombre de los audifonos: "+n.getNombre());
                System.out.println("Tipo de producto: "+n.getTipo());
                System.out.println("Color: "+n.getColor());
                System.out.println("Cantidad: "+n.getCant());
                System.out.println("Precio: "+n.getPrecio());
            }
            else{
                if(Objects.equals(n.getTipo(), "CD")){
                    System.out.println("-------------PRODUCTO "+i+"----------------\n");
                    System.out.println("Album: "+n.getNombre());
                    System.out.println("Artista: "+n.getAutor());
                    System.out.println("Genero: "+n.getGenero());
                    System.out.println("Duracion: "+n.getMinutos()+" minutos aprox");
                    System.out.println("Tipo de producto: "+n.getTipo());
                    System.out.println("Cantidad: "+n.getCant());
                    System.out.println("Precio: "+n.getPrecio());
                }
                else{
                    System.out.println("-------------PRODUCTO "+i+"----------------\n");
                    System.out.println("Album: "+n.getNombre());
                    System.out.println("Artista: "+n.getAutor());
                    System.out.println("Genero: "+n.getGenero());
                    System.out.println("Duracion: "+n.getMinutos()+" minutos aprox");
                    System.out.println("Tipo de producto: "+n.getTipo());
                    System.out.println("Cantidad: "+n.getCant());
                    System.out.println("Precio: "+n.getPrecio());
                }
            }
        
        System.out.println("---------   TOTAL: $"+total+"  ----------------\n");
        
        
        Cnode t = c.getHead();
        while(true){
            if(c.getHead()==c.getTail()){
                c.setHead(null);
                c.setTail(null);
                t.del_cnode(t);
                break;
            }
            if(t.getNext()!=c.getTail()){
                t=t.getNext();
            }else{
                c.setTail(t);
                t.del_cnode(t.getNext());
                c.getTail().setNext(null);
                t=c.getHead();
            }
        }
        
    }
    
    
    /**
     * Busca un nodo por su nombre
     * @param l es la lista
     * @param c es la lista del carrito, por si se necesita
     * @param p1 es la lista del Reprodcutor CD, por si se necesita
     * @param p2 es la lista del DVD, por si se necesita
     * @param mode modo de busqueda
     * @return puede retornar un null o un nodo correspondiente
     */
    @Override
    public Pnode search_node(Gerente l, Vendedor c, CD_PLAYER p1, DVD_PLAYER p2, int mode)
    {
            if(l.is_empty(l)!=false){
                System.out.println("Lista vacia");
                return null;
            }
            System.out.println("Te atiende el vendedor: "+l.getNombreTrabajador());
            int opc;
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
                        System.out.print("\nQue quieres hacer? \n1)Agregar al carrito\n2)Insertar en DVD\n\n4)Salir\n---> ");
                        opc=in.readInteger();
                        switch(opc){

                            case 1:
                                c.add_carrito(c, node);
                                System.out.print("Buscar otro disco?\n1)Si\n2)No\n---> ");
                                opc=in.readInteger();
                                if(opc==1){

                                    this.search_node(l, c, p1, p2, mode);
                                }
                                return null;


                            case 2:
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

                        System.out.println("Nombre album: "+node.getNombre());
                        System.out.println("Precio: $"+node.getPrecio());
                        System.out.println("");
                        System.out.print("\nQue quieres hacer? \n1)Agregar al carrito\n2)Insertar en CD\n3)Salir\n---> ");
                        opc=in.readInteger();
                        switch(opc){

                            case 1:
                                c.add_carrito(c, node);
                                System.out.print("Buscar otro Producto?\n1)Si\n2)No\n---> ");
                                opc=in.readInteger();
                                if(opc==1){

                                    this.search_node(l, c, p1, p2, mode);
                                }
                                return null;


                            case 2:
                                Pnode t = new Pnode(node.getNombre(), node.getPrecio(), node.getId(), node.getTipo(), node.getAutor(), node.getGenero(), node.getnPistas(), node.getCanciones(), node.getMinutos());



                                if(p1.add_product(p1, t)){
                                    p1.rec_list(p1, t);
                                }

                                System.out.print("Buscar otro disco?\n1)Si\n2)No\n---> ");
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
                    System.out.println("Nombre de los audifonos: "+node.getNombre());
                    System.out.println("Color: "+node.getColor());
                    System.out.println("Precio: $"+node.getPrecio());
                    System.out.println("");
                    System.out.print("\nQue quieres hacer? \n1)Agregar al carrito\n2)Salir\n---> ");
                    opc=in.readInteger();
                        switch(opc){

                            case 1:
                                c.add_carrito(c, node);
                            break;

                            case 2:
                                return null;


                        }
                }

            }
            else{
                System.out.println("No se encontró el producto");
            }
            return node;
        
        
    }
}

