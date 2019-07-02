package cn.tedu.store.service;



import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cn.tedu.store.entity.District;




@RunWith(SpringRunner.class)
@SpringBootTest
public class DistrictServiceTests2 {

	@Autowired IDistrictService districtService;

	@Test
	public void getByParent() {
		String parent = "86";
		List<District> list = districtService.getByParent(parent);
		System.err.println("Begin");
		for (District district : list) {
			System.err.println(district);
		}
	}


}
