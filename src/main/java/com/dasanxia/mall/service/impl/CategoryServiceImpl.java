package com.dasanxia.mall.service.impl;

import com.dasanxia.mall.dao.CategoryMapper;
import com.dasanxia.mall.pojo.Category;
import com.dasanxia.mall.service.ICateogoryService;
import com.dasanxia.mall.vo.CategoryVo;
import com.dasanxia.mall.vo.ResponseVo;
import com.fasterxml.jackson.databind.util.BeanUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.dasanxia.mall.consts.MallConst.ROOT_PARENT_ID;

@Service
public class CategoryServiceImpl implements ICateogoryService {
    @Autowired
    private CategoryMapper categoryMapper;
    @Override
    public ResponseVo<List<CategoryVo>> selectAll() {
        List<CategoryVo>categoryVoList = new ArrayList<>();
        List<Category> categories = categoryMapper.selectAll();

        for(Category category : categories){
            if(category.getParentId().equals(ROOT_PARENT_ID)){
                CategoryVo categoryVo = new CategoryVo();
                BeanUtils.copyProperties(category,categoryVo);
                categoryVoList.add(categoryVo);
            }
        }
        //查询子目录
        findSubCategory(categoryVoList,categories);
        return ResponseVo.success(categoryVoList);
    }

    private void findSubCategory(List<CategoryVo> categoryVoList,List<Category>categories){
        for(CategoryVo categoryVo:categoryVoList){
            List<CategoryVo> subCategoryVoList = new ArrayList<>();

            for(Category category: categories){
                if(categoryVo.getId().equals(category.getParentId())){
                    CategoryVo subCategoryVo = new CategoryVo();
                    BeanUtils.copyProperties(category,subCategoryVo);
                    subCategoryVoList.add(subCategoryVo);
                }
                categoryVo.setSubCategories(subCategoryVoList);
                findSubCategory(subCategoryVoList,categories);
            }

        }

    }
}
