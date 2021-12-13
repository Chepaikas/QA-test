package Test19;

import org.junit.Test;

public class Tests {

    public Application app = new Application();

    @Test
    public void addAndDelete() throws Exception {
        int amountToAdd = 5;
        while (amountToAdd>0) {
            app.chooseParameters("Most Popular");
            app.addToCart(1,"Large");
            amountToAdd--;
        }
        app.deleteCart();
        app.closeApplication();
    }
}
