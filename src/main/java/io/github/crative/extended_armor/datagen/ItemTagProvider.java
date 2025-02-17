package io.github.crative.extended_armor.datagen;

import io.github.crative.extended_armor.items.ExtendedArmorItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.HolderLookup;
import net.minecraft.registry.tag.ItemTags;

import java.util.concurrent.CompletableFuture;

public class ItemTagProvider extends FabricTagProvider.ItemTagProvider {
	public ItemTagProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> completableFuture) {
		super(output, completableFuture);
	}

	@Override
	protected void configure(HolderLookup.Provider arg) {
		getOrCreateTagBuilder(ItemTags.TRIMMABLE_ARMOR)
			//* Copper
			.add(ExtendedArmorItems.COPPER_HELMET)
			.add(ExtendedArmorItems.COPPER_CHESTPLATE)
			.add(ExtendedArmorItems.COPPER_LEGGINGS)
			.add(ExtendedArmorItems.COPPER_BOOTS)

			//* Stealth
			.add(ExtendedArmorItems.STEALTH_HELMET)
			.add(ExtendedArmorItems.STEALTH_CHESTPLATE)
			.add(ExtendedArmorItems.STEALTH_LEGGINGS)
			.add(ExtendedArmorItems.STEALTH_BOOTS)

			//* Obsidian
			.add(ExtendedArmorItems.OBSIDIAN_HELMET)
			.add(ExtendedArmorItems.OBSIDIAN_CHESTPLATE)
			.add(ExtendedArmorItems.OBSIDIAN_LEGGINGS)
			.add(ExtendedArmorItems.OBSIDIAN_BOOTS);
	}
}
