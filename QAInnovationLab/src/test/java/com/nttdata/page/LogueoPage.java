package com.nttdata.page;

import org.openqa.selenium.By;

public class LogueoPage {

    //Localizadores de elementos
    public static By userInput = By.id("field-email");
    public static By passInput = By.id("field-password");
    public static By loginButton = By.id("submit-login");
    public static By quantityInput = By.id("quantity_wanted");
    public static By productsTitle = By.cssSelector("div.card-block > h1");
    public static By precio = By.cssSelector("div.current-price span.price");
}
