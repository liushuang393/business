import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TT {

	public static void main(String[] args) {

//		String keyValue = "${KEY_ENTER}";
//		String[] keys = keyValue.split("@");
//		keys = keyValue.split("\\+");
//		System.out.println(keys.length);
//
//		RecentDestinations rs = new RecentDestinations();
//		rs.setAccount("");
//		rs.setId("Save as PDF");
//		rs.setOrigin("local");
//		AppState appState = new AppState();
//		appState.setRecentDestinations(rs);
//		appState.setPageSize("A3");
//		appState.setSelectedDestinationId("Save as PDF");
//		appState.setVersion(2);
//
//		GsonBuilder gsonBuilder = new GsonBuilder();
//		gsonBuilder.setPrettyPrinting();
//		Gson gson = gsonBuilder.create();
//		String jsonStr = gson.toJson(appState);
//		System.out.println(jsonStr);
//		String ss = "aaa";
//		if ("*".equals(ss)) {
//			System.out.println(true);
//		}
		String chgShotName = "//span\\[co/ntains(.,'Japanese')]?\\";
		chgShotName = chgShotName.replaceAll("[\\\\/:*?\"<>|.,']", "_");
		chgShotName = chgShotName.replaceAll("__", "_");
//		shotName = shotName.replaceAll("__", "_");
//		String ss1 = "${win916}".replaceAll("[$|{|}]", "");

		List<File> bookNames = new ArrayList<>();
		System.out.println(chgShotName);
		String filePath = "C:/Users/liushuang/OneDrive - 株式会社ソフトロード/デスクトップ/work/05_FW/workspace/seleniumForJava/input/bak";
		File dir = new File(filePath);
		File[] files = dir.listFiles();
		for (File f : files) {
			bookNames.add(f);
		}

		for (File name : bookNames) {
			System.out.println(name.getName());
		}
		System.out.println("-------------------------");
		Arrays.sort(files, (a, b) -> a.getName().compareTo(b.getName()));

		for (File name : files) {
			System.out.println(name.getName());
		}

//		File newdir = new File(
//				"C:\\Users\\liushuang\\OneDrive - 株式会社ソフトロード\\デスクトップ\\work\\05_FW\\workspace\\seleniumForJava/evidence/移行後\\pdf\\");
//		if (!newdir.exists()) {
//			boolean bb = newdir.mkdir();
//			System.out.println(bb);
//		}
//
	}

}
