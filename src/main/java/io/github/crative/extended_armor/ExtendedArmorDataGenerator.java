package io.github.crative.extended_armor;

import io.github.crative.extended_armor.datagen.ItemTagProvider;
import io.github.crative.extended_armor.datagen.ModModelProvider;
import io.github.crative.extended_armor.datagen.ModRecipeProvider;
import io.github.crative.extended_armor.datagen.TranslationProvider;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class ExtendedArmorDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

		pack.addProvider(ModModelProvider::new);
		pack.addProvider(ItemTagProvider::new);
		pack.addProvider(ModRecipeProvider::new);
		pack.addProvider(TranslationProvider::new);
	}
}
