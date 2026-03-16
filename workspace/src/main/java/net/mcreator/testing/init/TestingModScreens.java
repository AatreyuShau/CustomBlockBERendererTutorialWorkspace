/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.testing.init;

import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.api.distmarker.Dist;

import net.mcreator.testing.client.gui.ExampleBlockGUIScreen;

@EventBusSubscriber(Dist.CLIENT)
public class TestingModScreens {
	@SubscribeEvent
	public static void clientLoad(RegisterMenuScreensEvent event) {
		event.register(TestingModMenus.EXAMPLE_BLOCK_GUI.get(), ExampleBlockGUIScreen::new);
	}

	public interface ScreenAccessor {
		void updateMenuState(int elementType, String name, Object elementState);
	}
}