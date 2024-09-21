/* El paquete entities generalmente contiene las clases que representan las entidades de tu dominio.
   En este caso, la clase Customer es una entidad que representa a un cliente en la aplicación.*/

package com.astrotenerife.cliente.entities;


import jakarta.persistence.Id;
import lombok.*;
import jakarta.persistence.*;

//Clase clientes
//podemos no depender de los get y set. Utilizamos lombok


//Los getter y setter también se pueden añadir individualmente
//@Getter @Setter

//podemos borrar los constructores y utilizar
//@AllArgsConstructor @NoArgsConstructor

//@EqualsAndHashCode
//@ToString
@Data
// También podemos utilizar @Data para tener todos los anteriores

@Entity

// esto crea una tabla en la base de datos , pero si ya esta creada no la vuelve a crear
@Table(name = "customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String firstname;
    private String lastname;
    private String email;
    private String phone;
    private String address;

    /* public Customer() {
    }

    public Customer(String firstname, String lastname, String email, String phone, String address) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.phone = phone;
        this.address = address;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(firstname, customer.firstname) && Objects.equals(lastname, customer.lastname) && Objects.equals(email, customer.email) && Objects.equals(phone, customer.phone) && Objects.equals(address, customer.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstname, lastname, email, phone, address);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                '}';
    }*/
}
