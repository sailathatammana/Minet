package mvc;

import java.util.List;

public abstract class BaseModel {

    public List<String> getMenuOptions() {
        return null;
    }

    public void handleOption(int selectedOption) throws IndexOutOfBoundsException {
    }
}
