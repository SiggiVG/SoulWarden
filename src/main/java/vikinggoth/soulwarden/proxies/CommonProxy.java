package vikinggoth.soulwarden.proxies;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import vikinggoth.soulwarden.registries.ConfigBlocks;
import vikinggoth.soulwarden.registries.ConfigItems;
import vikinggoth.soulwarden.registries.ConfigRecipes;

/**
 * Created by Friedrich on 8/18/2015.
 */
public abstract class CommonProxy implements IProxy
{
    @Override
    public void preInit(FMLPreInitializationEvent e)
    {

        ConfigItems.createItems();
        ConfigBlocks.createBlocks();
    }

    @Override
    public void init(FMLInitializationEvent e)
    {
        ConfigRecipes.initCrafting();
    }

    @Override
    public void postInit(FMLPostInitializationEvent e)
    {

    }
}
