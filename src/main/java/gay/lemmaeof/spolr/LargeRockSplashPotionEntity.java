package gay.lemmaeof.spolr;

import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.FallingBlockEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.Item;
import net.minecraft.particle.ItemStackParticleEffect;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class LargeRockSplashPotionEntity extends ThrownItemEntity {
	public LargeRockSplashPotionEntity(EntityType<LargeRockSplashPotionEntity> type, World world) {
		super(type, world);
	}

	public LargeRockSplashPotionEntity(double x, double y, double z, World world) {
		super(SplashPotionOfLargeRock.LARGE_ROCK_SPLASH_POTION_ENTITY, x, y, z, world);
	}

	public LargeRockSplashPotionEntity(LivingEntity owner, World world) {
		super(SplashPotionOfLargeRock.LARGE_ROCK_SPLASH_POTION_ENTITY, owner, world);
	}

	@Override
	public Item getDefaultItem() {
		return SplashPotionOfLargeRock.LARGE_ROCK_SPLASH_POTION;
	}

	public void handleStatus(byte status) {
		if (status == 3) {
			ParticleEffect effect = new ItemStackParticleEffect(ParticleTypes.ITEM, getStack());

			for(int i = 0; i < 8; ++i) {
				this.world.addParticle(effect, this.getX(), this.getY(), this.getZ(), 0.0, 0.0, 0.0);
			}
		}

	}

	@Override
	public void onEntityHit(EntityHitResult hit) {
		super.onEntityHit(hit);
		Entity entity = hit.getEntity();
		entity.damage(DamageSource.thrownProjectile(this, this.getOwner()), 2.0F);
		if (!this.world.isClient) {
			createRock(hit.getPos());
		}
	}

	@Override
	public void onCollision(HitResult hit) {
		super.onCollision(hit);
		if (!this.world.isClient) {
			this.world.sendEntityStatus(this, (byte)3);
			createRock(hit.getPos());
			this.discard();
		}
	}


	private void createRock(Vec3d position) {
		FallingBlockEntity e = FallingBlockEntity.fall(this.world, new BlockPos(position).up(), Blocks.STONE.getDefaultState());
		world.spawnEntity(e);
	}
}
