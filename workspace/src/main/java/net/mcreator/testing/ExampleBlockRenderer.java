/*
 * The code of this mod element is always locked.
 *
 * You can register new events in this class too.
 *
 * If you want to make a plain independent class, create it using
 * Project Browser -> New... and make sure to make the class
 * outside net.mcreator.testing as this package is managed by MCreator.
 *
 * If you change workspace package, modid or prefix, you will need
 * to manually adapt this file to these changes or remake it.
 *
 * This class will be added in the mod root package.
*/
package net.mcreator.testing;

import net.neoforged.neoforge.items.IItemHandler;
import net.neoforged.neoforge.common.extensions.ILevelExtension;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.api.distmarker.Dist;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.core.BlockPos;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.Minecraft;

import net.mcreator.testing.block.entity.ExampleBlockEntity;

import com.mojang.math.Axis;
import com.mojang.blaze3d.vertex.PoseStack;

@EventBusSubscriber(Dist.CLIENT)
public class ExampleBlockRenderer implements BlockEntityRenderer<ExampleBlockEntity> {
	public ExampleBlockRenderer(BlockEntityRendererProvider.Context ctx) {
	}

	@Override
	public void render(ExampleBlockEntity thisBlock, float partialTick, PoseStack pose, MultiBufferSource buffer, int packedLight, int packedOverlay, Vec3 cameraPos) {
		Level level = thisBlock.getLevel();
		ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();
		BlockState state = thisBlock.getBlockState();
		ItemStack stack = itemFromBlockInventory(thisBlock.getLevel(), thisBlock.getBlockPos(), 0).copy();
		float time = (System.currentTimeMillis() % 360000) / 50f;
		//
		pose.pushPose();
		pose.translate(0.5f, 0.51f, 0.5f);
		pose.scale(0.5f, 0.5f, 0.5f);
		//pose.translate(0.5f, 0.75f + Math.abs(Math.sin(time / 10) * 0.33), 0.5f);
		//pose.mulPose(Axis.YP.rotationDegrees(time * 2.0f));
		//pose.mulPose(Minecraft.getInstance().getEntityRenderDispatcher().cameraOrientation());
		pose.mulPose(Axis.XP.rotationDegrees(90.0f));
		itemRenderer.renderStatic(stack, ItemDisplayContext.FIXED, getLightLevel(thisBlock.getLevel(), thisBlock.getBlockPos()), OverlayTexture.NO_OVERLAY, pose, buffer, thisBlock.getLevel(), 1);
		pose.popPose();
	}

	private static ItemStack itemFromBlockInventory(LevelAccessor world, BlockPos pos, int slot) {
		if (world instanceof ILevelExtension ext) {
			IItemHandler handler = ext.getCapability(Capabilities.ItemHandler.BLOCK, pos, null);
			if (handler != null && slot >= 0 && slot < handler.getSlots()) {
				return handler.getStackInSlot(slot);
			}
		}
		return ItemStack.EMPTY;
	}

	private int getLightLevel(Level level, BlockPos pos) {
		int bLight = level.getBrightness(LightLayer.BLOCK, pos);
		int sLight = level.getBrightness(LightLayer.SKY, pos);
		return LightTexture.pack(bLight, sLight);
	}
}