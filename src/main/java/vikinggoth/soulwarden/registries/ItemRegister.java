package vikinggoth.soulwarden.registries;

import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;
import vikinggoth.soulwarden.SoulWarden;

/**
 * Created by Friedrich on 8/18/2015.
 */
public class ItemRegister
{
    //Materials
    public static Item soulgem;
    public static Item soulgem_black;
    public static Item shard_stygian;
    public static Item ember;

    public static Item clay_porcelain;
    public static Item bone_ash;
    public static Item bone_shard;
    public static Item feather_beetle; // a scarab wing, can by used to make feathers
    public static Item hide_subtle; //leather cow
    public static Item hibe_chitin; //use to make chitin armor

    //Glowing Colors
    public static Item glow_colored;
    //White = Grave Mist Vapor
    //Orange = Luminous Marigold Pollen
    //Magenta = Glow Cloud Vapor
    //Light Blue = Ethereal Ooze Ball
    //Yellow (is taken by default glowstone) = Lamplighter Beeswax
    //Lime = Alnwick Flower
    //Pink = Brain Serum
    //Gray = Carcin  Growth
    //Light Gray = Death Cloud Vapor
    //Cyan = Opalescent Coral (Scintillack)
    //Purple/Violet = Nightshade Berry
    //Blue = Soul Cloud Vapor
    //Brown = Worm Flesh
    //Green = Grasping Kelp
    //Red = Blood Red Polyp
    //Black = Tar Bubble

    //Bones... I could store them as a single item with damage values
    public static Item bone_variant;
    /*
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
    */

    //Keys
    //used to open seals in marigold crypts
    public static Item key_gold;
    //used to open seals in Cali Lilly crypts
    public static Item key_iron;
    //can open any door, including portal to Stygia, crafted with skull, bone, and bone_shard
    public static Item key_skeleton;

    //Metals
    public static Item hematite; //Bleedstone, smelts into Iron Ingots, used for Serrated enchantment and Bleeding Weapons, called Bleedstone in game
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
    public static Item flask_essence; //use durability to heal, can be refilled/repaired at essence well, craft with essence flask shard to enchant with unbreaking
    public static Item drought_ressurect; //used to exit the Necropolis, a Ressurection alter can also be used
    public static Item drought_death; //Links you to the realm of death, meaning you can use portal w/o being at 1/2 heart

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

        bone_ash = new Item().setUnlocalizedName("ash_bone");
        bone_shard = new Item().setUnlocalizedName("shard_bone");

        ember = new Item().setUnlocalizedName("coal_ember");

        soulgem = new Item().setUnlocalizedName("soulgem");
        soulgem_black = new Item().setUnlocalizedName("soulgem_black");
        hematite = new Item().setUnlocalizedName("hematite");
        //flask_essence = new ItemFlask().setUnlocalizedName("flask_essence");

        registerItems();
    }

    public static void registerItems()
    {
        regItem(soulgem);
        regItem(soulgem_black);

        regItem(hematite);

        regItem(bone_ash);
    }

    private static void regItem(Item item)
    {
        item.setCreativeTab(SoulWarden.SWTab);
        GameRegistry.registerItem(item, item.getUnlocalizedName().substring(5));
    }
}
