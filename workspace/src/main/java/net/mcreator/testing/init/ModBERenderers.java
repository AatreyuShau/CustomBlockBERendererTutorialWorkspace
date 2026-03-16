package net.mcreator.testing.init;

import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.api.distmarker.Dist;

import net.mcreator.testing.ExampleBlockRenderer;

@EventBusSubscriber(Dist.CLIENT)
public class ModBERenderers {
	@SubscribeEvent
	public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
		event.registerBlockEntityRenderer(TestingModBlockEntities.EXAMPLE.get(), ExampleBlockRenderer::new);
	}
}