package business;

import entity.Category;
import util.ConnectionDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CategoryBusiness {

    // Thêm mới danh mục
    public void addCategory(Scanner scanner) {
        Connection connection = ConnectionDB.openConnection();  // Use openConnection instead of getConnection
        if (connection != null) {
            try {
                System.out.println("Nhập tên danh mục:");
                String name = scanner.nextLine();

                System.out.println("Nhập trạng thái danh mục (true: hoạt động, false: không hoạt động):");
                boolean status = Boolean.parseBoolean(scanner.nextLine());

                String sql = "INSERT INTO category (name, status) VALUES (?, ?)";
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, name);
                preparedStatement.setBoolean(2, status);

                int rowsAffected = preparedStatement.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Thêm danh mục thành công!");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                ConnectionDB.closeConnection(connection);  // Close connection using the method in ConnectionDB
            }
        }
    }

    // Hiển thị tất cả danh mục
    public void showAllCategories() {
        Connection connection = ConnectionDB.openConnection();  // Use openConnection instead of getConnection
        if (connection != null) {
            try {
                String sql = "SELECT * FROM category";
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sql);

                List<Category> categories = new ArrayList<>();
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String name = resultSet.getString("name");
                    boolean status = resultSet.getBoolean("status");

                    Category category = new Category(id, name, status);
                    categories.add(category);
                }

                for (Category category : categories) {
                    category.showData();
                }

            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                ConnectionDB.closeConnection(connection);  // Close connection using the method in ConnectionDB
            }
        }
    }

    // Sửa danh mục
    public void updateCategory(Scanner scanner) {
        Connection connection = ConnectionDB.openConnection();  // Use openConnection instead of getConnection
        if (connection != null) {
            try {
                System.out.println("Nhập ID danh mục cần sửa:");
                int id = Integer.parseInt(scanner.nextLine());

                System.out.println("Nhập tên danh mục mới:");
                String newName = scanner.nextLine();

                System.out.println("Nhập trạng thái mới (true: hoạt động, false: không hoạt động):");
                boolean newStatus = Boolean.parseBoolean(scanner.nextLine());

                String sql = "UPDATE category SET name = ?, status = ? WHERE id = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, newName);
                preparedStatement.setBoolean(2, newStatus);
                preparedStatement.setInt(3, id);

                int rowsAffected = preparedStatement.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Cập nhật danh mục thành công!");
                } else {
                    System.out.println("Không tìm thấy danh mục với ID: " + id);
                }

            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                ConnectionDB.closeConnection(connection);  // Close connection using the method in ConnectionDB
            }
        }
    }

    // Xóa danh mục
    public void deleteCategory(Scanner scanner) {
        Connection connection = ConnectionDB.openConnection();  // Use openConnection instead of getConnection
        if (connection != null) {
            try {
                System.out.println("Nhập ID danh mục cần xóa:");
                int id = Integer.parseInt(scanner.nextLine());

                String sql = "DELETE FROM category WHERE id = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setInt(1, id);

                int rowsAffected = preparedStatement.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Xóa danh mục thành công!");
                } else {
                    System.out.println("Không tìm thấy danh mục với ID: " + id);
                }

            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                ConnectionDB.closeConnection(connection);  // Close connection using the method in ConnectionDB
            }
        }
    }
}
