/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sjm.financialapplication.service.impl;

import com.sjm.financialapplication.dao.CategoryDAO;
import com.sjm.financialapplication.presenter.Category;
import com.sjm.financialapplication.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author sm6668
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryDAO categoryDAO;

    @Override
    @Transactional
    public void delete(Category cat) {
        categoryDAO.delete(cat);
    }

    @Override
    @Transactional
    public void update(Category cat) {
        categoryDAO.update(cat);
    }

    @Override
    @Transactional
    public void save(Category cat) {
        categoryDAO.save(cat);
    }

}
