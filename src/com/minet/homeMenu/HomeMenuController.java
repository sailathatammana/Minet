package com.minet.homeMenu;

import com.minet.mvc.BaseController;
import com.minet.utils.Display;

public class HomeMenuController extends BaseController<String> {
    HomeMenuModel model = new HomeMenuModel();
    HomeMenuView view = new HomeMenuView(model.getMenuOptions());

    @Override
    public String run() {
        String input = view.chooseOption();
        try {
            int selectedOption = Integer.parseInt(input);
            if (handleOption(selectedOption) == 1) return model.getMenuOptions().get(selectedOption - 1);
        } catch (NumberFormatException exception) {
            view.printStringInvalidOption();
        } catch (IndexOutOfBoundsException exception) {
            view.printNumberInvalidOption();
        }
        return "InvalidOption";
    }

    @Override
    public int handleOption(int selectedOption) {
        switch (selectedOption) {
            case 1, 2, 3 -> {
                return 1;
            }
            case 4 -> Display.exit();
            default -> throw new IndexOutOfBoundsException();
        }
        return 0;
    }

}