/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.testing.init;

import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredBlock;

import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.Block;

import net.mcreator.testing.block.ExampleBlock;
import net.mcreator.testing.TestingMod;

import java.util.function.Function;

public class TestingModBlocks {
	public static final DeferredRegister.Blocks REGISTRY = DeferredRegister.createBlocks(TestingMod.MODID);
	public static final DeferredBlock<Block> EXAMPLE;
	static {
		EXAMPLE = register("example", ExampleBlock::new);
	}

	// Start of user code block custom blocks
	// End of user code block custom blocks
	private static <B extends Block> DeferredBlock<B> register(String name, Function<BlockBehaviour.Properties, ? extends B> supplier) {
		return REGISTRY.registerBlock(name, supplier);
	}
}