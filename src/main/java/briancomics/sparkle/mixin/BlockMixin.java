package briancomics.sparkle.mixin;

import briancomics.sparkle.SparkleMain;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Random;

@Mixin(Block.class)
public class BlockMixin {
	@Inject(method = "randomDisplayTick", at = @At("HEAD"))
	private void spawnParticles (BlockState state, World world, BlockPos pos, Random random, CallbackInfo ci) {
		if (random.nextFloat() < 0.1F) {
			if (state.isIn(SparkleMain.SPARKLY_BLOCKS)) {
				double positionX = (((double) pos.getX()) - 0.25D) + (random.nextDouble() * 1.5D);
				double positionY = (((double) pos.getY()) - 0.25D) + (random.nextDouble() * 1.5D);
				double positionZ = (((double) pos.getZ()) - 0.25D) + (random.nextDouble() * 1.5D);
				while (!world.isAir(new BlockPos(positionX, positionY, positionZ))) {
					positionX = (((double) pos.getX()) - 0.25D) + (random.nextDouble() * 1.5D);
					positionY = (((double) pos.getY()) - 0.25D) + (random.nextDouble() * 1.5D);
					positionZ = (((double) pos.getZ()) - 0.25D) + (random.nextDouble() * 1.5D);
				}
				world.addParticle(SparkleMain.SPARKLE_PARTICLE, positionX, positionY, positionZ, 0, 0, 0);
			}
		}
	}
}
