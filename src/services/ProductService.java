package services;

import model.Product;
import utils.CSVUtils;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

public class ProductService implements IProductService {
    public final static String PATH = "data/products.csv";
    private static ProductService instance;

    private ProductService() {

    }

    public static ProductService getInstance() {
        if (instance == null)
            instance = new ProductService();
        return instance;
    }

    @Override
    public List<Product> findAll() {
        List<Product> products = new ArrayList<>();
        List<String> records = CSVUtils.read(PATH);
        for (String record : records) {
            products.add(Product.parse(record));
        }
        return products;
    }

    @Override
    public void add(Product newProduct) {
        List<Product> products = findAll();
        newProduct.setCreatedAt(Instant.now());
        products.add(newProduct);
        CSVUtils.write(PATH, products);

    }

    @Override
    public Product findNameProduct(long id) {
        List<Product> products = findAll();
        for (Product product: products){
            if (product.getId()==id)
                return product;
        }
        return null;
    }


    @Override
    public Product findById(long id) {
        List<Product> products = findAll();
        for (Product product :products){
            if (product.getId() == id)
                return product;
        }
        return null;
    }

    @Override
    public boolean exist(int id) {
        return findById(id)!=null;
    }

    @Override
    public boolean existByName(String name) {
        return findName(name) != null;
//        List<Product> products = findAll();
//        for (Product product : products){
//            if (product.getName().equals(name))
//                return true;
//        }
//        return false;
    }

    @Override
    public boolean existById(int id) {
        List<Product> products = findAll();
        for (Product product :products){
            if (product.getId()==id)
                return true;
        }
        return false;
    }

    @Override
    public void deleteById(int id) {
        List<Product> products = findAll();
        products.removeIf(new Predicate<Product>() {
            @Override
            public boolean test(Product product) {
                return product.getId() == id;
            }
        });
        CSVUtils.write(PATH, products);
    }

    @Override
    public Product findName(String name) {
        List<Product> products = findAll();
        for (Product product : products){
            if (product.getName().contains(name)){
                return product;
            }
        }
        return null;
    }

    @Override
    public List<Product> findProductByName(String name) {
        List<Product> products = findAll();
       List<Product> findList = new ArrayList<>();
        for (Product product:products ) {
            if (product.getName().contains(name)) {

                 findList.add(product);
            }
        }
        return findList;
    }



    @Override
    public void update(Product newProduct) {
        List<Product> products = findAll();
        for (Product product : products) {
            if (product.getId() == newProduct.getId()) {
                String name = newProduct.getName();
                if (name != null && !name.isEmpty())
                    product.setName(name);

                Integer quantity = newProduct.getQuantity();
//                if (quantity !=null){
//                    product.setQuantity(product.getQuantity());
                if (quantity != null) {
                    product.setQuantity(quantity);

                }
                CSVUtils.write(PATH, products);
                Double price = newProduct.getPrice();
                if (price != null) {
                    product.setPrice(price);
                }

                String description = newProduct.getDescription();
                if (description != null && !description.isEmpty())
                    product.setDescription(description);

                product.setUpdateAt(Instant.now());
                CSVUtils.write(PATH, products);
                break;
            }
        }
    }

//    @Override
//    public void updateName(Product newProduct) {
//        List<Product> products = findAll();
//        for (Product product : products){
//            if (product.getId()== newProduct.getId()){
//                String name = product.getName();
//                if (name != null && !name.isEmpty())
//                    product.setName(newProduct.getName());
//                product.setUpdateAt(Instant.now());
//                CSVUtils.write(PATH, products);
//                break;
//            }
//        }
//    }
//
//    @Override
//    public void updatePrice(Product newProduct) {
//        List<Product> products = findAll();
//        for (Product product : products){
//            if (product.getId()== newProduct.getId()){
//                double price = product.getPrice();
//                if (price>0)
//                    product.setPrice(newProduct.getPrice());
//                product.setUpdateAt(Instant.now());
//                CSVUtils.write(PATH, products);
//                break;
//            }
//        }
//    }

    @Override
    public void updateQuantity(long id, int quantity) {
        List<Product> products = findAll();
        for (Product product : products) {
            if (product.getId() == id)
                if (product.getQuantity() >= quantity) {
                    product.setQuantity(product.getQuantity() - quantity);
                    CSVUtils.write(PATH, products);
                    break;
                }
        }
    }



    @Override
    public List<Product> findAllOderByPriceASC() {
        List<Product> products = new ArrayList<>(findAll());
        products.sort(new Comparator<Product>() {
            @Override
            public int compare(Product productOne, Product productTwo) {
                double result = productOne.getPrice() - productTwo.getPrice();
                if (result == 0)
                    return 0;
                return result > 0 ? 1 : -1;
            }
        });
        return products;
    }

    @Override
    public List<Product> findAllOderBypriceDESC() {
        List<Product> products = new ArrayList<>(findAll());
        products.sort(new Comparator<Product>() {
            @Override
            public int compare(Product productOne, Product productTwo) {
                double result = productTwo.getPrice() - productOne.getPrice();
                if (result == 0)
                    return 0;
                return result > 0 ? 1 : -1;
            }
        });

        return products;
    }
}
