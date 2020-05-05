package com.jeremy.form;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Auther: laizc
 * @Date: 2020/5/5 18:37
 * @Description:
 */
@Getter
@Setter
public class CategoryForm {

    private Integer categoryId;
    /**
     * 类目名字
     */
    private String  categoryName;

    /**
     * 类目编号
     */
    private Integer categoryType;
}
