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
		topnav.add(new MenuItem("로그인", "login2.jsp"));
		topnav.add(new MenuItem("로그인", "#"));
		topnav.add(new MenuItem("로그인", "#"));
		attrContext.putAttribute("topnav", new Attribute(topnav), true);
		
		List<MenuItem> nav = new ArrayList<MenuItem>();
		nav.add(new MenuItem("서비스", "#"));
		nav.add(new MenuItem("커뮤니티", "#"));
		nav.add(new MenuItem("고객센터", "#"));
		nav.add(new MenuItem("마이페이지", "#"));
		attrContext.putAttribute("nav", new Attribute(nav), true);
		
	}


}
