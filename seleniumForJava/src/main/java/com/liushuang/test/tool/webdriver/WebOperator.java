package com.liushuang.test.tool.webdriver;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class WebOperator {

	private static final String[] FIND_COMMAND = {"CMD", "/c", "TASKLIST", "/M",
			"/FO", "CSV", "|", "FINDSTR", "\\\"chromedriver.exe\\\""};

	public static void killAllDrivers() {

		try {
			List<String> dpids = getAllDriverProcessIds();
			killProcess(dpids, false);
		} catch (Exception e) {

			e.printStackTrace();
			log.error("killProcess", e);
		}

	}

	private static void killProcess(List<String> dpids, boolean isTreeKill)
			throws Exception {
		if (dpids.size() > 0) {
			List<String> killCommand = new ArrayList<String>();
			ProcessBuilder pb = null;
			Process process = null;

			killCommand.add("TASKKILL");
			killCommand.add("/F");

			if (isTreeKill == true) {
				killCommand.add("/T");
			}

			for (String pid : dpids) {
				killCommand.add("/PID");
				killCommand.add(pid);
			}

			try {
				pb = new ProcessBuilder(killCommand);
				process = pb.start();
				process.waitFor();
			} catch (Exception e) {
				throw e;
			} finally {
				if (process != null) {
					process.destroy();
				}
			}
		}

	}

	private static List<String> getAllDriverProcessIds() throws Exception {
		ProcessBuilder pb = new ProcessBuilder(FIND_COMMAND);
		List<String> pids = new ArrayList<>();

		pb.redirectErrorStream(true);

		Process ps = pb.start();
		ps.waitFor();

		try (BufferedReader br = new BufferedReader(
				new InputStreamReader(ps.getInputStream(), "MS932"))) {
			String line = "";
			while ((line = br.readLine()) != null) {
				for (String s : line.replace("\",\"", "\t").split("\t")) {
					pids.add(s);
				}
			}

			ps.waitFor();

			return pids;
		} finally {
			if (ps != null) {
				ps.destroy();
			}
		}
	}
}
