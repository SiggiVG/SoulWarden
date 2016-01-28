package vikinggoth.soulwarden.world.dimension;

import net.minecraftforge.common.DimensionManager;
import vikinggoth.soulwarden.configuration.ConfigurationHandler;

/**
 * Created by Friedrich on 12/6/2015.
 */
public class DimensionRegistry
{
    public static final int DIM_NECRO_ID = ConfigurationHandler.dimNecroID;

    public static void init()
    {
        registerDimension();
    }

    public static void registerDimension()
    {
        DimensionManager.registerProviderType(DIM_NECRO_ID, WorldProviderStygia.class, false);
        DimensionManager.registerDimension(DIM_NECRO_ID, DIM_NECRO_ID);
    }
}
