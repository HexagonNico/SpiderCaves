package io.github.hexagonnico.spidercaves.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ambient.Bat;
import net.minecraft.world.entity.monster.Spider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Spider egg block.
 *
 * @author Nico
 */
public class SpiderEggBlock extends Block {

    /** Single egg hitbox */
    private static final VoxelShape ONE_EGG_AABB = Block.box(3.0, 0.0, 3.0, 12.0, 7.0, 12.0);
    /** Multiple eggs hitbox */
    private static final VoxelShape MULTIPLE_EGGS_AABB = Block.box(1.0, 0.0, 1.0, 15.0, 7.0, 15.0);

    /**
     * Constructs a spider egg block.
     *
     * @param properties Block properties
     */
    public SpiderEggBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.getStateDefinition().any().setValue(BlockStateProperties.EGGS, 1));
    }

    @Override
    public void stepOn(@NotNull Level world, @NotNull BlockPos pos, @NotNull BlockState state, @NotNull Entity entity) {
        if(!entity.isSteppingCarefully()) {
            destroyEgg(world, state, pos, entity, 30);
        }
        super.stepOn(world, pos, state, entity);
    }

    @Override
    public void fallOn(@NotNull Level world, @NotNull BlockState state, @NotNull BlockPos pos, @NotNull Entity entity, float v) {
        destroyEgg(world, state, pos, entity, 2);
        super.fallOn(world, state, pos, entity, v);
    }

    @Override
    public void playerDestroy(@NotNull Level world, @NotNull Player player, @NotNull BlockPos pos, @NotNull BlockState state, @Nullable BlockEntity blockEntity, @NotNull ItemStack itemStack) {
        super.playerDestroy(world, player, pos, state, blockEntity, itemStack);
        decreaseEggs(world, pos, state);
    }

    @Override
    @SuppressWarnings("deprecation")
    public boolean canBeReplaced(@NotNull BlockState state, @NotNull BlockPlaceContext context) {
        if(!context.isSecondaryUseActive() && context.getItemInHand().is(this.asItem()) && state.getValue(BlockStateProperties.EGGS) < 4) {
            return true;
        }
        return super.canBeReplaced(state, context);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        BlockState state = context.getLevel().getBlockState(context.getClickedPos());
        if(state.is(this)) {
            return state.setValue(BlockStateProperties.EGGS, Math.min(4, state.getValue(BlockStateProperties.EGGS) + 1));
        }
        return super.getStateForPlacement(context);
    }

    @Override
    @SuppressWarnings("deprecation")
    public @NotNull VoxelShape getShape(@NotNull BlockState state, @NotNull BlockGetter world, @NotNull BlockPos pos, @NotNull CollisionContext context) {
        return state.getValue(BlockStateProperties.EGGS) > 1 ? MULTIPLE_EGGS_AABB : ONE_EGG_AABB;
    }

    @Override
    protected void createBlockStateDefinition(@NotNull StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(BlockStateProperties.EGGS);
    }

    /**
     * Randomly destroys an egg if possible.
     *
     * @param world World
     * @param state Spider egg block state
     * @param pos Spider egg block position
     * @param entity Entity that destroys the egg
     * @param chance Chance to destroy
     */
    private static void destroyEgg(Level world, BlockState state, BlockPos pos, Entity entity, int chance) {
        if(canDestroyEgg(world, entity) && !world.isClientSide() && world.random.nextInt(chance) == 0) {
            decreaseEggs(world, pos, state);
        }
    }

    /**
     * Decreases the number of eggs and spawn a spider.
     * Destroys the block if there are no more eggs.
     *
     * @param world World
     * @param pos Spider egg block pos
     * @param state Spider egg block state
     */
    private static void decreaseEggs(Level world, BlockPos pos, BlockState state) {
        // TODO: Add custom sound?
        world.playSound(null, pos, SoundEvents.TURTLE_EGG_BREAK, SoundSource.BLOCKS, 0.7f, 0.9f + world.random.nextFloat() * 0.2f);
        int count = state.getValue(BlockStateProperties.EGGS);
        if(count <= 1) {
            world.destroyBlock(pos, false);
        } else {
            world.setBlock(pos, state.setValue(BlockStateProperties.EGGS, count - 1), 2);
            world.gameEvent(GameEvent.BLOCK_DESTROY, pos, GameEvent.Context.of(state));
        }
        Spider spider = EntityType.SPIDER.create(world);
        if(spider != null) {
            spider.moveTo(pos.getCenter().add(0.0, 0.2, 0.0));
            world.addFreshEntity(spider);
        }
    }

    /**
     * Checks if the given entity can destroy an egg.
     *
     * @param world Needed to get game rules
     * @param entity The entity
     * @return True if the entity can destroy an egg, otherwise false
     */
    private static boolean canDestroyEgg(Level world, Entity entity) {
        if(entity instanceof LivingEntity) {
            if(entity instanceof Spider || entity instanceof Bat) {
                return false;
            }
            return entity instanceof Player || world.getGameRules().getBoolean(GameRules.RULE_MOBGRIEFING);
        }
        return false;
    }
}
