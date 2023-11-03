package steps;

import static org.junit.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import io.github.bonigarcia.wdm.WebDriverManager;

public class LoginSteps {

WebDriver driver;  // Variavel global
String url = "https://www.saucedemo.com";

@Before // Antes de cada teste deve executar isso:
public void setup(){

    WebDriverManager.chromedriver().setup(); // Configuração do navegador
    driver = new ChromeDriver(); // Escolhendo o Google Chrome como navegador padrão

}

@After // depois de cada teste deve executar isso:
public void exit(){

    driver.quit(); // Fecha o navegador aberto

}

@Dado("que o usuario está na tela de login")
public void que_o_usuario_está_na_tela_de_login() {

    driver.get(url); // Acessa a URL
    
}

@Quando("preenche o campo de {string} e {string}")
public void preenche_o_campo_de_e(String usuario, String senha) {

    // Clica e preenche o campo username
    driver.findElement(By.id("user-name")).click(); // Clica
    driver.findElement(By.id("user-name")).sendKeys(usuario); // Escreve

    // Clica e preenche o campo password
    driver.findElement(By.id("password")).click(); // Clica
    driver.findElement(By.id("password")).sendKeys(senha); // Escreve
    
}

@Quando("clica no botão entrar")
public void clica_no_botão_entrar() {

    driver.findElement(By.id("login-button")).click(); // Clica
    
}

@Então("deve o usuario deve ter {string}")
public void deve_o_usuario_deve_ter(String resultado) {

    if(resultado == "Login com sucesso"){

        // Variavel com o valor da URL atual
        String validacao1 = driver.getCurrentUrl();

        // Compara se o resultado da URL esperada é igual a URL atual
        assertEquals("https://www.saucedemo.com/inventory.html", validacao1);


    }else if(resultado == "Login com falha"){

        // Variavel com o valor da mensagem de erro
        String validacao2 = driver.findElement(By.tagName("h3")).getText();
       
        // Compara se a mensagem de erro esperada é igual a mensagem de erro atual
        assertEquals("Epic sadface: Username and password do not match any user in this service", validacao2);

    }
    
}
    
}
