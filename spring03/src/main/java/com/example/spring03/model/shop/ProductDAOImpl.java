package com.example.spring03.model.shop;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository // 서버 켜질 때 자동 등록
public class ProductDAOImpl implements ProductDAO {

	@Autowired // 의존관계 주입
	SqlSession sqlSession;

	@Override
	public List<ProductDTO> list() {
		return sqlSession.selectList("product.list");
	}

	@Override
	public ProductDTO detail(int product_code) {
		return sqlSession.selectOne("product.detail", product_code);
	}

	@Override
	public void update(ProductDTO dto) {
		sqlSession.update("product.update", dto);
	}

	@Override
	public void delete(int product_code) {
		sqlSession.delete("product.delete", product_code);
	}

	@Override
	public void insert(ProductDTO dto) {
		sqlSession.insert("product.insert", dto);
	}

	@Override
	public String file_info(int product_code) {
		return sqlSession.selectOne("product.file_info", product_code);
	}

}
