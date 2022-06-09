package briancomics.sparkle;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;

public class SparkleClient implements ClientModInitializer {
	@Override
	public void onInitializeClient () {
		ParticleFactoryRegistry.getInstance().register(SparkleMain.SPARKLE_PARTICLE, SparkleParticle.Factory::new);
	}
}
