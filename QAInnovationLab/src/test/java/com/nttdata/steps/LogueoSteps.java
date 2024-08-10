package com.nttdata.steps;

import com.nttdata.page.LogueoPage;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LogueoSteps {


    private WebDriver driver;

    //constructor
    public LogueoSteps(WebDriver driver){
        this.driver = driver;
    }


    public void irLogin(){
        this.driver.findElement(By.xpath("//div[@id='_desktop_user_info']//a")).click();


    }
    /**
     * Escribir el usuario
     * @param user el usuario
     */
    public void typeUser(String user){
        WebElement userInputElement = driver.findElement(LogueoPage.userInput);
        userInputElement.sendKeys(user);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(444));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
        wait.until(ExpectedConditions.visibilityOfElementLocated(LogueoPage.loginButton));


    }

    /**
     * Escribir el password
     * @param password el password del usuario
     */
    public void typePassword(String password){

        this.driver.findElement(LogueoPage.passInput).sendKeys(password);
    }

    /**
     * Hacer click en el botón login
     */
    public void login(){

        this.driver.findElement(LogueoPage.loginButton).click();
    }


    public void irCategoria(String categoria) {


        this.driver.findElement(By.xpath("//a[contains(.,'" + categoria + "')]")).click();


    }

    public void irSubCategoria(String subcategoria) {
        this.driver.findElement(By.xpath("//a[@title='" + subcategoria + "']")).click();

    }

    public void irAlProducto() {
        this.driver.findElement(By.xpath("//div[@class='products row']/div[1]")).click();
    }

    public void ingresarCantidad(String unidades) {

        this.driver.findElement(By.xpath("//button[contains(@class,'bootstrap-touchspin-up')]")).click();


        //this.driver.findElement(LogueoPage.quantityInput).sendKeys(unidades);

    }

    public void agregarAlCarrito() {

        this.driver.findElement(By.xpath("//button[@class='btn btn-primary add-to-cart']")).click();
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void validoPopup() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement popupMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("myModalLabel")));


        String expectedMessage = "Product successfully added to your shopping cart";
        String actualMessage = popupMessage.getText();


        Assert.assertTrue("El producto no se agregó correctamente al carrito.", actualMessage.contains(expectedMessage));
    }

    public void validoMonto() {
        WebElement popup = this.driver.findElement(By.id("blockcart-modal"));

        // Extraer el precio del producto dentro del popup
        WebElement priceElement = popup.findElement(By.xpath(".//p[@class='product-price']"));
        String priceText = priceElement.getText().replace("PEN", "").trim(); // Elimina "PEN" y espacios
        double price = Double.parseDouble(priceText);

        // Extraer la cantidad del producto dentro del popup
        WebElement quantityElement = popup.findElement(By.xpath(".//span[@class='product-quantity']/strong"));
        int quantity = Integer.parseInt(quantityElement.getText().trim());

        // Realizar la multiplicación
        double total = price * quantity;

        // Extraer el valor de `product-total`
        WebElement totalElement = popup.findElement(By.xpath(".//p[@class='product-total']//span[@class='value']"));
        String totalText = totalElement.getText().replace("PEN", "").trim(); // Elimina 'PEN' y espacios en blanco
        double totalDisplayed = Double.parseDouble(totalText);

        // Comparar el valor calculado con el valor mostrado
        Assert.assertEquals("El total calculado no coincide con el total mostrado.", total, totalDisplayed, 0.01);



    }

    public void finalizoCompra() {

        this.driver.findElement(By.id("blockcart-modal")).findElement(By.xpath(".//a[@class='btn btn-primary']")).click();

    }
    public String getTitle(){
        return this.driver.findElement(LogueoPage.productsTitle).getText();
    }

    public void validoMontoCarrito() {
        double price = Double.parseDouble(this.driver.findElement(LogueoPage.precio).getText().replace("PEN", "").trim());
        int cantidad = Integer.parseInt(this.driver.findElement(By.cssSelector("div.input-group.bootstrap-touchspin input.js-cart-line-product-quantity")).getAttribute("value"));
        double total = Double.parseDouble(this.driver.findElement(By.xpath("//*[@id='main']/div/div[2]/div[1]/div[1]/div[2]/div[2]/span[2]")).getText().replace("PEN", "").trim());
        double totalDisplayed = price*cantidad;
        Assert.assertEquals("El total calculado no coincide con el total mostrado.", total, totalDisplayed, 0.01);


    }

    public void verificar() {

        //String header = this.driver.findElement(By.xpath("//*[@id='content']/section[1]/h2")).getText().trim();
        //Assert.assertEquals("Fallo al ingresar al pp", "Popular Products", header);
        //Assert.assertTrue("El elemento no es visible.", this.driver.findElement(By.xpath("//*[@id='content']/section[1]/h2")).isDisplayed());
        WebDriverWait wait = new WebDriverWait(this.driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.urlToBe("https://qalab.bensg.com/store/en/"));
        Assert.assertEquals(this.driver.getCurrentUrl(), "https://qalab.bensg.com/store/en/");



    }

    public void verificarCat(String categoria) {
        this.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));

        WebDriverWait wait = new WebDriverWait(this.driver, Duration.ofSeconds(2));


        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//a[contains(.,'" + categoria + "')]")
        ));


    }
}
