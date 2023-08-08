/*
Methods in this class modified and adapted from PinkGoosik's Visuality under the MIT license

https://github.com/PinkGoosik/visuality

        Permission is hereby granted, free of charge, to any person obtaining
        a copy of this software and associated documentation files (the
        'Software'), to deal in the Software without restriction, including
        without limitation the rights to use, copy, modify, merge, publish,
        distribute, sublicense, and/or sell copies of the Software, and to
        permit persons to whom the Software is furnished to do so, subject to
        the following conditions:

        The above copyright notice and this permission notice shall be
        included in all copies or substantial portions of the Software.

        THE SOFTWARE IS PROVIDED 'AS IS', WITHOUT WARRANTY OF ANY KIND,
        EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
        MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
        IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY
        CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT,
        TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE
        SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
*/


package briancomics.sparkle.mixin;

import briancomics.sparkle.SparkleMain;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.util.ParticleUtil;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Block.class)
public class BlockMixin {
	@Inject(method = "randomDisplayTick", at = @At("HEAD"))
	private void spawnParticles (BlockState state, World world, BlockPos pos, Random random, CallbackInfo ci) {
		for(Direction direction : Direction.values()) {
			BlockPos offset = pos.offset(direction);
			if(!world.getBlockState(offset).isOpaqueFullCube(world, offset)) {
				if(random.nextFloat() < 0.2 && state.isIn(SparkleMain.SPARKLY_BLOCKS)) {
					Direction.Axis axis = direction.getAxis();
					double x = axis == Direction.Axis.X ? 0.5 + 0.5625 * (double) direction.getOffsetX() : (double) random.nextFloat();
					double y = axis == Direction.Axis.Y ? 0.5 + 0.5625 * (double) direction.getOffsetY() : (double) random.nextFloat();
					double z = axis == Direction.Axis.Z ? 0.5 + 0.5625 * (double) direction.getOffsetZ() : (double) random.nextFloat();
					world.addParticle(SparkleMain.SPARKLE_PARTICLE, (double) pos.getX() + x, (double) pos.getY() + y, (double) pos.getZ() + z, 0, 0, 0);
				}
			}
		}
	}
}
