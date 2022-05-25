package services;

import model.Product;

import java.util.List;

public interface IProductService {
    List<Product> findAll();
    void add (Product newProduct);
    void update(Product newProduct);
     Product findNameProduct (long id);
    Product findById(long id);
    boolean exist (int id);
    boolean existByName(String name);
    boolean existById(int id);
    void deleteById(int id);
    Product findName(String name);
    List<Product> findProductByName(String name);
    //update

    void updateQuantity(long id, int quantity);
    List<Product> findAllOderByPriceASC();
    List<Product>findAllOderBypriceDESC();
}
