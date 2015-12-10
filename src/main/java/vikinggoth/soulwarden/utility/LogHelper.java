package vikinggoth.soulwarden.utility;

import net.minecraftforge.fml.common.FMLLog;
import vikinggoth.soulwarden.reference.Reference;

import java.util.logging.Level;

/**
 * Created by Friedrich on 12/5/2015.
 *
 * Currently does nothing
 */
public class LogHelper
{
    public static void log(Level logLevel, Object object)
    {
        //FMLLog.log(Reference.MOD_NAME, logLevel, String.valueOf(object));
    }

    public static void all(Object object)
    {
        log(Level.ALL, object);
    }

    /*public static void debug(Object object)
    {
        log(Level.DEBUG, object);
    }

    public static void error(Object object)
    {
        log(Level.ERROR, object);
    }

    public static void fatal(Object object)
    {
        log(Level.FATAL, object);
    }*/

    public static void info(Object object)
    {
        log(Level.INFO, object);
    }

    public static void off(Object object)
    {
        log(Level.OFF, object);
    }

    /*public static void trace(Object object)
    {
        log(Level.TRACE, object);
    }*/

    public static void warning(Object object)
    {
        log(Level.WARNING, object);
    }
}
