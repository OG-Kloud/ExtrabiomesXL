package net.minecraft.src;

import java.util.ArrayList;
import java.util.Random;
import net.minecraft.src.forge.*;

public class BlockDeadGrass extends BlockFlower implements ITextureProvider
{
	protected BlockDeadGrass(int i, int j)
	{
		super(i, j);
		float var3 = 0.4F;
        this.setBlockBounds(0.5F - var3, 0.0F, 0.5F - var3, 0.5F + var3, 0.5F, 0.5F + var3);
	}

	protected boolean canThisPlantGrowOnThisBlockID(int i)
	{
		return i == mod_ExtraBiomesXL.crackedSand.blockID;
	}

	public int idDropped(int i, Random random, int j)
	{
		return -1;
	}
	
	public String getTextureFile()
    {
            return "/ExtraBiomesXL/extrabiomes.png";
    }
}