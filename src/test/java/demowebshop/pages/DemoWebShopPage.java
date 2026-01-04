package demowebshop.pages;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.Cookie;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class DemoWebShopPage {

    SelenideElement shoppingCartButton = $("#topcartlink");
    SelenideElement titleOnTheShoppingCartPage = $(".page-title");
    SelenideElement productQuantityOnTheShoppingCartPage = $(".cart-qty");
    SelenideElement inputProductQuantityOnTheShoppingCartPage = $(".qty input");
    SelenideElement titleAfterRemovingOnTheShoppingCartPage = $(".page-body");
    SelenideElement productPriceAfterAddingOnTheShoppingCartPage = $(".product-subtotal");

    public DemoWebShopPage openPage() {
        open("/");
        return this;
    }

    public DemoWebShopPage setAuthCookie(String cookie) {
        WebDriverRunner.getWebDriver().manage().addCookie(new Cookie("NOPCOMMERCE.AUTH", cookie));
        refresh();
        return this;
    }

    public DemoWebShopPage clickOnShoppingCartButton() {
        shoppingCartButton.click();
        return this;
    }

    public DemoWebShopPage assertShoppingCartText() {
        titleOnTheShoppingCartPage.shouldHave(text("Shopping cart"));
        return this;
    }

    public DemoWebShopPage assertQuantityOnTheShoppingCartPage(String quantity) {
        productQuantityOnTheShoppingCartPage.shouldHave(text(quantity));
        return this;
    }

    public DemoWebShopPage setProductQuantityOnTheShoppingCartPage(String quantity) {
        inputProductQuantityOnTheShoppingCartPage.setValue(quantity).pressEnter();
        return this;
    }

    public DemoWebShopPage assertTitleAfterRemovingOnTheShoppingCartPage() {
        titleAfterRemovingOnTheShoppingCartPage.shouldHave(text("Your Shopping Cart is empty!"));
        return this;
    }

    public DemoWebShopPage assertProductPriceAfterAddingOnTheShoppingCartPage(String price) {
        productPriceAfterAddingOnTheShoppingCartPage.shouldHave(text(price));
        return this;
    }
}
