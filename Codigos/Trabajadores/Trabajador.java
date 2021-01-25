/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Trabajadores;

import Trabajadores.reproductores.CD_PLAYER;
import Trabajadores.reproductores.DVD_PLAYER;
import java.io.FileOutputStream;
import java.io.IOException;
import productos.Pnode;

/**
 *Es la super clase Trabajador
 * @author spart
 */
public abstract class Trabajador {
    
    
    private String nombreTrabajador;

    public String getNombreTrabajador() {
        return nombreTrabajador;
    }

    public void setNombreTrabajador(String nombreTrabajador) {
        this.nombreTrabajador = nombreTrabajador;
    }
    /**
     * Metodo abstracto para agregar producto
     * @param l lista
     * @param n nodo
     * @return  boolean
     */
    public abstract boolean add_product(Gerente l, Pnode n);
    /**
     * Metodo abstracto para buscar si un elemento esta en una lista
     * @param l lista 
     * @param n nodo 
     * @return  boolean
     */
    public abstract boolean is_in_list(Gerente l, Pnode n); 
    /**
     * Metodo abstracto para buscar nodo
     * @param l lista 
     * @param c lista carrito
     * @param p1 lista CD
     * @param p2 lista DVD
     * @param mode modo de busqueda
     * @return Pnode
     */
    public abstract Pnode search_node(Gerente l, Vendedor c, CD_PLAYER p1, DVD_PLAYER p2, int mode);
    /**
     * Metodo abstracto para borrar un nodo de la lista
     * @param l lista
     * @param c lista carrito
     * @param mode modo de busqueda
     */
    public abstract void del_list(Gerente l, Vendedor c, int mode);
    
}
