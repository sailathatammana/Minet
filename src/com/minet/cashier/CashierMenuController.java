package com.minet.cashier;

import com.minet.mvc.BaseController;
import com.minet.utils.Display;

public class CashierMenuController extends BaseController<String> {
    CashierMenuModel model = new CashierMenuModel();
    CashierMenuView view = new CashierMenuView(model.getMenuOptions());

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
            case 1, 2, 3, 4, 5 -> {
                return 1;
            }
            case 6 -> Display.exit();
            default -> throw new IndexOutOfBoundsException();
        }
        return -9;
    }
}
