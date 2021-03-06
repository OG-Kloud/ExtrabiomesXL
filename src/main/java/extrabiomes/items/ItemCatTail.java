/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.items;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.ItemBlock;
import extrabiomes.Extrabiomes;

public class ItemCatTail extends ItemBlock
{
    
    public ItemCatTail(int id)
    {
        super(id);
    }
    
    @Override
    public void registerIcons(IconRegister iconRegister)
    {
        itemIcon = iconRegister.registerIcon(Extrabiomes.TEXTURE_PATH + "cattail");
    }
    
}
