package Login;

public class LoginMenuModel {
    public void handleOption(String role) {
        switch (role) {
            case "Cashier" -> System.out.println("Cashier");
            case "Manager" -> System.out.println("Manager");
            case "Admin" -> System.out.println("Admin");
        }
    }
}
