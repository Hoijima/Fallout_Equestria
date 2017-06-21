package com.redsparkle.foe.items.guns.inits;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Created by hoijima on 19.06.17.
 */
public class EntityBullet extends EntityThrowable {
    public int damage = 15;

    public EntityBullet(World worldIn) {
        super(worldIn);
    }


    public EntityBullet(World worldIn, EntityLivingBase livingBaseIn) {
        super(worldIn, livingBaseIn);
    }

    public EntityBullet(World worldIn, double x, double y, double z) {
        super(worldIn, x, y, z);
    }


    @SideOnly(Side.CLIENT)
    public void handleStatusUpdate(byte id) {
        if (id == 3) {
            for (int i = 0; i < 8; ++i) {
                this.world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, this.posX, this.posY, this.posZ, 0.0D, 0.0D, 0.0D);
            }
        }
    }

    /**
     * Called when this EntityThrowable hits a block or entity.
     */

    protected void onImpact(RayTraceResult result) {
        if (result.entityHit != null) {
            int i = damage;


            result.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, this.getThrower()), (float) i);
        }

        if (!this.world.isRemote) {
            this.world.setEntityState(this, (byte) 3);
            this.setDead();
        }
    }

    @Override
    protected float getGravityVelocity() {
        return 0F;
    }
}