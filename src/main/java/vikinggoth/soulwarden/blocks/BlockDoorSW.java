package vikinggoth.soulwarden.blocks;

import net.minecraft.block.BlockDoor;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import vikinggoth.soulwarden.registries.BlockRegistry;
import vikinggoth.soulwarden.registries.ItemRegistry;

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
        return this == BlockRegistry.ghoulDoor ? ItemRegistry.ghoulDoor : (this == BlockRegistry.weepwillowDoor ? ItemRegistry.weepwillowDoor : (this == BlockRegistry.bonebeechDoor ? ItemRegistry.bonebeechDoor : ItemRegistry.handDoor));
    }
}
