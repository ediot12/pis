package layout;

import java.util.*;

import org.apache.tiles.Attribute;
import org.apache.tiles.AttributeContext;
import org.apache.tiles.context.TilesRequestContext;
import org.apache.tiles.preparer.PreparerException;
import org.apache.tiles.preparer.ViewPreparer;

public class MenuViewPreparer implements ViewPreparer {
	public void execute(TilesRequestContext tilesContext, AttributeContext attrContext) throws PreparerException{
		List<MenuItem> topnav = new ArrayList<MenuItem>();
		topnav.add(new MenuItem("�α���", "login2.jsp"));
		topnav.add(new MenuItem("�α���", "#"));
		topnav.add(new MenuItem("�α���", "#"));
		attrContext.putAttribute("topnav", new Attribute(topnav), true);
		
		List<MenuItem> nav = new ArrayList<MenuItem>();
		nav.add(new MenuItem("����", "#"));
		nav.add(new MenuItem("Ŀ�´�Ƽ", "#"));
		nav.add(new MenuItem("������", "#"));
		nav.add(new MenuItem("����������", "#"));
		attrContext.putAttribute("nav", new Attribute(nav), true);
		
	}


}
