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
    public static int dimStygiaID;

    public static int biomeStygiaSeaID;
    public static int biomeBoneBeachID;
    public static int biomeForestID;

    public static int enchIDSoulSteal;
    public static float dimStygiaHealthReq;

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

            //Dimension
            dimStygiaID = config.getInt(Configuration.CATEGORY_GENERAL, "dimStygiaID", DimensionManager.getNextFreeDimId(), 1, 255, "The ID of the Stygia Dimension");
            dimStygiaHealthReq = config.getFloat(Configuration.CATEGORY_GENERAL, "dimStygiaHealthReq", 20.0F, 1.0F, 20.0F, "How close to Death you need to be to enter Stygia");

            //Biomes
            biomeStygiaSeaID = config.getInt(Configuration.CATEGORY_GENERAL, "biomeStygiaSeaID", 71, 40, 255, "The ID of the StygianSea Biome");
            biomeBoneBeachID = config.getInt(Configuration.CATEGORY_GENERAL, "biomeBoneBeachID", 72, 40, 255, "The ID of the BoneBeach Biome");
            biomeForestID = config.getInt(Configuration.CATEGORY_GENERAL, "biomeForestID", 73, 40, 255, "The ID of the Stygian Forest Biome");

            //Enchantments
            enchIDSoulSteal = config.getInt(Configuration.CATEGORY_GENERAL, "enchIDSoulSteal", 71, 63, 255, "The ID of the Soul Steal Enchantment");

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
