package io.github.leandrofdx.tasksfuncionais;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class TasksTest {


	public WebDriver createDriver() throws MalformedURLException {
//		System.setProperty("webdriver.chrome.driver",
//				"/Users/leandro/Documents/treinamentos/devops/drives-navegadores/chromedriver");
		DesiredCapabilities cap = DesiredCapabilities.chrome();
		WebDriver driver = new RemoteWebDriver(new URL("http://192.168.1.4:4444/wd/hub"), cap);
		driver.navigate().to("http://192.168.1.4:8001/tasks/");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return driver;
	}

	@Test
	public void deveSalvarTarefaComSucesso() throws MalformedURLException {

		WebDriver driver = createDriver();

		try {
			driver.findElement(By.id("addTodo")).click();
			driver.findElement(By.id("task")).sendKeys("Tese via selenium");
			driver.findElement(By.id("dueDate")).sendKeys("10/10/2028");
			driver.findElement(By.id("saveButton")).click();
			Assert.assertEquals("Sucesso!", driver.findElement(By.id("message")).getText());
		} finally {
			driver.quit();
		}
	}

	@Test
	public void NaodeveSalvarTarefaSemDescricao() throws MalformedURLException {
		WebDriver driver = createDriver();
		try {
			driver.findElement(By.id("addTodo")).click();
			driver.findElement(By.id("dueDate")).sendKeys("10/10/2028");
			driver.findElement(By.id("saveButton")).click();
			Assert.assertEquals("Fill the task description", driver.findElement(By.id("message")).getText());
		} finally {
			driver.quit();
		}
	}

	@Test
	public void NaodeveSalvarTarefaData() throws MalformedURLException {

		WebDriver driver = createDriver();
		try {
			driver.findElement(By.id("addTodo")).click();
			driver.findElement(By.id("task")).sendKeys("Tese via selenium");
			driver.findElement(By.id("saveButton")).click();
			Assert.assertEquals("Fill the due date", driver.findElement(By.id("message")).getText());
		} finally {
			driver.quit();
		}

	}

}
