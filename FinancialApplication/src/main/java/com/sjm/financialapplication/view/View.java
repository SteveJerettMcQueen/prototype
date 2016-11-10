/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sjm.financialapplication.view;

/**
 *
 * @author sm6668
 */
public interface View {

    boolean loadView(Views view);

    boolean removeView(Views view);

    Object getView(Views view);

    int getSize();
}
