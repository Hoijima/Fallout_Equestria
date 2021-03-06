package com.redsparkle.foe.items.guns.entitys.bulletFired;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraftforge.fml.client.registry.IRenderFactory;

/**
 * Created by hoijima on 22.06.17.
 */
public class RenderFactoryBullet implements IRenderFactory {
    public RenderFactoryBullet(RenderManager renderManager) {
        createRenderFor(renderManager);
    }

    @Override
    public Render createRenderFor(RenderManager renderManager) {
        Render bullet = new RenderBullet(renderManager);
        return bullet;
    }
}
