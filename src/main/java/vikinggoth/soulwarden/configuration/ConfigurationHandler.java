package vikinggoth.soulwarden.configuration;

import net.minecraftforge.common.config.Configuration;

import java.io.File;

/**
 * Created by Friedrich on 12/5/2015.
 */
public class ConfigurationHandler
{
    //static boolean configValue;

    public static void init(File configFile)
    {
        //Create the configuration from the given configuration file
        Configuration config = new Configuration(configFile);

        try
        {
            //Load the configuration file
            config.load();

            //Read in properties from the configuration file
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
