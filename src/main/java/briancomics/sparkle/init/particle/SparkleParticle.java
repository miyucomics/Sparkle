package briancomics.sparkle.init.particle;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.particle.*;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.DefaultParticleType;

public class SparkleParticle extends SpriteBillboardParticle {
	private final SpriteProvider spriteProvider;

	protected SparkleParticle (ClientWorld clientWorld, double d, double e, double f, double red, double green, double blue, SpriteProvider provider) {
		super(clientWorld, d, e, f, red, green, blue);
		this.maxAge = 20;
		this.velocityX = 0;
		this.velocityY = 0;
		this.velocityZ = 0;
		this.spriteProvider = provider;
		this.setSprite(provider);
		this.setSpriteForAge(provider);
		this.scale(2.5F);
	}

	public void tick () {
		super.tick();
		this.setSpriteForAge(this.spriteProvider);
	}

	@Override
	public int getBrightness (float tint) {
		int i = super.getBrightness(tint);
		float f = (float) this.age / (float) this.maxAge;
		f *= f;
		f *= f;
		int j = i & 0xFF;
		int k = i >> 16 & 0xFF;
		if ((k += (int)(f * 15.0f * 16.0f)) > 240) {
			k = 240;
		}
		return j | k << 16;
	}

	@Override
	public ParticleTextureSheet getType () {
		return ParticleTextureSheet.PARTICLE_SHEET_TRANSLUCENT;
	}

	@Environment(value = EnvType.CLIENT)
	public record Factory(SpriteProvider spriteProvider) implements ParticleFactory<DefaultParticleType> {
		@Override
		public Particle createParticle (DefaultParticleType defaultParticleType, ClientWorld clientWorld, double d, double e, double f, double g, double h, double i) {
			SparkleParticle sparkleParticle = new SparkleParticle(clientWorld, d, e, f, g, h, i, this.spriteProvider);
			sparkleParticle.setAlpha(1.0f);
			return sparkleParticle;
		}
	}
}
