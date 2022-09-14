package homeMenu;

import mvc.BaseController;
import utils.Display;

import java.util.Scanner;

public class HomeMenuController extends BaseController {

    private final Scanner scanner;

    public HomeMenuController(HomeMenuModel model,HomeMenuView view) {
       super(model,view);
        this.scanner = new Scanner(System.in);
    }

    public void requestUserInput() {
        String input = view.chooseOption();;

        try {
            int selectedOption = Integer.parseInt(input);

            model.handleOption(selectedOption);
        } catch (NumberFormatException | IndexOutOfBoundsException exception) {
            view.printNumberInvalidOption();
            requestUserInput();
        }
    }
}