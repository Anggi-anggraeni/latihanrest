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
import com.eksad.latihanrest.model.Brand;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


@RestController
@RequestMapping(value="/api/v1")
@Api(tags = "Brand")
public class BrandController 
{
	@Autowired
	private BrandDao brandDao;
	
	@GetMapping("/brand")
	@ApiOperation(
			value = "API to retrieve all brand's data",
			notes = "Return data with JSON format",
			tags = "Get Data API"
			)
//	@RequestMapping("/getAll")
	public List<Brand> getAll()
	{
		List<Brand> result = new ArrayList<>();
		
		brandDao.findAll().forEach(result::add);
		
		return result;
	}
	
	
	@ApiOperation(
			value = "API to retrieve all brand's data by id",
			notes = "Return data with JSON format",
			tags = "Get Data API"
			)
	@GetMapping("brand/{id}")
	public Brand getOne(@PathVariable Long id)
	{
		return brandDao.findById(id).orElse(null);
	}
		
	@ApiOperation(
			value = "Add new brand data",
			notes = "Add new brand data to database",
			tags = "Data Manipulation API"
			)
	@PostMapping("/brand")
	public Brand save(@RequestBody Brand brand)
	{
		try {
			return brandDao.save(brand);
			} catch (Exception e) 
			{
			e.printStackTrace();
			return null;
			}
	}
		
	
	@ApiOperation(
			value = "Update brand data",
			notes = "Update brand data based on provided ID and attached data",
			tags = "Data Manipulation API"
			)
	@PutMapping("/brand/{id}")
	public Brand update(@RequestBody Brand brand, @PathVariable Long id)
	{
		Brand brandSelected = brandDao.findById(id).orElse(null);
		if (brandSelected != null)
		{
			brandSelected.setName(brand.getName());
			brandSelected.setProductType(brand.getProductType());
			
			return brandDao.save(brandSelected);
						
		} else 
			{
				return null;
			}
	}

	
	@ApiOperation(
			value = "Delete brand data",
			notes = "Delete brand data based on provided ID and remove data",
			tags = "Data Manipulation API"
			)
	@DeleteMapping("/brand/{id}")
	public HashMap<String, Object> delete(@PathVariable Long id)
	{
		HashMap<String, Object> result = new HashMap<String, Object>();
		brandDao.deleteById(id);
		result.put("message", "berhasil dihapus");
		return result;
	}
	
	
	
}


