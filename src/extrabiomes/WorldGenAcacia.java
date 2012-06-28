package extrabiomes;

import java.util.Random;

import net.minecraft.src.Block;
import net.minecraft.src.World;
import net.minecraft.src.WorldGenerator;
import extrabiomes.api.TerrainGenBlock;

public class WorldGenAcacia extends WorldGenerator {

	public WorldGenAcacia(final boolean doNotify) {
		super(doNotify);
	}

	@Override
	public boolean generate(World world, Random rand, int x, int y, int z) {
		int height = rand.nextInt(4) + 6;
		boolean canGrow = true;

		if (y < 1 || y + height + 1 > 256) {
			return false;
		}

		for (int y1 = y; y1 <= y + 1 + height; y1++) {
			byte clearance = 1;

			if (y1 == y) {
				clearance = 0;
			}

			if (y1 >= (y + 1 + height) - 2) {
				clearance = 2;
			}

			for (int x1 = x - clearance; x1 <= x + clearance && canGrow; x1++) {
				for (int z1 = z - clearance; z1 <= z + clearance && canGrow; z1++) {
					if (y1 >= 0 && y1 < 256) {
						final int blocktoCheck = world.getBlockId(x1, y1, z1);
						final BlockControl bc = BlockControl.INSTANCE;

						if (blocktoCheck != 0 && !bc.isLeaves(blocktoCheck)
								&& blocktoCheck != Block.grass.blockID
								&& blocktoCheck != Block.dirt.blockID
								&& !bc.isWood(blocktoCheck)) {
							canGrow = false;
						}
					} else {
						canGrow = false;
					}
				}
			}
		}

		if (!canGrow) {
			return false;
		}

		int ground = world.getBlockId(x, y - 1, z);

		if (ground != Block.grass.blockID && ground != Block.dirt.blockID
				|| y >= 256 - height - 1) {
			return false;
		}

		world.setBlock(x, y - 1, z, Block.dirt.blockID);
		byte canopyHeight = 3;
		int minCanopyRadius = 0;
		MetaBlock leaf = BlockControl.INSTANCE
				.getTerrainGenBlock(TerrainGenBlock.ACACIA_LEAVES);

		for (int y1 = (y - canopyHeight) + height; y1 <= y + height; y1++) {
			int distanceFromTop = y1 - (y + height);
			int canopyRadius = (minCanopyRadius + 1) - distanceFromTop;

			for (int x1 = x - canopyRadius; x1 <= x + canopyRadius; x1++) {
				int xOnRadius = x1 - x;

				for (int z1 = z - canopyRadius; z1 <= z + canopyRadius; z1++) {
					int zOnRadius = z1 - z;

					if ((Math.abs(xOnRadius) != canopyRadius
							|| Math.abs(zOnRadius) != canopyRadius || rand
							.nextInt(2) != 0 && distanceFromTop != 0)
							&& !Block.opaqueCubeLookup[world.getBlockId(x1, y1,
									z1)]) {
						setBlockAndMetadata(world, x1, y1, z1, leaf.blockId(),
								leaf.metadata());
					}
				}
			}
		}

		MetaBlock wood = BlockControl.INSTANCE
				.getTerrainGenBlock(TerrainGenBlock.ACACIA_WOOD);
		for (int y1 = 0; y1 < height; y1++) {
			int idToCheck = world.getBlockId(x, y + y1, z);

			if (idToCheck != 0 && !BlockControl.INSTANCE.isLeaves(idToCheck)) {
				continue;
			}

			setBlockAndMetadata(world, x, y + y1, z, wood.blockId(),
					wood.metadata());

		}
		return true;
	}

}