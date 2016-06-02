package vikinggoth.soulwarden.registers;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.common.registry.GameRegistry;
import vikinggoth.soulwarden.SoulWarden;
import vikinggoth.soulwarden.configuration.ConfigurationHandler;
import vikinggoth.soulwarden.items.enchants.EnchantmentSoulSteal;

/**
 * Created by Friedrich on 8/18/2015.
 */
public class ItemRegister
{
    //Enchantments
    public static Enchantment soulSteal = new EnchantmentSoulSteal(ConfigurationHandler.enchIDSoulSteal, new ResourceLocation("soulsteal"), 2, EnumEnchantmentType.WEAPON);

    //Tools
    public static Item.ToolMaterial SOULGEM = EnumHelper.addToolMaterial("SOULGEM", 2, 90, 5.0F, -0.5F, 18);

    public static Item soul_dagger;

    //Materials
    public static Item soulgem;
    public static Item soulgem_black;
    public static Item soulgem_charged;
    public static Item soulgem_black_charged;
    public static Item ember;

    //Metals
    public static Item hematite; //Bleedstone, smelts into Iron Ingots, used for Serrated enchantment and Bleeding Weapons, called Bleedstone in game
    //public static Item dust_silver;
    //public static Item dust_bronze;
    //ingots
    //public static Item ingot_rostygold;
    public static Item ingot_pewter;
    //public static Item ingot_copper;
    //public static Item ingot_tin;
    //public static Item ingot_bronze;
    //coins
    //public static Item coin_rostygold;
    //public static Item coin_pewter;
    //nuggets
    public static Item nugget_iron;
    //public static Item nugget_rostygold;
    public static Item nugget_pewter;
    //public static Item nugget_copper;
    //public static Item nugget_tin;
    //public static Item nugget_silver;
    //public static Item nugget_bronze;
    //dusts
    public static Item dust_iron;
    public static Item dust_gold;
    //public static Item dust_rostygold;
    public static Item dust_pewter;
    //public static Item dust_copper;
    //public static Item dust_tin;
    //Darksteel - only source is Daksteel Knights
    //public static Item shard_darksteel; //like nuggets/dust
    //public static Item chunk_darksteel; //like ingots

    //public static Item clay_porcelain;
    //public static Item bone_ash;
    //public static Item bone_shard;
    //public static Item feather_beetle; // a scarab wing, can by used to make feathers
    //public static Item hide_subtle; //leather cow
    //public static Item hibe_chitin; //use to make chitin armor

    //Food
    public static Item bonebooFruit;
    //public static Item flesh_milk; //milkgrub cow
    //public static Item flesh_silk; //silkworm sheep
    //public static Item flesh_pill; //pillbug pig
    //public static Item flesh_beetle; //scarab chicken
    //public static Item pomegranate; //grown on Pomegranate trees

    //Glowing Colors
    //public static Item glow_colored;
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
    //public static Item bone_variant;
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
    //public static Item key_gold;
    //used to open seals in Cali Lilly crypts
    //public static Item key_iron;
    //can open any door, including portal to Stygia, crafted with skull, bone, and bone_shard
    //public static Item key_skeleton;

    //Tools
    //public static Item flask_essence; //use durability to heal, can be refilled/repaired at essence well, craft with essence flask shard to enchant with unbreaking
    //public static Item draught_ressurect; //used to exit the Necropolis, a Ressurection alter can also be used
    //public static Item draught_death; //Links you to the realm of death, meaning you can use portal w/o being at 1/2 heart


    //Doors
    public static Item ghoulDoor;
    public static Item weepwillowDoor;
    public static Item bonebeechDoor;
    public static Item handDoor;

    public static void createItems()
    {
        //Tools
        soul_dagger = new ItemSword(SOULGEM).setUnlocalizedName("soul_dagger").setNoRepair();

        //Ore items
        soulgem = new Item().setUnlocalizedName("soulgem");
        soulgem_black = new Item().setUnlocalizedName("soulgem_black");
        soulgem_charged = new Item(){
            @Override
            public boolean hasEffect(ItemStack stack)
            {
                return true;
            }
        }.setUnlocalizedName("soulgem_charged");
        soulgem_black_charged = new Item(){
            @Override
            public boolean hasEffect(ItemStack stack)
            {
                return true;
            }
        }.setUnlocalizedName("soulgem_black_charged");
        ember = new Item().setUnlocalizedName("coal_ember");
        hematite = new Item().setUnlocalizedName("hematite");
        //Ore  ingots
        //ingot_rostygold = new Item().setUnlocalizedName("ingot_rostygold");
        ingot_pewter = new Item().setUnlocalizedName("ingot_pewter");
        //Ore Dusts
        dust_iron = new Item().setUnlocalizedName("dust_iron").setCreativeTab(CreativeTabs.tabMaterials);
        dust_gold = new Item().setUnlocalizedName("dust_gold").setCreativeTab(CreativeTabs.tabMaterials);
        //dust_rostygold = new Item().setUnlocalizedName("dust_rostygold");
        dust_pewter = new Item().setUnlocalizedName("dust_pewter");
        //Ore Nuggets
        nugget_iron = new Item().setUnlocalizedName("nugget_iron").setCreativeTab(CreativeTabs.tabMaterials);
        //nugget_rostygold = new Item().setUnlocalizedName("nugget_rostygold");
        nugget_pewter = new Item().setUnlocalizedName("nugget_pewter");
        //Darksteel
        //shard_darksteel = new Item().setUnlocalizedName("shard_darksteel");
        //chunk_darksteel = new Item().setUnlocalizedName("chunk_darksteel");

        //clay_porcelain = new Item().setUnlocalizedName("clay_porcelain");
        //bone_ash = new Item().setUnlocalizedName("ash_bone");
        //bone_shard = new Item().setUnlocalizedName("shard_bone");

        //feather_beetle = new Item().setUnlocalizedName("feather_beetle");

        bonebooFruit = new ItemFood(3, 0.4F, false).setUnlocalizedName("boneboo_fruit");

        registerItems();
    }

    public static void registerItems()
    {
        GameRegistry.registerItem(soul_dagger, soul_dagger.getUnlocalizedName().substring(5));

        regItem(soulgem);
        regItem(soulgem_black);
        regItem(soulgem_charged);
        regItem(soulgem_black_charged);
        regItem(ember);
        regItem(hematite);

        //regItem(ingot_rostygold);
        regItem(ingot_pewter);

        //TODO: textures
        GameRegistry.registerItem(dust_iron, dust_iron.getUnlocalizedName().substring(5));
        GameRegistry.registerItem(dust_gold, dust_gold.getUnlocalizedName().substring(5));
        //regItem(dust_rostygold);
        regItem(dust_pewter);

        GameRegistry.registerItem(nugget_iron, nugget_iron.getUnlocalizedName().substring(5));
        //regItem(nugget_rostygold);
        regItem(nugget_pewter);

        //regItem(clay_porcelain);
        //regItem(bone_ash);
        //regItem(bone_shard);

        //regItem(feather_beetle);

        regItem(bonebooFruit);
    }

    private static void regItem(Item item)
    {
        item.setCreativeTab(SoulWarden.SWTab);
        GameRegistry.registerItem(item, item.getUnlocalizedName().substring(5));
    }
}
