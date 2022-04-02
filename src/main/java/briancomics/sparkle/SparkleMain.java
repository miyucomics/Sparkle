package briancomics.sparkle;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.fabricmc.fabric.api.tag.TagFactory;
import net.minecraft.block.Block;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.tag.Tag;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class SparkleMain implements ModInitializer {
	public static final Tag<Block> SPARKLY_BLOCKS = TagFactory.BLOCK.create(new Identifier("sparkle", "sparkly"));
	public static DefaultParticleType SPARKLE_PARTICLE;

	@Override
	public void onInitialize () {
		SPARKLE_PARTICLE = Registry.register(Registry.PARTICLE_TYPE, new Identifier("sparkle", "sparkle"), FabricParticleTypes.simple(true));
	}
}
