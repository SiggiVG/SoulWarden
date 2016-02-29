package vikinggoth.soulwarden.proxies;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import vikinggoth.soulwarden.registers.BlockRegister;
import vikinggoth.soulwarden.registers.ItemRegister;
import vikinggoth.soulwarden.registers.RecipeRegister;

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
    }

    @Override
    public void init(FMLInitializationEvent e)
    {
        RecipeRegister.initCrafting();
    }

    @Override
    public void postInit(FMLPostInitializationEvent e)
    {

    }
}
