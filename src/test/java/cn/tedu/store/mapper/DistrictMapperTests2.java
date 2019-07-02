package cn.tedu.store.mapper;



import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cn.tedu.store.entity.District;



@RunWith(SpringRunner.class)
@SpringBootTest
public class DistrictMapperTests2 {

	@Autowired DistrictMapper districtmapper;

	@Test
	public void findByParent() {
		String parent = "320000";
		List<District> list = districtmapper.findByParent(parent);
		System.err.println("Begin");
		for (District district : list) {
			System.err.println(district);
		}
	}


}
