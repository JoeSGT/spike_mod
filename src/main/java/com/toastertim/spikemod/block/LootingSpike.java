package com.toastertim.spikemod.block;

import com.toastertim.spikemod.Config;
import com.toastertim.spikemod.SpikeMod;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Enchantments;
import net.minecraft.init.Items;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.common.util.FakePlayerFactory;

/**
 * Created by Tim on 10/5/2016.
 */
public class LootingSpike extends Block {

	public LootingSpike() {
		super(Material.ROCK);
		this.setUnlocalizedName("looting_spike");
		this.setCreativeTab(SpikeMod.SPIKE_TAB);
		this.setHardness(1F);
		this.setResistance(1F);
		this.setSoundType(SoundType.STONE);
		setRegistryName("looting_spike");
		SpikeBlocks.BLOCKS.add(this);
		SpikeBlocks.ITEMS.add(new ItemBlock(this).setRegistryName(getRegistryName()));
	}

	@Override
	public void onEntityWalk(World world, BlockPos pos, Entity entity) {
		if (!world.isRemote) {
			if (entity instanceof EntityLivingBase && !(entity instanceof EntityPlayer)) {
				FakePlayer player = FakePlayerFactory.getMinecraft((WorldServer) world);
				ItemStack onHand = new ItemStack(Items.STICK);
				onHand.addEnchantment(Enchantments.LOOTING, 3);
				player.setHeldItem(EnumHand.MAIN_HAND, onHand);
				entity.attackEntityFrom(DamageSource.causePlayerDamage(player), Config.lootingDamage);
				player.setHeldItem(EnumHand.MAIN_HAND, ItemStack.EMPTY);
			}

			entity.attackEntityFrom(DamageSource.CACTUS, Config.lootingDamage);
		}
	}

	//for the purposes of skyblocks, mobs can spawn on this block
	@Override
	public boolean canCreatureSpawn(IBlockState state, IBlockAccess world, BlockPos pos, EntityLiving.SpawnPlacementType type) {
		return true;
	}

	@Override
	public boolean isFullCube(IBlockState state) {
		return false;
	}

	@Override
	public boolean isNormalCube(IBlockState state) {
		return false;
	}

	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}
}
