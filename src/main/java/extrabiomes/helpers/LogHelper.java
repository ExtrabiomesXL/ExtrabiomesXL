/**
 * Copyright (c) Scott Killen and MisterFiber, 2012
 * 
 * This mod is distributed under the terms of the Minecraft Mod Public
 * License 1.0, or MMPL. Please check the contents of the license
 * located in /MMPL-1.0.txt
 */

package extrabiomes.helpers;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.common.base.Optional;

import extrabiomes.Extrabiomes;
import extrabiomes.lib.Reference;

/**
 * Helper methods for logging to the ExtrabiomesXL logger
 */
public enum LogHelper
{
    INSTANCE;
    
    /**
     * <pre>
     * static void fine({@link String} format,
     *                  {@link Object}... args);
     * </pre>
     * 
     * Write tracing information to the log at level <code>FINE</code>.
     * <p>
     * 
     * @param format - A format string
     * @param args - Arguments referenced by the format specifiers in the format string
     * @see java.util.logging.Level#FINE Level.FINE
     * @see java.util.Formatter Formatter
     */
    public static void fine(String format, Object... args)
    {
        INSTANCE.log(Level.DEBUG, format, args);
    }
    
    /**
     * <pre>
     * static void finer({@link String} format,
     *                   {@link Object}... args);
     * </pre>
     * 
     * Write tracing information to the log at level <code>FINER</code>.
     * <p>
     * 
     * @param format - A format string
     * @param args - Arguments referenced by the format specifiers in the format string
     * @see java.util.logging.Level#FINER Level.FINER
     * @see java.util.Formatter Formatter
     */
    public static void finer(String format, Object... args)
    {
        INSTANCE.log(Level.DEBUG, format, args);
    }
    
    /**
     * <pre>
     * static void finest({@link String} format,
     *                    {@link Object}... args);
     * </pre>
     * 
     * Write tracing information to the log at level <code>FINEST</code> .
     * <p>
     * 
     * @param format - A format string
     * @param args - Arguments referenced by the format specifiers in the format string
     * @see java.util.logging.Level#FINEST Level.FINEST
     * @see java.util.Formatter Formatter
     */
    public static void finest(String format, Object... data)
    {
        INSTANCE.log(Level.DEBUG, format, data);
    }
    
    /**
     * <pre>
     * static void info({@link String} format,
     *                  {@link Object}... args);
     * </pre>
     * 
     * Write an informational message to the console and log.
     * <p>
     * 
     * @param format - A format string
     * @param args - Arguments referenced by the format specifiers in the format string
     * @see java.util.logging.Level#INFO Level.INFO
     * @see java.util.Formatter Formatter
     */
    public static void info(String format, Object... args)
    {
        INSTANCE.log(Level.INFO, format, args);
    }
    
    /**
     * <pre>
     * static void log({@link Level} level,
     *                 {@link Throwable} exception,
     *                 {@link String} format,
     *                 {@link Object}... args);
     * </pre>
     * 
     * Write a message to the log, with associated <code>Throwable</code> information.
     * <p>
     * 
     * @param level - a {@link java.util.logging.Level log level}
     * @param exception - a {@link Throwable} error or exception
     * @param format - A format string
     * @param args - Arguments referenced by the format specifiers in the format string
     * @see java.util.Formatter Formatter
     */
    public static void log(Level level, Throwable exception, String format, Object... args)
    {
        INSTANCE.getLogger().log(level, String.format(format, args), exception);
    }
    
    /**
     * <pre>
     * static void severe({@link String} format,
     *                    {@link Object}... args);
     * </pre>
     * 
     * Write message indicating a severe failure to the log.
     * <p>
     * 
     * @param format - A format string
     * @param args - Arguments referenced by the format specifiers in the format string
     * @see java.util.logging.Level#SEVERE Level.SEVERE
     * @see java.util.Formatter Formatter
     */
    public static void severe(String format, Object... args)
    {
        INSTANCE.log(Level.FATAL, format, args);
    }
    
    /**
     * <pre>
     * static void warning({@link String} format,
     *                     {@link Object}... args);
     * </pre>
     * 
     * Write message indicating a potential problem to the log.
     * <p>
     * 
     * @param format - A format string
     * @param args - Arguments referenced by the format specifiers in the format string
     * @see java.util.logging.Level#WARNING Level.WARNING
     * @see java.util.Formatter Formatter
     */
    public static void warning(String format, Object... args)
    {
        INSTANCE.log(Level.WARN, format, args);
    }
    
    private Optional<Logger> logger = Optional.absent();
    
    private Logger getLogger()
    {
        if (!logger.isPresent())
            init();
        
        return logger.get();
    }
    
    private void init()
    {
        if (logger.isPresent())
            return;
        
        logger = Optional.of(LogManager.getLogger(Reference.MOD_ID));
        //logger.get().setParent(Extrabiomes.proxy.getFMLLogger());
    }
    
    private void log(Level level, String format, Object... data)
    {
        getLogger().log(level, String.format(format, data));
    }
}
