package persentation;

import business.CategoryBusiness;
import business.ProductBusiness;

import java.util.Scanner;

public class Ecommerce {
    private CategoryBusiness categoryBusiness;
    private ProductBusiness productBusiness;

    public Ecommerce() {
        categoryBusiness = new CategoryBusiness();
        productBusiness = new ProductBusiness();
    }

    public static void main(String[] args) {
        Ecommerce ecommerce = new Ecommerce();
        Scanner scanner = new Scanner(System.in);
        ecommerce.showMenu(scanner);
    }

    // Phương thức hiển thị menu chính
    public void showMenu(Scanner scanner) {
        while (true) {
            System.out.println("============= MENU =============");
            System.out.println("1. Quản lý danh mục");
            System.out.println("2. Quản lý sản phẩm");
            System.out.println("3. Thoát");
            System.out.println("Chọn chức năng:");

            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    categoryManager(scanner);
                    break;
                case 2:
                    productManager(scanner);
                    break;
                case 3:
                    System.out.println("Cảm ơn bạn đã sử dụng chương trình.");
                    System.exit(0);
                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng chọn lại.");
            }
        }
    }

    // Quản lý danh mục
    private void categoryManager(Scanner scanner) {
        while (true) {
            System.out.println("======= CATEGORY MANAGER =======");
            System.out.println("1. Thêm mới danh mục");
            System.out.println("2. Sửa danh mục");
            System.out.println("3. Xóa danh mục");
            System.out.println("4. Hiển thị danh sách danh mục");
            System.out.println("5. Quay lại menu chính");
            System.out.println("Chọn chức năng:");

            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    categoryBusiness.addCategory(scanner);
                    break;
                case 2:
                    categoryBusiness.updateCategory(scanner);
                    break;
                case 3:
                    categoryBusiness.deleteCategory(scanner);
                    break;
                case 4:
                    categoryBusiness.showAllCategories();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng chọn lại.");
            }
        }
    }

    // Quản lý sản phẩm
    private void productManager(Scanner scanner) {
        while (true) {
            System.out.println("======= PRODUCT MANAGER =======");
            System.out.println("1. Thêm mới sản phẩm");
            System.out.println("2. Sửa sản phẩm");
            System.out.println("3. Xóa sản phẩm");
            System.out.println("4. Tìm kiếm sản phẩm theo tên");
            System.out.println("5. Hiển thị danh sách sản phẩm");
            System.out.println("6. Quay lại menu chính");
            System.out.println("Chọn chức năng:");

            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    productBusiness.addProduct(scanner);
                    break;
                case 2:
                    productBusiness.updateProduct(scanner);
                    break;
                case 3:
                    productBusiness.deleteProduct(scanner);
                    break;
                case 4:
                    productBusiness.searchProductByName(scanner);
                    break;
                case 5:
                    productBusiness.showAllProducts();
                    break;
                case 6:
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng chọn lại.");
            }
        }
    }
}
