package vikinggoth.soulwarden.proxies;

import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import vikinggoth.soulwarden.configuration.ConfigurationHandler;
import vikinggoth.soulwarden.event.SWEventHandler;
import vikinggoth.soulwarden.registers.BlockRegister;
import vikinggoth.soulwarden.registers.ItemRegister;
import vikinggoth.soulwarden.registers.RecipeRegister;
import vikinggoth.soulwarden.world.dimension.WorldProviderStygia;
import vikinggoth.soulwarden.world.gen.SWWorldGen;

/**
 * Created by Friedrich on 8/18/2015.
 */
public abstract class CommonProxy implements IProxy
{
    @Override
    public void preInit(FMLPreInitializationEvent e)
    {
        ItemRegister.createItems();
        BlockRegister.createBlocks();
        RecipeRegister.initCrafting();
    }

    @Override
    public void init(FMLInitializationEvent e)
    {
        GameRegistry.registerWorldGenerator(new SWWorldGen(), 0);
        DimensionManager.registerProviderType(ConfigurationHandler.dimStygiaID, WorldProviderStygia.class, false);
        DimensionManager.registerDimension(ConfigurationHandler.dimStygiaID, ConfigurationHandler.dimStygiaID);
        //WorldTypeStygia.addWorldTypes();
    }

    @Override
    public void postInit(FMLPostInitializationEvent e)
    {
        MinecraftForge.EVENT_BUS.register(new SWEventHandler());

    }
}
