package vikinggoth.soulwarden;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import vikinggoth.soulwarden.proxies.CommonProxy;


/**
 * Created by Friedrich on 8/18/2015.
 */
@Mod(modid = SoulWarden.MODID, version = SoulWarden.VERSION, name = SoulWarden.NAME, dependencies = "")
public class SoulWarden
{
    public static final String MODID = "soulwarden";
    public static final String VERSION = "1.8-1.0.1";
    public static final String NAME = "Soul Warden";

    @SidedProxy(clientSide="vikinggoth.soulwarden.proxies.ClientProxy", serverSide="vikinggoth.soulwarden.proxies.CommomProxy")
    public static CommonProxy proxy;

    public static CreativeTabs SWTab = new CreativeTabs(CreativeTabs.getNextID(), SoulWarden.MODID)
    {
        @Override
        @SideOnly(Side.CLIENT)
        public Item getTabIconItem()
        {
            return Items.bone;
        }
    };

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent e)
    {
        proxy.preInit(e);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent e)
    {
        proxy.init(e);
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent e)
    {
        proxy.postInit(e);
    }
}
