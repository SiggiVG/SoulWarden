package vikinggoth.soulwarden.registries;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import vikinggoth.soulwarden.reference.Reference;

/**
 * Created by Friedrich on 12/9/2015.
 */
public class ItemRenderRegister
{
    private static String modid = Reference.MODID;

    public static void preInit()
    {

    }

    public static void registerItemRenderer()
    {
        regItem(ItemRegister.soulgem);
        regItem(ItemRegister.soulgem_black);
        regItem(ItemRegister.hematite);
    }

    private static void regItem(Item item)
    {
        regItem(item, 0, item.getUnlocalizedName().substring(5));
    }

    private static void regItem(Item item, int meta, String file)
    {
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, meta, new ModelResourceLocation(modid + ":" + file, "inventory"));
    }
}
