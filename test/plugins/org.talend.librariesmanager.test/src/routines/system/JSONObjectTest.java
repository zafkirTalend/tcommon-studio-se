package routines.system;

import junit.framework.TestCase;

import org.junit.Test;

public class JSONObjectTest extends TestCase {
	
	public class Bean {
		public int id;
		public String Name;
		
		public int getId() {
			return this.id;
		}
		
		public String getName() {
			return this.Name;
		}
		
		public Bean(int id,String name) {
			this.id = id;
			this.Name = name;
		}
	}
	
	@Test
	public void test() throws JSONException {
		Bean bean = new Bean(1,"wangwei");
		JSONObject object = new JSONObject(bean);
		
		assertEquals(false, object.isNull("id"));
		assertEquals(1, object.get("id"));
		
		assertEquals(true, object.isNull("name"));
		assertEquals(false, object.isNull("Name"));
		assertEquals("wangwei", object.get("Name"));
	}
}