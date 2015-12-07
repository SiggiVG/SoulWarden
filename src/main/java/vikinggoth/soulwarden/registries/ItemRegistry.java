package vikinggoth.soulwarden.registries;

import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;
import vikinggoth.soulwarden.SoulWarden;

/**
 * Created by Friedrich on 8/18/2015.
 */
public class ItemRegistry
{
    //Materials
    public static Item soulgem;
    public static Item soulgem_black;
    public static Item shard_stygian;
    public static Item ember;
    public static Item polyp_bloody;
    public static Item slimeball_ethereal;
    public static Item clay_porcelain;
    public static Item bone_ash;
    public static Item feather_beetle; // a scarab wing, can by used to make feathers
    public static Item hide_subtle; //leather cow
    public static Item hibe_chitin; //use to make chitin armor

    //Bones
    public static Item bone_charred; //immune to fire, weak defense
    public static Item bone_artificial; //high defense, weak soulfusion
    public static Item bone_enameled; //higher defense
    public static Item bone_porous; //weak defense, can float
    public static Item bone_burning; //sets things on fire, immune to fireweaker defense
    public static Item bone_bloody; //high health
    public static Item bone_ethereal; //produces light
    public static Item bone_transient; //can pass through blocks
    public static Item bone_soul; //high soulfusion
    public static Item bone_meaty; //higher health
    public static Item bone_serrated; //inflicts bleed

    //Metals
    public static Item hematite; //Bleedstone, smelts into Iron Ingots
    //nuggets
    public static Item nugget_rostygold;
    public static Item nugget_pewter;
    public static Item nugget_copper;
    public static Item nugget_tin;
    public static Item nugget_iron;
    public static Item nugget_silver;
    public static Item nugget_bronze;
    //dusts
    public static Item dust_rostygold;
    public static Item dust_copper;
    public static Item dust_tin;
    public static Item dust_pewter;
    public static Item dust_iron;
    public static Item dust_gold;
    public static Item dust_silver;
    public static Item dust_bronze;
    //ingots
    public static Item ingot_rostygold;
    public static Item ingot_pewter;
    public static Item ingot_copper;
    public static Item ingot_tin;
    public static Item ingot_bronze;
    //coins
    public static Item coin_rostygold;
    public static Item coin_pewter;

    //Tools
    public static Item flask_essence; //use durability to heal, can be refiller/repaired at essence well
    public static Item drought_ressurect; //used to exit the Necropolis, a Ressurection alter can also be used
    public static Item drought_death; //damages self to 1/2 heart, making the portal work

    //Food
    public static Item flesh_milk; //milkgrub cow
    public static Item flesh_silk; //silkworm sheep
    public static Item flesh_pill; //pillbug pig
    public static Item flesh_beetle; //scarab chicken
    public static Item pomegranate; //grown on Pomegranate trees



    //Doors
    public static Item ghoulDoor;
    public static Item weepwillowDoor;
    public static Item bonebeechDoor;
    public static Item handDoor;

    public static void createItems()
    {
        soulgem = new Item().setUnlocalizedName("soulgem");
        soulgem_black = new Item().setUnlocalizedName("soulgem_black");
        bone_ash = new Item().setUnlocalizedName("ash_bone");

        registerItems();
    }

    public static void registerItems()
    {
        regItem(soulgem);
        regItem(soulgem_black);
        regItem(bone_ash);
    }

    private static void regItem(Item item)
    {
        item.setCreativeTab(SoulWarden.SWTab);
        GameRegistry.registerItem(item, item.getUnlocalizedName().substring(5));
    }
}
