package vikinggoth.soulwarden.items;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

/**
 * Created by Friedrich on 8/18/2015.
 */
public class ItemSoulGem
{
    /**
     * Whether this Item can be used as a payment to activate the vanilla beacon.
     * @param stack the ItemStack
     * @return true if this Item can be used
     */
    public boolean isBeaconPayment(ItemStack stack)
    {
        return true;
    }
}
