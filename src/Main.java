import homeMenu.HomeMenu;

public class Main {
    public static void main(String[] args) {
        HomeMenu homeMenu = new HomeMenu();
        homeMenu.start();
       /*FileHandler<String> fileHandler = new FileHandler<>();
        fileHandler.writeToFile(List.of("Hi","Hello"),"assets/temp.txt");

        FileHandler<Integer> fileHandler1 = new FileHandler<>();
        fileHandler1.writeToFile(List.of(1,2),"assets/temp1.txt");*/
    }
}