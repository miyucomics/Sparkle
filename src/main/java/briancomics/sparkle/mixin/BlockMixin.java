package briancomics.sparkle.mixin;

import briancomics.sparkle.SparkleMain;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Block.class)
public class BlockMixin {
	private static final double extraDistance = 0.25D;
	@Inject(method = "randomDisplayTick", at = @At("HEAD"))
	private void spawnParticles (BlockState state, World world, BlockPos pos, Random random, CallbackInfo ci) {
		if (random.nextFloat() < 0.75F && state.isIn(SparkleMain.SPARKLY_BLOCKS)) {
			double positionX = ((double) pos.getX() - extraDistance) + (random.nextDouble() + (2 * extraDistance));
			double positionY = ((double) pos.getY() - extraDistance) + (random.nextDouble() + (2 * extraDistance));
			double positionZ = ((double) pos.getZ() - extraDistance) + (random.nextDouble() + (2 * extraDistance));
			world.addParticle(SparkleMain.SPARKLE_PARTICLE, positionX, positionY, positionZ, 0, 0, 0);
		}
	}
}
