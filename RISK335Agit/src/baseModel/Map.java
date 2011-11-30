/**
 * @author Chris Ray
 * Created on 2:30:47 AM Nov 27, 2011
 *
 */
package baseModel;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author Chris Ray Created on 2:30:47 AM Nov 27, 2011
 * 
 * 
 */
public class Map {

	// set up continents
	private String na;
	private Territory alaska;
	private Territory nwTerritory;
	private Territory alberta;
	private Territory ontario;
	private Territory greenland;
	private Territory quebec;
	private Territory eastUS;
	private Territory cenAmerica;
	private Territory westUS;

	private String sa;
	private Territory venezuela;
	private Territory peru;
	private Territory brazil;
	private Territory argentina;

	private String a = "Africa";
	private Territory nAfrica;
	private Territory egypt;
	private Territory eAfrica;
	private Territory congo;
	private Territory sAfrica;
	private Territory madagascar;

	private String au;
	private Territory indonesia;
	private Territory newGuinea;
	private Territory wAustralia;
	private Territory eAustralia;

	private String eu;
	private Territory iceland;
	private Territory gBritain;
	private Territory scandinavia;
	private Territory ukraine;
	private Territory nEurope;
	private Territory wEurope;
	private Territory sEurope;

	private String as;
	private Territory ural;
	private Territory siberia;
	private Territory yakutsk;
	private Territory kamchatka;
	private Territory irkutsk;
	private Territory mongolia;
	private Territory japan;
	private Territory afghanistan;
	private Territory china;
	private Territory middleEast;
	private Territory india;
	private Territory siam;

	private Continent nAmerica;
	private Continent sAmerica;
	private Continent africa;
	private Continent europe;
	private Continent asia;
	private Continent australia;

	private HashMap<String, Continent> map;

	/**
	 * 
	 * @author Chris Ray Created on 2:50:28 PM Nov 27, 2011<br />
	 * 
	 *         Constructs a HashMap of the map, containing all the continents,
	 *         the continents, contain all their territories, and the
	 *         territories list all their neighbors.
	 * 
	 */
	public Map() {

		buildTerritories();
		setUpNeighborsOfTerritories();
		buildContinents();
		buildMap();
		
		ArrayList<Territory> naTerritories = new ArrayList<Territory>();
		naTerritories.add(alaska);
		Continent nAmerica = new Continent("North America", naTerritories, 5);

		// setMap(new HashMap<String, Continent>());

	}

	/**
	 * @author Chris Ray Created on 2:24:43 PM Nov 27, 2011
	 * 
	 */
	private void buildTerritories() {
		na = "North America";
		alaska = new Territory("Alaska", na);
		nwTerritory = new Territory("Northwest Territory", na);
		alberta = new Territory("Alberta", na);
		ontario = new Territory("Ontario", na);
		greenland = new Territory("Greenland", na);
		quebec = new Territory("Quebec", na);
		eastUS = new Territory("Eastern United States", na);
		cenAmerica = new Territory("Central America", na);
		westUS = new Territory("Western United States", na);

		sa = "South America";
		venezuela = new Territory("Venezuela", sa);
		peru = new Territory("Peru", sa);
		brazil = new Territory("Brazil", sa);
		argentina = new Territory("Argentina", sa);

		a = "Africa";
		nAfrica = new Territory("North Africa", a);
		egypt = new Territory("Egypt", a);
		eAfrica = new Territory("East Africa", a);
		congo = new Territory("Congo", a);
		sAfrica = new Territory("South Africa", a);
		madagascar = new Territory("Madagascar", a);

		au = "Australia";
		indonesia = new Territory("Indonesia", au);
		newGuinea = new Territory("New Guinea", au);
		wAustralia = new Territory("Western Australia", au);
		eAustralia = new Territory("Eastern Australia", au);

		eu = "Europe";
		iceland = new Territory("Iceland", eu);
		gBritain = new Territory("Great Britain", eu);
		scandinavia = new Territory("Scandinavia", eu);
		ukraine = new Territory("Ukraine", eu);
		nEurope = new Territory("Northern Europe", eu);
		wEurope = new Territory("Western Europe", eu);
		sEurope = new Territory("Southern Europe", eu);

		as = "Asia";
		ural = new Territory("Ural", as);
		siberia = new Territory("Siberia", as);
		yakutsk = new Territory("Yakutsk", as);
		kamchatka = new Territory("Kamchatka", as);
		irkutsk = new Territory("Irkutsk", as);
		mongolia = new Territory("Mongolia", as);
		japan = new Territory("Japan", as);
		afghanistan = new Territory("Afghanistan", as);
		china = new Territory("China", as);
		middleEast = new Territory("Middle East", as);
		india = new Territory("India", as);
		siam = new Territory("Siam", as);
	}

	/**
	 * @author Chris Ray Created on 2:24:43 PM Nov 27, 2011
	 * 
	 */
	private void setUpNeighborsOfTerritories() {
		// set up continents' neighbors for North America
		ArrayList<Territory> t = new ArrayList<Territory>();
		t.add(nwTerritory);
		t.add(alberta);
		t.add(kamchatka);
		alaska.setNeighbors(t);

		t = new ArrayList<Territory>();
		t.add(alaska);
		t.add(alberta);
		t.add(ontario);
		t.add(greenland);
		nwTerritory.setNeighbors(t);

		t = new ArrayList<Territory>();
		t.add(alberta);
		t.add(westUS);
		t.add(eastUS);
		t.add(quebec);
		t.add(greenland);
		t.add(nwTerritory);
		ontario.setNeighbors(t);

		t = new ArrayList<Territory>();
		t.add(greenland);
		t.add(ontario);
		t.add(eastUS);
		quebec.setNeighbors(t);

		t = new ArrayList<Territory>();
		t.add(quebec);
		t.add(ontario);
		t.add(nwTerritory);
		t.add(iceland);
		greenland.setNeighbors(t);

		t = new ArrayList<Territory>();
		t.add(alaska);
		t.add(nwTerritory);
		t.add(ontario);
		t.add(westUS);
		alberta.setNeighbors(t);

		t = new ArrayList<Territory>();
		t.add(alberta);
		t.add(ontario);
		t.add(eastUS);
		t.add(cenAmerica);
		westUS.setNeighbors(t);

		t = new ArrayList<Territory>();
		t.add(westUS);
		t.add(cenAmerica);
		t.add(ontario);
		t.add(quebec);
		eastUS.setNeighbors(t);

		t = new ArrayList<Territory>();
		t.add(westUS);
		t.add(eastUS);
		t.add(venezuela);
		cenAmerica.setNeighbors(t);
		// ////////////////////////

		// set up continents' neighbors for South America
		t = new ArrayList<Territory>();
		t.add(cenAmerica);
		t.add(brazil);
		t.add(peru);
		venezuela.setNeighbors(t);

		t = new ArrayList<Territory>();
		t.add(venezuela);
		t.add(brazil);
		t.add(argentina);
		peru.setNeighbors(t);

		t = new ArrayList<Territory>();
		t.add(venezuela);
		t.add(peru);
		t.add(argentina);
		t.add(nAfrica);
		brazil.setNeighbors(t);

		t = new ArrayList<Territory>();
		t.add(peru);
		t.add(brazil);
		argentina.setNeighbors(t);
		// /////////////////////////

		// set up continents' neighbors for Africa
		t = new ArrayList<Territory>();
		t.add(brazil);
		t.add(egypt);
		t.add(eAfrica);
		t.add(congo);
		t.add(wEurope);
		t.add(sEurope);
		nAfrica.setNeighbors(t);

		t = new ArrayList<Territory>();
		t.add(nAfrica);
		t.add(eAfrica);
		t.add(middleEast);
		t.add(sEurope);
		egypt.setNeighbors(t);

		t = new ArrayList<Territory>();
		t.add(nAfrica);
		t.add(eAfrica);
		t.add(sAfrica);
		congo.setNeighbors(t);

		t = new ArrayList<Territory>();
		t.add(congo);
		t.add(nAfrica);
		t.add(egypt);
		t.add(middleEast);
		t.add(sAfrica);
		t.add(madagascar);
		eAfrica.setNeighbors(t);

		t = new ArrayList<Territory>();
		t.add(congo);
		t.add(eAfrica);
		t.add(madagascar);
		sAfrica.setNeighbors(t);

		t = new ArrayList<Territory>();
		t.add(sAfrica);
		t.add(eAfrica);
		madagascar.setNeighbors(t);
		// ////////////////////////

		// set up continents' neighbors for Austrailia
		t = new ArrayList<Territory>();
		t.add(newGuinea);
		t.add(siam);
		t.add(eAustralia);
		t.add(wAustralia);
		indonesia.setNeighbors(t);

		t = new ArrayList<Territory>();
		t.add(indonesia);
		t.add(wAustralia);
		t.add(eAustralia);
		newGuinea.setNeighbors(t);

		t = new ArrayList<Territory>();
		t.add(indonesia);
		t.add(newGuinea);
		t.add(eAustralia);
		// /////////////////////////////////

		// set up continents' neighbors for Europe
		t = new ArrayList<Territory>();
		t.add(greenland);
		t.add(gBritain);
		t.add(scandinavia);
		iceland.setNeighbors(t);

		t = new ArrayList<Territory>();
		t.add(iceland);
		t.add(scandinavia);
		t.add(nEurope);
		t.add(wEurope);
		gBritain.setNeighbors(t);

		t = new ArrayList<Territory>();
		t.add(gBritain);
		t.add(nEurope);
		t.add(sEurope);
		t.add(nAfrica);
		wEurope.setNeighbors(t);

		t = new ArrayList<Territory>();
		t.add(gBritain);
		t.add(scandinavia);
		t.add(ukraine);
		t.add(sEurope);
		t.add(wEurope);
		nEurope.setNeighbors(t);

		t = new ArrayList<Territory>();
		t.add(egypt);
		t.add(nAfrica);
		t.add(wEurope);
		t.add(nEurope);
		t.add(ukraine);
		t.add(middleEast);
		sEurope.setNeighbors(t);

		t = new ArrayList<Territory>();
		t.add(iceland);
		t.add(gBritain);
		t.add(nEurope);
		t.add(ukraine);
		scandinavia.setNeighbors(t);

		t = new ArrayList<Territory>();
		t.add(scandinavia);
		t.add(nEurope);
		t.add(sEurope);
		t.add(middleEast);
		t.add(afghanistan);
		t.add(ural);
		ukraine.setNeighbors(t);
		// //////////////////////

		// set up continents' neighbors for Asia
		t = new ArrayList<Territory>();
		t.add(eAfrica);
		t.add(egypt);
		t.add(sEurope);
		t.add(ukraine);
		t.add(afghanistan);
		t.add(india);
		middleEast.setNeighbors(t);

		t = new ArrayList<Territory>();
		t.add(middleEast);
		t.add(afghanistan);
		t.add(china);
		t.add(siam);
		india.setNeighbors(t);

		t = new ArrayList<Territory>();
		t.add(india);
		t.add(china);
		t.add(indonesia);
		siam.setNeighbors(t);

		t = new ArrayList<Territory>();
		t.add(siam);
		t.add(india);
		t.add(afghanistan);
		t.add(ural);
		t.add(siberia);
		t.add(irkutsk);
		t.add(mongolia);
		china.setNeighbors(t);

		t = new ArrayList<Territory>();
		t.add(middleEast);
		t.add(india);
		t.add(china);
		t.add(ural);
		t.add(ukraine);
		afghanistan.setNeighbors(t);

		t = new ArrayList<Territory>();
		t.add(ukraine);
		t.add(afghanistan);
		t.add(china);
		t.add(siberia);
		ural.setNeighbors(t);

		t = new ArrayList<Territory>();
		t.add(ural);
		t.add(afghanistan);
		t.add(china);
		t.add(mongolia);
		t.add(irkutsk);
		t.add(yakutsk);
		siberia.setNeighbors(t);

		t = new ArrayList<Territory>();
		t.add(siberia);
		t.add(irkutsk);
		t.add(kamchatka);
		yakutsk.setNeighbors(t);

		t = new ArrayList<Territory>();
		t.add(yakutsk);
		t.add(irkutsk);
		t.add(mongolia);
		t.add(japan);
		t.add(alaska);
		kamchatka.setNeighbors(t);

		t = new ArrayList<Territory>();
		t.add(siberia);
		t.add(yakutsk);
		t.add(kamchatka);
		t.add(mongolia);
		irkutsk.setNeighbors(t);

		t = new ArrayList<Territory>();
		t.add(china);
		t.add(siberia);
		t.add(irkutsk);
		t.add(kamchatka);
		t.add(japan);
		mongolia.setNeighbors(t);

		t = new ArrayList<Territory>();
		t.add(mongolia);
		t.add(kamchatka);
		japan.setNeighbors(t);
		// /////////////////////
	}

	/**
	 * @author Chris Ray Created on 2:24:43 PM Nov 27, 2011
	 * 
	 */
	private void buildContinents() {
		// North America
		ArrayList<Territory> c = new ArrayList<Territory>(); // children
		c.add(alaska);
		c.add(nwTerritory);
		c.add(alberta);
		c.add(westUS);
		c.add(ontario);
		c.add(greenland);
		c.add(cenAmerica);
		c.add(eastUS);
		c.add(quebec);
		nAmerica = new Continent(na, c, 5);

		// South America
		c = new ArrayList<Territory>(); // children
		c.add(venezuela);
		c.add(peru);
		c.add(argentina);
		c.add(brazil);
		sAmerica = new Continent(sa, c, 2);

		// Africa
		c = new ArrayList<Territory>(); // children
		c.add(nAfrica);
		c.add(egypt);
		c.add(eAfrica);
		c.add(congo);
		c.add(sAfrica);
		c.add(madagascar);
		africa = new Continent(a, c, 3);

		// Australia
		c = new ArrayList<Territory>(); // children
		c.add(indonesia);
		c.add(newGuinea);
		c.add(wAustralia);
		c.add(eAustralia);
		australia = new Continent(au, c, 2);

		// Europe
		c = new ArrayList<Territory>(); // children
		c.add(iceland);
		c.add(gBritain);
		c.add(scandinavia);
		c.add(wEurope);
		c.add(nEurope);
		c.add(sEurope);
		c.add(ukraine);
		europe = new Continent(eu, c, 5);

		// Asia
		c = new ArrayList<Territory>(); // children
		c.add(middleEast);
		c.add(afghanistan);
		c.add(india);
		c.add(siam);
		c.add(china);
		c.add(mongolia);
		c.add(siberia);
		c.add(ural);
		c.add(irkutsk);
		c.add(yakutsk);
		c.add(kamchatka);
		c.add(japan);
		asia = new Continent(as, c, 7);

	}

	/**
	 * @author Chris Ray Created on 2:24:43 PM Nov 27, 2011
	 * 
	 */
	private void buildMap() {
		map = new HashMap<String, Continent>();
		map.put(na, nAmerica);
		map.put(sa, sAmerica);
		map.put(a, africa);
		map.put(au, australia);
		map.put(eu, europe);
		map.put(as, asia);
	}

	/**
	 * @return the map
	 * @author Chris Ray Created on 2:42:57 AM Nov 27, 2011
	 */
	public HashMap<String, Continent> getMap() {
		return map;
	}

	/**
	 * @author Chris Ray Created on 3:50:10 PM Nov 27, 2011
	 * @return
	 * 
	 */
	public ArrayList<Territory> getTerritories() {
		ArrayList<Territory> ter = new ArrayList<Territory>();
		ter.addAll(getMap().get(na).getChildren());
		ter.addAll(getMap().get(sa).getChildren());
		ter.addAll(getMap().get(a).getChildren());
		ter.addAll(getMap().get(au).getChildren());
		ter.addAll(getMap().get(eu).getChildren());
		ter.addAll(getMap().get(as).getChildren());
		return ter;
	}

	/**
	 * @author Chris Ray Created on 10:52:03 PM Nov 29, 2011
	 * 
	 */
	public HashMap<String, Territory> getMapAsStringTerritoryHashMap() {
		ArrayList<Territory> ter = getTerritories();
		HashMap<String, Territory> stringHash = new HashMap<String, Territory>();

		for (Territory t : ter)
			stringHash.put(t.toString(), t);
		return stringHash;
	}

	/**
	 * @author Chris Ray Created on 11:47:50 PM Nov 29, 2011 <br />
	 * @return HashMap<Integer, Territory> matching colors to their respective
	 *         territories
	 * 
	 * 
	 */
	public HashMap<Integer, Territory> getMapAsColorTerritoryHashMap() {
		HashMap<Integer, Territory> cm = new HashMap<Integer, Territory>();
		HashMap<String, Territory> sh = getMapAsStringTerritoryHashMap();

		// north america
		cm.put(-8355840, sh.get("Alaska"));
		cm.put(-11513817, sh.get("North West Territory"));// duplicate color
		cm.put(-256, sh.get("Alberta")); // duplicate color
		cm.put(-7039927, sh.get("Ontario"));
		cm.put(256, sh.get("Greenland")); // duplicate color
		cm.put(-128, sh.get("Quebec"));// duplicate color
		cm.put(8355840, sh.get("Eastern United States")); // duplicate color
		cm.put(128, sh.get("Central America")); // duplicate color
		cm.put(11513817, sh.get("Western United States")); // duplicate color

		// South america
		cm.put(-32640, sh.get("Venezuela"));
		cm.put(-8388608, sh.get("Peru"));
		cm.put(-8372160, sh.get("Brazil"));
		cm.put(-65536, sh.get("Argentina"));

		// africa
		cm.put(-28325, sh.get("North Africa"));
		cm.put(-8372224, sh.get("Egypt")); // duplicate color
		cm.put(-32768, sh.get("East Africa"));
		cm.put(-5351680, sh.get("Congo")); // duplicate color
		cm.put(8372224, sh.get("South Africa")); // duplicate color
		cm.put(5351680, sh.get("Madagascar")); // duplicate color

		// Australia
		cm.put(-8388353, sh.get("Indonesia"));
		cm.put(-65281, sh.get("New Guinea"));
		cm.put(-8388544, sh.get("Western Austrailia"));
		cm.put(-12582848, sh.get("Eastern Austrailia"));

		cm.put(-16776961, sh.get("Iceland"));// duplicate color
		cm.put(-16760704, sh.get("Great Britain"));// duplicate color
		cm.put(-16744193, sh.get("Scandinavia"));
		cm.put(-16777088, sh.get("Ukraine"));
		cm.put(16776961, sh.get("Northern Europe"));// duplicate color
		cm.put(16776961, sh.get("Western Europe"));// duplicate color
		cm.put(16760704, sh.get("Southern Europe"));// duplicate color

		// Asia
		cm.put(-16760832, sh.get("Ural")); // duplicate color
		cm.put(-16744448, sh.get("Siberia"));// duplicate color
		cm.put(-16744320, sh.get("Yakutsk"));// duplicate color
		cm.put(-16744384, sh.get("Kamchatka"));// duplicate color
		cm.put(-8323328, sh.get("Irkutsk")); // duplicate color
		cm.put(16760832, sh.get("Mongolia"));// duplicate color
		cm.put(8323328, sh.get("Japan"));// duplicate color
		cm.put(-8323200, sh.get("Afghanistan"));// duplicate color
		cm.put(16744384, sh.get("China"));// duplicate color
		cm.put(16744448, sh.get("Middle East"));// duplicate color
		cm.put(16744320, sh.get("India"));// duplicate color
		cm.put(8323200, sh.get("Siam"));// duplicate color

		return cm;
	}
}
