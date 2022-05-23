package services;

import model.Product;
import model.Role;
import model.User;
import utils.CSVUtils;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class UserService implements IUserService{
    public final static String PATH = "data/users.csv";
    private static UserService instance;
    private UserService (){};
    public static UserService getInstance(){
        if (instance ==null)
            instance = new UserService();
        return instance;
    }
    @Override
    public List<User> findAll() {

        List<User> users = new ArrayList<>();
        List<String> records = CSVUtils.read(PATH);
        for (String record: records){
            users.add(User.paraseUser(record));
        }
        return users;
    }
    @Override
    public User adminLogin(String username, String password) {
        List<User> users = findAll();
        for (User user: users){
            if (user.getUsername().equals(username) && user.getPassword().equals(password)
                && user.getRole().equals(Role.ADMIN)){
                return user;
            }
        }
        return null;
    }

    @Override
    public void add(User newUser) {
     newUser.setCreatedAt(Instant.now());
     List<User> users = findAll();
     users.add(newUser);
     CSVUtils.write(PATH,users);
    }

    @Override
    public void update(User newUser) {
    List<User> users = findAll();
    for (User user: users){
        if (user.getId()==newUser.getId()){
            String fullName = newUser.getFullName();
            if (fullName !=null && !fullName.isEmpty())
                user.setFullName(fullName);
            String phone = newUser.getPhone();
            if (phone != null && !phone.isEmpty())
                user.setPhone(newUser.getPhone());
            String address = user.getAddress();
            if (address != null && !address.isEmpty())
                user.setAddress(newUser.getAddress());
            String email = user.getEmail();
            if (email !=null && !email.isEmpty())
                user.setEmail(newUser.getEmail());
            user.setUpdatedAt(Instant.now());
            CSVUtils.write(PATH, users);
            break;

            }
        }
    }

    @Override
    public boolean existt(int id) {
        List<User> users = findAll();
       for (User user : users) {
           if (user.getId() == id)
               return true;
       }
       return false;
    }


    @Override
    public boolean existById(int id) {
        return findById(id) != null;
//        List<User> users = findAll();
//        for (User user : users) {
//            if (user.getId() == id)
//                return true;
//        }
//        return false;
    }

    @Override
    public void deleteById(int id) {
        List<User> users = findAll();
        users.removeIf(new Predicate<User>() {
            @Override
            public boolean test(User user) {
                return user.getId() == id;
            }
        });
        CSVUtils.write(PATH, users);
    }

    @Override
    public boolean existByEmail(String email) {
        List<User> users = findAll();
        for (User user: users){
            if (user.getEmail().equals(email))
                return true;
        }
        return false;
    }

    @Override
    public boolean existByPhone(String phone) {
        List<User> users = findAll();
        for (User user: users){
            if (user.getPhone().equals(phone))
                return true;
        }
        return false;
    }

    @Override
    public boolean existByUsername(String userName) {
        List<User> users = findAll();
        for (User user: users){
            if (user.getUsername().equals(userName))
                return true;
        }
        return false;
    }

    @Override
    public User findById(int id) {
        List<User> users = findAll();
        for (User user: users){
            if (user.getId()==id)
                return user;
        }
        return null;
    }
}