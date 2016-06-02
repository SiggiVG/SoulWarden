package vikinggoth.soulwarden.items.enchants;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.util.ResourceLocation;

/**
 * Created by Friedrich on 5/31/2016.
 */
public class EnchantmentSoulSteal extends Enchantment
{

    public EnchantmentSoulSteal(int enchID, ResourceLocation enchName, int enchWeight, EnumEnchantmentType enchType)
    {
        super(enchID, enchName, enchWeight, enchType);
        this.name = "soulsteal";
    }

    /**
     * Returns the minimal value of enchantability needed on the enchantment level passed.
     */
    public int getMinEnchantability(int enchantmentLevel)
    {
        return 10 + 20 * (enchantmentLevel - 1);
    }

    /**
     * Returns the maximum value of enchantability nedded on the enchantment level passed.
     */
    public int getMaxEnchantability(int enchantmentLevel)
    {
        return super.getMinEnchantability(enchantmentLevel) + 50;
    }

    /**
     * Returns the maximum level that the enchantment can have.
     */
    public int getMaxLevel()
    {
        return 1;
    }
}
