package vikinggoth.soulwarden.proxies;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

/**
 * Created by Friedrich on 12/5/2015.
 */
public interface IProxy
{
    public void preInit(FMLPreInitializationEvent e);

    public void init(FMLInitializationEvent e);

    public void postInit(FMLPostInitializationEvent e);
}
