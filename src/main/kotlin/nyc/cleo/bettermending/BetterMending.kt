package nyc.cleo.bettermending

import net.axay.kspigot.main.KSpigot

class BetterMending : KSpigot() {
    override fun startup() {
        this.server.pluginManager.registerEvents(BetterMendingListeners(), this)
    }
}