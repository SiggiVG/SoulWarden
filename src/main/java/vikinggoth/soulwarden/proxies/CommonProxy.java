package vikinggoth.soulwarden.proxies;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import vikinggoth.soulwarden.registries.BlockRegistry;
import vikinggoth.soulwarden.registries.ItemRegistry;
import vikinggoth.soulwarden.registries.RecipeRegistry;

/**
 * Created by Friedrich on 8/18/2015.
 */
public abstract class CommonProxy implements IProxy
{
    @Override
    public void preInit(FMLPreInitializationEvent e)
    {

        ItemRegistry.createItems();
        BlockRegistry.createBlocks();
    }

    @Override
    public void init(FMLInitializationEvent e)
    {
        RecipeRegistry.initCrafting();
    }

    @Override
    public void postInit(FMLPostInitializationEvent e)
    {

    }
}
