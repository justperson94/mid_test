package kr.ac.jejunu;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;

import static org.junit.Assert.assertEquals;

public class ProductDaoTest {
    private ProductDao productDao;

    @Before
    public void setup() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(DaoFactory.class);
        productDao = applicationContext.getBean("getProductDao", ProductDao.class);
    }

    @Test
    public void get() throws SQLException {
        Long id = 1L;
        String title = "제주감귤";
        Integer price = 15000;

        Product product = productDao.get(id);
        assertEquals(id, product.getId());
        assertEquals(title, product.getTitle());
        assertEquals(price, product.getPrice());
    }

    @Test
    public void add() throws SQLException {
        Product product = new Product();
        product.setTitle("쌔삥");
        product.setPrice(100);
        Long id = productDao.insert(product);

        Product insertedProducted = productDao.get(id);
        assertEquals(insertedProducted.getId(), id);
        assertEquals(insertedProducted.getTitle(), product.getTitle());
        assertEquals(insertedProducted.getPrice(), product.getPrice());
    }
}