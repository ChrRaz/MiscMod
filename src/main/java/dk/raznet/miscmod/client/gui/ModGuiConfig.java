package dk.raznet.miscmod.client.gui;

import cpw.mods.fml.client.config.GuiConfig;
import dk.raznet.miscmod.handler.ConfigurationHandler;
import dk.raznet.miscmod.reference.Reference;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.common.config.Configuration;

/**
 * Created by ChrRaz on 22/07/2014.
 */
public class ModGuiConfig extends GuiConfig {

	public ModGuiConfig(GuiScreen guiScreen){
		super(guiScreen,
				new ConfigElement(ConfigurationHandler.config.getCategory(ConfigurationHandler.rootName)).getChildElements(),
				Reference.MOD_ID,
				false,
				false,
				I18n.format("miscmod.configgui.ConfigTitle"));
	}
}
