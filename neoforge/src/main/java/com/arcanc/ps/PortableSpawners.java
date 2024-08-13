package com.arcanc.ps;


import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;

@Mod(Database.MOD_ID)
public class PortableSpawners
{

    public PortableSpawners(IEventBus eventBus)
    {
        CommonClass.init();
    }
}