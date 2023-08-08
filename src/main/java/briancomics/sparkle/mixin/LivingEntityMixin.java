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
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity {
    LivingEntity self = LivingEntity.class.cast(this);

    @Shadow
    public abstract boolean isAlive();

    int ticksDelay = 0;

    public LivingEntityMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    @Inject(method = "tick", at = @At("TAIL"))
    private void tick(CallbackInfo ci) {
        var client = MinecraftClient.getInstance();
        if(getWorld().isClient() && ticksDelay != 0) ticksDelay--;
        if(getWorld().isClient() && this.isAlive() && client.player != null) {
            if (client.player.getUuid().equals(this.getUuid()) && client.options.getPerspective().isFirstPerson()) return;
            int shinyLevel = getShinyLevel(self);
            spawnSparkles(shinyLevel);
        }
    }

    @Unique
    private void spawnSparkles(int shinyLevel) {
        if(shinyLevel > 0) {
            if(this.random.nextInt(20 - shinyLevel) == 0) {
                double x = random.nextFloat() * 2 - 1;
                double y = random.nextFloat();
                double z = random.nextFloat() * 2 - 1;
                getWorld().addParticle(SparkleMain.SPARKLE_PARTICLE, this.getX() + x, this.getY() + y + 1, this.getZ() + z, 0, 0, 0);
            }
        }
    }

    @Unique
    private static int getShinyLevel(LivingEntity entity) {
        int shinyArmor = 0;
        for(ItemStack stack : entity.getArmorItems()) {
            if(stack.isIn(SparkleMain.SPARKLY_ITEMS)) {
                shinyArmor++;
            }
        }
        return shinyArmor;
    }
}
