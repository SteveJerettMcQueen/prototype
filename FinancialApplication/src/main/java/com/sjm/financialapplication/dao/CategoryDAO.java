/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sjm.financialapplication.dao;

import com.sjm.financialapplication.presenter.Category;

/**
 *
 * @author sm6668
 */
public interface CategoryDAO {

    void delete(Category cat);

    void update(Category cat);

    void save(Category cat);

}
