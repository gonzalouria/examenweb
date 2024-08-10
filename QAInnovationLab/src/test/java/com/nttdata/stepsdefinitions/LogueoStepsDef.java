package com.nttdata.stepsdefinitions;

import com.nttdata.steps.LogueoSteps;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;

import static com.nttdata.core.DriverManager.getDriver;
import static com.nttdata.core.DriverManager.screenShot;


public class LogueoStepsDef {

    private WebDriver driver ;


    @Given("estoy en la página de la tienda")
    public void estoyEnLaPáginaDeLaTienda() {
        driver = getDriver();
        driver.get("https://qalab.bensg.com/store");
        screenShot();

    }

    @And("me logueo con mi usuario {string} y clave {string}")
    public void meLogueoConMiUsuarioYClave(String usuario, String clave) {
        LogueoSteps logueoSteps = new LogueoSteps(driver);
        logueoSteps.irLogin();
        logueoSteps.typeUser(usuario);
        logueoSteps.typePassword(clave);
        logueoSteps.login();
        logueoSteps.verificar();
        screenShot();


    }


    @When("navego a la categoria {string} y subcategoria {string}")
    public void navegoALaCategoriaYSubcategoria(String categoria, String subcategoria) {
        LogueoSteps logueoSteps = new LogueoSteps(driver);
        logueoSteps.verificarCat(categoria);
        logueoSteps.irCategoria(categoria);
        logueoSteps.irSubCategoria(subcategoria);
        screenShot();

    }

    @And("agrego {string} unidades del primer producto al carrito")
    public void agregoUnidadesDelPrimerProductoAlCarrito(String unidades) {
        LogueoSteps logueoSteps = new LogueoSteps(driver);
        logueoSteps.irAlProducto();
        logueoSteps.ingresarCantidad(unidades);
        logueoSteps.agregarAlCarrito();
        screenShot();

    }

    @Then("valido en el popup la confirmación del producto agregado")
    public void validoEnElPopupLaConfirmaciónDelProductoAgregado() {
        LogueoSteps logueoSteps = new LogueoSteps(driver);
        logueoSteps.validoPopup();
        screenShot();

    }

    @And("valido en el popup que el monto total sea calculado correctamente")
    public void validoEnElPopupQueElMontoTotalSeaCalculadoCorrectamente() {
        LogueoSteps logueoSteps = new LogueoSteps(driver);
        logueoSteps.validoMonto();
    }

    @When("finalizo la compra")
    public void finalizoLaCompra() {
        LogueoSteps logueoSteps = new LogueoSteps(driver);
        logueoSteps.finalizoCompra();
        screenShot();

    }

    @Then("valido el titulo de la pagina del carrito")
    public void validoElTituloDeLaPaginaDelCarrito() {
        LogueoSteps logueoSteps = new LogueoSteps(driver);

        Assertions.assertEquals("SHOPPING CART", logueoSteps.getTitle());
        screenShot();


    }

    @And("vuelvo a validar el calculo de precios en el carrito")
    public void vuelvoAValidarElCalculoDePreciosEnElCarrito() {
        LogueoSteps logueoSteps = new LogueoSteps(driver);
        logueoSteps.validoMontoCarrito();

    }
}
