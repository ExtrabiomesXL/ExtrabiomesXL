/**
 * Copyright (c) Scott Killen and MisterFiber, 2012
 * 
 * This mod is distributed under the terms of the Minecraft Mod Public
 * License 1.0, or MMPL. Please check the contents of the license
 * located in /MMPL-1.0.txt
 */

package extrabiomes.plugin.scarecrow;

import java.util.logging.Level;
import java.util.logging.Logger;

public enum ScarecrowLog {
	INSTANCE;

	private static boolean	configured;
	private static Logger	myLog;

	public static void configureLogging() {
		if (configured) return;
		configured = true;

		myLog = Logger.getLogger("EBXL Scarecrow");
		myLog.setParent(Logger.getLogger("ExtrabiomesXL"));
	}

	public static void fine(String format, Object... data) {
		log(Level.FINE, format, data);
	}

	public static void finer(String format, Object... data) {
		log(Level.FINER, format, data);
	}

	public static void finest(String format, Object... data) {
		log(Level.FINEST, format, data);
	}

	public static Logger getLogger() {
		return myLog;
	}

	public static void info(String format, Object... data) {
		log(Level.INFO, format, data);
	}

	public static void log(Level level, String format, Object... data) {
		myLog.log(level, String.format(format, data));
	}

	public static void log(Level level, Throwable ex, String format,
			Object... data)
	{
		myLog.log(level, String.format(format, data), ex);
	}

	public static void severe(String format, Object... data) {
		log(Level.SEVERE, format, data);
	}

	public static void warning(String format, Object... data) {
		log(Level.WARNING, format, data);
	}
}
