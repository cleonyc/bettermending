package nyc.cleo.bettermending


import net.axay.kspigot.items.addLore
import net.axay.kspigot.items.meta
import net.axay.kspigot.items.setLore
import net.axay.kspigot.runnables.taskRunLater
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.Style
import net.kyori.adventure.text.format.TextColor
import org.bukkit.Material
import org.bukkit.enchantments.Enchantment
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.PrepareAnvilEvent
import org.bukkit.event.player.PlayerItemBreakEvent
import org.bukkit.event.player.PlayerItemMendEvent
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.Damageable

class BetterMendingListeners : Listener {
    @EventHandler
    fun onPlayerMend(event: PlayerItemMendEvent) {
        event.isCancelled = true
    }
    @EventHandler
    fun onAnvilUse(event: PrepareAnvilEvent) {
        if (event.result == null || event.inventory.secondItem == null ) {
            return
        }
        val result = event.result!!
        // checks that: result has mending, and result is damageable
        if (!result.enchantments.containsKey(Enchantment.MENDING) || result.itemMeta !is Damageable) {
            return
        }
        // set RepairCost of returned item to 0
        val nbtData = result.nbtData
        nbtData.remove("RepairCost")
        nbtData.putInt("RepairCost", 0)
        result.nbtData = nbtData
        result.removeEnchantment(Enchantment.MENDING)
        // if repairing, and one item is mending then set damage of returning item to 0
        val meta = result.itemMeta as Damageable
        if (event.inventory.secondItem!!.type != Material.ENCHANTED_BOOK) {
            meta.damage = 0
        }
        if (result.itemMeta.lore()?.contains(Component.text("(Was Repaired)")) == true) {
            event.result = result
            return
        }
        meta.setLore { this.lorelist.add(Component.text("(Was Repaired)").style(Style.style(TextColor.color(0, 129, 194)))) }
        result.itemMeta = meta
        event.result = result
        return
    }
    @EventHandler
    fun onItemBreak(event: PlayerItemBreakEvent) {
        if (!event.brokenItem.isRepairableBy(ItemStack(Material.NETHERITE_INGOT))) {
            return
        }
        taskRunLater(2) {
            event.brokenItem.amount = 1
        }
        when (event.brokenItem.type) {
            Material.NETHERITE_AXE -> {
                event.brokenItem.type = Material.DIAMOND_AXE
                taskRunLater(2) {
                    event.brokenItem.itemMeta = (event.brokenItem.itemMeta as Damageable).apply {
                        damage = 1000
                    }
                }
            }
            Material.NETHERITE_BOOTS -> {
                event.brokenItem.type = Material.DIAMOND_BOOTS
                taskRunLater(2) {
                    event.brokenItem.itemMeta = (event.brokenItem.itemMeta as Damageable).apply {
                        damage = 300
                    }
                }
            }
            Material.NETHERITE_CHESTPLATE -> {
                event.brokenItem.type = Material.DIAMOND_CHESTPLATE
                taskRunLater(2) {
                    event.brokenItem.itemMeta = (event.brokenItem.itemMeta as Damageable).apply {
                        damage = 350
                    }
                }
            }
            Material.NETHERITE_HELMET -> {
                event.brokenItem.type = Material.DIAMOND_HELMET
                taskRunLater(2) {
                    event.brokenItem.itemMeta = (event.brokenItem.itemMeta as Damageable).apply {
                        damage = 250
                    }
                }
            }
            Material.NETHERITE_HOE -> {
                event.brokenItem.type = Material.DIAMOND_HOE
                taskRunLater(2) {
                    event.brokenItem.itemMeta = (event.brokenItem.itemMeta as Damageable).apply {
                        damage = 1000
                    }
                }
            }
            Material.NETHERITE_LEGGINGS -> {
                event.brokenItem.type = Material.DIAMOND_LEGGINGS
                taskRunLater(2) {
                    event.brokenItem.itemMeta = (event.brokenItem.itemMeta as Damageable).apply {
                        damage = 350
                    }
                }
            }
            Material.NETHERITE_PICKAXE -> {
                event.brokenItem.type = Material.DIAMOND_PICKAXE
                taskRunLater(2) {
                    event.brokenItem.itemMeta = (event.brokenItem.itemMeta as Damageable).apply {
                        damage = 1000
                    }
                }
            }
            Material.NETHERITE_SHOVEL -> {
                event.brokenItem.type = Material.DIAMOND_SHOVEL
                taskRunLater(2) {
                    event.brokenItem.itemMeta = (event.brokenItem.itemMeta as Damageable).apply {
                        damage = 1000
                    }
                }
            }
            Material.NETHERITE_SWORD -> {
                event.brokenItem.type = Material.DIAMOND_SWORD
                taskRunLater(2) {
                    event.brokenItem.itemMeta = (event.brokenItem.itemMeta as Damageable).apply {
                        damage = 1000
                    }
                }
            }
            else -> {}
        }

    }
}