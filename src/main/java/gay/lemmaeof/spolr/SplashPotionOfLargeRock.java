package gay.lemmaeof.spolr;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SplashPotionOfLargeRock implements ModInitializer {
	public static final String MODID = "spolr";
	public static final Logger LOGGER = LoggerFactory.getLogger(MODID);

	public static final Item LARGE_ROCK_SPLASH_POTION = Registry.register(
			Registry.ITEM,
			new Identifier(MODID, "large_rock_splash_potion"),
			new LargeRockSplashPotionItem(new Item.Settings().group(ItemGroup.MISC))
	);

	public static final EntityType<LargeRockSplashPotionEntity> LARGE_ROCK_SPLASH_POTION_ENTITY = Registry.register(
			Registry.ENTITY_TYPE,
			new Identifier(MODID, "large_rock_splash_potion"),
			EntityType.Builder
				.<LargeRockSplashPotionEntity>create(LargeRockSplashPotionEntity::new, SpawnGroup.MISC)
				.setDimensions(0.25F, 0.25F)
				.maxTrackingRange(4)
				.trackingTickInterval(10)
				.build("spolr:large_rock_splash_potion")
	);

	@Override
	public void onInitialize(ModContainer mod) {
		LOGGER.info("Blame Taro.");
	}
}
