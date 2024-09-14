package entity;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Product {
    private int id;
    private String name;
    private float price;
    private float salePrice;
    private String image;
    private int categoryId; // Thêm category_id

    public Product() {}

    public Product(int id, String name, float price, float salePrice, String image, int categoryId) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.salePrice = salePrice;
        this.image = image;
        this.categoryId = categoryId;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(float salePrice) {
        this.salePrice = salePrice;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    // Input data from user
    public void inputData(Scanner scanner) {
        boolean valid = false;
        while (!valid) {
            try {
                System.out.print("Nhập tên sản phẩm: ");
                this.name = scanner.nextLine();

                System.out.print("Nhập giá sản phẩm: ");
                this.price = scanner.nextFloat();

                System.out.print("Nhập giá khuyến mãi sản phẩm: ");
                this.salePrice = scanner.nextFloat();

                scanner.nextLine(); // Clear the buffer

                System.out.print("Nhập đường dẫn hình ảnh sản phẩm: ");
                this.image = scanner.nextLine();

                System.out.print("Nhập mã danh mục sản phẩm (category_id): ");
                this.categoryId = scanner.nextInt(); // Nhập category_id

                valid = true; // If all inputs are valid, exit the loop

            } catch (InputMismatchException e) {
                System.out.println("Dữ liệu nhập không hợp lệ. Vui lòng nhập lại.");
                scanner.nextLine(); // Clear the invalid input
            }
        }
    }

    public void showData() {
        System.out.println("======= Chi tiết sản phẩm =======");
        System.out.println("ID sản phẩm: " + this.id);
        System.out.println("Tên sản phẩm: " + this.name);
        System.out.printf("Giá: %.2f VND\n", this.price);
        System.out.printf("Giá khuyến mãi: %.2f VND\n", this.salePrice);
        System.out.println("Hình ảnh: " + this.image);
        System.out.println("Mã danh mục (category_id): " + this.categoryId); // Hiển thị category_id
        System.out.println("================================");
    }
}
