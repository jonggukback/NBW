package com.finalpj.nbw.product.repository;

import com.finalpj.nbw.product.domain.CategoryFilter;
import com.finalpj.nbw.product.domain.Criteria;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.finalpj.nbw.product.dao.ProductDao;
import com.finalpj.nbw.product.domain.Product;

import java.util.List;
import java.util.Map;

@Repository
@Slf4j
public class ProductDaoImpl implements ProductDao{

	@Autowired
	SqlSessionTemplate sqlSessionTemplate;

	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public Product getProduct(String number) {
		Product product = null;
		product = sqlSessionTemplate.selectOne("getProduct",number);
		return product;
	}

	/* 추천 검색어 조회 */
	@Override
	public List<Map<String, Object>> selectProductByWord(Map<String, Object> paramMap) throws Exception {
		//log.info("DAO ==> "+ sqlSession.selectList("selectProductByWord", paramMap));
		return sqlSession.selectList("selectProductByWord", paramMap);
	}

	/* 상품 목록 조회 > 페이징 포함 */
	@Override
	public List<Product> selectProduct(Criteria criteria) {
		log.info("DAO 에서 매개로 전달될 criteria ====> "+ criteria);
		return sqlSession.selectList("selectProduct", criteria);
	}

	/* 조건검색 결과 게시물 개수 */
	@Override
	public int selectSearchCnt(Criteria criteria) {
		return sqlSession.selectOne("selectTotalProduct", criteria);
	}

	/* 조회된 상품의 카테고리를 다시 조회 > 첫 번째 기준(Criteria)에 의해서 반환된 카테고리 필터를 반환 */
	@Override
	public List<CategoryFilter> selectProductCategory(Criteria criteria) throws Exception {
		//log.info("selectProductCategory"+sqlSession.selectList("selectProductCategory", criteria));
		return sqlSession.selectList("selectProductCategory", criteria);
	}


	/* 검색된 카테고리 개수 반환 */
	@Override
	public List<CategoryFilter> selectProductCateInfo(Criteria criteria) throws Exception{
		//log.info("selectProductCategory"+sqlSession.selectList("selectProductCategory", criteria));
		return  sqlSession.selectList("selectProductCateInfo", criteria);
	}
}
