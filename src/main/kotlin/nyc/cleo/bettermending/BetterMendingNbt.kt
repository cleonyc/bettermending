package nyc.cleo.bettermending

import net.axay.kspigot.annotations.NMS_General
import net.minecraft.nbt.CompoundTag
import org.bukkit.craftbukkit.v1_19_R1.inventory.CraftItemStack
import org.bukkit.inventory.ItemStack

@NMS_General
var ItemStack.nbtData: CompoundTag
    get() {
        CraftItemStack.asNMSCopy(this).let {
            return if (it.hasTag()) (it.tag ?: CompoundTag()) else CompoundTag()
        }
    }
    set(data: CompoundTag) {
        val field = CraftItemStack::class.java.getDeclaredField("handle")
        field.isAccessible = true
        (field[this] as net.minecraft.world.item.ItemStack).tag = data
    }

