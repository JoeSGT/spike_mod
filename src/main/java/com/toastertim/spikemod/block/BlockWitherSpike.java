package com.toastertim.spikemod.block;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockWitherSpike extends BlockSpike{

	public BlockWitherSpike() {
		super(SpikeTypes.EXTRASHARPSPIKE, Material.ROCK, SoundType.STONE);
	}

	@Override
	public void handleSpikeDamage(World world, BlockPos pos, EntityLivingBase entity){
		entity.addPotionEffect(new PotionEffect(MobEffects.WITHER, 200, 1));
		entity.attackEntityFrom(DamageSource.CACTUS, type.getDamage());
	}
}
