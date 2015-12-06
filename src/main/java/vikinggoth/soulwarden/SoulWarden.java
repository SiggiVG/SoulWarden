package vikinggoth.soulwarden;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import vikinggoth.soulwarden.configuration.ConfigurationHandler;
import vikinggoth.soulwarden.proxies.CommonProxy;
import vikinggoth.soulwarden.reference.Reference;
import vikinggoth.soulwarden.registries.ConfigBlocks;


/**
 * Created by Friedrich on 8/18/2015.
 */
@Mod(modid = Reference.MODID, version = Reference.VERSION, name = Reference.MOD_NAME, dependencies = "")
public class SoulWarden
{

    @Mod.Instance(Reference.MODID)
    public static SoulWarden instance;

    @SidedProxy(clientSide=Reference.CLIENT_POXY_CLASS, serverSide=Reference.SERVER_PROXY_CLASS)
    public static CommonProxy proxy;

    public static CreativeTabs SWTab = new CreativeTabs(CreativeTabs.getNextID(), Reference.MODID)
    {
        @Override
        @SideOnly(Side.CLIENT)
        public Item getTabIconItem()
        {
            return Item.getItemFromBlock(ConfigBlocks.logSW);
        }

        @Override
        @SideOnly(Side.CLIENT)
        public int getIconItemDamage()
        {
            return 2;
        }
    };

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent e)
    {
        ConfigurationHandler.init(e.getSuggestedConfigurationFile());

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
