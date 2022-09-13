package homeMenu;

import utils.Display;

import java.util.Scanner;

public class HomeMenuController {
    private final HomeMenuModel model;
    private final Scanner scanner;

    public HomeMenuController(HomeMenuModel model) {
        this.model = model;
        this.scanner = new Scanner(System.in);
    }

    public void requestUserInput() {
        String input = scanner.nextLine();

        try {
            int selectedOption = Integer.parseInt(input);

            model.handleOption(selectedOption);
        } catch (NumberFormatException | IndexOutOfBoundsException exception) {
            Display.printInvalidOption();
            Display.chooseOption();
            requestUserInput();
        }
    }
}
