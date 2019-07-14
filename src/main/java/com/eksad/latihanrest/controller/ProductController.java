package com.eksad.latihanrest.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.eksad.latihanrest.DAO.BrandDao;
import com.eksad.latihanrest.DAO.ProductDao;
import com.eksad.latihanrest.model.Brand;
import com.eksad.latihanrest.model.Product;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


@RestController
@RequestMapping(value = "/api/v1")
@Api(tags = "Product")
public class ProductController
{
	@Autowired
	private ProductDao productDao;
	
	@Autowired
	BrandDao brandDao;
	
	@ApiOperation(
			value = "API to retrieve all product's data by id",
			notes = "Return data with JSON format",
			tags = "Get Data API"
			)
	@GetMapping(value = "product/{brandId}")
	public List<Product> getByBrandId(@PathVariable Long brandId)
	{
		List<Product> result = new ArrayList<Product>();
		productDao.findByBrandId(brandId).forEach(result::add);
		return result;
	}
	
	
	@ApiOperation(
			value = "Add new product data",
			notes = "Add new product data to database",
			tags = "Data Manipulation API"
			)
	@PostMapping(value = "/product")
	public Product InsertProduct(@RequestBody Product product)
	{
		Brand brand = brandDao.findById(product.getBrandId()).orElse(null);
		if (brand != null)
		{
			product.setBrand(brand);
			return productDao.save(product);
		}
		return null;
	}
	
	
	@ApiOperation(
			value = "Update product data",
			notes = "Update product data based on provided ID and attached data",
			tags = "Data Manipulation API"
			)
	@PutMapping(value = "/product/{id}")
	public String updateProduct(@RequestBody Product product,@PathVariable Long id)
	{
		Product productSelected = productDao.findById((id)).orElse(null);
		if (productSelected != null)
		{
			productSelected.setName(product.getName());
			productSelected.setBrand(product.getBrand());
			productSelected.setPrice(product.getPrice());
			
			productDao.save(productSelected);
			return "berhasil memperbaharui";
		} else {
			return "gagal memperbaharui";
		}
	}
	
	@ApiOperation(
			value = "Delete product data",
			notes = "Delete product data based on provided ID and attached data",
			tags = "Data Manipulation API"
			)
	@DeleteMapping(value = "/product/{id}")
	public HashMap<String, Object> delete(@PathVariable Long id)
	{
		HashMap<String, Object> result = new HashMap<String, Object>();
		productDao.deleteById(id);
		result.put("message", "berhasil dihapus");
		return result;
	}
	
	@ApiOperation(
			value = "API to retrieve all division's data by id",
			notes = "Return data with JSON format",
			tags = "Get Data API"
			)
	@GetMapping(value = "/product/{search}")
	public List<Product> getBySearch(@PathVariable String search)
	{
		List<Product> result = new ArrayList<Product>();
		productDao.findBySearch(search).forEach(result::add);
		return result;
	}
}
