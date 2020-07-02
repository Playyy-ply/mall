package com.dasanxia.mall.service;

import com.dasanxia.mall.vo.CategoryVo;
import com.dasanxia.mall.vo.ResponseVo;

import java.util.List;

public interface ICateogoryService {
    ResponseVo<List<CategoryVo>> selectAll();
}
