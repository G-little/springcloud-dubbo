package com.little.g.springcloud.admin.web.manager.mall;

import com.github.pagehelper.PageInfo;
import com.little.g.springcloud.admin.web.vo.*;
import com.little.g.springcloud.common.ResultJson;
import com.little.g.springcloud.common.dto.Page;
import com.little.g.springcloud.common.utils.ResponseUtil;
import com.little.g.springcloud.mall.api.*;
import com.little.g.springcloud.mall.dto.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static com.little.g.springcloud.admin.AdminErrorCodes.GOODS_NAME_EXIST;

@Component
@Slf4j
public class AdminGoodsManager {

	@Reference
	private LitemallGoodsService goodsService;

	@Reference
	private LitemallGoodsSpecificationService specificationService;

	@Reference
	private LitemallGoodsAttributeService attributeService;

	@Reference
	private LitemallGoodsProductService productService;

	@Reference
	private LitemallCategoryService categoryService;

	@Reference
	private LitemallBrandService brandService;

	@Reference
	private LitemallCartService cartService;

	@Reference
	private QCodeService qCodeService;

	public ResultJson<Page<LitemallGoodsDTO>> list(Integer goodsId, String goodsSn,
			String name, Integer page, Integer limit, String sort, String order) {
		PageInfo<LitemallGoodsDTO> pageInfo = goodsService.querySelective(goodsId,
				goodsSn, name, page, limit, sort, order);
		return ResponseUtil.okPage(pageInfo);
	}

	private ResultJson validate(GoodsAllinoneVo goodsAllinone) {
		LitemallGoodsDTO goods = goodsAllinone.getGoods();
		String name = goods.getName();
		if (StringUtils.isEmpty(name)) {
			return ResponseUtil.badArgument();
		}
		String goodsSn = goods.getGoodsSn();
		if (StringUtils.isEmpty(goodsSn)) {
			return ResponseUtil.badArgument();
		}
		// 品牌商可以不设置，如果设置则需要验证品牌商存在
		Integer brandId = goods.getBrandId();
		if (brandId != null && brandId != 0) {
			if (brandService.findById(brandId) == null) {
				return ResponseUtil.badArgumentValue();
			}
		}
		// 分类可以不设置，如果设置则需要验证分类存在
		Integer categoryId = goods.getCategoryId();
		if (categoryId != null && categoryId != 0) {
			if (categoryService.findById(categoryId) == null) {
				return ResponseUtil.badArgumentValue();
			}
		}

		LitemallGoodsAttributeDTO[] attributes = goodsAllinone.getAttributes();
		for (LitemallGoodsAttributeDTO attribute : attributes) {
			String attr = attribute.getAttribute();
			if (StringUtils.isEmpty(attr)) {
				return ResponseUtil.badArgument();
			}
			String value = attribute.getValue();
			if (StringUtils.isEmpty(value)) {
				return ResponseUtil.badArgument();
			}
		}

		LitemallGoodsSpecificationDTO[] specifications = goodsAllinone
				.getSpecifications();
		for (LitemallGoodsSpecificationDTO specification : specifications) {
			String spec = specification.getSpecification();
			if (StringUtils.isEmpty(spec)) {
				return ResponseUtil.badArgument();
			}
			String value = specification.getValue();
			if (StringUtils.isEmpty(value)) {
				return ResponseUtil.badArgument();
			}
		}

		LitemallGoodsProductDTO[] products = goodsAllinone.getProducts();
		for (LitemallGoodsProductDTO product : products) {
			Integer number = product.getNumber();
			if (number == null || number < 0) {
				return ResponseUtil.badArgument();
			}

			BigDecimal price = product.getPrice();
			if (price == null) {
				return ResponseUtil.badArgument();
			}

			String[] productSpecifications = product.getSpecifications();
			if (productSpecifications.length == 0) {
				return ResponseUtil.badArgument();
			}
		}

		return null;
	}

	/**
	 * 编辑商品
	 * <p>
	 * NOTE： 由于商品涉及到四个表，特别是litemall_goods_product表依赖litemall_goods_specification表，
	 * 这导致允许所有字段都是可编辑会带来一些问题，因此这里商品编辑功能是受限制： （1）litemall_goods表可以编辑字段；
	 * （2）litemall_goods_specification表只能编辑pic_url字段，其他操作不支持；
	 * （3）litemall_goods_product表只能编辑price, number和url字段，其他操作不支持；
	 * （4）litemall_goods_attribute表支持编辑、添加和删除操作。
	 * <p>
	 * NOTE2: 前后端这里使用了一个小技巧： 如果前端传来的update_time字段是空，则说明前端已经更新了某个记录，则这个记录会更新；
	 * 否则说明这个记录没有编辑过，无需更新该记录。
	 * <p>
	 * NOTE3: （1）购物车缓存了一些商品信息，因此需要及时更新。 目前这些字段是goods_sn, goods_name, price, pic_url。
	 * （2）但是订单里面的商品信息则是不会更新。 如果订单是未支付订单，此时仍然以旧的价格支付。
	 */
	@Transactional
	public ResultJson update(GoodsAllinoneVo goodsAllinone) {
		ResultJson error = validate(goodsAllinone);
		if (error != null) {
			return error;
		}

		LitemallGoodsDTO goods = goodsAllinone.getGoods();
		LitemallGoodsAttributeDTO[] attributes = goodsAllinone.getAttributes();
		LitemallGoodsSpecificationDTO[] specifications = goodsAllinone
				.getSpecifications();
		LitemallGoodsProductDTO[] products = goodsAllinone.getProducts();

		// 将生成的分享图片地址写入数据库
		String url = qCodeService.createGoodShareImage(goods.getId().toString(),
				goods.getPicUrl(), goods.getName());
		goods.setShareUrl(url);

		// 商品表里面有一个字段retailPrice记录当前商品的最低价
		BigDecimal retailPrice = new BigDecimal(Integer.MAX_VALUE);
		for (LitemallGoodsProductDTO product : products) {
			BigDecimal productPrice = product.getPrice();
			if (retailPrice.compareTo(productPrice) == 1) {
				retailPrice = productPrice;
			}
		}
		goods.setRetailPrice(retailPrice);

		// 商品基本信息表litemall_goods
		if (goodsService.updateById(goods) == 0) {
			throw new RuntimeException("更新数据失败");
		}

		Integer gid = goods.getId();

		// 商品规格表litemall_goods_specification
		for (LitemallGoodsSpecificationDTO specification : specifications) {
			// 目前只支持更新规格表的图片字段
			if (specification.getUpdateTime() == null) {
				specification.setSpecification(null);
				specification.setValue(null);
				specificationService.updateById(specification);
			}
		}

		// 商品货品表litemall_product
		for (LitemallGoodsProductDTO product : products) {
			if (product.getUpdateTime() == null) {
				productService.updateById(product);
			}
		}

		// 商品参数表litemall_goods_attribute
		for (LitemallGoodsAttributeDTO attribute : attributes) {
			if (attribute.getId() == null || attribute.getId().equals(0)) {
				attribute.setGoodsId(goods.getId());
				attributeService.add(attribute);
			}
			else if (attribute.getDeleted()) {
				attributeService.deleteById(attribute.getId());
			}
			else if (attribute.getUpdateTime() == null) {
				attributeService.updateById(attribute);
			}
		}

		// 这里需要注意的是购物车litemall_cart有些字段是拷贝商品的一些字段，因此需要及时更新
		// 目前这些字段是goods_sn, goods_name, price, pic_url
		for (LitemallGoodsProductDTO product : products) {
			cartService.updateProduct(product.getId(), goods.getGoodsSn(),
					goods.getName(), product.getPrice(), product.getUrl());
		}

		return ResponseUtil.ok();
	}

	@Transactional
	public ResultJson delete(LitemallGoodsDTO goods) {
		Integer id = goods.getId();
		if (id == null) {
			return ResponseUtil.badArgument();
		}

		Integer gid = goods.getId();
		goodsService.deleteById(gid);
		specificationService.deleteByGid(gid);
		attributeService.deleteByGid(gid);
		productService.deleteByGid(gid);
		return ResponseUtil.ok();
	}

	@Transactional
	public ResultJson<CategoryBrandVo> create(GoodsAllinoneVo goodsAllinone) {
		ResultJson error = validate(goodsAllinone);
		if (error != null) {
			return error;
		}

		LitemallGoodsDTO goods = goodsAllinone.getGoods();
		LitemallGoodsAttributeDTO[] attributes = goodsAllinone.getAttributes();
		LitemallGoodsSpecificationDTO[] specifications = goodsAllinone
				.getSpecifications();
		LitemallGoodsProductDTO[] products = goodsAllinone.getProducts();

		String name = goods.getName();
		if (goodsService.checkExistByName(name)) {
			return ResponseUtil.fail(GOODS_NAME_EXIST, "商品名已经存在");
		}

		// 商品表里面有一个字段retailPrice记录当前商品的最低价
		BigDecimal retailPrice = new BigDecimal(Integer.MAX_VALUE);
		for (LitemallGoodsProductDTO product : products) {
			BigDecimal productPrice = product.getPrice();
			if (retailPrice.compareTo(productPrice) == 1) {
				retailPrice = productPrice;
			}
		}
		goods.setRetailPrice(retailPrice);

		// 商品基本信息表litemall_goods
		goodsService.add(goods);

		// 将生成的分享图片地址写入数据库
		String url = qCodeService.createGoodShareImage(goods.getId().toString(),
				goods.getPicUrl(), goods.getName());
		if (!StringUtils.isEmpty(url)) {
			goods.setShareUrl(url);
			if (goodsService.updateById(goods) == 0) {
				throw new RuntimeException("更新数据失败");
			}
		}

		// 商品规格表litemall_goods_specification
		for (LitemallGoodsSpecificationDTO specification : specifications) {
			specification.setGoodsId(goods.getId());
			specificationService.add(specification);
		}

		// 商品参数表litemall_goods_attribute
		for (LitemallGoodsAttributeDTO attribute : attributes) {
			attribute.setGoodsId(goods.getId());
			attributeService.add(attribute);
		}

		// 商品货品表litemall_product
		for (LitemallGoodsProductDTO product : products) {
			product.setGoodsId(goods.getId());
			productService.add(product);
		}
		return ResponseUtil.ok();
	}

	public ResultJson<CategoryBrandVo> list2() {
		// http://element-cn.eleme.io/#/zh-CN/component/cascader
		// 管理员设置“所属分类”
		List<LitemallCategoryDTO> l1CatList = categoryService.queryL1();
		List<CatVo> categoryList = new ArrayList<>(l1CatList.size());

		for (LitemallCategoryDTO l1 : l1CatList) {
			CatVo l1CatVo = new CatVo();
			l1CatVo.setValue(l1.getId());
			l1CatVo.setLabel(l1.getName());

			List<LitemallCategoryDTO> l2CatList = categoryService.queryByPid(l1.getId());
			List<CatVo> children = new ArrayList<>(l2CatList.size());
			for (LitemallCategoryDTO l2 : l2CatList) {
				CatVo l2CatVo = new CatVo();
				l2CatVo.setValue(l2.getId());
				l2CatVo.setLabel(l2.getName());
				children.add(l2CatVo);
			}
			l1CatVo.setChildren(children);

			categoryList.add(l1CatVo);
		}

		// http://element-cn.eleme.io/#/zh-CN/component/select
		// 管理员设置“所属品牌商”
		List<LitemallBrandDTO> list = brandService.all();
		List<BrandVo> brandList = new ArrayList<>(l1CatList.size());
		for (LitemallBrandDTO brand : list) {
			BrandVo vo = new BrandVo();
			vo.setValue(brand.getId());
			vo.setLabel(brand.getName());
			brandList.add(vo);
		}

		CategoryBrandVo data = new CategoryBrandVo();
		data.setCategoryList(categoryList);
		data.setBrandList(brandList);
		return ResponseUtil.ok(data);
	}

	public ResultJson<GoodsDetailVo> detail(Integer id) {
		LitemallGoodsDTO goods = goodsService.findById(id);
		List<LitemallGoodsProductDTO> products = productService.queryByGid(id);
		List<LitemallGoodsSpecificationDTO> specifications = specificationService
				.queryByGid(id);
		List<LitemallGoodsAttributeDTO> attributes = attributeService.queryByGid(id);

		Integer categoryId = goods.getCategoryId();
		LitemallCategoryDTO category = categoryService.findById(categoryId);
		Integer[] categoryIds = new Integer[] {};
		if (category != null) {
			Integer parentCategoryId = category.getPid();
			categoryIds = new Integer[] { parentCategoryId, categoryId };
		}

		GoodsDetailVo data = new GoodsDetailVo();
		data.setGoods(goods);
		data.setSpecifications(specifications);
		data.setProducts(products);
		data.setAttributes(attributes);
		data.setCategoryIds(categoryIds);

		return ResponseUtil.ok(data);
	}

}
