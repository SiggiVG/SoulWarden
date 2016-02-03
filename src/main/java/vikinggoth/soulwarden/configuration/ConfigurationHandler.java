package vikinggoth.soulwarden.configuration;

import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.config.Configuration;

import java.io.File;

/**
 * Created by Friedrich on 12/5/2015.
 */
public class ConfigurationHandler
{
    //static boolean configValue;
    public static int dimNecroID;
    public static int seaLevelNecro;
    public static int pylonTickRate = 180;

    public static void init(File configFile)
    {
        //Create the configuration from the given configuration file
        Configuration config = new Configuration(configFile);

        try
        {
            //Load the configuration file
            config.load();

            //Read in properties from the configuration file
            dimNecroID = config.getInt(Configuration.CATEGORY_GENERAL, "dimStygiaID", DimensionManager.getNextFreeDimId(), 1, 255, "The ID of the Necropolis Dimension");
            seaLevelNecro = config.getInt(Configuration.CATEGORY_GENERAL, "seaLevelStygia", 127, 1, 255, "The Sea Level in the Stygian Dimension");
            //configValue = config.get(Configuration.CATEGORY_GENERAL, "configValue", true, "This is an Example Config File").getBoolean();
        }
        catch (Exception e)
        {
            //Log the Exception
        }
        finally
        {
            //Save the configuration file
            config.save();
        }

        //System.out.println(configValue);
    }
}
