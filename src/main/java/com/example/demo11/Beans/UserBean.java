package com.example.demo11.Beans;

import com.example.demo11.Model.User;
import com.example.demo11.dao.DaoImpl;
import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.bean.ManagedBean;
import jakarta.faces.bean.RequestScoped;
import jakarta.faces.context.FacesContext;

import java.util.ArrayList;
import java.util.List;

@ManagedBean
@RequestScoped
public class UserBean {
    private String username;
    private String password;
    private String firstname;
    private String lastname;
    private String email;
    private String phonenumber;
    private  String departement ;
    private User currentuser = new User();
    private DaoImpl userdao = new DaoImpl();
    @PostConstruct
    public void init(){
        loadUsers();
    }

    public User getCurrentuser() {
        return currentuser;
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

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getDepartement() {
        return departement;
    }

    public void setDepartement(String departement) {
        this.departement = departement;
    }

    public DaoImpl getUserdao() {
        return userdao;
    }

    public void setUserdao(DaoImpl userdao) {
        this.userdao = userdao;
    }

    public void setCurrentuser(User currentuser) {
        this.currentuser = currentuser;
    }

public List<User> users =new ArrayList<>();

    public List<User> getUsers() {
        return loadUsers();
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public String Register() {
   userdao.saveUser(currentuser);
        return "Login?faces-redirect=true";


    }
    public void deleteUser(int id){
        userdao.deleteUser( id);
    }
   /* public void prepareUpdate(int id){
        userdao.updateUser(id );
    }*/
    /*public String login() {
        boolean result = userdao.checkLogin(username, password);
        if (result) {
            return "List?faces-redirect=true";
        } else {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN,
                            "Invalid Login!",
                            "Please Try Again!"));
            return "Register?faces-redirect=true";
        }
    }*/
    public String login() {
        List<User> userList = getUsers();
        boolean userExists = false;
        for (User user : userList) {
            if (user.getUsername().equals(currentuser.getUsername()) && user.getPassword().equals(currentuser.getPassword())) {
                userExists = true;
                break;
            }
        }
        if (userExists) {
            return "List?faces-redirect=true";
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage( "Utilisateur non enregistré ,Veuillez vous inscrire."));
            return null;
        }
    }

    public List<User> loadUsers(){
        List<User> loadedUsers = userdao.Lister();
        System.out.println("Utilisateurs chargés: " + loadedUsers.size());
        return loadedUsers;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}


