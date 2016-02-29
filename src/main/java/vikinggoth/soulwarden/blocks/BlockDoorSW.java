package vikinggoth.soulwarden.blocks;

import net.minecraft.block.BlockDoor;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import vikinggoth.soulwarden.registers.BlockRegister;
import vikinggoth.soulwarden.registers.ItemRegister;

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
        return this == BlockRegister.ghoulDoor ? ItemRegister.ghoulDoor : (this == BlockRegister.weepwillowDoor ? ItemRegister.weepwillowDoor : (this == BlockRegister.bonebeechDoor ? ItemRegister.bonebeechDoor : ItemRegister.handDoor));
    }
}
