package manager;

import mvc.BaseController;
import utils.Display;

public class ManagerMenuController extends BaseController<String> {
    ManagerMenuModel model = new ManagerMenuModel();
    ManagerMenuView view = new ManagerMenuView(model.getMenuOptions());

    @Override
    public String run() {
        view.printBanner();
        String input = view.chooseOption();
        try {
            int selectedOption = Integer.parseInt(input);
            if (handleOption(selectedOption) == 1) return model.menuOptions.get(selectedOption - 1);
        } catch (NumberFormatException exception) {
            view.printStringInvalidOption();
        } catch (IndexOutOfBoundsException exception) {
            view.printNumberInvalidOption();
        }
        return "InvalidOption";
    }

    public int handleOption(int selectedOption) {
        switch (selectedOption) {
            case 1, 2 -> {
                return 1;
            }
            case 3 -> Display.exit();
            default -> throw new IndexOutOfBoundsException();
        }
        return -9;
    }
}