package vikinggoth.soulwarden.blocks;

import net.minecraft.block.BlockDoor;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import vikinggoth.soulwarden.registries.ConfigBlocks;
import vikinggoth.soulwarden.registries.ConfigItems;

/**
 * Created by Friedrich on 11/15/2015.
 */
public class BlockDoorSW extends BlockDoor
{

    public BlockDoorSW()
    {
        super(Material.wood);
        this.setHardness(3.0F).setStepSound(soundTypeWood);
        this.disableStats();
    }

    private Item getItem()
    {
        return this == ConfigBlocks.ghoulDoor ? ConfigItems.ghoulDoor : (this == ConfigBlocks.weepwillowDoor ? ConfigItems.weepwillowDoor : (this == ConfigBlocks.bonebeechDoor ? ConfigItems.bonebeechDoor : ConfigItems.handDoor));
    }
}
