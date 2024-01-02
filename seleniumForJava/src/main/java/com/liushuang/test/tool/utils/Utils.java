package com.liushuang.test.tool.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.liushuang.test.tool.commands.ActionOption;
import com.liushuang.test.tool.commands.OptionSelector;
import com.liushuang.test.tool.webdriver.WebDriverType;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Utils {

	public static final String PRINT_WAIT_TIME = "PRINT_WAIT_TIME";
	public static final String PRINT_FLG = "PRINT_FLG";
	/** 起動URL */
	public static final String TEST_URL = "TEST_URL";
	public static final String CLEAR_FLG = "CLEAR_FLG";
	public static final String EVIDENCE_PATH = "EVIDENCE_PATH";
	public static final String WINDOW_SIZE = "WINDOW_SIZE";
	public static final String WEB_DRIVER_VER = "WEB_DRIVER_VER";
	public static final String INPUT_PATH = "INPUT_PATH";
	public static final String MIGRATION_FLG = "MIGRATION_FLG";
	public static final String INPUT_FULL_PATH = "INPUT_FULL_PATH";
	public static final String EVIDENCE_FULL_PATH = "EVIDENCE_FULL_PATH";
	public static final String GET_ELEMENT_WAIT_TIME = "GET_ELEMENT_WAIT_TIME";
	public static final String AUTOMATIC_SCREENSHOT_FLG = "AUTOMATIC_SCREENSHOT_FLG";
	public static final String AUTOMATIC_SCREENSHOT_STOP_NAMES = "AUTOMATIC_SCREENSHOT_STOP_NAMES";

	private static final String TASKLIST = "tasklist";
	private static final String KILL = "taskkill /F /IM ";
	private static String[] processNames = { "chromedriver.exe", "msedgedriver.exe", "IEDriverServer.exe",
			"geckodriver.exe" };

	// 現行スレッドの初期値を取得
	public static final ThreadLocal<Map<String, String>> resourceMapThreadLocal = new ThreadLocal<>();
//	// ファイル現行スレッドの初期値を取得
//	public static final ThreadLocal<Map<String, String>> fileNameMapThreadLocal = new ThreadLocal<>();

	public static List<String> getSideFiles(String path) {
		List<String> sideFiles = new ArrayList<>();
		File f = new File(path);

		if (f.isDirectory()) {
			File[] files = f.listFiles();
			for (File f2 : files) {
				if (f2.isFile()) {
					sideFiles.add(f2.getPath());

				}
			}
		}
		return sideFiles;
	}

	/**
	 * 空白を判断
	 *
	 * @param str
	 * @return
	 */
	public static boolean isEmpt(String str) {
		return str == null || "".equals(str.trim()) ? true : false;
	}

	public static String getEvidenceFolder(String folder) {

		/** 構成ファイル */
		Map<String, String> resourceMap = resourceMapThreadLocal.get();
		String evidence = resourceMap.get(EVIDENCE_FULL_PATH);
		String retFolder = "";
		if (!isEmpt(folder)) {
			retFolder = String.format(evidence + "%s\\", folder);

		} else {
			retFolder = evidence + "other\\";
		}

		File newdir = new File(retFolder);
		if (!newdir.exists()) {
			newdir.mkdirs();
		}

		return retFolder;
	}

	public static void outputImg(WebDriver webDriver, String folder, String chgShotName, WebDriverType browser,
			String tagets) throws Exception {
		String fullFolder = getEvidenceFolder(folder);

		String shotName = chgShotName.replaceAll("[\\\\/:*?\"<>|.,']", "_");
		shotName = shotName.replaceAll("__", "_");
		try {
			new WebDriverWait(webDriver, Duration.ofSeconds(10)).until(webDrivert -> ((JavascriptExecutor) webDrivert)
					.executeScript("return document.readyState").equals("complete"));

			// =================pngキャプチャ保存==============
			if ("PNG".equals(resourceMapThreadLocal.get().get(PRINT_FLG).toUpperCase())) {

				File srcFile = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.FILE);
				File toFile = new File(fullFolder + shotName + ".png");

				if (toFile.exists()) {
					toFile.delete();
				}

				FileUtils.copyFile(srcFile, toFile);

			} else {

				if ("TRUE".equals(resourceMapThreadLocal.get().get(AUTOMATIC_SCREENSHOT_FLG).toUpperCase())) {

					String stopNames = resourceMapThreadLocal.get().get(AUTOMATIC_SCREENSHOT_STOP_NAMES);
					boolean stopFlg = false;

					String[] aryStopNames = stopNames.split(",");
					if (!Utils.isEmpt(tagets)) {
						for (String stopNmae : aryStopNames) {
							if (stopFlg == true) {
								break;
							}
							for (String taget : tagets.split("::")) {
								if (taget.contains(stopNmae)) {
									stopFlg = true;
									break;
								}
							}
						}
					}

					if (!stopFlg) {
						String printPDF = "";
						switch (browser) {
						case IE:
							printPDF = "exe/prePrintPDFForIE";
							break;
						case IE_MODE:
							printPDF = "exe/prePrintPDFForIE";
							break;
						default:
							printPDF = "exe/printPDF";
						}

						// =================pdfファイル保存==============
						System.setProperty("java.awt.headless", "false");
						java.awt.Robot robot = null;

						File toFile = new File(fullFolder + shotName + ".pdf");
						if (toFile.exists()) {
							toFile.delete();
						}

						String[] commadArray = { printPDF,
								// System.getProperty("user.dir") + "\\" +
								fullFolder + shotName + ".pdf" };
						Process p = Runtime.getRuntime().exec(commadArray);

						// ((JavascriptExecutor) webDriver).executeScript("window.print();");

						robot = new java.awt.Robot();
						robot.keyPress(java.awt.event.KeyEvent.VK_CONTROL);
						robot.keyPress(java.awt.event.KeyEvent.VK_P);
						robot.keyRelease(java.awt.event.KeyEvent.VK_CONTROL);
						robot.keyRelease(java.awt.event.KeyEvent.VK_P);

						Thread.sleep(Integer.parseInt(resourceMapThreadLocal.get().get(PRINT_WAIT_TIME)));
						switch (browser) {
						case IE:
							break;
						case IE_MODE:
							break;
						default:
							robot.keyPress(java.awt.event.KeyEvent.VK_ENTER);
							robot.keyRelease(java.awt.event.KeyEvent.VK_ENTER);
						}

						p.waitFor();
						p.destroy();
					}

				}
				Thread.sleep(1000);
			}

		} catch (IOException e) {
			e.printStackTrace();
			log.warn(e.getMessage());
			throw e;
		}

	}

	public static void outputHtml(String folderSub, com.liushuang.test.tool.result.Action action, String pageSource)
			throws FileNotFoundException {

		Map<String, String> resourceMap = resourceMapThreadLocal.get();
		String evidence = resourceMap.get(EVIDENCE_FULL_PATH);
		String folder = "";
		if (!isEmpt(folderSub)) {

			folder = String.format(evidence + "%s/data/", folderSub);

		} else {

			folder = evidence + "/other/";
		}

		String fileName = String.format("%s-%s_%03d", action.getParent().getTestID(), action.getTarget(),
				action.getActionNo());

		File newdir = new File(folder);
		if (!newdir.exists()) {
			newdir.mkdirs();
		}

		File file = new File(folder + fileName + ".html");

		try (PrintWriter pw = new PrintWriter(file)) {
			pw.print(pageSource);
			pw.flush();
		}
	}

	/**
	 * フォルダ・フィアルを削除
	 *
	 * @Description:エビデンスクリア
	 * @param path
	 * @param deldir
	 */
	public static void clearFolder(String path, boolean deldir) {
		File f = new File(path);
		if (!f.exists()) {

			return;
		}

		if (f.isFile() && deldir) {
			f.delete();
			log.debug("DELETE file {}", f.toString());
			return;
		} else if (f.isDirectory()) {
			File[] files = f.listFiles();
			for (File f2 : files) {
				clearFolder(f2.getPath(), deldir);
			}

			if (deldir) {
				f.delete();
				log.debug("DELETE directory {}", f.toString());
			}
		}
	}

	/**
	 *
	 * プロパティファイルを取得
	 *
	 * @param propertieFileFullName
	 * @return ResourceBundle
	 */
	public static ResourceBundle loadPropertieFile() {

		ResourceBundle config = ResourceBundle.getBundle("config", new ResourceBundleUtf8Control());

		String proFilePath = System.getProperty("user.dir") + "\\config.properties";

		if (new File(proFilePath).exists()) {
			log.info("Propertie File loading:{}", proFilePath);
			try (InputStreamReader inputStream = new InputStreamReader(new FileInputStream(proFilePath), "UTF-8");) {

				config = new PropertyResourceBundle(inputStream);
			} catch (IOException e) {

				log.error("Propertie File loading error:{}", e.toString());
			}
		}

		return config;
	}

	public static By buildSelector(String option, String tarGet, String Name) throws Exception {
		ActionOption actOption = ActionOption.parseOption(option);

		String optSelector = actOption.get("SELECTOR");
		By selector = OptionSelector.build(optSelector, tarGet, Name);

		return selector;
	}

	public static void killDriverProcess() {
		try {
			for (String processName : processNames) {

				if (isProcessRunning(processName)) {

					killProcess(processName);
					log.info("process {} is killed.", processName);
				}

			}
		} catch (Exception e) {
			log.warn(e.getMessage());
			e.printStackTrace();
		}
	}

	private static boolean isProcessRunning(String serviceName) throws Exception {

		Process p = Runtime.getRuntime().exec(TASKLIST);
		BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
		String line;
		while ((line = reader.readLine()) != null) {

			if (line.contains(serviceName)) {
				return true;
			}
		}

		return false;

	}

	private static void killProcess(String serviceName) throws Exception {

		Runtime.getRuntime().exec(KILL + serviceName);

	}
}
