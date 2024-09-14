package business;

import entity.Product;
import util.ConnectionDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ProductBusiness {
    // Thêm sản phẩm
    public void addProduct(Scanner scanner) {
        Product product = new Product();
        product.inputData(scanner); // Dùng inputData để nhập thông tin sản phẩm

        try (Connection connection = ConnectionDB.openConnection()) {
            String sql = "INSERT INTO product (name, price, image, sale_price, category_id) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, product.getName());
            statement.setFloat(2, product.getPrice());
            statement.setString(3, product.getImage());
            statement.setFloat(4, product.getSalePrice());
            statement.setInt(5, product.getCategoryId()); // Thêm category_id

            int rowsInserted = statement.executeUpdate(); // Thực hiện lệnh SQL
            if (rowsInserted > 0) {
                System.out.println("Thêm sản phẩm thành công!");
            } else {
                System.out.println("Thêm sản phẩm thất bại.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // Kết nối sẽ tự động đóng khi ra khỏi khối try-with-resources
    }

    // Sửa sản phẩm
    public void updateProduct(Scanner scanner) {
        int id = -1;
        boolean validInput = false;

        // Vòng lặp để yêu cầu người dùng nhập lại nếu nhập không đúng
        while (!validInput) {
            System.out.print("Nhập ID sản phẩm cần sửa: ");
            String input = scanner.nextLine();

            if (input.isEmpty()) {
                System.out.println("ID không được để trống. Vui lòng nhập lại.");
            } else {
                try {
                    id = Integer.parseInt(input); // Chuyển đổi chuỗi thành số nguyên
                    validInput = true; // Thoát khỏi vòng lặp nếu chuyển đổi thành công
                } catch (NumberFormatException e) {
                    System.out.println("ID không hợp lệ. Vui lòng nhập một số.");
                }
            }
        }

        try (Connection connection = ConnectionDB.openConnection()) {
            String selectSql = "SELECT * FROM product WHERE id = ?";
            PreparedStatement selectStatement = connection.prepareStatement(selectSql);
            selectStatement.setInt(1, id);
            ResultSet resultSet = selectStatement.executeQuery();

            if (resultSet.next()) {
                Product product = new Product();
                product.setId(id);
                product.inputData(scanner); // Nhập lại thông tin sản phẩm

                String updateSql = "UPDATE product SET name = ?, price = ?, image = ?, sale_price = ?, category_id = ? WHERE id = ?";
                PreparedStatement updateStatement = connection.prepareStatement(updateSql);
                updateStatement.setString(1, product.getName());
                updateStatement.setFloat(2, product.getPrice());
                updateStatement.setString(3, product.getImage());
                updateStatement.setFloat(4, product.getSalePrice());
                updateStatement.setInt(5, product.getCategoryId());
                updateStatement.setInt(6, product.getId());

                int rowsUpdated = updateStatement.executeUpdate();
                if (rowsUpdated > 0) {
                    System.out.println("Sửa sản phẩm thành công!");
                } else {
                    System.out.println("Sửa sản phẩm thất bại.");
                }
            } else {
                System.out.println("Không tìm thấy sản phẩm với ID này.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    // Xóa sản phẩm
    public void deleteProduct(Scanner scanner) {
        System.out.print("Nhập ID sản phẩm cần xóa: ");
        int id = Integer.parseInt(scanner.nextLine());

        try (Connection connection = ConnectionDB.openConnection()) {
            String sql = "DELETE FROM product WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            int rowsDeleted = statement.executeUpdate();

            if (rowsDeleted > 0) {
                System.out.println("Xóa sản phẩm thành công!");
            } else {
                System.out.println("Không tìm thấy sản phẩm với ID này.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Tìm kiếm sản phẩm theo tên
    public void searchProductByName(Scanner scanner) {
        System.out.print("Nhập tên sản phẩm cần tìm: ");
        String name = scanner.nextLine();

        try (Connection connection = ConnectionDB.openConnection()) {
            String sql = "SELECT * FROM product WHERE name LIKE ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, "%" + name + "%");
            ResultSet resultSet = statement.executeQuery();

            List<Product> products = new ArrayList<>();
            while (resultSet.next()) {
                Product product = new Product();
                product.setId(resultSet.getInt("id"));
                product.setName(resultSet.getString("name"));
                product.setPrice(resultSet.getFloat("price"));
                product.setImage(resultSet.getString("image"));
                product.setSalePrice(resultSet.getFloat("sale_price"));
                product.setCategoryId(resultSet.getInt("category_id")); // Lấy category_id
                products.add(product);
            }

            if (products.isEmpty()) {
                System.out.println("Không tìm thấy sản phẩm nào.");
            } else {
                for (Product product : products) {
                    product.showData();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Hiển thị tất cả sản phẩm
    public void showAllProducts() {
        try (Connection connection = ConnectionDB.openConnection()) {
            String sql = "SELECT * FROM product";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            List<Product> products = new ArrayList<>();
            while (resultSet.next()) {
                Product product = new Product();
                product.setId(resultSet.getInt("id"));
                product.setName(resultSet.getString("name"));
                product.setPrice(resultSet.getFloat("price"));
                product.setImage(resultSet.getString("image"));
                product.setSalePrice(resultSet.getFloat("sale_price"));
                product.setCategoryId(resultSet.getInt("category_id")); // Lấy category_id
                products.add(product);
            }

            if (products.isEmpty()) {
                System.out.println("Không có sản phẩm nào.");
            } else {
                for (Product product : products) {
                    product.showData();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
