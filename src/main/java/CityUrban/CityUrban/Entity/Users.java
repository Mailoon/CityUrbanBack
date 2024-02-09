/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CityUrban.CityUrban.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author Mailoon
 */
@Entity
@Table(name = "Users")
public class Users {
    
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private long id;
    
    @Column(name = "Name")
    private String name;
    
    @Column(name = "Phone")
    private long phone;
    
    @Column(name = "Email")
    private String email;
    
    private Address address;
    
    // Falta la parte mime para guarda imagenes pero esta por verse

    private Rol Rol;
    
}
