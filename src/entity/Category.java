package entity;

import java.util.Scanner;

public class Category {
    private int id;
    private String name;
    private boolean status;

    public Category(int id, String name, boolean status) {
        this.id = id;
        this.name = name;
        this.status = status;
    }

    public Category() {

    }

    // Getter cho thuộc tính id
    public int getId() {
        return id;
    }

    // Getter cho thuộc tính name
    public String getName() {
        return name;
    }

    // Getter cho thuộc tính status
    public boolean getStatus() {
        return status;
    }

    // Setter cho thuộc tính id
    public void setId(int id) {
        this.id = id;
    }

    // Setter cho thuộc tính name
    public void setName(String name) {
        this.name = name;
    }

    // Setter cho thuộc tính status
    public void setStatus(boolean status) {
        this.status = status;
    }


    // Phương thức show thông tin
    public void showData() {
        System.out.println("ID: " + this.id);
        System.out.println("Tên danh mục: " + this.name);
        System.out.println("Trạng thái: " + (this.status ? "Hoạt động" : "Không hoạt động"));
    }

    // Phương thức nhập thông tin
    public void inputData(Scanner scanner) {
        System.out.println("Nhập vào tên danh mục:");
        this.name = scanner.nextLine();

        System.out.println("Nhập vào trạng thái (true cho Hoạt động, false cho Không hoạt động):");
        this.status = Boolean.parseBoolean(scanner.nextLine());
    }
}
