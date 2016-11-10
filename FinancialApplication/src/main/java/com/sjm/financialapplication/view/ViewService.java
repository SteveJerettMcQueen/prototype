/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sjm.financialapplication.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Steve
 */
@Component
public class ViewService {

    @Autowired
    private View view;

    public boolean loadView(Views v) {
        return view.loadView(v);
    }

    public boolean removeView(Views v) {
        return view.removeView(v);
    }

    public Object getView(Views v) {
        return view.getView(v);
    }

    public int getSize() {
        return view.getSize();
    }
}
