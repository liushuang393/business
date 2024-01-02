package com.liushuang.test.tool.webdriver;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.liushuang.test.tool.utils.Utils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class WebDriverControl {

	public static final String WINDOW_HANDLES = "window_handles";
	public static final String WINDOW_ROOT = "root";
	private WebDriver driver = null;
	private boolean isOpen = false;
	private Stack<String> windowHandleStack = new Stack<>();
	private String testUrl;
	private int windowSizeRow = 1024;
	private int windowSizeCol = 768;
	private WebDriverType browser = WebDriverType.None;

	private String webDriverVer = "97";
	private final Map<String, Object> varsHandles = new HashMap<>();
	// private WebDriverType prepareType = WebDriverType.None;
	// リモートWebDriverクライアントを実行するには、まずRemoteWebDriverに接続する必要があります。
	// これを行うには、テストを実行しているサーバーのアドレスをURLに指定します。
	// FirefoxOptions firefoxOptions = new FirefoxOptions();
	// chromeOptions.setCapability("browserVersion", "67");
	// chromeOptions.setCapability("platformName", "Windows XP");
	// WebDriver driver = new RemoteWebDriver(new URL("http://www.example.com"),
	// firefoxOptions);
	// driver.get("http://www.google.com");

	// ローカルファイルDetector
	// driver.setFileDetector(new LocalFileDetector());
	// driver.get("http://sso.dev.saucelabs.com/test/guinea-file-upload");
	// WebElement upload = driver.findElement(By.id("myfile"));
	// upload.sendKeys("/Users/sso/the/local/path/to/darkbulb.jpg");

	// スクリーンショットの取得
	// WebDriver driver = new ChromeDriver();
	// driver.get("http://www.example.com");
	// File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	// FileUtils.copyFile(scrFile, new File("./image.png"));
	// driver.quit();
	// TODO Selenium Grid対応すれば、driver対応必要
	// public WebDriver createDriverGrid() throws MalformedURLException {
	// String hubUrl= "http://localhost:4446/wd/hub";
	// ChromeOptions capabilities= new ChromeOptions();
	// capabilities.setCapability(CapabilityType.BROWSER_NAME, "chrome");
	// driver.set(new RemoteWebDriver(new URL(hubUrl), capabilities));
	// return driver.get();
	// }
	public void initDriver() throws Exception {
		if (driver != null) {
			close();
		}

		String pathdrv = "exe/" + webDriverVer;
		Map<String, Object> prefs = new HashMap<>();
		String[] exclude = { "enable-automation", "load-extension" };
		switch (browser) {
		case EDGE:
			System.setProperty("webdriver.edge.driver", pathdrv + "/msedgedriver.exe");

			// ============== prefs準備 =========
			prefs.put("printing.print_preview_sticky_settings.appState", printSetUp());
			prefs.put("profile.default_content_settings.popups", 0);// ポップアップ禁止設定
			// prefs.put("download.default_directory", "~/Downloads");// ダウンロードファイルパス設定
			EdgeOptions edgeOptions = new EdgeOptions();
			edgeOptions.addArguments("--remote-allow-origins=*");
			edgeOptions.setExperimentalOption("prefs", prefs);// prefs --> chopt
			edgeOptions.setExperimentalOption("useAutomationExtension", false);
			edgeOptions.setExperimentalOption("excludeSwitches", exclude);
			edgeOptions.addArguments(new String[] {
					// "--headless",
					"--disable-gpu", "--test-type", "disable-infobars",
					// "--start-maximized",
					"--disable-site-isolation-trials"
					// , "--incognito"
					// "--kiosk-printing"
			});
			driver = new EdgeDriver(edgeOptions);
			JavascriptExecutor jsedge = (JavascriptExecutor) driver;
			jsedge.executeScript("document.body.style.zoom='100%'");
			log.info("{} is started.[WebDriver:{}]", pathdrv + "/msedgedriver", "Edge");
			break;
		case FIREFOX:

			System.setProperty("webdriver.gecko.driver", pathdrv + "/geckodriver.exe");
			driver = new FirefoxDriver();
			log.info("{} is started.[WebDriver:{}]", pathdrv + "/geckodriver.exe", "FIREFOX");
			break;
		case IE:
			System.setProperty("webdriver.ie.driver", pathdrv + "/IEDriverServer.exe");

			InternetExplorerOptions ieOptions = new InternetExplorerOptions();
			ieOptions.ignoreZoomSettings();
			driver = new InternetExplorerDriver(ieOptions);
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("document.body.style.zoom='100%'");

			log.info("{} is started.[WebDriver:{}]", pathdrv + "/IEDriverServer.exe", "IE（64・32)");
			break;
		case IE_MODE:
			System.setProperty("webdriver.ie.driver", pathdrv + "/IEDriverServer.exe");

			InternetExplorerOptions ieOptionsmode = new InternetExplorerOptions();
			ieOptionsmode.attachToEdgeChrome();
			ieOptionsmode.withEdgeExecutablePath("C:\\Program Files (x86)\\Microsoft\\Edge\\Application\\msedge.exe");
			ieOptionsmode.ignoreZoomSettings();
			driver = new InternetExplorerDriver(ieOptionsmode);
			JavascriptExecutor jsmode = (JavascriptExecutor) driver;
			jsmode.executeScript("document.body.style.zoom='100%'");

			log.info("{} is started.[WebDriver:{}]", pathdrv + "/IEDriverServer.exe", "IE Mode（64・32)");
			break;
		default:
			System.setProperty("webdriver.chrome.driver", pathdrv + "/chromedriver.exe");
			// WebDriverManager.chromedriver().setup();
			// ============== prefs準備 =========

			prefs.put("printing.print_preview_sticky_settings.appState", printSetUp());
			prefs.put("profile.default_content_settings.popups", 0);// ポップアップ禁止設定
			// prefs.put("download.default_directory", "~/Downloads");// ダウンロードファイルパス設定
			// prefsdic.put("profile.default_content_settings.geolocation", 2);
			// prefsdic.put("profile.managed_default_content_settings.images", 2);
			// prefsdic.put("printing.print_preview_sticky_settings.appState", dic);
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--remote-allow-origins=*");
			// HashMap<String, Object> chromeOptionsMap = new HashMap<>();
			// options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
			options.addArguments(new String[] {
					// "--headless",
					"--disable-gpu", "--test-type", "disable-infobars", "--disable-site-isolation-trials"
					// , "--incognito"
					// "\"--user-data-dir=C:/Users/liushuang/AppData/Local/Google/Chrome/User
					// Data/\""
					// "--start-maximized"
					// "--kiosk-printing"
			});
			// Dictionary appState = new Dictionary<String, Object>();
			// {
			// { "recentDestinations",
			// new List<object>(){
			// new Dictionary<string, string>() {
			// { "id", "Save as PDF" },
			// { "origin", "local" },
			// {"account", "" },
			// }
			// }},
			// { "selectedDestinationId", "Save as PDF" },
			// { "version", 2 },
			// { "isHeaderFooterEnabled", false },
			// { "isLandscapeEnabled", true }
			// };

			// options.AddUserProfilePreference(
			// "printing.print_preview_sticky_settings.appState", printSetUp()
			// JsonSerializer(appState);
			// );

			options.setExperimentalOption("prefs", prefs);// prefs --> chopt
			options.setExperimentalOption("excludeSwitches", exclude);// prefs --> chopt
			// options.setBinary("C:\\Program Files\\Google\\Chrome
			// Beta\\Application\\chrome.exe");
			driver = new ChromeDriver(options);
			JavascriptExecutor jsdf = (JavascriptExecutor) driver;
			jsdf.executeScript("document.body.style.zoom='100%'");

			log.info("{} is started.[WebDriver:{}]", pathdrv + "/chromedriver", "CHROME");
			break;

		}

		// driver.get("edge://version");

		// driver.get("http://www.google.com/xhtml");
		Thread.sleep(3000);
		// WebElement searchBox = driver.findElement(By.name("q"));
		// searchBox.sendKeys("ChromeDriver");
		// searchBox.submit();

		driver.manage().timeouts().implicitlyWait(duration(10));
		driver.manage().window().setSize(new Dimension(windowSizeRow, windowSizeCol));
		// driver.manage().window().maximize();

		if (!WebDriverType.IE.equals(browser)) {
			driver.manage().window().fullscreen();
		}

	}

	private String printSetUp() {
		// 印刷としてPDF保存する設定

		// String appState = "{" + "\"recentDestinations\": [ " + "{" + "\"id\": \"Save
		// as PDF\","
		// + "\"origin\": \"local\"," + " \"account\":\"\"" + "}]," +
		// "\"selectedDestinationId\":\"Save as PDF\","
		// + "\"version\":2," + "\"isLandscapeEnabled\":True, " + // 印刷の向きを指定
		// // tureで横向き、falseで縦向き。
		// "\"pageSize\":'A3'," + // 用紙タイプ(A3、A4、A5、Legal、 Letter、Tabloidなど)
		// // "mediaSize": {"height_microns": 355600, "width_microns": 215900}, //紙のサイズ
		// // （10000マイクロメートル = １cm）
		// // "marginsType": 0, //余白タイプ //0:デフォルト 1:余白なし 2:最小
		// // "scalingType": 3 , //0：デフォルト 1：ページに合わせる 2：用紙に合わせる 3：カスタム
		// // "scaling": "141" ,//倍率
		// // "profile.managed_default_content_settings.images": 2, //画像を読み込ませない
		// "\"isHeaderFooterEnabled\":False," + // ヘッダーとフッター
		// "\"isCssBackgroundEnabled\":True" + // 背景のグラフィック
		// // "isDuplexEnabled": False, //両面印刷 tureで両面印刷、falseで片面印刷
		// // "isColorEnabled": True, //カラー印刷 trueでカラー、falseで白黒
		// // "isCollateEnabled":True // 部単位で印刷
		//
		// "}";

		// prefs={'printing.print_preview_sticky_settings.appState':json.dumps(appState),"download.default_directory":"~/Downloads"}
		// // appState
		// -->
		// pref
		return "{\"recentDestinations\":[{\"id\": \"Save as PDF\",\"origin\": \"local\",\"account\":\"\"}],\"selectedDestinationId\": \"Save as PDF\",\"version\": 2,\"isGcpPromoDismissed\":false,\"isLandscapeEnabled\":true,\"pageSize\": \"A3\",\"marginsType\": 2,\"scalingType\": 3,\"scaling\": \"80\",\"isHeaderFooterEnabled\": false,\"isCssBackgroundEnabled\": true}";
		// return JSON.parseObject(String.valueOf(isOpen));
	}

	public void open(String url) throws Exception {
		initDriver();
		driver.get(url);
		isOpen = true;
	}

	public String getPageSource() throws Exception {
		return driver.getPageSource();
	}

	public WebElement findElement(By selector, String tagets, StringBuilder comments) throws Exception {
		try {
			new WebDriverWait(this.driver, Duration.ofSeconds(3)).until(webDrivert -> ((JavascriptExecutor) webDrivert)
					.executeScript("return document.readyState").equals("complete"));
		} catch (Exception e) {
			log.warn("findElement Wait" + e.getMessage());
		}

		// List<WebElement> elements = new WebDriverWait(driver, Duration.ofSeconds(4))
		// .until(driver -> driver.findElements(selector));

		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(6))
				.pollingEvery(Duration.ofSeconds(2)).ignoring(NoSuchElementException.class);

		List<WebElement> elements = wait.until(new Function<WebDriver, List<WebElement>>() {
			public List<WebElement> apply(WebDriver driver) {
				return driver.findElements(selector);
			}
		});

		// tagets利用し、値再度取得
		if (elements.size() == 0 || elements.size() >= 2) {
			WebElement webElement = findElementBytagets(tagets, comments);

			if (webElement != null) {
				return webElement;
			}
		}

		// //複数画面を開くの場合,他画面で探して
		// Set<String> windowHandles = driver.getWindowHandles();
		// String nowWindowHandle = driver().getWindowHandle();
		// log.info("WebDriverControl.findElement: windowHandles ::{}", windowHandles);
		// for (String handle : windowHandles) {
		// driver.switchTo().window(handle);
		// WebElement webElement = findElementBytagets(tagets, comments);
		// if (webElement != null) {
		// log.info("WebDriverControl.findElement::{}に遷移した", driver.getTitle());
		// return webElement;
		// }
		// }
		// //どこでも,探していない,戻る
		// driver.switchTo().window(nowWindowHandle);

		if (elements.size() == 0) {
			try {
				String waitTime = Utils.resourceMapThreadLocal.get().get(Utils.GET_ELEMENT_WAIT_TIME);
				if (Utils.isEmpt(waitTime)) {
					waitTime = "3000";
				}
				log.info("GET_ELEMENT_WAIT_TIME is :{} ms", waitTime);
				Thread.sleep(Integer.parseInt(waitTime));
				WebElement webE = findElementBytagets(tagets, comments);
				if (webE != null) {
					return webE;
				}
			} catch (InterruptedException e) {
				log.warn("findElement Wait" + e.getMessage());
			}

			return null;
		} else if (elements.size() == 1) {
			return elements.get(0);
		} else {
			comments.append("Target Element >=2 is found! retrun [0] ");
			return elements.get(0);
		}
	}

	private WebElement findElementBytagets(String tagets, StringBuilder comments) {

		WebElement webElement = null;
		WebElement webElement2 = null;
		String tempsidetarget = "";
		try {
			if (!Utils.isEmpt(tagets)) {
				String[] aryTaget = tagets.split("::");

				for (String sidetarget : aryTaget) {
					log.info("WebDriverControl.findElementBytagets:: taget is {} .", sidetarget);
					String[] sidetargetArry = sidetarget.split("=", 2);
					String option = "Selector=" + sidetargetArry[0];
					String target = sidetargetArry[1];

					By selector = Utils.buildSelector(option, target, "WebDriverControl:finElement");
					List<WebElement> elementss = driver.findElements(selector);
					if (elementss.size() == 1) {
						webElement = elementss.get(0);
						break;
					} else if (elementss.size() >= 2) {
						tempsidetarget = sidetarget;
						webElement2 = elementss.get(0);
					}
				}
			}
		} catch (Exception e) {
			log.warn(e.getMessage());
		}

		if (webElement != null) {
			return webElement;
		} else if (webElement2 != null) {
			comments.append("Targets Element >=2 is found! retrun [0] --> target(" + tempsidetarget + ")");
			return webElement2;
		}

		return null;
	}

	public void executeScript(String script, WebElement elemt) {
		((JavascriptExecutor) driver).executeScript(script, elemt);
	}

	public void pushWindowHandle(String handle) {
		windowHandleStack.push(handle);
	}

	public String popWindowHandle() {
		return windowHandleStack.pop();
	}

	private Duration duration(long seconds) {
		return Duration.ofSeconds(seconds);
	}

	public void close() {
		if (isOpen) {
			try {
				Set<String> windowHandles = driver.getWindowHandles();
				for (String handle : windowHandles) {
					driver.close();
					driver.switchTo().window(handle);
				}

				isOpen = false;
			} catch (Exception e) {
				driver.quit();
			}
		}
		// driver = null;
	}

	public String waitForWindow(int timeout) {
		try {
			Thread.sleep(timeout);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Set<String> whNow = driver.getWindowHandles();
		Set<String> whThen = (Set<String>) varsHandles.get(WINDOW_HANDLES);
		log.info("waitForWindow:: window_handles are {}", whThen.toString());
		log.info("waitForWindow:: Now WindowHandles are {}", whNow.toString());
		String nowHd = "";
		if (whNow.size() > whThen.size()) {
			whNow.removeAll(whThen);

			for (String handle : whNow) {
				nowHd = handle;
			}
		}

		return "".equals(nowHd) ? whNow.iterator().next() : nowHd;
	}

	public boolean isOpen() {
		return isOpen;
	}

	public void setOpen(boolean isOpen) {
		this.isOpen = isOpen;
	}

	public void setTestUrl(String url) {
		this.testUrl = url;
	}

	public String getTestUrl() {
		return testUrl;
	}

	public void setWindowSizeRow(int windowSizeRow) {
		this.windowSizeRow = windowSizeRow;
	}

	public void setWindowSizeCol(int windowSizeCol) {
		this.windowSizeCol = windowSizeCol;
	}

	public WebDriver driver() {
		return driver;
	}

	public void setWebDriverType(WebDriverType browser) {
		this.browser = browser;

	}

	public WebDriverType getWebDriverType() {
		return this.browser;

	}

	public void setWebDriverVer(String webDriverVer) {
		this.webDriverVer = webDriverVer;
	}

	public Map<String, Object> getVarshandles() {
		return varsHandles;
	}

	public void setWindowHandle(String key, Object value) {
		varsHandles.put(key, value);
	}
}
