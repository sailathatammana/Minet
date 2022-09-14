package mvc;

public abstract class BaseController {
    protected final BaseModel model;
    protected final BaseView view;

    public BaseController(BaseModel model, BaseView view) {
        this.model = model;
        this.view = view;
    }

    public void requestUserInput() {
    }

}
