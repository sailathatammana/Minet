package mvc;

public abstract class BaseController<T> {

    public BaseController() {
    }

    public T run() {
        return null;
    }

    public boolean handleOption(int selectedOption) {
        return false;
    }

}
