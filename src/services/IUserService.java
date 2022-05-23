package services;

import model.User;

import java.util.List;

public interface IUserService {
    List<User> findAll();
    User adminLogin(String name,String password );
    void add(User newUser);
    void update(User newUser);
      boolean existt (int id);
    boolean existById(int id);
    void deleteById(int id);
    boolean existByEmail(String email);
    boolean existByPhone(String phone);
    boolean existByUsername(String userName);
    User findById(int id);
}
