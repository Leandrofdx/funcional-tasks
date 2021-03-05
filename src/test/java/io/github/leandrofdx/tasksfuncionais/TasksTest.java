package io.github.leandrofdx.tasksfuncionais;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TasksTest {

	public WebDriver createDriver() {
		System.setProperty("webdriver.chrome.driver",
				"/Users/leandro/Documents/treinamentos/devops/drives-navegadores/chromedriver");
		WebDriver driver = new ChromeDriver();
		driver.navigate().to("http://localhost:8001/tasks/");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return driver;
	}

	@Test
	public void deveSalvarTarefaComSucesso() {

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
	public void NaodeveSalvarTarefaSemDescricao() {
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
	public void NaodeveSalvarTarefaData() {

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
