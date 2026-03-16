# CustomBlockBERendererTutorialWorkspace
Workspace for tutorial on making blocks render items

---

## > Code for `blockRenderer`
```java
@Override
public void render(PedestalBlockEntity thisBlock, float partialTick, PoseStack pose, MultiBufferSource buffer, int packedLight, int packedOverlay, Vec3 cameraPos) {
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
```
---

## > Code for `ModBERenderers`
```java
@EventBusSubscriber(Dist.CLIENT)
public class ModBERenderers {
	@SubscribeEvent
	public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
		event.registerBlockEntityRenderer(YourModBlockEntities.BLOCK.get(), YourRenderer::new);
	}
}
```
