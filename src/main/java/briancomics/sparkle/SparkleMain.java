package briancomics.sparkle;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class SparkleMain implements ModInitializer {
	public static final TagKey<Block> SPARKLY_BLOCKS = TagKey.of(Registries.BLOCK.getKey(), new Identifier("sparkle", "sparkly"));
	public static final TagKey<Item> SPARKLY_ITEMS = TagKey.of(Registries.ITEM.getKey(), new Identifier("sparkle", "sparkly"));
	public static DefaultParticleType SPARKLE_PARTICLE;

	@Override
	public void onInitialize () {
		SPARKLE_PARTICLE = Registry.register(Registries.PARTICLE_TYPE, new Identifier("sparkle", "sparkle"), FabricParticleTypes.simple(true));
	}
}
