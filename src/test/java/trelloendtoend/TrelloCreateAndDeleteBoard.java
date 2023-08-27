package trelloendtoend;

import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.qsp.trello.genericutility.BaseClass;
import com.qsp.trello.genericutility.FileUtility;
import com.qsp.trello.pomrepo.TrelloBoardsPage;
import com.qsp.trello.pomrepo.TrelloHomePage;
import com.qsp.trello.pomrepo.TrelloLoginPage;
import com.qsp.trello.pomrepo.TrelloLoginToContinuePage;

public class TrelloCreateAndDeleteBoard extends BaseClass {

	@Test
	public void CreateAndDeleteBoard() throws IOException {
		Reporter.log("Execution has been started");
		TrelloHomePage homePage = new TrelloHomePage(driver);
		homePage.getLoginOption().click();
		Reporter.log("Clicked on login option");
		TrelloLoginPage loginPage = new TrelloLoginPage(driver);
		loginPage.getEmailTextField().sendKeys(fileUtils.readTheDataFromPropertyFile("email"));
		Reporter.log("Email has been entered");
		loginPage.getLoginButton().submit();
		Reporter.log("Clicked on login button");
		TrelloLoginToContinuePage loginToContinuePage = new TrelloLoginToContinuePage(driver);
		//loginToContinuePage.getPasswordTextField().sendKeys(fileUtils.readTheDataFromPropertyFile("password"));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(loginToContinuePage.getPasswordTextField())).sendKeys(fileUtils.readTheDataFromPropertyFile("password"));
		Reporter.log("Password has been entered");
		loginToContinuePage.getLoginToContinueButton().submit();
		Reporter.log("Clicked on login to continue button");
        TrelloBoardsPage boardsPage = new TrelloBoardsPage(driver);
        boardsPage.getCreateNewBoardOption().click();
        Reporter.log("Clicked on Create New Board");
        boardsPage.getBoardTitle().sendKeys(fileUtils.readTheDataFromPropertyFile("boardtitle"));
        Reporter.log("Entered the Board Title");
        wait.until(ExpectedConditions.elementToBeClickable(boardsPage.getCreateButton()));
        Actions actions = new Actions(driver);
        actions.moveToElement(boardsPage.getCreateButton()).click(boardsPage.getCreateButton()).perform();
        Reporter.log("Clicked on Create button");
        Reporter.log("Execution has been stopped");
	}
}
