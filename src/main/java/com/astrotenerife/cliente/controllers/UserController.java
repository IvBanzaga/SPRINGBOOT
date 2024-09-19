/* UserController utiliza UserService para realizar las operaciones CRUD de los clientes.*/

package com.astrotenerife.cliente.controllers;

import com.astrotenerife.cliente.entities.User;
import com.astrotenerife.cliente.services.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;
//@RequestMapping("/api/v1/users")
//@RequiredArgsConstructor
@RestController



@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.DELETE})
public class UserController {

    /*private List<User> list = new ArrayList<>();*/

    // La anotación autowired se utiliza para inyectar dependencias en Spring, en este caso, la clase UserService.
    @Autowired
    private UserServiceImp service;

    /*public UserController() {
        User c1 = new User();
        c1.setId(1);
        c1.setFirstname("Lucas");
        c1.setLastname("Gonzalez");
        c1.setEmail("i.bazaga@gmail.com");
        c1.setPhone("922123456");
        c1.setAddress("Calle Falsa 123");

        User c2 = new User();
        c2.setId(2);
        c2.setFirstname("Juan");
        c2.setLastname("Perez");
        c2.setEmail("andres@gmail.com");
        c2.setPhone("922123456");
        c2.setAddress("Calle Falsa 123");

        list.add(c1);
        list.add(c2);
    }*/
    // List
    @GetMapping("/prueba1")
    public List<String> prueba1(){
        List<String> list = new ArrayList<>();
        list.add("Una lista de nombres");
        list.add("Lucas");
        list.add("Juan");
        list.add("Pedro");
        return list;
    }

    // Map
    @GetMapping("/prueba2")
    public Map<String, String> prueba2(){
        Map<String, String> map = new HashMap<>();
        map.put("descripción", "HashMap no garantiza el orden de inserción");
        map.put("nombre", "Lucas");
        map.put("apellido", "Gonzalez");
        map.put("edad", "25");
        map.put("ciudad", "Santa Cruz de Tenerife");
        map.put("pais", "España");
        map.put("teléfono", "922123456");
        return map;
    }

    // TreeMap
    @GetMapping("/prueba3")
    public Map<String, String> prueba3(){
        Map<String, String> map = new TreeMap<>();
        map.put("descripción", "TreeMap ordena por clave");
        map.put("nombre", "Lucas");
        map.put("apellido", "Gonzalez");
        map.put("edad", "25");
        map.put("ciudad", "Santa Cruz de Tenerife");
        map.put("pais", "España");
        map.put("teléfono", "922123456");
        return map;
    }

    // LinkedHashMap
    @GetMapping("/prueba4")
    public Map<String, String> prueba4(){
        Map<String, String> map = new LinkedHashMap<>();
        map.put("descripción", "LinkedHashMap mantiene el orden de inserción");
        map.put("nombre", "Lucas");
        map.put("apellido", "Gonzalez");
        map.put("edad", "25");
        map.put("ciudad", "Santa Cruz de Tenerife");
        map.put("pais", "España");
        map.put("teléfono", "922123456");
        return map;
    }

    // Traer Devolver un cliente específico
    @GetMapping("/user/{id}")
    public User getUser(@PathVariable Integer id) {

        return service.getUser(id);
    }

    // Traer Devolver todos los clientes
    @GetMapping("/user")
    public List<User> getAllUsers() {
        return service.getAllUsers();
    }

    // Eliminar Cliente
    @DeleteMapping("/user/{id}")
    public void deleteUser(@PathVariable Integer id) {
        service.deleteUser(id);
    }

    // Agregar Cliente
    @PostMapping("/user")
    public void addUser(@RequestBody User user) {
        service.addUser(user);
    }

    // Modificar Cliente
    @PutMapping("/user/{id}")
    public void updateUser(@PathVariable Integer id, @RequestBody User updateUser) {
        service.updateUser(id, updateUser);
    }

    // Buscar Cliente
    @GetMapping("/user/search")
    public List<User> searchUser(@RequestParam(name = "email", required = false) String email,
                                         @RequestParam(name = "phone", required = false) String phone, @RequestParam(name = "address", required = false) String address, @RequestParam(name = "firstname", required = false) String firstname, @RequestParam(name = "lastname", required = false) String lastname) {
        return service.searchUser(email, phone, address, firstname, lastname);
    }


 /*
    // String
    @GetMapping("/prueba")
    public String prueba(){
        return "Hola mundo";
    }

    // List
    @GetMapping("/prueba2")
    public List<String> prueba2(){
        List<String> list = new ArrayList<>();
        list.add("Lucas");
        list.add("Juan");
        list.add("Pedro");
        return list;
    }

    // Map
    @GetMapping("/prueba3")
    public Map<String, String> prueba3(){
        Map<String, String> map = new HashMap<>();
        map.put("nombre", "Lucas");
        map.put("apellido", "Gonzalez");
        map.put("edad", "25");
        map.put("ciudad", "Santa Cruz de Tenerife");
        map.put("pais", "España");
        map.put("teléfono", "922123456");
        return map;
    }

    // Object Cliente entities user
    @GetMapping("/prueba4")
    public User prueba4() {
        User c = new User();

        c.setFirstname("Lucas");
        c.setLastname("Gonzalez");
        c.setEmail("i.bazaga@gmail.com");
        c.setPhone("922123456");
        c.setAddress("Calle Falsa 123");

        return c;
    }

    // List of objects Clientes entities user
    @GetMapping("/prueba5")
    public List<User> prueba5() {
        List<User> users = new ArrayList<>();

        User c1 = new User();
        c1.setFirstname("Lucas");
        c1.setLastname("Gonzalez");
        c1.setEmail("ivan.cpweb@gmail.com");
        c1.setPhone("922123456");
        c1.setAddress("Calle Falsa 123");

        User c2 = new User();
        c2.setFirstname("Juan");
        c2.setLastname("Perez");
        c2.setEmail("andres@gmail.com");
        c2.setPhone("922123456");
        c2.setAddress("Calle Falsa 123");

        users.add(c1);
        users.add(c2);

        return users;

    }
    */

}
