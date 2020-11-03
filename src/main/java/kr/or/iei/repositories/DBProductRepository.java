package kr.or.iei.repositories;

import kr.or.iei.entities.Product;
import kr.or.iei.entities.ProductRepository;
import kr.or.iei.entities.ProductType;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBProductRepository implements ProductRepository {

    private static final DBProductRepository INSTANCE = new DBProductRepository();

    private static final String JDBC_URL = "jdbc:oracle:thin:@localhost:1521:XE";
    private static final String DB_USER = "system";
    private static final String DB_PASS = "oracle";

    private DBProductRepository() {

    }

    public static DBProductRepository getInstance() {
        return INSTANCE;
    }

    private Connection getConnection() {
        try {
            return DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
        } catch (SQLException ignore) {
            return null;
        }
    }

    @Override
    public List<Product> getProductListByType(ProductType productType) {
        final Connection conn = getConnection();
        final String sql = "SELECT * FROM product WHERE product_type = ?";
        try (final PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, productType.name());
            final ResultSet rs = pstmt.executeQuery();
            final List<Product> list = new ArrayList<>();
            while (rs.next()) {
                list.add(createProductFromResultSet(rs));
            }
            conn.close();
            return list;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public Product getProductById(int productId) {
        final Connection conn = getConnection();
        final String sql = "SELECT * FROM product WHERE id = ?";
        try (final PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setLong(1, productId);
            final ResultSet rs = pstmt.executeQuery();
            rs.next();
            final Product product = createProductFromResultSet(rs);
            conn.close();
            return product;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    private Product createProductFromResultSet(ResultSet rs) throws SQLException {
        final long productId = rs.getLong("id");
        final String productType = rs.getString("product_type");
        final String name = rs.getString("name");
        final long price = rs.getLong("price");
        return new Product(productId, ProductType.valueOf(productType), name, price);
    }

}
