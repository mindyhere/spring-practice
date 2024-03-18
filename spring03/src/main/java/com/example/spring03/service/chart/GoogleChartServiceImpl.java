package com.example.spring03.service.chart;

import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.spring03.model.shop.CartDAO;
import com.example.spring03.model.shop.CartDTO;

@Service
public class GoogleChartServiceImpl implements GoogleChartService {

	@Autowired
	CartDAO cartDao;

	@Override
	public JSONObject getChartData() {
		List<CartDTO> items = cartDao.cart_money();
		System.out.println("getChartData..."+items);
		JSONObject data = new JSONObject(); // Json객체
		JSONObject col1 = new JSONObject();
		JSONObject col2 = new JSONObject();
		JSONArray title = new JSONArray(); // Json배열
		col1.put("label", "상품명"); // "label":"상품명"
		col1.put("type", "string");
		col2.put("label", "금액");
		col2.put("type", "number");

		// Json배열에 Json객체 입력
		title.add(col1);
		title.add(col2);
		data.put("cols", title); 
		// 컬럼정보 {"cols":[]}
//		(ex) "cols": [{"label": "상품명", "type": "string"},
//		 	          {"label": "금액","type": "number"}]

		JSONArray body = new JSONArray();

		for (CartDTO dto : items) {
			JSONObject name = new JSONObject();
			name.put("v", dto.getProduct_name());

			JSONObject money = new JSONObject();
			money.put("v", dto.getMoney());

			JSONArray row = new JSONArray();
			row.add(name);
			row.add(money);
			
			JSONObject cell = new JSONObject();
			cell.put("c", row);
			body.add(cell);
		}
		System.out.println("getChartData..."+body);
		data.put("rows", body); // 데이터 {"rows":[]}
		System.out.println("getChartData..."+data);
		return data; // json객체 리턴
	}

}
