package briancomics.sparkle.mixin;

import briancomics.sparkle.SparkleMain;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ItemEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ItemEntity.class)
public abstract class ItemEntityMixin extends Entity {
	@Shadow public abstract ItemStack getStack ();

	public ItemEntityMixin (EntityType<?> type, World world) {
		super(type, world);
	}

	@Inject(method = "tick", at = @At("HEAD"))
	private void spawnParticles (CallbackInfo ci) {
		if (getStack().isIn(SparkleMain.SPARKLY_ITEMS) && random.nextFloat() < 0.1F) {
			double positionX = (getX() - 0.5D) + random.nextDouble();
			double positionY = (getY() - 0.5D) + random.nextDouble();
			double positionZ = (getZ() - 0.5D) + random.nextDouble();
			getWorld().addParticle(SparkleMain.SPARKLE_PARTICLE, positionX, positionY, positionZ, 0, 0, 0);
		}
	}
}
