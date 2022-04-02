package briancomics.sparkle;

import briancomics.sparkle.init.particle.SparkleParticle;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.event.client.ClientSpriteRegistryCallback;
import net.minecraft.screen.PlayerScreenHandler;
import net.minecraft.util.Identifier;

public class SparkleClient implements ClientModInitializer {
	@Override
	public void onInitializeClient () {
		ClientSpriteRegistryCallback.event(PlayerScreenHandler.BLOCK_ATLAS_TEXTURE).register(((atlasTexture, registry) -> {
			registry.register(new Identifier("sparkle", "particle/sparkle_0"));
		}));

		ParticleFactoryRegistry.getInstance().register(SparkleMain.SPARKLE_PARTICLE, SparkleParticle.Factory::new);
	}
}
