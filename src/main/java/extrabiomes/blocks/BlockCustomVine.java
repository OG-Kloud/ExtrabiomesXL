package extrabiomes.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import extrabiomes.Extrabiomes;
import net.minecraft.block.BlockVine;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.world.ColorizerFoliage;
import net.minecraft.world.IBlockAccess;

public class BlockCustomVine extends BlockVine {

	public enum BlockType {
		GLORIOSA, SPANISH_MOSS;
	}

	public final BlockType	type;

	public BlockCustomVine(int blockID, BlockType type) {
		super(blockID);
		this.type = type;
	}
	
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister iconRegister)
    {
    	final String iconPath = Extrabiomes.TEXTURE_PATH + "vine_" + type.toString().toLowerCase();
		this.blockIcon = iconRegister.registerIcon(iconPath);
    }

    /*
    @SideOnly(Side.CLIENT)
    public int getBlockColor()
    {
        return 0xffffff;
    }//*/
    
    @SideOnly(Side.CLIENT)

    public int colorMultiplier(IBlockAccess par1IBlockAccess, int par2, int par3, int par4)
    {
        return 0xffffff;
    }

}
