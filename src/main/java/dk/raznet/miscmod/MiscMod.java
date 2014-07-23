package dk.raznet.miscmod;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import dk.raznet.miscmod.handler.ConfigurationHandler;
import dk.raznet.miscmod.proxy.IProxy;
import dk.raznet.miscmod.reference.Reference;

/**
 * Created by ChrRaz on 7/20/14.
 */
@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.MOD_VERSION, guiFactory = Reference.GUI_FACTORY_CLASS)
public class MiscMod {

	@Mod.Instance(Reference.MOD_ID)
	public static MiscMod instance;

	@SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.SERVER_PROXY_CLASS)
	public static IProxy proxy;

	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		ConfigurationHandler.init(event.getSuggestedConfigurationFile());

		/*
		for (Field field : ConfigurationHandler.ConfigValues.class.getFields())
			try {
				LogHelper.info(field.getName() + "=" + field.get(null));
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
			*/
	}

	@Mod.EventHandler
	public void init(FMLInitializationEvent event) {

	}

	@Mod.EventHandler
	public void postInit(FMLPostInitializationEvent event) {

	}
}
