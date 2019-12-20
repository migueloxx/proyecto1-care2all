package application.model;
import java.util.ArrayList;
import java.util.Optional;

public class Authentication {

    private final ArrayList<User> userList;

    /**
     * Constructor de clase
     * User("usuario", "contraseña", "nombre completo")
     */
    public Authentication(){
        this.userList = new ArrayList<>();
        userList.add(new User("doctor","doctor","Doctor Demostracion"));
        userList.add(new User("dependiente","dependiente","Dependiente Demostracion"));
        userList.add(new User("tutor","tutor","Tutor Demostracion"));
    }

    /**
     * Autenticacion de usuario
     * @param user usuario
     * @param pass palabra secreta de usuario
     * @return boolean
     */
    public boolean userExists(String user, String pass){
        return userList.stream().filter((p) -> (user.equals(p.getNick()))).anyMatch((p) -> (pass.equals(p.getPass())));
    }

    /**
     * Obtiene un Usuario segun parametros de entrada
     * @param user usuario
     * @param pass palabra secreta de usuario
     * @return User
     */
    public Optional<User> getUser(String user, String pass){
       return userList.stream().filter(u -> u.getNick().equals(user) ).filter(u -> u.getPass().equals(pass)).findFirst();
    }
}